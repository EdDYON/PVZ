package keletu.pvzmod.box;

import keletu.pvzmod.blocks.PlantBoxBlockEntity;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemPlantBox extends BlockItem {
    public ItemPlantBox(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> keletu.pvzmod.box.client.PlantBoxClientScreens.openOverviewHeld(stack));
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        PlantBoxInfo info = getInfoOrDefault(context.getItemInHand());
        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockPos placePos = placeContext.getClickedPos();
        InteractionResult result = super.useOn(context);

        if (result.consumesAction() && !context.getLevel().isClientSide) {
            BlockEntity blockEntity = context.getLevel().getBlockEntity(placePos);
            if (blockEntity instanceof PlantBoxBlockEntity plantBoxBlockEntity) {
                plantBoxBlockEntity.setBoxInfo(info);
            }
        }

        return result;
    }

    public static boolean openHeldBox(Level level, ServerPlayer player, ItemStack boxStack, PlantBoxInfo info) {
        PlantBoxReward reward = rollReward(info, player.getRandom());
        if (reward.stack().isEmpty()) {
            player.displayClientMessage(Component.translatable("message.pvz_myh.plant_box.empty"), true);
            return false;
        }

        if (!consumeKey(player, info.boxKey)) {
            failNoKey(level, player);
            return false;
        }

        if (!player.getAbilities().instabuild) {
            boxStack.shrink(1);
        }

        giveReward(level, player, reward);
        return true;
    }

    public static boolean openPlacedBox(Level level, BlockPos pos, ServerPlayer player, PlantBoxInfo info) {
        PlantBoxReward reward = rollReward(info, player.getRandom());
        if (reward.stack().isEmpty()) {
            player.displayClientMessage(Component.translatable("message.pvz_myh.plant_box.empty"), true);
            return false;
        }

        if (!consumeKey(player, info.boxKey)) {
            failNoKey(level, player);
            return false;
        }

        level.removeBlock(pos, false);
        giveReward(level, player, reward);
        return true;
    }

    @Override
    public Component getName(ItemStack stack) {
        PlantBoxInfo info = getBoxInfo(stack);
        if (info != null && info.boxName != null && !info.boxName.isBlank()) {
            return Component.literal(info.boxName);
        }
        return super.getName(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        PlantBoxInfo info = getInfoOrDefault(stack);
        tooltip.add(Component.translatable("tooltip.pvz_myh.plant_box").withStyle(ChatFormatting.GRAY));
        appendKeyLine(info, tooltip);
        appendGrade(tooltip, info, 5, ChatFormatting.GOLD);
        appendGrade(tooltip, info, 4, ChatFormatting.RED);
        appendGrade(tooltip, info, 3, ChatFormatting.LIGHT_PURPLE);
        appendGrade(tooltip, info, 2, ChatFormatting.DARK_PURPLE);
        appendGrade(tooltip, info, 1, ChatFormatting.BLUE);
        appendGrade(tooltip, info, 0, ChatFormatting.GRAY);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return getBoxInfo(stack) != null;
    }

    public static PlantBoxInfo getBoxInfo(ItemStack stack) {
        if (!stack.is(PVZItems.PLANT_BOX.get())) {
            return null;
        }

        if (!stack.hasTag() || !stack.getTag().contains(PlantBoxInfo.TAG_NAME)) {
            return null;
        }

        return PlantBoxInfo.load(stack.getTag().getCompound(PlantBoxInfo.TAG_NAME));
    }

    public static ItemStack setBoxInfo(PlantBoxInfo info, ItemStack stack) {
        if (stack.is(PVZItems.PLANT_BOX.get()) && info != null) {
            stack.getOrCreateTag().put(PlantBoxInfo.TAG_NAME, info.save());
        }
        return stack;
    }

    public static PlantBoxInfo getInfoOrDefault(ItemStack stack) {
        PlantBoxInfo info = getBoxInfo(stack);
        return info == null ? PlantBoxManager.primaryBox() : info;
    }

    public static Map<ItemStack, Integer> getItemGroup(PlantBoxInfo info) {
        Map<ItemStack, Integer> items = new LinkedHashMap<>();
        appendRewardGrade(items, info, 0);
        appendRewardGrade(items, info, 1);
        appendRewardGrade(items, info, 2);
        appendRewardGrade(items, info, 3);
        appendRewardGrade(items, info, 4);
        appendRewardGrade(items, info, 5);
        return items;
    }

    private static void appendKeyLine(PlantBoxInfo info, List<Component> tooltip) {
        ItemStack keyStack = stackFromId(info.boxKey);
        if (!keyStack.isEmpty()) {
            tooltip.add(Component.translatable("tooltip.pvz_myh.plant_box.key", keyStack.getHoverName())
                    .withStyle(ChatFormatting.GRAY));
        }
    }

    private static void appendGrade(List<Component> tooltip, PlantBoxInfo info, int grade, ChatFormatting color) {
        List<String> rewards = info.rewardsForGrade(grade);
        if (rewards == null || rewards.isEmpty()) {
            return;
        }

        MutableComponent title = grade == 0
                ? Component.translatable("tooltip.pvz_myh.plant_box.junk")
                : Component.translatable("tooltip.pvz_myh.plant_box.grade", grade);
        tooltip.add(title.withStyle(color));
        for (String rewardId : rewards) {
            ItemStack reward = PlantBoxItemParser.parse(rewardId);
            if (!reward.isEmpty()) {
                tooltip.add(Component.literal("  ").append(reward.getHoverName()).withStyle(color));
            }
        }
    }

    public static boolean consumeKey(Player player, String keyId) {
        if (player.getAbilities().instabuild) {
            return true;
        }

        ResourceLocation keyLocation = ResourceLocation.tryParse(keyId);
        if (keyLocation == null) {
            return false;
        }

        for (ItemStack stack : player.getInventory().items) {
            if (matches(stack, keyLocation)) {
                stack.shrink(1);
                return true;
            }
        }

        for (ItemStack stack : player.getInventory().offhand) {
            if (matches(stack, keyLocation)) {
                stack.shrink(1);
                return true;
            }
        }

        return false;
    }

    public static void failNoKey(Level level, Player player) {
        player.displayClientMessage(Component.translatable("message.pvz_myh.plant_box.no_key"), true);
        level.playSound(null, player.blockPosition(), SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 0.7F, 1.0F);
    }

    public static void giveReward(Level level, ServerPlayer player, PlantBoxReward reward) {
        ItemStack remaining = reward.stack().copy();
        if (!player.getInventory().add(remaining)) {
            player.drop(remaining, false);
        }

        level.playSound(null, player.blockPosition(), PVZSounds.PLANT_BOX_OPEN.get(), SoundSource.PLAYERS, 0.8F, 1.0F);
    }

    private static boolean matches(ItemStack stack, ResourceLocation itemId) {
        return !stack.isEmpty() && itemId.equals(ForgeRegistries.ITEMS.getKey(stack.getItem()));
    }

    private static ItemStack stackFromId(String itemId) {
        ResourceLocation location = ResourceLocation.tryParse(itemId);
        if (location == null || ForgeRegistries.ITEMS.getValue(location) == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(ForgeRegistries.ITEMS.getValue(location));
    }

    public static List<PlantBoxReward> rollOpeningRewards(PlantBoxInfo info, RandomSource random) {
        List<PlantBoxReward> rewards = new ArrayList<>();
        PlantBoxReward finalReward = rollReward(info, random);
        if (finalReward.stack().isEmpty()) {
            return rewards;
        }

        for (int i = 0; i < 50; i++) {
            PlantBoxReward reward = rollReward(info, random);
            rewards.add(reward.stack().isEmpty() ? finalReward : reward);
        }
        rewards.set(45, finalReward);
        return rewards;
    }

    public static PlantBoxReward rollReward(PlantBoxInfo info, RandomSource random) {
        int[] weights = info.normalizedWeights();
        List<Integer> grades = new ArrayList<>();
        List<Integer> gradeWeights = new ArrayList<>();
        int totalWeight = 0;

        for (int grade = 5; grade >= 0; grade--) {
            List<String> rewards = info.rewardsForGrade(grade);
            if (rewards == null || rewards.isEmpty()) {
                continue;
            }

            int weight = grade == 0 ? info.normalizedJunkWeight() : weights[5 - grade];
            if (weight <= 0) {
                continue;
            }

            grades.add(grade);
            gradeWeights.add(weight);
            totalWeight += weight;
        }

        if (totalWeight <= 0 || grades.isEmpty()) {
            return PlantBoxReward.EMPTY;
        }

        int roll = random.nextInt(totalWeight);
        int cursor = 0;
        int selectedGrade = grades.get(grades.size() - 1);
        for (int i = 0; i < grades.size(); i++) {
            cursor += gradeWeights.get(i);
            if (roll < cursor) {
                selectedGrade = grades.get(i);
                break;
            }
        }

        List<String> rewards = info.rewardsForGrade(selectedGrade);
        if (rewards == null || rewards.isEmpty()) {
            return PlantBoxReward.EMPTY;
        }

        String rewardId = rewards.get(random.nextInt(rewards.size()));
        return new PlantBoxReward(PlantBoxItemParser.parse(rewardId, random), selectedGrade);
    }

    private static void appendRewardGrade(Map<ItemStack, Integer> items, PlantBoxInfo info, int grade) {
        for (String rewardId : info.rewardsForGrade(grade)) {
            ItemStack stack = PlantBoxItemParser.parse(rewardId);
            if (!stack.isEmpty()) {
                items.put(stack, grade);
            }
        }
    }

    public record PlantBoxReward(ItemStack stack, int grade) {
        public static final PlantBoxReward EMPTY = new PlantBoxReward(ItemStack.EMPTY, 0);
    }
}
