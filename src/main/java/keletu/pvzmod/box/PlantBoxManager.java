package keletu.pvzmod.box;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.init.PVZItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PlantBoxManager {
    private static final int[] LEGACY_GRADE_WEIGHTS = new int[]{3, 8, 24, 75, 300};
    private static final int LEGACY_JUNK_WEIGHT = 420;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type BOX_LIST_TYPE = new TypeToken<List<PlantBoxInfo>>() {
    }.getType();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(PVZMod.MODID);
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("plant_boxes.json");

    private static List<PlantBoxInfo> boxes = new ArrayList<>();

    private PlantBoxManager() {
    }

    public static void loadOrCreateDefaults() {
        try {
            if (!Files.isDirectory(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
            }

            if (!Files.exists(CONFIG_FILE)) {
                writeDefaults();
            }

            try (Reader reader = Files.newBufferedReader(CONFIG_FILE, StandardCharsets.UTF_8)) {
                List<PlantBoxInfo> loaded = GSON.fromJson(reader, BOX_LIST_TYPE);
                boxes = loaded == null || loaded.isEmpty() ? defaultBoxes() : loaded;
                boxes.forEach(PlantBoxManager::fillMissingDefaults);
            }
        } catch (Exception exception) {
            PVZMod.LOGGER.warn("Failed to load plant box config, using defaults", exception);
            boxes = defaultBoxes();
        }
    }

    public static List<PlantBoxInfo> boxes() {
        if (boxes.isEmpty()) {
            boxes = defaultBoxes();
        }
        return boxes;
    }

    public static PlantBoxInfo primaryBox() {
        return boxes().get(0);
    }

    public static ItemStack createBoxStack(PlantBoxInfo info) {
        ItemStack stack = new ItemStack(PVZItems.PLANT_BOX.get());
        ItemPlantBox.setBoxInfo(info, stack);
        return stack;
    }

    private static void writeDefaults() throws Exception {
        try (Writer writer = Files.newBufferedWriter(CONFIG_FILE, StandardCharsets.UTF_8)) {
            GSON.toJson(defaultBoxes(), BOX_LIST_TYPE, writer);
        }
    }

    private static void fillMissingDefaults(PlantBoxInfo info) {
        if (info.junkWeight <= 0 || info.junkWeight == LEGACY_JUNK_WEIGHT) {
            info.junkWeight = PlantBoxInfo.DEFAULT_JUNK_WEIGHT;
        }
        if (info.junk == null || info.junk.isEmpty()) {
            info.junk = PlantBoxInfo.defaultJunkRewards();
        }
        if (info.gradeWeights == null || info.gradeWeights.length == 0 || Arrays.equals(info.gradeWeights, LEGACY_GRADE_WEIGHTS)) {
            info.gradeWeights = PlantBoxInfo.DEFAULT_GRADE_WEIGHTS.clone();
        }
    }

    private static List<PlantBoxInfo> defaultBoxes() {
        PlantBoxInfo info = new PlantBoxInfo();
        info.boxName = "Plant Supply Box";
        info.boxKey = "pvz_myh:plant_key";
        info.boxDropChance = 0.12F;
        info.keyDropChance = 0.22F;
        info.gradeWeights = PlantBoxInfo.DEFAULT_GRADE_WEIGHTS.clone();
        info.junkWeight = PlantBoxInfo.DEFAULT_JUNK_WEIGHT;

        info.dropEntities = List.of(
                "minecraft:zombie",
                "minecraft:skeleton",
                "minecraft:husk",
                "minecraft:drowned",
                "minecraft:stray",
                "minecraft:zombie_villager"
        );

        info.junk = PlantBoxInfo.defaultJunkRewards();

        info.grade1 = List.of(
                "pvz_myh:sun_flower_card",
                "pvz_myh:peashooter_card",
                "pvz_myh:puff_shroom_card",
                "pvz_myh:walnut_card"
        );
        info.grade2 = List.of(
                "pvz_myh:snowpea_card",
                "pvz_myh:repeater_card",
                "pvz_myh:potato_mine_card",
                "pvz_myh:scaredy_shroom_card"
        );
        info.grade3 = List.of(
                "pvz_myh:gatling_pea_card",
                "pvz_myh:fume_shroom_card",
                "pvz_myh:pumpkin_card"
        );
        info.grade4 = List.of(
                "pvz_myh:primal_peashooter_card",
                "pvz_myh:electric_peashooter_card",
                "pvz_myh:tall_nut_card"
        );
        info.grade5 = List.of(
                "pvz_myh:super_gatling_pea_card",
                "pvz_myh:energy_bean"
        );

        return new ArrayList<>(List.of(info));
    }
}
