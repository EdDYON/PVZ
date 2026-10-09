package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.PlanterHurtByTargetGoal;
import keletu.pvzmod.entities.ai.PlanterHurtTargetGoal;
import keletu.pvzmod.entities.ai.TrueRangedAttackGoal;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class EntityPlantShooterBase extends EntityPlantBase implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> IS_SHOOTING = SynchedEntityData.defineId(EntityPlantShooterBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> SHOOT_TIME = SynchedEntityData.defineId(EntityPlantShooterBase.class, EntityDataSerializers.INT);
    private int shootingAnimationTicks;

    public EntityPlantShooterBase(EntityType<? extends EntityPlantBase> entityType, Level level) {
        super(entityType, level);
    }

    public EntityPlantShooterBase(EntityType<? extends EntityPlantBase> entityType, Level level, ItemStack spawnStack) {
        super(entityType, level, spawnStack);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, createRangedAttackGoal());

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new PlanterHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new PlanterHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    protected Goal createRangedAttackGoal() {
        return new TrueRangedAttackGoal(this, 0.0F, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, this.range), 1, 0, 30, 25);
    }

    public boolean hasAttackLineOfSight(LivingEntity target) {
        return this.getSensing().hasLineOfSight(target);
    }

    public SoundEvent getShootSound() {
        return PVZSounds.PEASHOOTER_SHOOT.get();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide) {
            this.faceTarget(target);

            ThrowableProjectile projectile = entitySelect(this.level());
            projectile.setPos(this.getX(), this.getEyeY() - 0.2D, this.getZ());

            double toX = target.getX() - this.getX();
            double toZ = target.getZ() - this.getZ();
            if (toX * toX + toZ * toZ < 1.0E-6D) {
                Vec3 look = this.getLookAngle();
                toX = look.x;
                toZ = look.z;
            }

            projectile.shoot(toX, 0, toZ, this.plantStatFloat(PlantStatManager.PROJECTILE_SPEED, 1.6D), 0.0F);

            this.level().addFreshEntity(projectile);

            if (target instanceof PathfinderMob && (((PathfinderMob) target).getTarget() == null || !((PathfinderMob) target).getTarget().isAlive())) {
                ((PathfinderMob) target).setTarget(this);
            }
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && this.hasAttackLineOfSight(target);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_SHOOTING, false);
        this.entityData.define(SHOOT_TIME, 0);
    }

    public boolean isShooting() {
        return this.entityData.get(IS_SHOOTING);
    }

    public void setShooting(boolean shooting) {
        this.entityData.set(IS_SHOOTING, shooting);
    }

    public int getShootTime() {
        return this.entityData.get(SHOOT_TIME);
    }

    public void setShootTime(int shooting) {
        this.entityData.set(SHOOT_TIME, shooting);
    }

    @Override
    public void tick() {
        super.tick();

        //if (this.getShootTime() != 0) {
        //    this.setShootTime(Math.max(this.getShootTime() - 1, 0));
        //}
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Shooting", this.isShooting());
        pCompound.putInt("Timing", this.getShootTime());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setShooting(pCompound.getBoolean("Shooting"));
        this.setShootTime(pCompound.getInt("Timing"));
    }

    public ThrowableProjectile entitySelect(Level level) {
        return new Snowball(level, this);
    }
}
