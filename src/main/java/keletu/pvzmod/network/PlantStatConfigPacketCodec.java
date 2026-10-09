package keletu.pvzmod.network;

import keletu.pvzmod.plantconfig.PlantStatConfig;
import net.minecraft.network.FriendlyByteBuf;

import java.util.LinkedHashMap;
import java.util.Map;

final class PlantStatConfigPacketCodec {
    private PlantStatConfigPacketCodec() {
    }

    static void encode(FriendlyByteBuf buffer, Map<String, PlantStatConfig> configs) {
        buffer.writeVarInt(configs.size());
        for (Map.Entry<String, PlantStatConfig> entry : configs.entrySet()) {
            PlantStatConfig config = entry.getValue();
            buffer.writeUtf(entry.getKey());
            buffer.writeUtf(config.name == null ? "" : config.name);
            int valueCount = config.values == null ? 0 : config.values.size();
            buffer.writeVarInt(valueCount);
            if (config.values != null) {
                for (Map.Entry<String, Double> value : config.values.entrySet()) {
                    buffer.writeUtf(value.getKey());
                    buffer.writeDouble(value.getValue() == null ? 0.0D : value.getValue());
                }
            }
        }
    }

    static LinkedHashMap<String, PlantStatConfig> decode(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        LinkedHashMap<String, PlantStatConfig> configs = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            String id = buffer.readUtf();
            PlantStatConfig config = new PlantStatConfig(buffer.readUtf());
            int valueCount = buffer.readVarInt();
            for (int valueIndex = 0; valueIndex < valueCount; valueIndex++) {
                config.values.put(buffer.readUtf(), buffer.readDouble());
            }
            configs.put(id, config);
        }
        return configs;
    }
}
