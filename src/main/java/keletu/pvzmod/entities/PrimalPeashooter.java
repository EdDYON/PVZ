package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.TrueRangedAttackGoal;
import keletu.pvzmod.entities.projectile.PrimalPeaProjectile;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PrimalPeashooter extends EntityPlantShooterBase {
    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState blinkAnimation = new AnimationState();
    public final AnimationState shootAnimation = new AnimationState();
    private int blinkTime = 0;

    public PrimalPeashooter(EntityType<? extends EntityPlantShooterBase> entityType, Level par1World) {
        super(entityType, par1World, new ItemStack(PVZItems.PRIMAL_PEASHOOTER_CARD.get()));
    }

    @Override
    protected TrueRangedAttackGoal createRangedAttackGoal() {
        return new TrueRangedAttackGoal(this, 0.0F, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, this.range), 1, 2, 55, 25);
    }

    @Override
    public SoundEvent getShootSound() {
        return PVZSounds.PRIMALPEASHOOTER_SHOOT.get();
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public ThrowableProjectile entitySelect(Level world) {
        PrimalPeaProjectile ent = new PrimalPeaProjectile(
                world,
                this,
                this.plantStatFloat(PlantStatManager.PROJECTILE_DAMAGE, 3.0D),
                this.random.nextFloat() * 100.0F < this.plantStatFloat("special_chance_percent", 15.0D) ? 1 : 0
        );
        return ent;
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
