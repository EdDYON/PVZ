package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PotatoMine extends EntityPlantBase {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleAnimationState2 = new AnimationState();
    public final AnimationState growAnimation = new AnimationState();
    public final AnimationState boomAnimation = new AnimationState();
    private static final EntityDataAccessor<Integer> GROW_TIME = SynchedEntityData.defineId(PotatoMine.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BOOM_TIME = SynchedEntityData.defineId(PotatoMine.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> START_BOOM = SynchedEntityData.defineId(PotatoMine.class, EntityDataSerializers.BOOLEAN);

    public PotatoMine(EntityType<? extends PotatoMine> entityType, Level par1World) {
        super(entityType, par1World, new ItemStack(PVZItems.POTATO_MINE_CARD.get()));
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GROW_TIME, 0);
        this.entityData.define(BOOM_TIME, 0);
        this.entityData.define(START_BOOM, false);
    }

    public int getGrowTime() {
        return this.entityData.get(GROW_TIME);
    }

    public void setGrowTime(int time) {
        this.entityData.set(GROW_TIME, time);
    }

    public int getBoomTime() {
        return this.entityData.get(BOOM_TIME);
    }

    public void setBoomTime(int time) {
        this.entityData.set(BOOM_TIME, time);
    }

    public boolean startBoom() {
        return this.entityData.get(START_BOOM);
    }

    public void setStartBoom(boolean isStart) {
        this.entityData.set(START_BOOM, isStart);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("GrowTime", this.getGrowTime());
        pCompound.putInt("BoomTime", this.getBoomTime());
        pCompound.putBoolean("isStart", this.startBoom());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGrowTime(pCompound.getInt("GrowTime"));
        this.setBoomTime(pCompound.getInt("BoomTime"));
        this.setStartBoom(pCompound.getBoolean("isStart"));
    }

    @Override
    public void tick() {
        super.tick();

        int waitTime = waitTime();
        if (this.getGrowTime() >= waitTime && !this.startBoom()) {
            double triggerRadius = this.plantStatDouble("trigger_radius", 1.0D);
            for (Entity entity : this.level().getEntities(this, this.getBoundingBox().inflate(triggerRadius))) {
                if (entity instanceof Mob mob && mob != this.getOwner() && !(mob instanceof IPlantWontHurt)) {
                    this.setStartBoom(true);
                    break;
                }
            }
        }

        if (this.getGrowTime() < waitTime) {
            this.setGrowTime(this.getGrowTime() + 1);
        }

        if (this.startBoom()) {
            this.setBoomTime(this.getBoomTime() + 1);
        }

        if (this.getBoomTime() == this.plantStatInt("explosion_fuse_ticks", 15, 1, 72000)) {
            explodeAndDamage();
            this.remove(RemovalReason.DISCARDED);
        }

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void explodeAndDamage() {
        if (this.level().isClientSide) return;

        ServerLevel serverLevel = (ServerLevel) this.level();

        double radius = this.plantStatDouble("explosion_radius", 3.0D);
        float maxDamage = this.plantStatFloat("explosion_damage", 90.0D);

        serverLevel.playSound(null, this.blockPosition(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.BLOCKS,
                1.0F, 1.0F);

        serverLevel.sendParticles(
                ParticleTypes.EXPLOSION,
                this.getX(),
                this.getY(),
                this.getZ(),
                1,
                0,
                0,
                0,
                0.15F
        );

        for (int i = 0; i < 20; i++) {
            double angle = (Math.PI * 2D) * i / 20;

            serverLevel.sendParticles(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.POTATO)),
                    this.getX() + Math.cos(angle) * 0.12D,
                    this.getY() + 0.1D,
                    this.getZ() + Math.sin(angle) * 0.12D,
                    10,
                    0,
                    0,
                    0,
                    0.2F
            );
        }

        for (Entity entity : this.level().getEntities(this, this.getBoundingBox().inflate(radius))) {
            if (entity instanceof Mob mob && mob != this.getOwner() && !(mob instanceof IPlantWontHurt)) {
                double distance = this.distanceTo(mob);
                if (distance <= radius) {
                    float damage = (float) (maxDamage * (1.0D - distance / radius));
                    damage = Math.max(damage, 1.0F);
                    mob.hurt(this.damageSources().explosion(this, this), damage);
                }
            }
        }
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof Mob && this.getGrowTime() >= waitTime()) {
            this.setStartBoom(true);
        }
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(false);
    }

    @Override
    public boolean isInvulnerable() {
        return super.isInvulnerable() || this.startBoom();
    }

    private void stopAllAnimationStates() {
        this.idleAnimationState.stop();
        this.idleAnimationState2.stop();
        this.growAnimation.stop();
        this.boomAnimation.stop();
    }

    private void setupAnimationStates() {
        int waitTime = waitTime();
        int growTime = this.plantStatInt("grow_animation_ticks", 20, 0, 72000);
        if (this.getBoomTime() > 0) {
            this.idleAnimationState.stop();
            this.idleAnimationState2.stop();
            this.growAnimation.stop();
            this.boomAnimation.startIfStopped(this.tickCount);
        } else if (this.getGrowTime() < waitTime - growTime) {
            this.idleAnimationState2.stop();
            this.growAnimation.stop();
            this.boomAnimation.stop();
            this.idleAnimationState.startIfStopped(this.tickCount);
        } else if (this.getGrowTime() < waitTime) {
            this.idleAnimationState.stop();
            this.idleAnimationState2.stop();
            this.boomAnimation.stop();
            this.growAnimation.startIfStopped(this.tickCount);
        } else {
            this.idleAnimationState.stop();
            this.growAnimation.stop();
            this.boomAnimation.stop();
            this.idleAnimationState2.startIfStopped(this.tickCount);
        }
    }

    private int waitTime() {
        return this.plantStatInt("grow_wait_ticks", 315, 1, 72000);
    }
}
