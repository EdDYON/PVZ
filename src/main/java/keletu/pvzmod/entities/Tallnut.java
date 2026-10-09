package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Tallnut extends Walnut {

    public final AnimationState idleAnimation0 = new AnimationState();
    public final AnimationState idleAnimation1 = new AnimationState();
    public final AnimationState idleAnimation2 = new AnimationState();
    private static final float PROTECT_RADIUS = 16.0F;

    public Tallnut(EntityType<? extends Walnut> type, Level par1World) {
        super(type, par1World, PROTECT_RADIUS);
        this.spawnStack = new ItemStack(PVZItems.TALL_NUT_CARD.get(), 1);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityPlantBase.createAttributes().add(Attributes.MAX_HEALTH, 20.0D * 21);
    }

    @Override
    protected float getStuckArrowModelHeight() {
        return 29.0F;
    }

    @Override
    public void setupAnimationStates() {
        if (this.getHealth() > this.getMaxHealth() * 2 / 3) {
            this.idleAnimation1.stop();
            this.idleAnimation2.stop();
            this.idleAnimation0.startIfStopped(this.tickCount);
        } else if (this.getHealth() > this.getMaxHealth() * 1 / 3 && this.getHealth() <= this.getMaxHealth() * 2 / 3) {
            this.idleAnimation0.stop();
            this.idleAnimation2.stop();
            this.idleAnimation1.startIfStopped(this.tickCount);
        } else {
            this.idleAnimation0.stop();
            this.idleAnimation1.stop();
            this.idleAnimation2.startIfStopped(this.tickCount);
        }
    }
}
