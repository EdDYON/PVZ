package keletu.pvzmod.plantconfig.client;

import keletu.pvzmod.plantconfig.PlantStatConfig;
import net.minecraft.client.Minecraft;

import java.util.LinkedHashMap;
import java.util.Map;

public final class PlantStatsConfigClient {
    private PlantStatsConfigClient() {
    }

    public static void open(Map<String, PlantStatConfig> configs) {
        Minecraft.getInstance().setScreen(new PlantStatsConfigScreen(new LinkedHashMap<>(configs)));
    }
}
