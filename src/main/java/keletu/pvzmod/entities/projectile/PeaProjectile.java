package keletu.pvzmod.entities.projectile;

import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class PeaProjectile extends ThrowableItemProjectile {
    private float damage = 3.0F;
    private EntityPlantBase shooter;

    public PeaProjectile(EntityType<? extends PeaProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public PeaProjectile(Level level, EntityPlantBase shooter, float damage) {
        super(PVZEntities.PEA_PROJECTILE.get(), shooter, level);
        this.damage = damage;
        this.shooter = shooter;
        this.setNoGravity(true);
    }

    @Override
    protected Item getDefaultItem() {
        return PVZItems.PEA.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity target = result.getEntity();

        if (target instanceof IPlantWontHurt || (this.shooter != null && target == shooter.getOwner())) {
            return;
        }

        if (!this.level().isClientSide) {
            Vec3 oldMotion = target.getDeltaMovement();

            boolean oldHasImpulse = target.hasImpulse;
            boolean oldHurtMarked = target.hurtMarked;

            LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;

            target.hurt(
                    this.damageSources().mobProjectile(this, owner),
                    this.damage
            );

            target.invulnerableTime = 0;

            target.setDeltaMovement(oldMotion);
            target.hasImpulse = oldHasImpulse;
            target.hurtMarked = oldHurtMarked;

            this.playSound(PVZSounds.PEA_BOOM.get(), 1.0F, 1.0F);
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
            this.playSound(PVZSounds.PEA_BOOM.get(), 1.0F, 1.0F);
            this.discard();
        }
    }

    private void spawnBreakParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            int particleCount = 10 + this.random.nextInt(10);

            serverLevel.sendParticles(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(PVZItems.PEA.get())),
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