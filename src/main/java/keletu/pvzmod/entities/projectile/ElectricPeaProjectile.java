package keletu.pvzmod.entities.projectile;

import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.init.PVZDamageTypes;
import keletu.pvzmod.init.PVZEffects;
import keletu.pvzmod.init.PVZEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ElectricPeaProjectile extends ThrowableProjectile {

    private static final EntityDataAccessor<Integer> TARGET_ID_1 =
            SynchedEntityData.defineId(ElectricPeaProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ID_2 =
            SynchedEntityData.defineId(ElectricPeaProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ID_3 =
            SynchedEntityData.defineId(ElectricPeaProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TARGET_ID_4 =
            SynchedEntityData.defineId(ElectricPeaProjectile.class, EntityDataSerializers.INT);
    private float damage = 3.0F;
    private float shockDamage = 1.0F;
    private EntityPlantBase shooter;
    private final Set<UUID> shockedEntities = new HashSet<>();
    private static final int SHOCKED_DURATION = 20;

    public ElectricPeaProjectile(EntityType<? extends ElectricPeaProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public ElectricPeaProjectile(Level level, EntityPlantBase shooter, float damage) {
        super(PVZEntities.ELECTRIC_PEA_PROJECTILE.get(), shooter, level);
        this.damage = damage;
        this.shooter = shooter;
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TARGET_ID_1, -1);
        this.entityData.define(TARGET_ID_2, -1);
        this.entityData.define(TARGET_ID_3, -1);
        this.entityData.define(TARGET_ID_4, -1);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (result.getEntity() instanceof IPlantWontHurt || (this.shooter != null && result.getEntity() == shooter.getOwner()))
            return;

        if (!this.level().isClientSide && result.getEntity() instanceof LivingEntity target) {
            Vec3 oldMotion = target.getDeltaMovement();

            boolean oldHasImpulse = target.hasImpulse;
            boolean oldHurtMarked = target.hurtMarked;

            LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;

            if (this.random.nextFloat() * 100.0F < shockChancePercent()) {
                target.addEffect(new MobEffectInstance(PVZEffects.SHOCKED.get(), shockedDuration(), 0));
            }
            target.hurt(PVZDamageTypes.causeElectricPeaProjectileDamage(this.level(), this, owner), damage);

            target.invulnerableTime = 0;

            target.setDeltaMovement(oldMotion);
            target.hasImpulse = oldHasImpulse;
            target.hurtMarked = oldHurtMarked;
            this.shockNearbyEntities();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            this.shockNearbyEntities();

            if (this.tickCount > 40) {
                this.discard();
                ((ServerLevel) this.level()).sendParticles(
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
            }
        }
    }

    private boolean canShock(LivingEntity entity) {
        if (entity == null || !entity.isAlive()) {
            return false;
        }

        if (entity == this.getOwner()) {
            return false;
        }

        if (entity == this.shooter) {
            return false;
        }

        if (entity instanceof IPlantWontHurt) {
            return false;
        }

        if (this.shooter != null && entity == this.shooter.getOwner()) {
            return false;
        }

        return true;
    }

    private void clearRenderTargets() {
        this.entityData.set(TARGET_ID_1, -1);
        this.entityData.set(TARGET_ID_2, -1);
        this.entityData.set(TARGET_ID_3, -1);
        this.entityData.set(TARGET_ID_4, -1);
    }

    private void setRenderTargets(java.util.List<LivingEntity> targets) {
        clearRenderTargets();

        if (targets.size() > 0) this.entityData.set(TARGET_ID_1, targets.get(0).getId());
        if (targets.size() > 1) this.entityData.set(TARGET_ID_2, targets.get(1).getId());
        if (targets.size() > 2) this.entityData.set(TARGET_ID_3, targets.get(2).getId());
        if (targets.size() > 3) this.entityData.set(TARGET_ID_4, targets.get(3).getId());
    }

    public java.util.List<LivingEntity> getRenderTargets() {
        java.util.List<LivingEntity> result = new java.util.ArrayList<>();
        addTargetById(result, this.entityData.get(TARGET_ID_1));
        addTargetById(result, this.entityData.get(TARGET_ID_2));
        addTargetById(result, this.entityData.get(TARGET_ID_3));
        addTargetById(result, this.entityData.get(TARGET_ID_4));
        return result;
    }

    private void addTargetById(java.util.List<LivingEntity> result, int id) {
        if (id < 0 || this.level() == null) return;
        Entity entity = this.level().getEntity(id);
        if (entity instanceof LivingEntity living && living.isAlive()) {
            result.add(living);
        }
    }

    private void shockNearbyEntities() {
        if (this.level().isClientSide) {
            return;
        }

        AABB range = this.getBoundingBox().inflate(shockRange());
        java.util.List<LivingEntity> renderTargets = new java.util.ArrayList<>();

        for (LivingEntity living : this.level().getEntitiesOfClass(LivingEntity.class, range)) {
            if (!canShock(living)) {
                continue;
            }

            renderTargets.add(living);

            if (shockedEntities.contains(living.getUUID())) {
                continue;
            }

            living.hurt(
                    PVZDamageTypes.causeElectricPeaProjectileDamage(this.level(), this, this.getOwner()),
                    shockDamage()
            );
            living.invulnerableTime = 0;
            if (this.random.nextFloat() * 100.0F < shockChancePercent()) {
                living.addEffect(new MobEffectInstance(PVZEffects.SHOCKED.get(), shockedDuration(), 0));
            }

            shockedEntities.add(living.getUUID());
        }

        this.setRenderTargets(renderTargets);
    }

    private float shockDamage() {
        return this.shooter == null ? this.shockDamage : this.shooter.plantStatFloat("electric_shock_damage", this.shockDamage);
    }

    private double shockRange() {
        return this.shooter == null ? 2.0D : this.shooter.plantStatDouble("electric_shock_range", 2.0D);
    }

    private float shockChancePercent() {
        return this.shooter == null ? 20.0F : this.shooter.plantStatFloat("electric_shock_chance_percent", 20.0D);
    }

    private int shockedDuration() {
        return this.shooter == null ? SHOCKED_DURATION : this.shooter.plantStatInt("electric_shocked_duration_ticks", SHOCKED_DURATION, 1, 72000);
    }
}
