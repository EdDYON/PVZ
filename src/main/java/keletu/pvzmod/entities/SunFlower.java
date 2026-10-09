package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SunFlower extends EntityPlantBase {

    private static final EntityDataAccessor<Integer> GENERATE = SynchedEntityData.defineId(SunFlower.class, EntityDataSerializers.INT);
    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState blinkAnimation = new AnimationState();
    public final AnimationState generateSunAnimation = new AnimationState();
    private int blinkTime = 0;

    public SunFlower(EntityType<? extends EntityPlantBase> type, Level par1World) {
        super(type, par1World, new ItemStack(PVZItems.SUN_FLOWER_CARD.get(), 1));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GENERATE, 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityPlantBase.createAttributes().add(Attributes.MAX_HEALTH, 20.0D);
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(false);
    }

    public int getGenerateTime() {
        return this.entityData.get(GENERATE);
    }

    public void setGenTime(int time) {
        this.entityData.set(GENERATE, time);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        int interval = sunInterval();
        if (this.entityData.get(GENERATE) < interval) {
            this.setGenTime(this.getGenerateTime() + 1);
        }

        if (this.getGenerateTime() == sunSpawnTick(interval) && !this.level().isClientSide) {
            ItemEntity item = new ItemEntity(this.level(), this.xo, this.yo + this.getEyeHeight(), this.zo, new ItemStack(PVZItems.SUN.get()), this.level().random.nextDouble() * 0.2D - 0.1D, 0.05f, this.level().random.nextDouble() * 0.2D - 0.1D);
            item.setGlowingTag(true);
            this.level().addFreshEntity(item);
        }

        if (this.getGenerateTime() >= interval) {
            this.setGenTime(0);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("GenerateSun", this.entityData.get(GENERATE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(GENERATE, pCompound.getInt("GenerateSun"));
    }

    private void setupBlinkAnimation() {
        if (this.blinkTime > 0) {
            this.blinkTime--;
            return;
        }

        this.blinkAnimation.start(this.tickCount);

        this.blinkTime = (60 + this.random.nextInt(80));
    }

    public void setupAnimationStates() {
        setupBlinkAnimation();
        if (this.getGenerateTime() >= this.plantStatInt("sun_animation_tick", 360, 0, 72000)) {
            this.idleAnimation.stop();
            this.generateSunAnimation.startIfStopped(this.tickCount);
        } else {
            this.idleAnimation.startIfStopped(this.tickCount);
            this.generateSunAnimation.stop();
        }
    }

    private int sunInterval() {
        return this.plantStatInt("sun_interval_ticks", 400, 1, 72000);
    }

    private int sunSpawnTick(int interval) {
        return Mth.clamp(this.plantStatInt("sun_spawn_tick", 390, 0, 72000), 0, interval);
    }
}
