package keletu.pvzmod.entities.ai;

import keletu.pvzmod.entities.EntityPlantBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class PlanterHurtTargetGoal extends TargetGoal {
    private final EntityPlantBase tameAnimal;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public PlanterHurtTargetGoal(EntityPlantBase pTameAnimal) {
        super(pTameAnimal, false);
        this.tameAnimal = pTameAnimal;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {
        if (this.tameAnimal.getOwner() != null) {
            LivingEntity living = this.tameAnimal.getOwner();
            this.ownerLastHurt = living.getLastHurtMob();
            int i = living.getLastHurtMobTimestamp();
            return i != this.timestamp && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT) && this.tameAnimal.wantsToAttack(this.ownerLastHurt, living);
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        LivingEntity living = this.tameAnimal.getOwner();
        if (living != null) {
            this.timestamp = living.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
