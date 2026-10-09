package keletu.pvzmod.models.render;

import keletu.pvzmod.blocks.LootChestBlock;
import keletu.pvzmod.blocks.LootChestBlockEntity;
import keletu.pvzmod.models.gecko.PVZGeoModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LootChestBlockRenderer extends GeoBlockRenderer<LootChestBlockEntity> {
    public LootChestBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new PVZGeoModel<>());
    }

    @Override
    protected Direction getFacing(LootChestBlockEntity blockEntity) {
        BlockState state = blockEntity.getBlockState();
        return state.hasProperty(LootChestBlock.FACING) ? state.getValue(LootChestBlock.FACING) : Direction.NORTH;
    }
}
