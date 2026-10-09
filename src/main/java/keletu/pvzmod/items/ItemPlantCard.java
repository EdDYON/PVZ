package keletu.pvzmod.items;

import keletu.pvzmod.entities.*;
import keletu.pvzmod.init.PVZBlocks;
import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.function.Supplier;

public class ItemPlantCard extends Item {
    private final Supplier<? extends EntityType<?>> entityTypeSupplier;

    public ItemPlantCard(Properties properties, Supplier<? extends EntityType<?>> entityTypeSupplier) {
        super(properties);
        this.entityTypeSupplier = entityTypeSupplier;
    }

    public Entity createPlantEntity(Level level) {
        return this.entityTypeSupplier.get().create(level);
    }

    private EntityType<? extends EntityPlantShooterBase> getSuperGatlingFusionType() {
        if (this == PVZItems.SNOWPEA_CARD.get()) {
            return PVZEntities.SUPER_SNOW_GATLING_PEA.get();
        }

        if (this == PVZItems.PRIMAL_PEASHOOTER_CARD.get()) {
            return PVZEntities.SUPER_PRIMAL_GATLING_PEA.get();
        }

        if (this == PVZItems.ELECTRIC_PEASHOOTER_CARD.get()) {
            return PVZEntities.SUPER_ELECTRIC_GATLING_PEA.get();
        }

        return null;
    }

