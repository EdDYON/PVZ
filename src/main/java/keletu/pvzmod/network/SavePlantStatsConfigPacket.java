package keletu.pvzmod.network;

import keletu.pvzmod.plantconfig.PlantStatConfig;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SavePlantStatsConfigPacket {
    private final LinkedHashMap<String, PlantStatConfig> configs;

    public SavePlantStatsConfigPacket(Map<String, PlantStatConfig> configs) {
        this.configs = new LinkedHashMap<>(configs);
    }

    public static void encode(SavePlantStatsConfigPacket packet, FriendlyByteBuf buffer) {
        PlantStatConfigPacketCodec.encode(buffer, packet.configs);
    }

    public static SavePlantStatsConfigPacket decode(FriendlyByteBuf buffer) {
        return new SavePlantStatsConfigPacket(PlantStatConfigPacketCodec.decode(buffer));
    }

    public static void handle(SavePlantStatsConfigPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || !player.hasPermissions(2)) {
                return;
            }
            PlantStatManager.replaceAndSave(packet.configs);
            if (player.server != null) {
                PlantStatManager.applyToLoadedPlants(player.server);
            }
            player.displayClientMessage(Component.literal("植物数值配置已保存"), false);
            PVZNetworking.sendToPlayer(new OpenPlantStatsConfigPacket(PlantStatManager.snapshot()), player);
        });
        context.setPacketHandled(true);
    }
}
