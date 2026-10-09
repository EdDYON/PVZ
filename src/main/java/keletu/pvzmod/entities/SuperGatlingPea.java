package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.TrueSuperRangedAttackGoal;
import keletu.pvzmod.entities.projectile.PeaProjectile;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SuperGatlingPea extends EntityPlantShooterBase {
    public static final int SUPER_RAPID_FIRE_DURATION = 40;
    public static final int SUPER_RAPID_FIRE_COOLDOWN = 300;
    public static final float SUPER_RAPID_FIRE_CHANCE = 0.02F;
    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState blinkAnimation = new AnimationState();
    public final AnimationState shootAnimation = new AnimationState();
    public final AnimationState superAnimation = new AnimationState();
    private int blinkTime = 0;
    private static final EntityDataAccessor<Integer> SUPER_TICK =
            SynchedEntityData.defineId(SuperGatlingPea.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SUPER_COOLDOWN =
            SynchedEntityData.defineId(SuperGatlingPea.class, EntityDataSerializers.INT);

    public SuperGatlingPea(EntityType<? extends SuperGatlingPea> type, Level level) {
        super(type, level, new ItemStack(PVZItems.SUPER_GATLING_PEA_CARD.get()));
    }


    @Override
    protected TrueSuperRangedAttackGoal createRangedAttackGoal() {
        return new TrueSuperRangedAttackGoal(this, 0.0D, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, 15.0D), 4, 1, 30, 25);
    }

    @Override
    public ThrowableProjectile entitySelect(Level world) {
        PeaProjectile ent = new PeaProjectile(world, this, this.plantStatFloat(PlantStatManager.PROJECTILE_DAMAGE, 3.0D));
        return ent;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SUPER_TICK, 0);
        this.entityData.define(SUPER_COOLDOWN, 0);
    }

    public int getSuperTick() {
        return this.entityData.get(SUPER_TICK);
    }

    public void setSuperTick(int tick) {
        this.entityData.set(SUPER_TICK, tick);
    }

    public int getSuperCooldown() {
        return this.entityData.get(SUPER_COOLDOWN);
    }

    public void setSuperCooldown(int tick) {
        this.entityData.set(SUPER_COOLDOWN, tick);
    }

    public boolean isSuperFiring() {
        return this.getSuperTick() > 0;
    }

    public boolean canTriggerSuperRapidFire() {
        return this.getSuperTick() <= 0 && this.getSuperCooldown() <= 0;
    }

    public float superRapidFireChance() {
        return this.plantStatFloat("super_chance_percent", SUPER_RAPID_FIRE_CHANCE * 100.0F) / 100.0F;
    }

    public void triggerSuperRapidFire() {
        this.setSuperTick(this.plantStatInt("super_duration_ticks", SUPER_RAPID_FIRE_DURATION, 1, 72000));
        this.setSuperCooldown(this.plantStatInt("super_cooldown_ticks", SUPER_RAPID_FIRE_COOLDOWN, 0, 72000));
    }

    public boolean canUseEnergyBean(@Nullable Player player) {
        return !this.isSuperFiring() && (player == null || this.getOwnerUUID() == null || this.getOwnerUUID().equals(player.getUUID()));
    }

    public boolean activateEnergyBean(@Nullable Player player) {
        if (!this.canUseEnergyBean(player) || this.level().isClientSide) {
            return false;
        }

        this.setSuperTick(this.plantStatInt("super_duration_ticks", SUPER_RAPID_FIRE_DURATION, 1, 72000));
        this.setSuperCooldown(0);
        this.playSound(PVZSounds.SUPERGATLINGPEA_POWER.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().broadcastEntityEvent(this, (byte) 5);

        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    this.getX(),
                    this.getY() + 0.75D,
                    this.getZ(),
                    24,
                    0.35D,
                    0.45D,
                    0.35D,
                    0.02D
            );
            serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.getX(),
                    this.getY() + 0.6D,
                    this.getZ(),
                    12,
                    0.3D,
                    0.35D,
                    0.3D,
                    0.02D
            );
        }

        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float power) {
        if (this.isSuperFiring()) {
            this.fireSuperShot(target);
        } else {
            super.performRangedAttack(target, power);
        }
    }

    @Override
    public SoundEvent getShootSound() {
        return PVZSounds.SUPERGATLINGPEA_SHOOT.get();
    }

    @Override
    public void tick() {
        super.tick();

        this.setXRot(0.0F);
        this.xRotO = 0.0F;

        if (!this.level().isClientSide) {
            if (this.getSuperCooldown() > 0) {
                this.setSuperCooldown(this.getSuperCooldown() - 1);
            }

            if (this.getSuperTick() > 0) {
                LivingEntity target = this.getTarget();

                if (target != null && target.isAlive() && this.canAttack(target)) {
                    //this.getLookControl().setLookAt(target, 30.0F, 30.0F);
                    this.performRangedAttack(target, 1.0F);
                } else {
                    this.fireSuperShotForward();
                }

                this.setSuperTick(this.getSuperTick() - 1);
            }
        }

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void fireSuperShot(LivingEntity target) {
        if (this.level().isClientSide || target == null) {
            return;
        }

        this.faceTarget(target);

        int shots = superShotCount();
        float spread = this.plantStatFloat("super_spread_degrees", 45.0D);
        float offset = this.plantStatFloat("super_spread_offset_degrees", 7.5D);
        float speed = this.plantStatFloat("super_projectile_speed", 1.2D);
        for (int i = 0; i < shots; i++) {
            ThrowableProjectile pea = this.entitySelect(this.level());

            pea.setPos(
                    this.getX(),
                    this.getEyeY() - 0.1D,
                    this.getZ()
            );

            double dx = target.getX() - pea.getX();
            double dy = target.getEyeY() - pea.getY();
            double dz = target.getZ() - pea.getZ();

            Vec3 direction = new Vec3(dx, dy, dz).normalize();

            float spreadYaw = (this.random.nextFloat() * spread) - offset;
            float spreadPitch = (this.random.nextFloat() * spread) - offset;

            Vec3 spreadDirection = direction
                    .yRot((float) Math.toRadians(spreadYaw))
                    .xRot((float) Math.toRadians(spreadPitch))
                    .normalize();

            pea.shoot(spreadDirection.x, 0, spreadDirection.z, speed, 0.0F);

            this.level().addFreshEntity(pea);
        }
    }

    private void fireSuperShotForward() {
        if (this.level().isClientSide) {
            return;
        }

        int shots = superShotCount();
        float spread = this.plantStatFloat("super_spread_degrees", 45.0D);
        float offset = this.plantStatFloat("super_spread_offset_degrees", 7.5D);
        float speed = this.plantStatFloat("super_projectile_speed", 1.2D);
        for (int i = 0; i < shots; i++) {
            ThrowableProjectile pea = this.entitySelect(this.level());

            float yawOffset = (this.random.nextFloat() * spread) - offset;
            float pitchOffset = (this.random.nextFloat() * spread) - offset;

            float shootYaw = this.yHeadRot + yawOffset;
            //float shootPitch = this.getXRot() + pitchOffset;

            Vec3 look = Vec3.directionFromRotation(0, shootYaw);

            pea.setPos(this.getX(), this.getEyeY() - 0.1D, this.getZ());
            pea.shoot(look.x, look.y, look.z, speed, 0.0F);
            this.level().addFreshEntity(pea);
        }
    }

    private int superShotCount() {
        int minShots = this.plantStatInt("super_min_shots", 3, 1, 128);
        int extraShots = this.plantStatInt("super_extra_shots", 3, 0, 128);
        return minShots + (extraShots <= 0 ? 0 : this.random.nextInt(extraShots));
    }

    private void setupBlinkAnimation() {
        if (this.blinkTime > 0) {
            this.blinkTime--;
            return;
        }

        if (!this.isShooting()) {
            this.blinkAnimation.start(this.tickCount);
        }

        this.blinkTime = (60 + this.random.nextInt(80));
    }

    public void setupAnimationStates() {
        setupBlinkAnimation();
        if (!this.isShooting() && !this.isSuperFiring())
            this.idleAnimation.startIfStopped(this.tickCount);
        else
            this.idleAnimation.stop();

    }

    @Override
    public void handleEntityEvent(byte pId) {
        super.handleEntityEvent(pId);
        if (pId == 5) {
            this.superAnimation.start(this.tickCount);
            this.shootAnimation.stop();
        }
    }

    @Override
    public void handleStartShootEvent() {
        this.shootAnimation.start(this.tickCount);
        this.superAnimation.stop();
    }
}
