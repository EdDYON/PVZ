package keletu.pvzmod.network;

import keletu.pvzmod.penny.PennyTradeOffer;
import keletu.pvzmod.penny.client.PennyTradeClientSync;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class SyncPennyTradeOffersPacket {
    public static final int MODE_REPLACE = 0;

    private final List<PennyTradeOffer> offers;
    private final int mode;

    public SyncPennyTradeOffersPacket(List<PennyTradeOffer> offers, int mode) {
        this.offers = List.copyOf(offers);
        this.mode = mode;
    }

    public static void encode(SyncPennyTradeOffersPacket packet, FriendlyByteBuf buffer) {
        buffer.writeVarInt(packet.mode);
        PennyTradeOffer.encodeList(packet.offers, buffer);
    }

    public static SyncPennyTradeOffersPacket decode(FriendlyByteBuf buffer) {
        int mode = buffer.readVarInt();
        return new SyncPennyTradeOffersPacket(PennyTradeOffer.decodeList(buffer), mode);
    }

    public static void handle(SyncPennyTradeOffersPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PennyTradeClientSync.apply(packet.offers, packet.mode)));
        context.setPacketHandled(true);
    }
}
