package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Pumpkin extends EntityPlantBase implements IProtectPlant {

    public final AnimationState idleAnimation = new AnimationState();
    private final double protectRadio = 8;
    private static final int RETARGET_INTERVAL = 10;

    public Pumpkin(EntityType<? extends EntityPlantBase> type, Level par1World) {
        super(type, par1World, new ItemStack(PVZItems.WALNUT_CARD.get(), 1));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityPlantBase.createAttributes().add(Attributes.MAX_HEALTH, 10.0D * 35);
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(false);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        if (this.level().isClientSide || this.tickCount % retargetInterval() != 0) {
            return;
        }

        List<EntityPlantBase> entityPlants = this.level().getEntitiesOfClass(EntityPlantBase.class, this.getBoundingBox(), entityPlantBase -> !(entityPlantBase instanceof Pumpkin));

        for (EntityPlantBase base : entityPlants) {
            if (!base.isPassenger() && this.getFirstPassenger() == null && base != this) {
                base.startRiding(this);
            }
        }

        double protectRadius = protectRadius();
        AABB searchBox = new AABB(
                this.getX() - protectRadius, this.getY(), this.getZ() - protectRadius,
                this.getX() + protectRadius, this.getY() + 1.0D, this.getZ() + protectRadius
        );

        List<Mob> nearbyMobs = this.level().getEntitiesOfClass(Mob.class, searchBox);

        for (Mob mob : nearbyMobs) {
            if (this.getFirstPassenger() != null && mob.getTarget() == this.getFirstPassenger() && !(this.getFirstPassenger() instanceof Pumpkin)) {
                mob.setTarget(this);
            }
        }
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof Mob) {
            entity.push(this);
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.1D;
    }

    public void setupAnimationStates() {
        this.idleAnimation.startIfStopped(this.tickCount);
    }

    private double protectRadius() {
        return this.plantStatDouble("protect_radius", this.protectRadio);
    }

    private int retargetInterval() {
        return this.plantStatInt("retarget_interval_ticks", RETARGET_INTERVAL, 1, 72000);
    }
}
