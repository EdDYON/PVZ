package keletu.pvzmod.blocks;

import keletu.pvzmod.init.PVZBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PotBlockEntity extends BlockEntity {
    public PotBlockEntity(BlockPos pos, BlockState state) {
        super(PVZBlocks.POT_BLOCK_ENTITY.get(), pos, state);
    }
}
