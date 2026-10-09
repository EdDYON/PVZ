package keletu.pvzmod.network;

import keletu.pvzmod.PVZMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PVZNetworking {
    private static final String PROTOCOL_VERSION = "1";
    private static int messageId;
    private static SimpleChannel channel;

    private PVZNetworking() {
    }

    public static void register() {
        if (channel != null) {
            return;
        }

        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(PVZMod.MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        channel.registerMessage(
                nextId(),
                OpenPlantBoxRequestPacket.class,
                OpenPlantBoxRequestPacket::encode,
                OpenPlantBoxRequestPacket::decode,
                OpenPlantBoxRequestPacket::handle
        );
        channel.registerMessage(
                nextId(),
                StartPlantBoxOpenPacket.class,
                StartPlantBoxOpenPacket::encode,
                StartPlantBoxOpenPacket::decode,
                StartPlantBoxOpenPacket::handle
        );
        channel.registerMessage(
                nextId(),
                OpenPlantStatsConfigPacket.class,
                OpenPlantStatsConfigPacket::encode,
                OpenPlantStatsConfigPacket::decode,
                OpenPlantStatsConfigPacket::handle
        );
        channel.registerMessage(
                nextId(),
                SavePlantStatsConfigPacket.class,
                SavePlantStatsConfigPacket::encode,
                SavePlantStatsConfigPacket::decode,
                SavePlantStatsConfigPacket::handle
        );
        channel.registerMessage(
                nextId(),
                BuyPennyTradePacket.class,
                BuyPennyTradePacket::encode,
                BuyPennyTradePacket::decode,
                BuyPennyTradePacket::handle
        );
        channel.registerMessage(
                nextId(),
                SyncPennyTradeOffersPacket.class,
                SyncPennyTradeOffersPacket::encode,
                SyncPennyTradeOffersPacket::decode,
                SyncPennyTradeOffersPacket::handle
        );
    }

    public static void sendToPlayer(Object packet, ServerPlayer player) {
        if (channel != null) {
            channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
        }
    }

    public static void sendToServer(Object packet) {
        if (channel != null) {
            channel.sendToServer(packet);
        }
    }

    private static int nextId() {
        return messageId++;
    }
}
