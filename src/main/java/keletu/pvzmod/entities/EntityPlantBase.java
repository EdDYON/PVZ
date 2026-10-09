package keletu.pvzmod.entities;

import keletu.pvzmod.entities.ai.JumpControlNoJumping;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityPlantBase extends AbstractGolem implements OwnableEntity, IPlantWontHurt {
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(EntityPlantBase.class, EntityDataSerializers.OPTIONAL_UUID);
    public ItemStack spawnStack;
    public float range = 10.0F;
    private boolean configuredStatsApplied;

    public EntityPlantBase(EntityType<? extends EntityPlantBase> entityType, Level level) {
        super(entityType, level);
    }

    public EntityPlantBase(EntityType<? extends EntityPlantBase> entityType, Level level, ItemStack spawnStack) {
        this(entityType, level);
        this.spawnStack = spawnStack;
    }

    @Override
    public float maxUpStep() {
        return 0.0F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.getOwnerUUID() != null) {
            pCompound.putUUID("Owner", this.getOwnerUUID());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        UUID uuid;
        if (pCompound.hasUUID("Owner")) {
            uuid = pCompound.getUUID("Owner");
        } else {
            String s = pCompound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }

        if (uuid != null) {
            this.setOwnerUUID(uuid);
        }
    }

    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID pUuid) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(pUuid));
    }

    public boolean isOwnedBy(LivingEntity pEntity) {
        return pEntity == this.getOwner();
    }

    @Override
    public JumpControl getJumpControl() {
        //Cannot Jump.
        return new JumpControlNoJumping(this);
    }

    @Override
    protected void registerGoals() {
        this.getNavigation().setCanFloat(false); // setAvoidsWater
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0F);
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof Mob) {
            entity.push(this);
        }
    }

    @Override
    protected void playStepSound(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) {
    }

    @Override
    public MobType getMobType() {
        return /*PvZ.PLANT_TYPE*/ MobType.UNDEFINED;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void tick() {
        if (!this.configuredStatsApplied) {
            PlantStatManager.applyTo(this);
            this.configuredStatsApplied = true;
        }

        this.zza = 0.0F;
        this.xxa = 0.0F;
        this.setSpeed(0.0F);

        super.tick();
    }

    @Override
    public void knockback(double strength, double x, double z) {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && !this.isOwnedBy(target) && !(target instanceof Creeper) && !(target instanceof Ghast);
    }

    @Override
    public boolean isNoAi() {
        return false;
    }

    public boolean wantsToAttack(LivingEntity pTarget, LivingEntity pOwner) {
        if (!(pTarget instanceof Creeper) && !(pTarget instanceof Ghast)) {
            if (pTarget instanceof IPlantWontHurt) {
                return false;
            } else if (pTarget instanceof Player && pOwner instanceof Player && !((Player) pOwner).canHarmPlayer((Player) pTarget)) {
                return false;
            } else if (pTarget instanceof AbstractHorse && ((AbstractHorse) pTarget).isTamed()) {
                return false;
            } else {
                return !(pTarget instanceof TamableAnimal) || !((TamableAnimal) pTarget).isTame();
            }
        } else {
            return false;
        }
    }

    protected void setPlantYaw(float yaw) {
        float wrappedYaw = Mth.wrapDegrees(yaw);

        this.setYRot(wrappedYaw);
        this.yRotO = wrappedYaw;
        this.setYBodyRot(wrappedYaw);
        this.yBodyRotO = wrappedYaw;
        this.setYHeadRot(wrappedYaw);
        this.yHeadRotO = wrappedYaw;
    }

    public void faceTarget(@Nullable LivingEntity target) {
        if (target == null) {
            return;
        }

        double toX = target.getX() - this.getX();
        double toZ = target.getZ() - this.getZ();

        if (toX * toX + toZ * toZ < 1.0E-6D) {
            return;
        }

        float yaw = (float) (Mth.atan2(toZ, toX) * (180.0D / Math.PI)) - 90.0F;
        this.setPlantYaw(yaw);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        super.handleEntityEvent(pId);
        if (pId == 4) {
            this.handleStartShootEvent();
        }
    }

    /**
     * <p>可选重写。触发攻击动画
     */
    protected void handleStartShootEvent() {
    }

    public String plantStatsId() {
        ResourceLocation location = ForgeRegistries.ENTITY_TYPES.getKey(this.getType());
        return location == null ? "" : location.getPath();
    }

    public double plantStatDouble(String key, double fallback) {
        return PlantStatManager.value(this.plantStatsId(), key, fallback);
    }

    public float plantStatFloat(String key, double fallback) {
        return (float) this.plantStatDouble(key, fallback);
    }

    public int plantStatInt(String key, int fallback, int min, int max) {
        int value = Mth.floor(this.plantStatDouble(key, fallback) + 0.5D);
        return Mth.clamp(value, min, max);
    }
}
