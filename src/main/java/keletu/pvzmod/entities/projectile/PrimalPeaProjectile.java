package keletu.pvzmod.entities.projectile;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.init.PVZEffects;
import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.init.PVZParticles;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.particles.TextureParticleOption;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class PrimalPeaProjectile extends ThrowableProjectile {

    private static final EntityDataAccessor<Integer> DATA_TYPE = SynchedEntityData.defineId(PrimalPeaProjectile.class, EntityDataSerializers.INT);
    private float damage = 3.0F;
    private EntityPlantBase shooter;

    public PrimalPeaProjectile(EntityType<? extends PrimalPeaProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public PrimalPeaProjectile(Level level, EntityPlantBase shooter, float damage, int type) {
        super(PVZEntities.PRIMAL_PEA_PROJECTILE.get(), shooter, level);
        this.damage = damage;
        this.shooter = shooter;
        this.entityData.set(DATA_TYPE, type);
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_TYPE, 0);
    }

    public int getProjectileType() {
        return this.entityData.get(DATA_TYPE);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();

        if (target instanceof IPlantWontHurt || (this.shooter != null && target == shooter.getOwner())) {
            return;
        }

        if (!this.level().isClientSide && result.getEntity() instanceof LivingEntity mob) {
            float stunChance = this.shooter == null ? 50.0F : this.shooter.plantStatFloat("primal_stun_chance_percent", 50.0D);
            if (this.random.nextFloat() * 100.0F < stunChance) {
                int stunDuration = this.shooter == null ? 10 : this.shooter.plantStatInt("primal_stun_duration_ticks", 10, 1, 72000);
                mob.addEffect(new MobEffectInstance(PVZEffects.STUN.get(), stunDuration));
            } else {
                Vec3 motion = this.getDeltaMovement();
                double knockback = this.shooter == null ? 2.5D : this.shooter.plantStatDouble("primal_knockback_strength", 2.5D);
                mob.knockback(knockback, -motion.x, -motion.z);
            }

            Vec3 oldMotion = target.getDeltaMovement();

            boolean oldHasImpulse = target.hasImpulse;
            boolean oldHurtMarked = target.hurtMarked;

            LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;
            float multiplier = this.shooter == null ? 2.0F : this.shooter.plantStatFloat("special_damage_multiplier", 2.0D);

            target.hurt(
                    this.damageSources().mobProjectile(this, owner),
                    getProjectileType() == 1 ? this.damage * multiplier : this.damage
            );

            target.invulnerableTime = 0;

            target.setDeltaMovement(oldMotion);
            target.hasImpulse = oldHasImpulse;
            target.hurtMarked = oldHurtMarked;

            this.playSound(PVZSounds.PRIMALPEA_BOOM.get(), 1.0F, 1.0F);
            this.spawnBreakParticles();
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount > 40) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.01F, 0));
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);


        if (!this.level().isClientSide) {
            this.spawnBreakParticles();
            this.playSound(PVZSounds.PRIMALPEA_BOOM.get(), 1.0F, 1.0F);
            this.discard();
        }
    }

    private void spawnBreakParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            int particleCount = 10 + this.random.nextInt(10);

            serverLevel.sendParticles(
                    new TextureParticleOption(PVZParticles.TEXTURE_BREAK.get(), this.getProjectileType() == 1 ? new ResourceLocation(PVZMod.MODID, "item/small_stone") : new ResourceLocation(PVZMod.MODID, "item/primal_pea")),
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    particleCount,
                    0.25D,
                    0.25D,
                    0.25D,
                    0.15D
            );
        }
    }
}
