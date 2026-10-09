package keletu.pvzmod.network;

import keletu.pvzmod.blocks.PlantBoxBlockEntity;
import keletu.pvzmod.box.ItemPlantBox;
import keletu.pvzmod.box.PlantBoxInfo;
import keletu.pvzmod.init.PVZBlocks;
import keletu.pvzmod.init.PVZItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class OpenPlantBoxRequestPacket {
    private final boolean placed;
    private final BlockPos pos;

    public OpenPlantBoxRequestPacket(boolean placed, BlockPos pos) {
        this.placed = placed;
        this.pos = pos == null ? BlockPos.ZERO : pos.immutable();
    }

    public static void encode(OpenPlantBoxRequestPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBoolean(packet.placed);
        buffer.writeBlockPos(packet.pos);
    }

    public static OpenPlantBoxRequestPacket decode(FriendlyByteBuf buffer) {
        return new OpenPlantBoxRequestPacket(buffer.readBoolean(), buffer.readBlockPos());
    }

    public static void handle(OpenPlantBoxRequestPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) {
                return;
            }
            packet.handleServer(player);
        });
        context.setPacketHandled(true);
    }

    private void handleServer(ServerPlayer player) {
        Level level = player.level();
        PlantBoxInfo info;
        ItemStack heldBox = player.getMainHandItem();

        if (this.placed) {
            if (!level.isLoaded(this.pos) || player.distanceToSqr(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) > 64.0D) {
                return;
            }
            BlockState state = level.getBlockState(this.pos);
            if (!state.is(PVZBlocks.PLANT_BOX.get())) {
                return;
            }
            BlockEntity blockEntity = level.getBlockEntity(this.pos);
            if (!(blockEntity instanceof PlantBoxBlockEntity plantBoxBlockEntity)) {
                return;
            }
            info = plantBoxBlockEntity.getBoxInfo();
        } else {
            if (!heldBox.is(PVZItems.PLANT_BOX.get())) {
                return;
            }
            info = ItemPlantBox.getInfoOrDefault(heldBox);
        }

        List<ItemPlantBox.PlantBoxReward> rewards = ItemPlantBox.rollOpeningRewards(info, player.getRandom());
        if (rewards.isEmpty()) {
            player.displayClientMessage(Component.translatable("message.pvz_myh.plant_box.empty"), true);
            return;
        }

        if (!ItemPlantBox.consumeKey(player, info.boxKey)) {
            ItemPlantBox.failNoKey(level, player);
            return;
        }

        if (this.placed) {
            level.removeBlock(this.pos, false);
        } else if (!player.getAbilities().instabuild) {
            heldBox.shrink(1);
        }

        int resultIndex = Math.min(45, rewards.size() - 1);
        ItemPlantBox.giveReward(level, player, rewards.get(resultIndex));
        PVZNetworking.sendToPlayer(StartPlantBoxOpenPacket.fromRewards(rewards), player);
    }
}
