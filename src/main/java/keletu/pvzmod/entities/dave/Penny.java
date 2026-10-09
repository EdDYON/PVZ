package keletu.pvzmod.entities.dave;

import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.penny.PennyTradeMenu;
import keletu.pvzmod.penny.PennyTradeOffer;
import keletu.pvzmod.penny.PennyTradeOffers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.ArrayList;

public class Penny extends PathfinderMob implements Npc, Merchant, IPlantWontHurt {
    private static final String DAILY_TRADE_DAY_KEY = "PennyDailyTradeDay";
    private static final String DAILY_TRADES_KEY = "PennyDailyTrades";
    private static final String OFFER_STACK_KEY = "Stack";
    private static final String OFFER_PRICE_KEY = "Price";
    private static final String PLAYER_DAILY_TRADE_KEY = "PVZPennyDailyTrade";
    private static final String PLAYER_DAY_KEY = "Day";
    private static final String PLAYER_BOUGHT_MASK_KEY = "BoughtMask";


    private MerchantOffers trades;
    private @Nullable Player customer;
    private int pennyXp;
    private List<PennyTradeOffer> currentPlantTrades = List.of();
    private long dailyTradeDay = Long.MIN_VALUE;
    public final AnimationState idleAnimation = new AnimationState();

    public Penny(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(false);
        //this.goalSelector.addGoal(3, new DaveTradeGoal(this));
        //this.goalSelector.addGoal(3, new DaveLookAtTradePlayer(this));
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
        this.playSound(PVZSounds.PENNY_SPEAK.get(), this.getSoundVolume(), this.getVoicePitch());
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return this.pennyXp;
    }

    @Override
    public void overrideXp(int i) {
        this.pennyXp = i;
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
        return PVZSounds.PENNY_SPEAK.get();
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return PVZSounds.PENNY_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected AABB makeBoundingBox() {
        double xSize = 3.2D;
        double ySize = 4.0D;
        double zSize = 8.0D;

        return new AABB(this.getX() - xSize / 2.0D, this.getY(), this.getZ() - zSize / 2.0D, this.getX() + xSize / 2.0D, this.getY() + ySize, this.getZ() + zSize / 2.0D);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong(DAILY_TRADE_DAY_KEY, this.dailyTradeDay);

        ListTag offersTag = new ListTag();
        for (PennyTradeOffer offer : this.currentPlantTrades) {
            CompoundTag offerTag = new CompoundTag();
            offerTag.put(OFFER_STACK_KEY, offer.stack().save(new CompoundTag()));
            offerTag.putInt(OFFER_PRICE_KEY, offer.price());
            offersTag.add(offerTag);
        }
        compound.put(DAILY_TRADES_KEY, offersTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.dailyTradeDay = compound.contains(DAILY_TRADE_DAY_KEY) ? compound.getLong(DAILY_TRADE_DAY_KEY) : Long.MIN_VALUE;

        List<PennyTradeOffer> loadedOffers = new ArrayList<>();
        ListTag offersTag = compound.getList(DAILY_TRADES_KEY, Tag.TAG_COMPOUND);
        for (int i = 0; i < offersTag.size(); i++) {
            CompoundTag offerTag = offersTag.getCompound(i);
            ItemStack stack = ItemStack.of(offerTag.getCompound(OFFER_STACK_KEY));
            if (!stack.isEmpty()) {
                loadedOffers.add(new PennyTradeOffer(stack, offerTag.getInt(OFFER_PRICE_KEY)));
            }
        }
        this.currentPlantTrades = loadedOffers.size() == PennyTradeOffers.OFFER_COUNT ? loadedOffers : List.of();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes().add(Attributes.KNOCKBACK_RESISTANCE, 10.0F).add(Attributes.MAX_HEALTH, 100.0F);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.isTrading() && !player.isShiftKeyDown() && this.getTarget() != player) {
            if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                this.setTradingPlayer(player);
                List<PennyTradeOffer> visibleOffers = this.getDailyTradeOffers(serverPlayer);
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, menuPlayer) -> new PennyTradeMenu(containerId, inventory, this, visibleOffers), Component.translatable("screen.pvz_myh.penny.title")), buffer -> {
                    buffer.writeVarInt(this.getId());
                    PennyTradeOffer.encodeList(visibleOffers, buffer);
                });
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.idleAnimation.startIfStopped(this.tickCount);
    }

    public List<PennyTradeOffer> getDailyTradeOffers(ServerPlayer player) {
        this.refreshDailyTradeOffersIfNeeded();
        List<PennyTradeOffer> visibleOffers = new ArrayList<>(this.currentPlantTrades.size());
        for (int i = 0; i < this.currentPlantTrades.size(); i++) {
            PennyTradeOffer offer = this.currentPlantTrades.get(i);
            visibleOffers.add(this.hasBoughtDailyTrade(player, i) ? new PennyTradeOffer(ItemStack.EMPTY, offer.price()) : offer);
        }
        return visibleOffers;
    }

    public long getDailyTradeDay() {
        this.refreshDailyTradeOffersIfNeeded();
        return this.dailyTradeDay;
    }

    public boolean hasBoughtDailyTrade(ServerPlayer player, int index) {
        if (index < 0 || index >= PennyTradeOffers.OFFER_COUNT) {
            return true;
        }
        return (this.getPlayerDailyTradeData(player).getInt(PLAYER_BOUGHT_MASK_KEY) & (1 << index)) != 0;
    }

    public void markDailyTradeBought(ServerPlayer player, int index) {
        if (index < 0 || index >= PennyTradeOffers.OFFER_COUNT) {
            return;
        }

        CompoundTag data = this.getPlayerDailyTradeData(player);
        data.putInt(PLAYER_BOUGHT_MASK_KEY, data.getInt(PLAYER_BOUGHT_MASK_KEY) | (1 << index));
    }

    public void refreshDailyTradeOffersIfNeeded() {
        long currentDay = this.getCurrentGameDay();
        if (this.dailyTradeDay == currentDay && this.currentPlantTrades.size() == PennyTradeOffers.OFFER_COUNT) {
            return;
        }

        this.dailyTradeDay = currentDay;
        this.currentPlantTrades = PennyTradeOffers.roll(this.getRandom());
    }

    private CompoundTag getPlayerDailyTradeData(ServerPlayer player) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag data = persistentData.getCompound(PLAYER_DAILY_TRADE_KEY);
        long currentDay = this.getCurrentGameDay();

        if (!data.contains(PLAYER_DAY_KEY) || data.getLong(PLAYER_DAY_KEY) != currentDay) {
            data = new CompoundTag();
            data.putLong(PLAYER_DAY_KEY, currentDay);
            data.putInt(PLAYER_BOUGHT_MASK_KEY, 0);
        }

        persistentData.put(PLAYER_DAILY_TRADE_KEY, data);
        return data;
    }

    private long getCurrentGameDay() {
        return Math.max(0L, this.level().getDayTime() / 24000L);
    }
}
