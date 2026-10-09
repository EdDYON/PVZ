package keletu.pvzmod.blocks;

import keletu.pvzmod.box.PlantBoxInfo;
import keletu.pvzmod.box.PlantBoxManager;
import keletu.pvzmod.init.PVZBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlantBoxBlockEntity extends BlockEntity {
    private PlantBoxInfo boxInfo = PlantBoxManager.primaryBox();

    public PlantBoxBlockEntity(BlockPos pos, BlockState state) {
        super(PVZBlocks.PLANT_BOX_BLOCK_ENTITY.get(), pos, state);
    }

    public PlantBoxInfo getBoxInfo() {
        return this.boxInfo == null ? PlantBoxManager.primaryBox() : this.boxInfo;
    }

    public void setBoxInfo(PlantBoxInfo boxInfo) {
        this.boxInfo = boxInfo == null ? PlantBoxManager.primaryBox() : boxInfo;
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(PlantBoxInfo.TAG_NAME, this.getBoxInfo().save());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(PlantBoxInfo.TAG_NAME)) {
            this.boxInfo = PlantBoxInfo.load(tag.getCompound(PlantBoxInfo.TAG_NAME));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.put(PlantBoxInfo.TAG_NAME, this.getBoxInfo().save());
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        CompoundTag tag = packet.getTag();
        if (tag != null) {
            load(tag);
        }
    }
}
