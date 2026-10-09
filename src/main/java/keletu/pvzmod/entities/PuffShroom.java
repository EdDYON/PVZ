package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.TrueRangedAttackGoal;
import keletu.pvzmod.entities.projectile.SporeProjectile;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PuffShroom extends EntityPlantShooterBase {

    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState blinkAnimation = new AnimationState();
    public final AnimationState shootAnimation = new AnimationState();

    private int blinkTime = 0;

    public PuffShroom(EntityType<? extends EntityPlantShooterBase> entityType, Level par1World) {
        super(entityType, par1World, new ItemStack(PVZItems.PUFF_SHROOM_CARD.get()));
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public ThrowableProjectile entitySelect(Level world) {
        SporeProjectile ent = new SporeProjectile(world, this, this.plantStatFloat(PlantStatManager.PROJECTILE_DAMAGE, 3.0D));
        return ent;
    }

    @Override
    protected TrueRangedAttackGoal createRangedAttackGoal() {
        return new TrueRangedAttackGoal(this, 0.0F, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, 8.0D), 1, 0, 30, 15);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide) {
            this.faceTarget(target);

            ThrowableProjectile projectile = entitySelect(this.level());
            projectile.setPos(this.getX(), this.getEyeY() - 0.6F, this.getZ());

            double toX = target.getX() - this.getX();
            double toZ = target.getZ() - this.getZ();

            projectile.shoot(toX, 0, toZ, this.plantStatFloat(PlantStatManager.PROJECTILE_SPEED, 1.6D), 0.0F);

            this.playSound(PVZSounds.PUFF_SHROOM_SHOOT.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

            this.level().addFreshEntity(projectile);

            if (target instanceof PathfinderMob && (((PathfinderMob) target).getTarget() == null || !((PathfinderMob) target).getTarget().isAlive())) {
                ((PathfinderMob) target).setTarget(this);
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0F);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && target.distanceTo(this) <= this.plantStatFloat(PlantStatManager.ATTACK_RANGE, 8.0D);
    }

    @Override
    public boolean hasAttackLineOfSight(LivingEntity target) {
        if (super.hasAttackLineOfSight(target)) {
            return true;
        }

        Vec3 from = new Vec3(this.getX(), this.getEyeY() - 0.6D, this.getZ());
        Vec3 to = new Vec3(target.getX(), target.getY() + Math.min(target.getBbHeight() * 0.45D, 0.8D), target.getZ());
        HitResult hitResult = this.level().clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        return hitResult.getType() == HitResult.Type.MISS;
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
        this.idleAnimation.startIfStopped(this.tickCount);
        setupBlinkAnimation();
    }

    @Override
    public void handleStartShootEvent() {
        this.shootAnimation.start(this.tickCount);
    }
}
