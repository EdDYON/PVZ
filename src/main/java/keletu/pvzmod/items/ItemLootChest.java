package keletu.pvzmod.items;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.models.gecko.PVZGeoResourceProvider;
import keletu.pvzmod.models.render.GeoLootChestItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ItemLootChest extends BlockItem implements GeoItem, PVZGeoResourceProvider {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Quality quality;

    public ItemLootChest(Block block, Properties properties, Quality quality) {
        super(block, properties);
        this.quality = quality;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.pvz_myh.loot_chest", Component.translatable(this.quality.translationKey)).withStyle(this.quality.color));
        tooltip.add(Component.translatable("tooltip.pvz_myh.loot_chest.open").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.quality != Quality.COMMON;
    }

    @Override
    public ResourceLocation getGeoModelResource() {
        return new ResourceLocation(PVZMod.MODID, "geo/item/" + this.quality.modelName + ".geo.json");
    }

    @Override
    public ResourceLocation getGeoTextureResource() {
        return new ResourceLocation(PVZMod.MODID, "textures/item/" + this.quality.modelName + ".png");
    }

    @Override
    public ResourceLocation getGeoAnimationResource() {
        return new ResourceLocation(PVZMod.MODID, "animations/item/" + this.quality.modelName + ".animation.json");
    }

    @Override
    public String getGeoAnimationName() {
        return this.quality.animationName;
    }

    public String getModelName() {
        return this.quality.modelName;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final GeoLootChestItemRenderer renderer = new GeoLootChestItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

    public enum Quality {
        COMMON("common_chest", "animation.common_chest.chest_open_close",
                "tooltip.pvz_myh.loot_chest.quality.common", ChatFormatting.GRAY, 2, 0.9F, new LootEntry[]{
                entry(() -> Items.ROTTEN_FLESH, 18, 2, 6),
                entry(() -> Items.BONE, 14, 2, 6),
                entry(() -> Items.WHEAT_SEEDS, 12, 2, 8),
                entry(() -> Items.STICK, 10, 2, 5),
                entry(PVZItems.PEA, 10, 4, 12),
                entry(PVZItems.SUN, 8, 3, 8),
                entry(PVZItems.PEASHOOTER_CARD, 5, 1, 1),
                entry(PVZItems.SUN_FLOWER_CARD, 5, 1, 1),
                entry(PVZItems.WALNUT_CARD, 4, 1, 1),
                entry(() -> Items.IRON_INGOT, 3, 1, 3),
                entry(() -> Items.DIAMOND, 1, 1, 1)
        }),
        EPIC("epic_chest", "animation.epic_chest.chest_open_close",
                "tooltip.pvz_myh.loot_chest.quality.epic", ChatFormatting.LIGHT_PURPLE, 3, 1.05F, new LootEntry[]{
                entry(PVZItems.SUN, 12, 8, 16),
                entry(PVZItems.DIAMOND, 10, 2, 5),
                entry(() -> Items.EMERALD, 8, 2, 6),
                entry(() -> Items.DIAMOND, 8, 1, 3),
                entry(() -> Items.GOLDEN_APPLE, 5, 1, 2),
                entry(PVZItems.REPEATER_CARD, 6, 1, 1),
                entry(PVZItems.SNOWPEA_CARD, 6, 1, 1),
                entry(PVZItems.GATLING_PEA_CARD, 4, 1, 1),
                entry(PVZItems.ELECTRIC_PEASHOOTER_CARD, 4, 1, 1),
                entry(() -> Items.NETHERITE_SCRAP, 2, 1, 1),
                entry(() -> Items.ENCHANTED_GOLDEN_APPLE, 1, 1, 1)
        }),
        LEGENDARY("legendary_chest", "animation.legendary_chest.animation",
                "tooltip.pvz_myh.loot_chest.quality.legendary", ChatFormatting.GOLD, 4, 1.2F, new LootEntry[]{
                entry(PVZItems.DIAMOND, 12, 5, 10),
                entry(() -> Items.DIAMOND, 10, 3, 6),
                entry(() -> Items.NETHERITE_SCRAP, 8, 1, 3),
                entry(() -> Items.TOTEM_OF_UNDYING, 5, 1, 1),
                entry(() -> Items.ENCHANTED_GOLDEN_APPLE, 4, 1, 2),
                entry(() -> Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 3, 1, 1),
                entry(PVZItems.SUPER_GATLING_PEA_CARD, 7, 1, 1),
                entry(PVZItems.PRIMAL_PEASHOOTER_CARD, 6, 1, 1),
                entry(PVZItems.FUME_SHROOM_CARD, 6, 1, 1),
                entry(PVZItems.PUMPKIN_CARD, 5, 1, 1),
                entry(PVZItems.TALL_NUT_CARD, 5, 1, 1),
                entry(() -> Items.ROTTEN_FLESH, 2, 1, 4)
        });

        private final String modelName;
        private final String animationName;
        private final String translationKey;
        private final ChatFormatting color;
        private final int rolls;
        private final float pitch;
        private final LootEntry[] loot;
        private final int totalWeight;

        Quality(String modelName, String animationName, String translationKey, ChatFormatting color, int rolls, float pitch, LootEntry[] loot) {
            this.modelName = modelName;
            this.animationName = animationName;
            this.translationKey = translationKey;
            this.color = color;
            this.rolls = rolls;
            this.pitch = pitch;
            this.loot = loot;

            int total = 0;
            for (LootEntry entry : loot) {
                total += entry.weight;
            }
            this.totalWeight = total;
        }

        public String getModelName() {
            return this.modelName;
        }

        public String getAnimationName() {
            return this.animationName;
        }

        public int getRolls() {
            return this.rolls;
        }

        public float getPitch() {
            return this.pitch;
        }

        public int getOpenTicks() {
            return 32;
        }

        public ItemStack rollReward(RandomSource random) {
            return this.roll(random);
        }

        private ItemStack roll(RandomSource random) {
            int roll = random.nextInt(this.totalWeight);
            int cursor = 0;
            for (LootEntry entry : this.loot) {
                cursor += entry.weight;
                if (roll < cursor) {
                    return entry.create(random);
                }
            }
            return this.loot[0].create(random);
        }
    }

    private static LootEntry entry(Supplier<? extends Item> item, int weight, int minCount, int maxCount) {
        return new LootEntry(item, weight, minCount, maxCount);
    }

    private static LootEntry entry(RegistryObject<Item> item, int weight, int minCount, int maxCount) {
        return entry((Supplier<? extends Item>) item, weight, minCount, maxCount);
    }

    private record LootEntry(Supplier<? extends Item> item, int weight, int minCount, int maxCount) {
        private ItemStack create(RandomSource random) {
            int count = this.minCount + random.nextInt(this.maxCount - this.minCount + 1);
            return new ItemStack(this.item.get(), count);
        }
    }
}
