package keletu.pvzmod.blocks;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.init.PVZBlocks;
import keletu.pvzmod.items.ItemLootChest;
import keletu.pvzmod.models.gecko.PVZGeoResourceProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class LootChestBlockEntity extends BlockEntity implements GeoBlockEntity, PVZGeoResourceProvider {
    private static final String OPEN_CONTROLLER = "open_controller";
    private static final String OPEN_TRIGGER = "open";

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean opening;
    private int openingTicks;
    @Nullable
    private UUID opener;

    public LootChestBlockEntity(BlockPos pos, BlockState state) {
        super(PVZBlocks.LOOT_CHEST_BLOCK_ENTITY.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, LootChestBlockEntity blockEntity) {
        if (!(level instanceof ServerLevel serverLevel) || !blockEntity.opening) {
            return;
        }

        blockEntity.openingTicks++;
        if (blockEntity.openingTicks == 8) {
            blockEntity.spawnOpeningParticles(serverLevel);
            serverLevel.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.45F, blockEntity.getQuality().getPitch());
        }
        if (blockEntity.openingTicks >= blockEntity.getQuality().getOpenTicks()) {
            blockEntity.finishOpening(serverLevel, pos, state);
        }
    }

    public boolean startOpening(Player player) {
        if (this.opening) {
            return false;
        }

        this.opening = true;
        this.openingTicks = 0;
        this.opener = player.getUUID();
        this.triggerAnim(OPEN_CONTROLLER, OPEN_TRIGGER);
        this.setChanged();
        return true;
    }

    @Override
    public ResourceLocation getGeoModelResource() {
        return new ResourceLocation(PVZMod.MODID, "geo/item/" + this.getQuality().getModelName() + ".geo.json");
    }

    @Override
    public ResourceLocation getGeoTextureResource() {
        return new ResourceLocation(PVZMod.MODID, "textures/item/" + this.getQuality().getModelName() + ".png");
    }

    @Override
    public ResourceLocation getGeoAnimationResource() {
        return new ResourceLocation(PVZMod.MODID, "animations/item/" + this.getQuality().getModelName() + ".animation.json");
    }

    @Override
    public String getGeoAnimationName() {
        return this.getQuality().getAnimationName();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, OPEN_CONTROLLER, 0, state -> PlayState.STOP)
                .triggerableAnim(OPEN_TRIGGER, RawAnimation.begin().thenPlay(this.getGeoAnimationName())));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public ItemLootChest.Quality getQuality() {
        return this.getBlockState().getBlock() instanceof LootChestBlock chestBlock
                ? chestBlock.getQuality()
                : ItemLootChest.Quality.COMMON;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("Opening", this.opening);
        tag.putInt("OpeningTicks", this.openingTicks);
        if (this.opener != null) {
            tag.putUUID("Opener", this.opener);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.opening = tag.getBoolean("Opening");
        this.openingTicks = tag.getInt("OpeningTicks");
        this.opener = tag.hasUUID("Opener") ? tag.getUUID("Opener") : null;
    }

    private void finishOpening(ServerLevel level, BlockPos pos, BlockState state) {
        ServerPlayer player = this.opener == null ? null : level.getServer().getPlayerList().getPlayer(this.opener);

        this.spawnFinishParticles(level);
        level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.35F, this.getQuality().getPitch());

        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.65D;
        double z = pos.getZ() + 0.5D;
        for (int i = 0; i < this.getQuality().getRolls(); i++) {
            ItemStack reward = this.getQuality().rollReward(level.random);
            ItemEntity itemEntity = new ItemEntity(level, x, y, z, reward);
            itemEntity.setDeltaMovement(
                    (level.random.nextDouble() - 0.5D) * 0.28D,
                    0.22D + level.random.nextDouble() * 0.16D,
                    (level.random.nextDouble() - 0.5D) * 0.28D
            );
            level.addFreshEntity(itemEntity);
            if (player != null) {
                player.displayClientMessage(Component.translatable("message.pvz_myh.loot_chest.reward", reward.getCount(), reward.getHoverName()), true);
            }
        }

        level.levelEvent(2001, pos, Block.getId(state));
        level.removeBlock(pos, false);
    }

    private void spawnOpeningParticles(ServerLevel level) {
        BlockPos pos = this.getBlockPos();
        level.sendParticles(this.getOpenParticle(),
                pos.getX() + 0.5D, pos.getY() + 0.75D, pos.getZ() + 0.5D,
                this.getParticleCount() / 2, 0.28D, 0.24D, 0.28D, 0.04D);
    }

    private void spawnFinishParticles(ServerLevel level) {
        BlockPos pos = this.getBlockPos();
        level.sendParticles(this.getOpenParticle(),
                pos.getX() + 0.5D, pos.getY() + 0.85D, pos.getZ() + 0.5D,
                this.getParticleCount(), 0.42D, 0.34D, 0.42D, 0.10D);
        level.sendParticles(ParticleTypes.POOF,
                pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                12, 0.28D, 0.22D, 0.28D, 0.03D);
    }

    private ParticleOptions getOpenParticle() {
        return switch (this.getQuality()) {
            case COMMON -> ParticleTypes.HAPPY_VILLAGER;
            case EPIC -> ParticleTypes.ENCHANT;
            case LEGENDARY -> ParticleTypes.FIREWORK;
        };
    }

    private int getParticleCount() {
        return switch (this.getQuality()) {
            case COMMON -> 20;
            case EPIC -> 36;
            case LEGENDARY -> 56;
        };
    }
}
