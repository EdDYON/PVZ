package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZEffects;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class BonkChoy extends EntityPlantBase {

    public final AnimationState stand = new AnimationState();
    //public final AnimationState blink = new AnimationState();

    public final AnimationState hit = new AnimationState();

    public final AnimationState hit2 = new AnimationState();

    private static final EntityDataAccessor<Integer> ATTACK_ANIMATION =
            SynchedEntityData.defineId(BonkChoy.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> ATTACK_ANIMATION_TICK =
            SynchedEntityData.defineId(BonkChoy.class, EntityDataSerializers.INT);

    private int attackCooldown = 0;

    private static final double ATTACK_RANGE = 1.5D;

    private static final float ATTACK_DAMAGE = 4.0F;

    private static final int ATTACK_ANIMATION_DURATION = 10;

    private static final float UPPERCUT_CHANCE = 0.20F;

    public BonkChoy(EntityType<? extends EntityPlantBase> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ATTACK_ANIMATION, 0);
        this.entityData.define(ATTACK_ANIMATION_TICK, 0);
    }

    @Override
    public void tick() {
        super.tick();
        this.setXRot(0.0F);
        this.xRotO = 0.0F;
        this.tickAnimationState();


        if (!this.level().isClientSide) {
            Monster target = this.findNearestMonster();
            if (target != null) {
                this.faceTarget(target);
            }
            this.serverTickAttack();
        }
    }

    private void serverTickAttack() {
        if (!this.isAlive())
            return;

        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }

        int animTick = this.entityData.get(ATTACK_ANIMATION_TICK);
        if (animTick > 0) {
            this.entityData.set(ATTACK_ANIMATION_TICK, animTick - 1);
        } else {
            this.entityData.set(ATTACK_ANIMATION, 0);
        }

        if (this.attackCooldown > 0) {
            return;
        }

        Monster target = this.findNearestMonster();

        if (target == null) {
            return;
        }

        this.attackMonster(target);

        this.attackCooldown = attackCooldownTicks();
    }

    private Monster findNearestMonster() {
        AABB attackBox = this.getBoundingBox().inflate(attackRange());

        List<Monster> monsters = this.level().getEntitiesOfClass(
                Monster.class,
                attackBox,
                monster -> monster.isAlive() && !monster.isSpectator() && this.canAttack(monster)
        );

        if (monsters.isEmpty()) {
            return null;
        }

        return monsters.stream()
                .min(Comparator.comparingDouble(monster -> monster.distanceToSqr(this)))
                .orElse(null);
    }

    private void attackMonster(Monster target) {
        float attackDamage = attackDamage();
        boolean willKill = target.getHealth() <= attackDamage;

        boolean uppercut = willKill || this.random.nextFloat() * 100.0F < uppercutChancePercent();

        if (uppercut) {
            this.playAttackAnimation(2);
        } else {
            this.playAttackAnimation(1);
        }

        DamageSource source = this.damageSources().mobAttack(this);


        Vec3 oldMotion = target.getDeltaMovement();

        boolean oldHasImpulse = target.hasImpulse;
        boolean oldHurtMarked = target.hurtMarked;

        boolean hurt = target.hurt(source, attackDamage);

        target.invulnerableTime = 0;

        target.setDeltaMovement(oldMotion);
        target.hasImpulse = oldHasImpulse;
        target.hurtMarked = oldHurtMarked;

        if (hurt && uppercut) {
            this.uppercutMonster(target);
            target.addEffect(new MobEffectInstance(PVZEffects.STUN.get(), uppercutStunTicks(), 0));
            this.playSound(PVZSounds.BONKCHOY_HIT2.get(), 1.0F, 1.0F);
        } else {
            this.playSound(PVZSounds.BONKCHOY_HIT.get(), 1.0F, 1.0F);
        }
    }

    private void uppercutMonster(Monster target) {
        Vec3 motion = target.getDeltaMovement();

        target.setDeltaMovement(
                motion.x,
                uppercutVerticalVelocity(),
                motion.z
        );

        target.hasImpulse = true;
        target.hurtMarked = true;
    }

    private void playAttackAnimation(int type) {
        this.entityData.set(ATTACK_ANIMATION, type);
        this.entityData.set(ATTACK_ANIMATION_TICK, ATTACK_ANIMATION_DURATION);
    }

    private double attackRange() {
        return this.plantStatDouble(PlantStatManager.ATTACK_RANGE, ATTACK_RANGE);
    }

    private float attackDamage() {
        return this.plantStatFloat(PlantStatManager.PROJECTILE_DAMAGE, ATTACK_DAMAGE);
    }

    private int attackCooldownTicks() {
        return this.plantStatInt(PlantStatManager.COOLDOWN_TICKS, ATTACK_ANIMATION_DURATION, 1, 72000);
    }

    private float uppercutChancePercent() {
        return this.plantStatFloat("uppercut_chance_percent", UPPERCUT_CHANCE * 100.0F);
    }

    private int uppercutStunTicks() {
        return this.plantStatInt("uppercut_stun_ticks", 10, 1, 72000);
    }

    private double uppercutVerticalVelocity() {
        return this.plantStatDouble("uppercut_vertical_velocity", 0.55D);
    }

    private void tickAnimationState() {
        int attackAnim = this.entityData.get(ATTACK_ANIMATION);
        int attackTick = this.entityData.get(ATTACK_ANIMATION_TICK);

        if (attackTick > 0) {
            this.stand.stop();

            if (attackAnim == 1) {
                this.hit2.stop();

                if (!this.hit.isStarted()) {
                    this.hit.start(this.tickCount);
                }
            } else if (attackAnim == 2) {
                this.hit.stop();

                if (!this.hit2.isStarted()) {
                    this.hit2.start(this.tickCount);
                }
            }
        } else {
            this.hit.stop();
            this.hit2.stop();

            if (!this.stand.isStarted()) {
                this.stand.start(this.tickCount);
            }
        }

        /*if (this.level().isClientSide) {
            if (!this.blink.isStarted() && this.random.nextInt(100) == 0) {
                this.blink.start(this.tickCount);
            }

            if (this.blink.isStarted() && this.tickCount % 40 == 0) {
                this.blink.stop();
            }
        }*/
    }
}
