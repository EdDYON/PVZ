package keletu.pvzmod.entities.dave;

import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.entities.ai.DaveLookAtTradePlayer;
import keletu.pvzmod.entities.ai.DaveTradeGoal;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Dave extends PathfinderMob implements Npc, Merchant, IPlantWontHurt {

    private static final EntityDataAccessor<Boolean> DATA_ACTIVE_MOVING =
            SynchedEntityData.defineId(Dave.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_SPEAK_TICKS =
            SynchedEntityData.defineId(Dave.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BLINK =
            SynchedEntityData.defineId(Dave.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_PANICKING =
            SynchedEntityData.defineId(Dave.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState stand = new AnimationState();

    public final AnimationState walk = new AnimationState();

    public final AnimationState run = new AnimationState();
    public final AnimationState blink = new AnimationState();

    public final AnimationState speak = new AnimationState();
    public final AnimationState speak2 = new AnimationState();
    public final AnimationState longSpeak = new AnimationState();

    private MerchantOffers trades;
    private @Nullable Player customer;
    private int daveXp;
    private AvoidEntityGoal avoidUndeadGoal;

    public Dave(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ACTIVE_MOVING, false);
        this.entityData.define(DATA_SPEAK_TICKS, 0);
        this.entityData.define(BLINK, 0);
        this.entityData.define(DATA_PANICKING, false);
    }

    public boolean isLongSpeaking() {
        return this.entityData.get(DATA_SPEAK_TICKS) > 20;
    }

    public boolean isSpeaking() {
        return this.entityData.get(DATA_SPEAK_TICKS) > 0;
    }

    public void startSpeaking(int ticks) {
        this.entityData.set(DATA_SPEAK_TICKS, ticks);
    }

    public int getBlinkTime() {
        return this.entityData.get(BLINK);
    }

    public void setBlinkTime(int ticks) {
        this.entityData.set(BLINK, ticks);
    }

    public boolean isActivelyMoving() {
        return this.entityData.get(DATA_ACTIVE_MOVING);
    }

    public boolean isPanicking() {
        return this.entityData.get(DATA_PANICKING);
    }

    private boolean calculateActiveMoving() {
        boolean hasMoveIntent =
                !this.getNavigation().isDone()
                        || this.getMoveControl().hasWanted();

        boolean isActuallyMoving =
                this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;

        return hasMoveIntent && isActuallyMoving;
    }

    @Override
    protected void registerGoals() {
        this.avoidUndeadGoal = new AvoidEntityGoal<>(this, Monster.class, 6.0F, 1.0D, 1.0D, (entity) -> entity.getMobType() == MobType.UNDEAD);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, avoidUndeadGoal);
        this.goalSelector.addGoal(3, new DaveTradeGoal(this));
        this.goalSelector.addGoal(3, new DaveLookAtTradePlayer(this));
        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.customer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.customer;
    }

    public boolean isTrading() {
        return this.getTradingPlayer() != null;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.trades == null) {
            this.trades = new MerchantOffers();
        }

        return this.trades;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {
        this.trades = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer merchantOffer) {
        this.playSound(PVZSounds.DAVE_TRADE.get(), this.getSoundVolume(), this.getVoicePitch());
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return this.daveXp;
    }

    @Override
    public void overrideXp(int i) {
        this.daveXp = i;
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public boolean canRestock() {
        return true;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return PVZSounds.DAVE_TRADE.get();
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return PVZSounds.DAVE_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return PVZSounds.DAVE_HURT.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.MAX_HEALTH, 30);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.isTrading() && !player.isShiftKeyDown() && this.getTarget() != player) {
            if (!this.level().isClientSide) {
                this.setTradingPlayer(player);
                if (!this.isSpeaking()) {
                    if (this.random.nextInt(10) != 0) {
                        this.startSpeaking(20);
                        this.playSound(PVZSounds.DAVE_SPEAK.get(), 10.0F, 1.0F);
                    } else {
                        this.startSpeaking(60);
                        this.playSound(PVZSounds.DAVE_SPEAK_LONG.get(), 10.0F, 1.0F);
                    }
                }
                this.openTradingScreen(player, this.getDisplayName(), 0);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            this.entityData.set(DATA_ACTIVE_MOVING, this.calculateActiveMoving());

            boolean panicking = this.avoidUndeadGoal != null && this.avoidUndeadGoal.toAvoid != null;
            this.entityData.set(DATA_PANICKING, panicking);

            int speakTicks = this.entityData.get(DATA_SPEAK_TICKS);
            if (speakTicks == 21) {
                this.entityData.set(DATA_SPEAK_TICKS, 0);
            } else if (speakTicks > 0) {
                this.entityData.set(DATA_SPEAK_TICKS, speakTicks - 1);
            }
        }

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupBlinkAnimation() {
        if (this.getBlinkTime() > 0) {
            this.setBlinkTime(this.getBlinkTime() - 1);
            return;
        }

        this.blink.start(this.tickCount);

        this.setBlinkTime(60 + this.random.nextInt(80));
    }

    private void setupAnimationStates() {
        setupBlinkAnimation();

        if (this.isPanicking()) {
            this.stand.stop();
            this.walk.stop();
            this.speak.stop();
            this.speak2.stop();
            this.longSpeak.stop();

            this.run.startIfStopped(this.tickCount);
            return;
        }

        this.run.stop();

        if (this.isLongSpeaking()) {
            this.stand.stop();
            this.walk.stop();
            this.longSpeak.startIfStopped(this.tickCount);
            return;
        }

        if (this.isSpeaking()) {
            this.stand.stop();
            this.walk.stop();
            if (this.random.nextInt(2) == 0 && !this.speak2.isStarted()) {
                this.speak.startIfStopped(this.tickCount);
            } else if (!this.speak.isStarted()) {
                this.speak2.startIfStopped(this.tickCount);
            }
            return;
        }

        this.speak.stop();
        this.speak2.stop();
        this.longSpeak.stop();

        if (this.isActivelyMoving()) {
            this.stand.stop();
            this.walk.startIfStopped(this.tickCount);
        } else {
            this.walk.stop();
            this.stand.startIfStopped(this.tickCount);
        }
    }
}
