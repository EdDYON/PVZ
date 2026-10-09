package keletu.pvzmod.network;

import keletu.pvzmod.penny.PennyTradeMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BuyPennyTradePacket {
    private final int offerIndex;

    public BuyPennyTradePacket(int offerIndex) {
        this.offerIndex = offerIndex;
    }

    public static void encode(BuyPennyTradePacket packet, FriendlyByteBuf buffer) {
        buffer.writeVarInt(packet.offerIndex);
    }

    public static BuyPennyTradePacket decode(FriendlyByteBuf buffer) {
        return new BuyPennyTradePacket(buffer.readVarInt());
    }

    public static void handle(BuyPennyTradePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof PennyTradeMenu menu) {
                menu.buy(player, packet.offerIndex);
            }
        });
        context.setPacketHandled(true);
    }
}
