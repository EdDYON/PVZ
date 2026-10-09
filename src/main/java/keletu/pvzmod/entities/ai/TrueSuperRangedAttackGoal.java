package keletu.pvzmod.entities.ai;

import keletu.pvzmod.entities.SuperGatlingPea;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class TrueSuperRangedAttackGoal extends Goal {
    private final SuperGatlingPea mob;
    private LivingEntity target;
    private final double speedModifier;
    private int attackTime = -1;
    private final float attackRadius;
    private int seeTime;
    private final int burstCount;
    private final int burstDelay;
    private int remainingShots;
    private int burstTimer;
    private int cooldown;
    private int cooldown_first;

    public TrueSuperRangedAttackGoal(SuperGatlingPea attacker, double speedModifier, float attackRadius) {
        this(attacker, speedModifier, attackRadius, 1, 0, 30, 25);
    }

    public TrueSuperRangedAttackGoal(SuperGatlingPea attacker, double speedModifier, float attackRadius, int burstCount, int burstDelay, int cooldown, int cooldown_first) {
        if (attacker == null) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }

        this.cooldown = cooldown;
        this.cooldown_first = cooldown_first;
        this.mob = attacker;
        this.speedModifier = speedModifier;
        this.attackRadius = attackRadius;
        this.burstCount = Math.max(1, burstCount);
        this.burstDelay = Math.max(0, burstDelay);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            this.mob.setShooting(true);
            this.attackTime = cooldownFirst();
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.target == null || !this.target.isAlive()) {
            this.clearTargetAndStop();
            return false;
        }

        return true;
    }

    @Override
    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.attackTime = -1;
        this.remainingShots = 0;
        this.burstTimer = 0;
        this.mob.setShooting(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.target == null || !this.target.isAlive() || !this.mob.canAttack(this.target)) {
            this.clearTargetAndStop();
            return;
        }

        if (this.mob.isSuperFiring()) {
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            this.mob.setShooting(true);
            return;
        }

        double living = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean hasSight = this.mob.getSensing().hasLineOfSight(this.target);
        float currentAttackRadius = attackRadius();

        if (hasSight) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        if (!(living > (double) (currentAttackRadius * currentAttackRadius)) && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
        }

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        if (this.remainingShots > 0) {
            --this.burstTimer;
            if (this.burstTimer <= 0) {
                if (hasSight && this.target != null && this.target.isAlive()) {
                    float f2 = (float) Math.sqrt(living) / currentAttackRadius;
                    float f3 = Mth.clamp(f2, 0.1F, 1.0F);
                    this.mob.performRangedAttack(this.target, f3);
                }
                --this.remainingShots;

                if (this.remainingShots > 0) {
                    this.burstTimer = burstDelay();
                }
            }
        }

        if (attackTime == cooldownFirst()) {
            tryTriggerSuperRapidFire();
        }
        if (--this.attackTime == 0) {
            if (!hasSight) {
                return;
            }

            float f2 = (float) Math.sqrt(living) / currentAttackRadius;
            float f3 = Mth.clamp(f2, 0.1F, 1.0F);

            this.mob.playSound(this.mob.getShootSound(), 1.0F, 1.0F / (this.mob.getRandom().nextFloat() * 0.4F + 0.8F));
            this.mob.performRangedAttack(this.target, f3);

            this.remainingShots = burstCount() - 1;
            if (this.remainingShots > 0) {
                this.burstTimer = burstDelay();
            }

            this.attackTime = cooldown();
        } else if (this.attackTime < 0) {
            this.attackTime = cooldownFirst();
        }
    }

    private void clearTargetAndStop() {
        this.target = null;
        this.mob.setTarget(null);
        this.seeTime = 0;
        this.attackTime = -1;
        this.remainingShots = 0;
        this.burstTimer = 0;
        this.mob.setShooting(false);
    }

    private void tryTriggerSuperRapidFire() {
        if (this.mob.canTriggerSuperRapidFire() && this.mob.getRandom().nextFloat() < this.mob.superRapidFireChance()) {
            this.mob.triggerSuperRapidFire();
            this.mob.playSound(PVZSounds.SUPERGATLINGPEA_POWER.get(), 1.0F, 1.0F / (this.mob.getRandom().nextFloat() * 0.4F + 0.8F));
            this.mob.level().broadcastEntityEvent(this.mob, (byte) 5);
        } else {
            this.mob.level().broadcastEntityEvent(this.mob, (byte) 4);
        }
    }

    private float attackRadius() {
        return Math.max(0.1F, this.mob.plantStatFloat(PlantStatManager.ATTACK_RANGE, this.attackRadius));
    }

    private int burstCount() {
        return this.mob.plantStatInt(PlantStatManager.BURST_COUNT, this.burstCount, 1, 128);
    }

    private int burstDelay() {
        return this.mob.plantStatInt(PlantStatManager.BURST_DELAY_TICKS, this.burstDelay, 0, 72000);
    }

    private int cooldown() {
        return this.mob.plantStatInt(PlantStatManager.COOLDOWN_TICKS, this.cooldown, 1, 72000);
    }

    private int cooldownFirst() {
        return this.mob.plantStatInt(PlantStatManager.FIRST_COOLDOWN_TICKS, this.cooldown_first, 1, 72000);
    }
}
