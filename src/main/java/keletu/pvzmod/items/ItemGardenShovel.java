package keletu.pvzmod.items;

import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.init.PVZBlocks;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.models.render.GardenShovelRender;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ItemGardenShovel extends ShovelItem {

    public ItemGardenShovel(Properties properties) {
        super(Tiers.IRON, 3.0F, -2.4F, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        BlockPos checkPos = blockpos.above();
        AABB checkBox = new AABB(checkPos).inflate(0, 1, 0);
        if (blockstate.getBlock() == PVZBlocks.POT.get() && level.getEntitiesOfClass(EntityPlantBase.class, checkBox).isEmpty()) {
            level.destroyBlock(blockpos, false);
            return InteractionResult.SUCCESS;
        } else {
            return super.useOn(context);
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof EntityPlantBase target) {
            if (entity.level().isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                BlockPos pos = BlockPos.containing(entity.position());
                if (target.getOwnerUUID() == null || target.getOwnerUUID().equals(playerIn.getUUID())) {
                    target.remove(Entity.RemovalReason.DISCARDED);
                    playerIn.level().playSound(null, pos, PVZSounds.REMOVE.get(), SoundSource.PLAYERS);
                    ((ServerLevel) playerIn.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()),
                            pos.getX() + 0.5,
                            pos.getY(),
                            pos.getZ() + 0.5,
                            20,
                            0.25D,
                            0.25D,
                            0.25D,
                            0.15D);

                    if (!playerIn.isCreative())
                        stack.hurtAndBreak(1, playerIn, (p_43122_) -> {
                            p_43122_.broadcastBreakEvent(hand);
                        });
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final GardenShovelRender renderer = new GardenShovelRender();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }
}
