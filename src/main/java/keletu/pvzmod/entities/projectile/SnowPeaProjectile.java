package keletu.pvzmod.entities.projectile;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.init.PVZEffects;
import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.init.PVZParticles;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.particles.TextureParticleOption;
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

public class SnowPeaProjectile extends ThrowableProjectile {
    private float damage = 3.0F;
    private EntityPlantBase shooter;

    public SnowPeaProjectile(EntityType<? extends SnowPeaProjectile> type, Level level) {
        super(type, level);
    }

    public SnowPeaProjectile(Level level, EntityPlantBase shooter, float damage) {
        super(PVZEntities.ICE_PEA_PROJECTILE.get(), shooter, level);
        this.damage = damage;
        this.shooter = shooter;
        this.setNoGravity(true);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();

        if (target instanceof IPlantWontHurt || (this.shooter != null && target == shooter.getOwner())) {
            return;
        }

        if (!this.level().isClientSide && result.getEntity() instanceof LivingEntity mob) {
            Vec3 oldMotion = mob.getDeltaMovement();

            boolean oldHasImpulse = mob.hasImpulse;
            boolean oldHurtMarked = mob.hurtMarked;

            LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;
            int duration = this.shooter == null ? 100 : this.shooter.plantStatInt("snow_duration_ticks", 100, 1, 72000);

            mob.addEffect(new MobEffectInstance(PVZEffects.SNOW.get(), duration, 0));
            mob.hurt(
                    this.damageSources().mobProjectile(this, owner),
                    this.damage
            );

            mob.invulnerableTime = 0;

            mob.setDeltaMovement(oldMotion);
            mob.hasImpulse = oldHasImpulse;
            mob.hurtMarked = oldHurtMarked;

            this.playSound(PVZSounds.PEA_BOOM.get(), 1.0F, 1.0F);
            this.spawnBreakParticles();
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {
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
            this.playSound(PVZSounds.PEA_BOOM.get(), 1.0F, 1.0F);
            this.discard();
        }
    }

    private void spawnBreakParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            int particleCount = 10 + this.random.nextInt(10);

            serverLevel.sendParticles(
                    new TextureParticleOption(PVZParticles.TEXTURE_BREAK.get(), new ResourceLocation(PVZMod.MODID, "item/ice_pea")),
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
