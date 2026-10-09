package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.TrueRangedAttackGoal;
import keletu.pvzmod.entities.projectile.SporeProjectile;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class ScaredyShroom extends EntityPlantShooterBase {

    private static final EntityDataAccessor<Integer> SCARED_TIME = SynchedEntityData.defineId(ScaredyShroom.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_SCARED = SynchedEntityData.defineId(ScaredyShroom.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleAnimationStateSpecial = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();
    public final AnimationState hideAnimation = new AnimationState();
    public final AnimationState hiddenAnimation = new AnimationState();
    public final AnimationState standAnimation = new AnimationState();
    private int idleAnimationCooldown = 0;

    private enum ShroomState {
        IDLE, HIDING, HIDDEN, STANDING_UP
    }

    private ShroomState animationState = ShroomState.IDLE;
    private int hideTickCounter = -1;

    public ScaredyShroom(EntityType<? extends EntityPlantShooterBase> entityType, Level par1World) {
        super(entityType, par1World, new ItemStack(PVZItems.SCAREDY_SHROOM_CARD.get()));
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
        return new TrueRangedAttackGoal(this, 0.0F, this.plantStatFloat(PlantStatManager.ATTACK_RANGE, this.range), 1, 0, 30, 20);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCARED_TIME, 0);
        this.entityData.define(IS_SCARED, false);
    }

    public int getScaredTime() {
        return this.entityData.get(SCARED_TIME);
    }

    public void setScaredTime(int time) {
        this.entityData.set(SCARED_TIME, time);
    }

    public boolean isScared() {
        return this.entityData.get(IS_SCARED);
    }

    public void setScared(boolean isStart) {
        this.entityData.set(IS_SCARED, isStart);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("GrowTime", this.getScaredTime());
        pCompound.putBoolean("isStart", this.isScared());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setScaredTime(pCompound.getInt("GrowTime"));
        this.setScared(pCompound.getBoolean("isStart"));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide) {
            if (this.isScared() || this.getScaredTime() > 0) {
                return;
            }

            this.faceTarget(target);

            ThrowableProjectile projectile = entitySelect(this.level());
            projectile.setPos(this.getX(), this.getEyeY() - 0.78125F, this.getZ());

            double toX = target.getX() - this.getX();
            double toZ = target.getZ() - this.getZ();

            projectile.shoot(toX, 0, toZ, this.plantStatFloat(PlantStatManager.PROJECTILE_SPEED, 1.6D), 0.0F);

            this.playSound(PVZSounds.PUFF_SHROOM_SHOOT.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

            this.level().addFreshEntity(projectile);

            if (target instanceof PathfinderMob mob && (mob.getTarget() == null || !mob.getTarget().isAlive())) {
                mob.setTarget(this);
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && !hasMonsterWithinFearRange();
    }

    @Override
    public void tick() {
        super.tick();

        this.setXRot(0.0F);
        this.xRotO = 0.0F;

        updateScaredState();
        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void updateScaredState() {
        if (!this.level().isClientSide) {
            boolean hasMonsterNearby = hasMonsterWithinFearRange();

            int hideTicks = hideTicks();
            if ((hasMonsterNearby || this.getScaredTime() > 0) && this.getScaredTime() < hideTicks && !this.isScared()) {
                this.setScaredTime(this.getScaredTime() + 1);
                if (this.getScaredTime() == hideTicks) {
                    // 触发缩回
                    this.setScared(true);
                    this.level().broadcastEntityEvent(this, (byte) 5);
                }
            }

            if (!hasMonsterNearby && this.isScared()) {
                this.setScaredTime(this.getScaredTime() - 1);
                if (this.getScaredTime() == 0) {
                    // 触发弹起
                    this.setScared(false);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
            }
        }
    }

    private boolean hasMonsterWithinFearRange() {
        double halfSize = this.plantStatDouble("fear_range", 2.5D);
        AABB box = new AABB(
                this.getX() - halfSize, this.getY(), this.getZ() - halfSize,
                this.getX() + halfSize, this.getY() + halfSize, this.getZ() + halfSize
        );

        return !this.level().getEntitiesOfClass(Monster.class, box, Entity::isAlive).isEmpty();
    }

    public void setupIdleAnimation() {
        if (this.idleAnimationCooldown > 0) {
            this.idleAnimationCooldown--;
        } else {
            this.idleAnimationState.stop();
            this.idleAnimationStateSpecial.stop();

            if (this.random.nextInt(3) == 0) {
                this.idleAnimationStateSpecial.start(this.tickCount);
                this.idleAnimationCooldown = 40;
            } else {
                this.idleAnimationState.start(this.tickCount);
                this.idleAnimationCooldown = 20;
            }
        }
    }

    public void setupAnimationStates() {
        if (this.hiddenAnimation.isStarted() || this.hiddenAnimation.isStarted() || this.standAnimation.isStarted()) {
            this.idleAnimationState.stop();
            this.idleAnimationStateSpecial.stop();
            this.idleAnimationCooldown = 0;
        } else {
            setupIdleAnimation();
        }


        switch (animationState) {
            case HIDING -> {
                if (this.hideAnimation.isStarted()) {
                    this.hideTickCounter++;
                    if (this.hideTickCounter >= hideTicks()) {
                        animationState = ShroomState.HIDDEN;
                        this.hiddenAnimation.start(this.tickCount);
                        this.hideAnimation.stop();
                        this.shootAnimationState.stop();
                    }
                }
            }
            case STANDING_UP -> {
                if (this.standAnimation.isStarted()) {
                    this.hideTickCounter--;
                    if (this.hideTickCounter <= 0) {
                        animationState = ShroomState.IDLE;
                        this.standAnimation.stop();
                        this.idleAnimationState.start(this.tickCount);
                        this.shootAnimationState.stop();
                    }
                }
            }
            case HIDDEN -> {
                if (!this.isScared()) {
                    animationState = ShroomState.STANDING_UP;
                    this.standAnimation.start(this.tickCount);
                    this.hiddenAnimation.stop();
                    this.hideTickCounter = hideTicks();
                    this.shootAnimationState.stop();
                }
            }
            case IDLE -> {
                if (this.isScared()) {
                    animationState = ShroomState.HIDING;
                    this.hideAnimation.start(this.tickCount);
                    this.standAnimation.stop();
                    this.hideTickCounter = 0;
                    this.shootAnimationState.stop();
                }
            }
        }

    }

    @Override
    public void handleEntityEvent(byte pId) {
        super.handleEntityEvent(pId);
        if (pId == 5) { // 开始缩回
            animationState = ShroomState.HIDING;
            this.hideAnimation.start(this.tickCount);
            this.standAnimation.stop();
            this.hideTickCounter = 0;
        } else if (pId == 6) { // 开始弹起
            animationState = ShroomState.STANDING_UP;
            this.standAnimation.start(this.tickCount);
            this.hiddenAnimation.stop();
            this.hideAnimation.stop();
            this.hideTickCounter = hideTicks();
        }
    }


    @Override
    public void handleStartShootEvent() {
        this.shootAnimationState.start(this.tickCount);
    }

    private int hideTicks() {
        return this.plantStatInt("hide_ticks", 20, 1, 72000);
    }
}
