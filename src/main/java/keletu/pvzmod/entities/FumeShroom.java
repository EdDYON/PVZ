package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.TrueRangedAttackGoal;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZParticles;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FumeShroom extends EntityPlantShooterBase {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();
    private static final double BEAM_KNOCKBACK = 0.04D;

    public FumeShroom(EntityType<? extends EntityPlantShooterBase> entityType, Level par1World) {
        super(entityType, par1World, new ItemStack(PVZItems.FUME_SHROOM_CARD.get()));
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    protected TrueRangedAttackGoal createRangedAttackGoal() {
        return new TrueRangedAttackGoal(this, 0.0F, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, this.range), 1, 0, 50, 40);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (this.level().isClientSide) return;

        this.faceTarget(target);

        Vec3 flatDir = new Vec3(
                target.getX() - this.getX(),
                0.0D,
                target.getZ() - this.getZ()
        );

        Vec3 dir;
        if (flatDir.lengthSqr() < 1.0E-6D) {
            dir = this.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize();
        } else {
            dir = flatDir.normalize();
        }

        Vec3 nozzle = new Vec3(this.getX(), this.getY() + 0.7D, this.getZ()).add(dir.scale(0.45D));
        Vec3 end = nozzle.add(dir.scale(beamLength()));

        damageEntitiesAlongBeam(nozzle, end, beamRadius(), beamDamage());

        List<LivingEntity> hits = getEntitiesAlongBeam(nozzle, end, beamRadius());
        for (LivingEntity hit : hits) {
            if (hit instanceof PathfinderMob mob) {
                if (mob.getTarget() == null || !mob.getTarget().isAlive()) {
                    mob.setTarget(this);
                }
            }
        }
    }

    private void damageEntitiesAlongBeam(Vec3 start, Vec3 end, float radius, float damage) {
        List<LivingEntity> entities = getEntitiesAlongBeam(start, end, radius);

        for (LivingEntity entity : entities) {
            if (entity == this) continue;
            Vec3 oldMotion = entity.getDeltaMovement();

            boolean oldHasImpulse = entity.hasImpulse;
            boolean oldHurtMarked = entity.hurtMarked;

            entity.hurt(this.damageSources().mobAttack(this), damage);
            entity.invulnerableTime = 0;

            entity.setDeltaMovement(oldMotion);
            entity.hasImpulse = oldHasImpulse;
            entity.hurtMarked = oldHurtMarked;
        }
    }

    private List<LivingEntity> getEntitiesAlongBeam(Vec3 start, Vec3 end, float radius) {
        AABB box = new AABB(start, end).inflate(radius + 0.5D);

        return this.level().getEntitiesOfClass(LivingEntity.class, box, entity -> {
            if (entity == this) return false;
            if (!entity.isAlive()) return false;

            if (entity instanceof IPlantWontHurt || entity == this.getOwner()) return false;

            double dist = distanceToSegment(entity.position().add(0, entity.getBbHeight() * 0.5D, 0), start, end);
            return dist <= radius;
        });
    }

    private double distanceToSegment(Vec3 point, Vec3 segStart, Vec3 segEnd) {
        Vec3 seg = segEnd.subtract(segStart);
        Vec3 toPoint = point.subtract(segStart);

        double segLenSqr = seg.lengthSqr();
        if (segLenSqr < 1.0E-6D) {
            return point.distanceTo(segStart);
        }

        double t = toPoint.dot(seg) / segLenSqr;
        t = Math.max(0.0D, Math.min(1.0D, t));

        Vec3 projection = segStart.add(seg.scale(t));
        return point.distanceTo(projection);
    }

    public void spawnBeamParticles(ServerLevel level, Vec3 nozzle, Vec3 dir) {
        int count = 5;
        double nozzleForwardOffset = 0.45D;

        Vec3 side = new Vec3(-dir.z, 0.0D, dir.x).normalize();
        Vec3 basePos = nozzle.add(dir.scale(nozzleForwardOffset));

        for (int i = 0; i < count; i++) {
            double forwardOffset = this.random.nextDouble() * 0.9D;
            double sideOffset = (this.random.nextDouble() - 0.5D) * 0.18D;
            double upOffset = (this.random.nextDouble() - 0.5D) * 0.10D;

            Vec3 spawnPos = basePos
                    .add(dir.scale(forwardOffset))
                    .add(side.scale(sideOffset))
                    .add(0.0D, upOffset, 0.0D);

            double speed = 0.45D + this.random.nextDouble() * 0.22D;

            double sideSpeed = (this.random.nextDouble() - 0.5D) * 0.06D;
            double upSpeed = (this.random.nextDouble() - 0.5D) * 0.03D + 0.01D;

            double vx = dir.x * speed + side.x * sideSpeed;
            double vy = upSpeed;
            double vz = dir.z * speed + side.z * sideSpeed;

            level.sendParticles(
                    PVZParticles.SPORE.get(),
                    spawnPos.x, spawnPos.y, spawnPos.z,
                    0,
                    vx, vy, vz,
                    1.0D
            );
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && target.distanceTo(this) <= beamLength() + 2.0F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    public void tick() {
        super.tick();

        this.setXRot(0.0F);
        this.xRotO = 0.0F;
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    public void setupAnimationStates() {
        this.idleAnimationState.startIfStopped(this.tickCount);
    }

    @Override
    public void handleStartShootEvent() {
        this.shootAnimationState.start(this.tickCount);
    }

    private float beamLength() {
        return this.plantStatFloat("beam_length", 10.0D);
    }

    private float beamRadius() {
        return this.plantStatFloat("beam_radius", 1.25D);
    }

    private float beamDamage() {
        return this.plantStatFloat("beam_damage", 2.0D);
    }
}
