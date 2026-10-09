package keletu.pvzmod.network;

import keletu.pvzmod.plantconfig.PlantStatConfig;
import keletu.pvzmod.plantconfig.client.PlantStatsConfigClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OpenPlantStatsConfigPacket {
    private final LinkedHashMap<String, PlantStatConfig> configs;

    public OpenPlantStatsConfigPacket(Map<String, PlantStatConfig> configs) {
        this.configs = new LinkedHashMap<>(configs);
    }

    public static void encode(OpenPlantStatsConfigPacket packet, FriendlyByteBuf buffer) {
        PlantStatConfigPacketCodec.encode(buffer, packet.configs);
    }

    public static OpenPlantStatsConfigPacket decode(FriendlyByteBuf buffer) {
        return new OpenPlantStatsConfigPacket(PlantStatConfigPacketCodec.decode(buffer));
    }

    public static void handle(OpenPlantStatsConfigPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(
                Dist.CLIENT,
                () -> () -> PlantStatsConfigClient.open(packet.configs)
        ));
        context.setPacketHandled(true);
    }
}
