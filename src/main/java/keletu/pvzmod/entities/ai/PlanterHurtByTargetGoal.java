package keletu.pvzmod.entities.ai;

import keletu.pvzmod.entities.EntityPlantBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class PlanterHurtByTargetGoal extends TargetGoal {
    private final EntityPlantBase tameAnimal;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public PlanterHurtByTargetGoal(EntityPlantBase pTameAnimal) {
        super(pTameAnimal, false);
        this.tameAnimal = pTameAnimal;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {
        if (targetMob != null && this.tameAnimal.getOwner() != null) {
            LivingEntity living = this.tameAnimal.getOwner();
            if (living == null) {
                return false;
            } else {
                this.ownerLastHurtBy = living.getLastHurtByMob();
                int t = living.getLastHurtByMobTimestamp();
                return t != this.timestamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT) && this.tameAnimal.wantsToAttack(this.ownerLastHurtBy, living);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurtBy);
        LivingEntity living = this.tameAnimal.getOwner();
        if (living != null) {
            this.timestamp = living.getLastHurtByMobTimestamp();
        }

        super.start();
    }
}
