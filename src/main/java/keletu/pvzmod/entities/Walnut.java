package keletu.pvzmod.entities;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Walnut extends EntityPlantBase implements IProtectPlant {

    private static final String STUCK_ARROW_RECORDS_KEY = "StuckArrowRecords";
    private static final EntityDataAccessor<String> DATA_STUCK_ARROW_RECORDS = SynchedEntityData.defineId(Walnut.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<ItemStack> HELMET_ARMOR = SynchedEntityData.defineId(Walnut.class, EntityDataSerializers.ITEM_STACK);
    private static final int MAX_STUCK_ARROW_RECORDS = 12;
    private static final double STUCK_ARROW_EPSILON = 1.0E-6D;
    private static final float STUCK_ARROW_SURFACE_INSET = 0.55F;
    public final AnimationState idleAnimation0 = new AnimationState();
    public final AnimationState idleAnimation1 = new AnimationState();
    public final AnimationState idleAnimation2 = new AnimationState();
    private final double protectRadio;
    private static final int RETARGET_INTERVAL = 10;
    private int lastRecordedArrowTick = Integer.MIN_VALUE;
    private String cachedStuckArrowRecordsRaw = "";
    private List<StuckArrowRecord> cachedStuckArrowRecords = List.of();

    public Walnut(EntityType<? extends EntityPlantBase> type, Level par1World, float protectRadio) {
        super(type, par1World, new ItemStack(PVZItems.WALNUT_CARD.get(), 1));
        this.protectRadio = protectRadio;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STUCK_ARROW_RECORDS, "");
        this.entityData.define(HELMET_ARMOR, ItemStack.EMPTY);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityPlantBase.createAttributes().add(Attributes.MAX_HEALTH, 10.0D * 16);
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
        } else {
            reconcileStuckArrowRecords();
        }

        if (this.level().isClientSide || this.tickCount % retargetInterval() != 0) {
            return;
        }

        double protectRadius = protectRadius();
        AABB searchBox = new AABB(
                this.getX() - protectRadius, this.getY(), this.getZ() - protectRadius,
                this.getX() + protectRadius, this.getY() + 1.0D, this.getZ() + protectRadius
        );

        List<Mob> nearbyMobs = this.level().getEntitiesOfClass(Mob.class, searchBox);

        for (Mob mob : nearbyMobs) {
            LivingEntity target = mob.getTarget();
            if (target instanceof IPlantWontHurt plant && !(plant instanceof IProtectPlant)) {
                mob.setTarget(this);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt && !this.level().isClientSide && source.getDirectEntity() instanceof AbstractArrow arrow) {
            recordStuckArrow(arrow);
        }
        return hurt;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        ListTag recordsTag = new ListTag();
        for (StuckArrowRecord record : this.getStuckArrowRecords()) {
            recordsTag.add(record.save());
        }
        pCompound.put(STUCK_ARROW_RECORDS_KEY, recordsTag);
    }

    @Override
    public void remove(RemovalReason reason) {
        if (reason.shouldDestroy()) {
            if (!this.level().isClientSide) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    this.spawnAtLocation(itemstack);
                    this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                }
            }
        }
        super.remove(reason);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains(STUCK_ARROW_RECORDS_KEY, Tag.TAG_LIST)) {
            this.setStuckArrowRecords(readStuckArrowRecords(pCompound.getList(STUCK_ARROW_RECORDS_KEY, Tag.TAG_COMPOUND)));
        } else if (pCompound.contains(STUCK_ARROW_RECORDS_KEY, Tag.TAG_STRING)) {
            this.setStuckArrowRecords(parseLegacyStuckArrowRecords(pCompound.getString(STUCK_ARROW_RECORDS_KEY)));
        } else {
            this.setStuckArrowRecords(List.of());
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (tryPlaceHelmet(player, hand, heldItem)) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (tryRemoveHelmet(player, hand, heldItem)) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    private boolean tryPlaceHelmet(Player player, InteractionHand hand, ItemStack heldItem) {
        if (heldItem.isEmpty()) {
            return false;
        }

        if (!(heldItem.getItem() instanceof ArmorItem armor) || armor.getType() != ArmorItem.Type.HELMET) {
            return false;
        }

        if (!this.level().isClientSide) {
            if (this.hasHelmet()) {
                ItemStack removed = this.removeHelmetItem();

                if (!player.addItem(removed)) {
                    player.drop(removed, false);
                }
            }

            ItemStack placed = heldItem.copy();
            placed.setCount(1);
            this.setItemSlot(EquipmentSlot.HEAD, placed);

            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }

            this.level().playSound(null, this.blockPosition(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return true;
    }

    private boolean tryRemoveHelmet(Player player, InteractionHand hand, ItemStack heldItem) {
        if (!heldItem.isEmpty()) {
            return false;
        }

        if (!this.hasHelmet()) {
            return false;
        }

        if (!this.level().isClientSide) {
            ItemStack removed = this.removeHelmetItem();

            if (!player.addItem(removed)) {
                player.drop(removed, false);
            }

            this.level().playSound(null, this.blockPosition(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 0.8F);
        }

        return true;
    }

    public boolean hasHelmet() {
        return !this.getItemBySlot(EquipmentSlot.HEAD).isEmpty();
    }

    public ItemStack removeHelmetItem() {
        ItemStack stack = this.getItemBySlot(EquipmentSlot.HEAD);
        this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        return stack;
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

    public List<StuckArrowRecord> getStuckArrowRecords() {
        String raw = this.entityData.get(DATA_STUCK_ARROW_RECORDS);
        if (!raw.equals(this.cachedStuckArrowRecordsRaw)) {
            this.cachedStuckArrowRecordsRaw = raw;
            this.cachedStuckArrowRecords = List.copyOf(parseLegacyStuckArrowRecords(raw));
        }
        return this.cachedStuckArrowRecords;
    }

    protected float getStuckArrowModelHeight() {
        return 20.0F;
    }

    protected float getStuckArrowModelHalfWidth() {
        return 7.0F;
    }

    protected float getStuckArrowModelHalfDepth() {
        return 6.0F;
    }

    private void recordStuckArrow(AbstractArrow arrow) {
        List<StuckArrowRecord> records = new ArrayList<>(getStuckArrowRecords());
        records.add(createStuckArrowRecord(arrow));
        this.lastRecordedArrowTick = this.tickCount;
        this.setStuckArrowRecords(limitStuckArrowRecords(records));
    }

    private StuckArrowRecord createStuckArrowRecord(AbstractArrow arrow) {
        Vec3 localHit = worldToLocal(arrow.position().subtract(this.position()));
        Vec3 motion = arrow.getDeltaMovement();
        Vec3 localMotion = motion.lengthSqr() > STUCK_ARROW_EPSILON
                ? worldToLocalDirection(motion.normalize())
                : new Vec3(0.0D, 0.0D, 1.0D);
        Vec3 modelDirection = normalizeOrFallback(new Vec3(localMotion.x, -localMotion.y, localMotion.z), new Vec3(0.0D, 0.0D, 1.0D));
        Vec3 modelHit = worldHitToModelSpace(localHit);
        Vec3 surfacePoint = projectModelPointToSurface(modelHit, modelDirection);

        return new StuckArrowRecord(
                (float) surfacePoint.x,
                (float) surfacePoint.y,
                (float) surfacePoint.z,
                (float) localMotion.x,
                (float) -localMotion.y,
                (float) localMotion.z
        );
    }

    private Vec3 worldToLocal(Vec3 vector) {
        return worldToLocalDirection(vector);
    }

    private Vec3 worldToLocalDirection(Vec3 vector) {
        float yaw = (this.getYRot() - 180.0F) * Mth.DEG_TO_RAD;
        double cos = Mth.cos(yaw);
        double sin = Mth.sin(yaw);
        double x = vector.x * cos - vector.z * sin;
        double z = vector.x * sin + vector.z * cos;
        return new Vec3(x, vector.y, z);
    }

    private Vec3 worldHitToModelSpace(Vec3 localHit) {
        float x = (float) Mth.clamp(localHit.x / (this.getBbWidth() * 0.5D) * getStuckArrowModelHalfWidth(), -getStuckArrowModelHalfWidth(), getStuckArrowModelHalfWidth());
        float y = (float) Mth.clamp(-(localHit.y / this.getBbHeight()) * getStuckArrowModelHeight(), -getStuckArrowModelHeight(), 0.0D);
        float z = (float) Mth.clamp(localHit.z / (this.getBbWidth() * 0.5D) * getStuckArrowModelHalfDepth(), -getStuckArrowModelHalfDepth(), getStuckArrowModelHalfDepth());
        return new Vec3(x, y, z);
    }

    private Vec3 projectModelPointToSurface(Vec3 modelPoint, Vec3 modelDirection) {
        double halfWidth = getStuckArrowModelHalfWidth();
        double halfDepth = getStuckArrowModelHalfDepth();
        double halfHeight = getStuckArrowModelHeight() * 0.5D;
        Vec3 centeredPoint = new Vec3(modelPoint.x, modelPoint.y + halfHeight, modelPoint.z);

        if (centeredPoint.lengthSqr() < STUCK_ARROW_EPSILON) {
            centeredPoint = new Vec3(modelDirection.x * halfWidth, modelDirection.y * halfHeight, modelDirection.z * halfDepth);
        }

        double scaleX = halfWidth / Math.max(Math.abs(centeredPoint.x), 0.0001D);
        double scaleY = halfHeight / Math.max(Math.abs(centeredPoint.y), 0.0001D);
        double scaleZ = halfDepth / Math.max(Math.abs(centeredPoint.z), 0.0001D);
        double scale = Math.min(scaleX, Math.min(scaleY, scaleZ));
        Vec3 shellPoint = centeredPoint.scale(scale);
        Vec3 inwardDirection = normalizeOrFallback(modelDirection, shellPoint.scale(-1.0D));
        Vec3 insetPoint = shellPoint.add(inwardDirection.scale(STUCK_ARROW_SURFACE_INSET));

        return clampModelPoint(new Vec3(insetPoint.x, insetPoint.y - halfHeight, insetPoint.z));
    }

    private Vec3 clampModelPoint(Vec3 point) {
        return new Vec3(
                Mth.clamp(point.x, -getStuckArrowModelHalfWidth(), getStuckArrowModelHalfWidth()),
                Mth.clamp(point.y, -getStuckArrowModelHeight(), 0.0D),
                Mth.clamp(point.z, -getStuckArrowModelHalfDepth(), getStuckArrowModelHalfDepth())
        );
    }

    private Vec3 normalizeOrFallback(Vec3 vector, Vec3 fallback) {
        if (vector.lengthSqr() > STUCK_ARROW_EPSILON) {
            return vector.normalize();
        }
        if (fallback.lengthSqr() > STUCK_ARROW_EPSILON) {
            return fallback.normalize();
        }
        return new Vec3(0.0D, 0.0D, 1.0D);
    }

    private void reconcileStuckArrowRecords() {
        List<StuckArrowRecord> records = new ArrayList<>(getStuckArrowRecords());
        if (records.isEmpty()) {
            return;
        }

        if (this.tickCount <= this.lastRecordedArrowTick + 1) {
            return;
        }

        int arrowCount = this.getArrowCount();
        if (arrowCount < 0) {
            return;
        }

        int allowedCount = Math.min(arrowCount, MAX_STUCK_ARROW_RECORDS);
        if (records.size() <= allowedCount) {
            return;
        }

        this.setStuckArrowRecords(new ArrayList<>(records.subList(records.size() - allowedCount, records.size())));
    }

    private List<StuckArrowRecord> readStuckArrowRecords(ListTag recordsTag) {
        List<StuckArrowRecord> records = new ArrayList<>();
        for (Tag tag : recordsTag) {
            if (tag instanceof CompoundTag recordTag) {
                StuckArrowRecord record = StuckArrowRecord.load(recordTag);
                if (record != null) {
                    records.add(record);
                }
            }
        }
        return limitStuckArrowRecords(records);
    }

    private List<StuckArrowRecord> parseLegacyStuckArrowRecords(String rawRecords) {
        if (rawRecords.isEmpty()) {
            return List.of();
        }

        List<StuckArrowRecord> result = new ArrayList<>();
        for (String rawRecord : rawRecords.split(";")) {
            StuckArrowRecord record = StuckArrowRecord.parse(rawRecord);
            if (record != null) {
                result.add(record);
            }
        }
        return limitStuckArrowRecords(result);
    }

    private List<StuckArrowRecord> limitStuckArrowRecords(List<StuckArrowRecord> records) {
        if (records.size() <= MAX_STUCK_ARROW_RECORDS) {
            return records;
        }
        return new ArrayList<>(records.subList(records.size() - MAX_STUCK_ARROW_RECORDS, records.size()));
    }

    private void setStuckArrowRecords(List<StuckArrowRecord> records) {
        List<StuckArrowRecord> normalizedRecords = List.copyOf(limitStuckArrowRecords(new ArrayList<>(records)));
        String raw = serializeStuckArrowRecords(normalizedRecords);
        this.cachedStuckArrowRecordsRaw = raw;
        this.cachedStuckArrowRecords = normalizedRecords;
        this.entityData.set(DATA_STUCK_ARROW_RECORDS, raw);
    }

    private String serializeStuckArrowRecords(List<StuckArrowRecord> records) {
        StringBuilder builder = new StringBuilder();
        for (StuckArrowRecord record : records) {
            if (!builder.isEmpty()) {
                builder.append(';');
            }
            builder.append(record.serialize());
        }
        return builder.toString();
    }

    private static String format(float value) {
        return String.format(Locale.ROOT, "%.3f", value);
    }

    public static class StuckArrowRecord {
        public final float x;
        public final float y;
        public final float z;
        public final float dirX;
        public final float dirY;
        public final float dirZ;

        public StuckArrowRecord(float x, float y, float z, float dirX, float dirY, float dirZ) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dirX = dirX;
            this.dirY = dirY;
            this.dirZ = dirZ;
        }

        public CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("X", this.x);
            tag.putFloat("Y", this.y);
            tag.putFloat("Z", this.z);
            tag.putFloat("DirX", this.dirX);
            tag.putFloat("DirY", this.dirY);
            tag.putFloat("DirZ", this.dirZ);
            return tag;
        }

        public String serialize() {
            return format(this.x) + ','
                    + format(this.y) + ','
                    + format(this.z) + ','
                    + format(this.dirX) + ','
                    + format(this.dirY) + ','
                    + format(this.dirZ);
        }

        public static StuckArrowRecord load(CompoundTag tag) {
            if (!tag.contains("X", Tag.TAG_FLOAT)
                    || !tag.contains("Y", Tag.TAG_FLOAT)
                    || !tag.contains("Z", Tag.TAG_FLOAT)
                    || !tag.contains("DirX", Tag.TAG_FLOAT)
                    || !tag.contains("DirY", Tag.TAG_FLOAT)
                    || !tag.contains("DirZ", Tag.TAG_FLOAT)) {
                return null;
            }

            return new StuckArrowRecord(
                    tag.getFloat("X"),
                    tag.getFloat("Y"),
                    tag.getFloat("Z"),
                    tag.getFloat("DirX"),
                    tag.getFloat("DirY"),
                    tag.getFloat("DirZ")
            );
        }

        public static StuckArrowRecord parse(String raw) {
            String[] parts = raw.split(",");
            if (parts.length != 6) {
                return null;
            }

            try {
                return new StuckArrowRecord(
                        Float.parseFloat(parts[0]),
                        Float.parseFloat(parts[1]),
                        Float.parseFloat(parts[2]),
                        Float.parseFloat(parts[3]),
                        Float.parseFloat(parts[4]),
                        Float.parseFloat(parts[5])
                );
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
    }

    private double protectRadius() {
        return this.plantStatDouble("protect_radius", this.protectRadio);
    }

    private int retargetInterval() {
        return this.plantStatInt("retarget_interval_ticks", RETARGET_INTERVAL, 1, 72000);
    }
}