    private static void applyPlantYaw(Entity entity, float yaw) {
        entity.setYRot(yaw);
        entity.setYBodyRot(yaw);
        entity.setYHeadRot(yaw);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        EntityType<? extends EntityPlantShooterBase> fusionType = getSuperGatlingFusionType();

        if (entity instanceof SuperGatlingPea target && fusionType != null) {
            if (entity.level().isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                if (target.getType() == fusionType) {
                    return InteractionResult.FAIL;
                }

                BlockPos pos = BlockPos.containing(entity.position());
                if (target.getOwnerUUID() == null || target.getOwnerUUID().equals(player.getUUID())) {
                    target.remove(Entity.RemovalReason.DISCARDED);
                    player.level().playSound(null, player.blockPosition(), PVZSounds.PLANT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    ((ServerLevel) player.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()),
                            pos.getX() + 0.5,
                            pos.getY(),
                            pos.getZ() + 0.5,
                            20,
                            0.25D,
                            0.25D,
                            0.25D,
                            0.15D);


                    EntityPlantShooterBase newOne = fusionType.create(player.level());

                    if (newOne == null)
                        return InteractionResult.FAIL;

                    double spawnX = target.getX();
                    double spawnY = target.getY();
                    double spawnZ = target.getZ();
                    float yaw = player.getYRot();

                    newOne.moveTo(spawnX, spawnY, spawnZ, yaw, 0.0F);
                    applyPlantYaw(newOne, yaw);

                    player.level().addFreshEntity(newOne);

                    newOne.setOwnerUUID(player.getUUID());

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        } else if (entity instanceof EntityPlantBase target && entityTypeSupplier == PVZEntities.PUMPKIN && !target.isPassenger() && !(target instanceof Pumpkin)) {
            if (entity.level().isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                BlockPos pos = BlockPos.containing(entity.position());
                if (target.getOwnerUUID() == null || target.getOwnerUUID().equals(player.getUUID())) {
                    player.level().playSound(null, player.blockPosition(), PVZSounds.PLANT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    ((ServerLevel) player.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()),
                            pos.getX() + 0.5,
                            pos.getY(),
                            pos.getZ() + 0.5,
                            20,
                            0.25D,
                            0.25D,
                            0.25D,
                            0.15D);


                    Pumpkin pumpkin = (Pumpkin) entityTypeSupplier.get().create(entity.level());

                    if (pumpkin == null)
                        return InteractionResult.FAIL;

                    double spawnX = target.getX();
                    double spawnY = target.getY();
                    double spawnZ = target.getZ();
                    float yaw = player.getYRot();

                    pumpkin.moveTo(spawnX, spawnY, spawnZ, yaw, 0.0F);
                    applyPlantYaw(pumpkin, yaw);

                    player.level().addFreshEntity(pumpkin);

                    pumpkin.setOwnerUUID(player.getUUID());
                    target.startRiding(pumpkin);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        } else if (entity instanceof Pumpkin target && entityTypeSupplier != PVZEntities.PUMPKIN && target.getFirstPassenger() == null) {
            if (entity.level().isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                BlockPos pos = BlockPos.containing(entity.position());
                if (target.getOwnerUUID() == null || target.getOwnerUUID().equals(player.getUUID())) {
                    player.level().playSound(null, player.blockPosition(), PVZSounds.PLANT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    ((ServerLevel) player.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()),
                            pos.getX() + 0.5,
                            pos.getY(),
                            pos.getZ() + 0.5,
                            20,
                            0.25D,
                            0.25D,
                            0.25D,
                            0.15D);


                    EntityPlantBase targetPlant = (EntityPlantBase) entityTypeSupplier.get().create(entity.level());

                    if (targetPlant == null)
                        return InteractionResult.FAIL;

                    double spawnX = target.getX();
                    double spawnY = target.getY();
                    double spawnZ = target.getZ();
                    float yaw = player.getYRot();

                    targetPlant.moveTo(spawnX, spawnY, spawnZ, yaw, 0.0F);
                    applyPlantYaw(targetPlant, yaw);

                    player.level().addFreshEntity(targetPlant);

                    targetPlant.setOwnerUUID(player.getUUID());
                    targetPlant.startRiding(target);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        if (!player.mayUseItemAt(clickedPos, context.getClickedFace(), itemStack)) {
            return InteractionResult.FAIL;
        }

        BlockState clickedState = level.getBlockState(clickedPos);
        BlockPos spawnPos = clickedPos.above((int) Math.ceil(entityTypeSupplier.get().getHeight()));
        BlockPos checkPos = clickedPos.above();

        if (!level.getBlockState(spawnPos).isAir() && !level.getBlockState(spawnPos).canBeReplaced()) {
            return InteractionResult.PASS;
        }

        //PvZBlocks.ENDOWED_GRASS
        boolean isEndowedGrass = clickedState.is(Blocks.GRASS_BLOCK);
        boolean isWaterLily = clickedState.is(Blocks.LILY_PAD);
        boolean isFlowerPot = clickedState.is(PVZBlocks.POT.get());

        if (isEndowedGrass || (isWaterLily) || isFlowerPot) {
            AABB checkBox = new AABB(checkPos).inflate(0, 1, 0);
            if (!level.getEntitiesOfClass(EntityPlantBase.class, checkBox).isEmpty()) {
                return InteractionResult.FAIL;
            }

            if (!level.isClientSide) {
                Entity entity = entityTypeSupplier.get().create(level);
                if (entity instanceof EntityPlantBase base) {
                    if (isWaterLily && entity instanceof PotatoMine) {
                        return InteractionResult.FAIL;
                    }
                    player.level().playSound(null, player.blockPosition(), PVZSounds.PLANT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                    double spawnX;
                    double spawnY;
                    double spawnZ;

                    if (isEndowedGrass) {
                        spawnX = clickedPos.getX() + 0.5D;
                        spawnY = clickedPos.getY() + 1.0D;
                        spawnZ = clickedPos.getZ() + 0.5D;
                        level.setBlockAndUpdate(clickedPos, Blocks.GRASS_BLOCK.defaultBlockState());
                    } else {
                        spawnX = clickedPos.getX() + 0.5D;
                        spawnY = clickedPos.getY() + 0.5D;
                        spawnZ = clickedPos.getZ() + 0.5D;
                    }

                    float yaw = player.getYRot();

                    entity.moveTo(spawnX, spawnY, spawnZ, yaw, 0.0F);
                    applyPlantYaw(entity, yaw);

                    level.addFreshEntity(entity);

                    base.setOwnerUUID(player.getUUID());

                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}
