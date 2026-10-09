package keletu.pvzmod.plantconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.EntityPlantBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PlantStatManager {
    public static final String HEALTH = "health";
    public static final String FOLLOW_RANGE = "follow_range";
    public static final String ATTACK_RANGE = "attack_range";
    public static final String PROJECTILE_DAMAGE = "projectile_damage";
    public static final String PROJECTILE_SPEED = "projectile_speed";
    public static final String COOLDOWN_TICKS = "cooldown_ticks";
    public static final String FIRST_COOLDOWN_TICKS = "first_cooldown_ticks";
    public static final String BURST_COUNT = "burst_count";
    public static final String BURST_DELAY_TICKS = "burst_delay_ticks";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type CONFIG_TYPE = new TypeToken<LinkedHashMap<String, PlantStatConfig>>() {
    }.getType();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(PVZMod.MODID);
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("plant_stats.json");

    private static LinkedHashMap<String, PlantStatConfig> configs = defaultConfigs();

    private PlantStatManager() {
    }

    public static synchronized void loadOrCreateDefaults() {
        try {
            if (!Files.isDirectory(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
            }

            if (!Files.exists(CONFIG_FILE)) {
                write(defaultConfigs());
            }

            try (Reader reader = Files.newBufferedReader(CONFIG_FILE, StandardCharsets.UTF_8)) {
                LinkedHashMap<String, PlantStatConfig> loaded = GSON.fromJson(reader, CONFIG_TYPE);
                configs = normalize(loaded);
            }
            write(configs);
        } catch (Exception exception) {
            PVZMod.LOGGER.warn("Failed to load plant stat config, using defaults", exception);
            configs = defaultConfigs();
        }
    }

    public static synchronized LinkedHashMap<String, PlantStatConfig> snapshot() {
        return copy(configs);
    }

    public static synchronized void replaceAndSave(Map<String, PlantStatConfig> replacement) {
        configs = normalize(replacement);
        try {
            write(configs);
        } catch (Exception exception) {
            PVZMod.LOGGER.warn("Failed to save plant stat config", exception);
        }
    }

    public static synchronized void resetAndSave() {
        configs = defaultConfigs();
        try {
            write(configs);
        } catch (Exception exception) {
            PVZMod.LOGGER.warn("Failed to reset plant stat config", exception);
        }
    }

    public static synchronized LinkedHashMap<String, PlantStatConfig> defaultConfigs() {
        LinkedHashMap<String, PlantStatConfig> defaults = new LinkedHashMap<>();

        defaults.put("sun_flower", common("向日葵", 20.0D, 24.0D)
                .put("sun_interval_ticks", 400.0D)
                .put("sun_spawn_tick", 390.0D)
                .put("sun_animation_tick", 360.0D));

        defaults.put("pea_shooter", shooter("豌豆射手", 20.0D, 24.0D, 10.0D, 3.0D, 30.0D, 25.0D, 1.0D, 0.0D));
        defaults.put("snow_pea", shooter("寒冰射手", 20.0D, 24.0D, 10.0D, 3.0D, 30.0D, 25.0D, 1.0D, 0.0D)
                .put("snow_duration_ticks", 100.0D));
        defaults.put("repeater", shooter("双发射手", 20.0D, 24.0D, 10.0D, 3.0D, 30.0D, 25.0D, 2.0D, 2.0D));
        defaults.put("gatling_pea", shooter("机枪射手", 20.0D, 24.0D, 10.0D, 3.0D, 20.0D, 15.0D, 4.0D, 1.0D));
        defaults.put("primal_peashooter", shooter("原始豌豆射手", 20.0D, 24.0D, 10.0D, 3.0D, 55.0D, 25.0D, 1.0D, 2.0D)
                .put("special_chance_percent", 15.0D)
                .put("special_damage_multiplier", 2.0D)
                .put("primal_stun_chance_percent", 50.0D)
                .put("primal_stun_duration_ticks", 10.0D)
                .put("primal_knockback_strength", 2.5D));
        defaults.put("electric_peashooter", shooter("电击豌豆射手", 20.0D, 24.0D, 10.0D, 3.0D, 45.0D, 20.0D, 1.0D, 0.0D)
                .put("electric_shock_damage", 1.0D)
                .put("electric_shock_range", 2.0D)
                .put("electric_shock_chance_percent", 20.0D)
                .put("electric_shocked_duration_ticks", 20.0D));

        defaults.put("walnut", common("坚果墙", 160.0D, 24.0D)
                .put("protect_radius", 10.0D)
                .put("retarget_interval_ticks", 10.0D));
        defaults.put("tallnut", common("高坚果", 420.0D, 24.0D)
                .put("protect_radius", 16.0D)
                .put("retarget_interval_ticks", 10.0D));
        defaults.put("pumpkin", common("南瓜头", 350.0D, 24.0D)
                .put("protect_radius", 8.0D)
                .put("retarget_interval_ticks", 10.0D));

        PlantStatConfig superBase = shooter("超级机枪射手", 20.0D, 24.0D, 15.0D, 3.0D, 30.0D, 25.0D, 4.0D, 1.0D)
                .put("super_chance_percent", 2.0D)
                .put("super_duration_ticks", 40.0D)
                .put("super_cooldown_ticks", 300.0D)
                .put("super_min_shots", 3.0D)
                .put("super_extra_shots", 3.0D)
                .put("super_spread_degrees", 45.0D)
                .put("super_spread_offset_degrees", 7.5D)
                .put("super_projectile_speed", 1.2D);
        defaults.put("super_gatling_pea", superBase.copy());
        PlantStatConfig superSnow = superBase.copy();
        superSnow.name = "超级机枪射手（寒冰）";
        superSnow.put("snow_duration_ticks", 100.0D);
        defaults.put("super_snow_gatling_pea", superSnow);
        PlantStatConfig superPrimal = superBase.copy();
        superPrimal.name = "超级机枪射手（原始）";
        superPrimal.put("special_chance_percent", 15.0D)
                .put("special_damage_multiplier", 2.0D)
                .put("primal_stun_chance_percent", 50.0D)
                .put("primal_stun_duration_ticks", 10.0D)
                .put("primal_knockback_strength", 2.5D);
        defaults.put("super_primal_gatling_pea", superPrimal);
        PlantStatConfig superElectric = superBase.copy();
        superElectric.name = "超级机枪射手（电击）";
        superElectric.put(PROJECTILE_DAMAGE, 4.0D)
                .put("electric_shock_damage", 1.0D)
                .put("electric_shock_range", 2.0D)
                .put("electric_shock_chance_percent", 20.0D)
                .put("electric_shocked_duration_ticks", 20.0D);
        defaults.put("super_electric_gatling_pea", superElectric);

        defaults.put("potato_mine", common("土豆地雷", 20.0D, 24.0D)
                .put("grow_wait_ticks", 315.0D)
                .put("grow_animation_ticks", 20.0D)
                .put("trigger_radius", 1.0D)
                .put("explosion_fuse_ticks", 15.0D)
                .put("explosion_radius", 3.0D)
                .put("explosion_damage", 90.0D));
        defaults.put("bonkchoy", common("菜问", 20.0D, 24.0D)
                .put(ATTACK_RANGE, 1.5D)
                .put(PROJECTILE_DAMAGE, 4.0D)
                .put(COOLDOWN_TICKS, 10.0D)
                .put("uppercut_chance_percent", 20.0D)
                .put("uppercut_stun_ticks", 10.0D)
                .put("uppercut_vertical_velocity", 0.55D));
        defaults.put("puff_shroom", shooter("小喷菇", 10.0D, 16.0D, 8.0D, 3.0D, 30.0D, 15.0D, 1.0D, 0.0D));
        defaults.put("fume_shroom", shooter("大喷菇", 20.0D, 16.0D, 10.0D, 2.0D, 50.0D, 40.0D, 1.0D, 0.0D)
                .put("beam_length", 10.0D)
                .put("beam_radius", 1.25D)
                .put("beam_damage", 2.0D));
        defaults.put("scaredy_shroom", shooter("胆小菇", 20.0D, 24.0D, 10.0D, 3.0D, 30.0D, 20.0D, 1.0D, 0.0D)
                .put("fear_range", 2.5D)
                .put("hide_ticks", 20.0D));

        return copy(defaults);
    }

    public static double value(String plantId, String key, double fallback) {
        PlantStatConfig config = configs.get(plantId);
        if (config == null) {
            config = defaultConfigs().get(plantId);
        }
        return config == null ? fallback : config.get(key, fallback);
    }

    public static void applyTo(EntityPlantBase plant) {
        String id = plant.plantStatsId();
        double maxHealth = Math.max(1.0D, value(id, HEALTH, plant.getMaxHealth()));
        AttributeInstance health = plant.getAttribute(Attributes.MAX_HEALTH);
        if (health != null && Math.abs(health.getBaseValue() - maxHealth) > 1.0E-4D) {
            double oldMax = Math.max(1.0D, health.getBaseValue());
            float oldHealth = plant.getHealth();
            boolean wasFull = plant.tickCount <= 2 || oldHealth >= oldMax - 0.01D;
            health.setBaseValue(maxHealth);
            if (wasFull) {
                plant.setHealth((float) maxHealth);
            } else {
                float ratioHealth = (float) (oldHealth / oldMax * maxHealth);
                plant.setHealth(Math.max(1.0F, Math.min((float) maxHealth, ratioHealth)));
            }
        }

        double followRange = Math.max(0.0D, value(id, FOLLOW_RANGE, 24.0D));
        AttributeInstance follow = plant.getAttribute(Attributes.FOLLOW_RANGE);
        if (follow != null && Math.abs(follow.getBaseValue() - followRange) > 1.0E-4D) {
            follow.setBaseValue(followRange);
        }

        plant.range = (float) Math.max(0.0D, value(id, ATTACK_RANGE, plant.range));
    }

    public static void applyToLoadedPlants(MinecraftServer server) {
        for (ServerLevel level : server.getAllLevels()) {
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof EntityPlantBase plant) {
                    applyTo(plant);
                }
            }
        }
    }

    private static PlantStatConfig common(String name, double health, double followRange) {
        return new PlantStatConfig(name)
                .put(HEALTH, health)
                .put(FOLLOW_RANGE, followRange);
    }

    private static PlantStatConfig shooter(String name, double health, double followRange, double attackRange, double damage, double cooldown, double firstCooldown, double burstCount, double burstDelay) {
        return common(name, health, followRange)
                .put(ATTACK_RANGE, attackRange)
                .put(PROJECTILE_DAMAGE, damage)
                .put(PROJECTILE_SPEED, 1.6D)
                .put(COOLDOWN_TICKS, cooldown)
                .put(FIRST_COOLDOWN_TICKS, firstCooldown)
                .put(BURST_COUNT, burstCount)
                .put(BURST_DELAY_TICKS, burstDelay);
    }

    private static LinkedHashMap<String, PlantStatConfig> normalize(Map<String, PlantStatConfig> input) {
        LinkedHashMap<String, PlantStatConfig> defaults = defaultConfigs();
        LinkedHashMap<String, PlantStatConfig> normalized = copy(defaults);

        if (input != null) {
            for (Map.Entry<String, PlantStatConfig> entry : input.entrySet()) {
                String id = entry.getKey();
                if (id == null || id.isBlank() || entry.getValue() == null) {
                    continue;
                }
                PlantStatConfig cleaned = sanitize(entry.getValue());
                PlantStatConfig existingDefault = defaults.get(id);
                if (existingDefault != null) {
                    cleaned.mergeMissingFrom(existingDefault);
                }
                normalized.put(id, cleaned);
            }
        }

        for (Map.Entry<String, PlantStatConfig> entry : defaults.entrySet()) {
            PlantStatConfig config = normalized.get(entry.getKey());
            if (config == null) {
                normalized.put(entry.getKey(), entry.getValue().copy());
            } else {
                config.mergeMissingFrom(entry.getValue());
            }
        }
        return normalized;
    }

    private static PlantStatConfig sanitize(PlantStatConfig source) {
        PlantStatConfig cleaned = new PlantStatConfig(source.name);
        if (source.values == null) {
            return cleaned;
        }
        for (Map.Entry<String, Double> value : source.values.entrySet()) {
            if (value.getKey() == null || value.getKey().isBlank() || value.getValue() == null || !Double.isFinite(value.getValue())) {
                continue;
            }
            double numeric = Math.max(0.0D, value.getValue());
            if (value.getKey().contains("chance_percent")) {
                numeric = Math.min(100.0D, numeric);
            }
            if (HEALTH.equals(value.getKey())) {
                numeric = Math.max(1.0D, numeric);
            }
            cleaned.values.put(value.getKey(), numeric);
        }
        return cleaned;
    }

    private static LinkedHashMap<String, PlantStatConfig> copy(Map<String, PlantStatConfig> source) {
        LinkedHashMap<String, PlantStatConfig> copy = new LinkedHashMap<>();
        for (Map.Entry<String, PlantStatConfig> entry : source.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                copy.put(entry.getKey(), entry.getValue().copy());
            }
        }
        return copy;
    }

    private static void write(Map<String, PlantStatConfig> values) throws Exception {
        if (!Files.isDirectory(CONFIG_DIR)) {
            Files.createDirectories(CONFIG_DIR);
        }
        try (Writer writer = Files.newBufferedWriter(CONFIG_FILE, StandardCharsets.UTF_8)) {
            GSON.toJson(values, CONFIG_TYPE, writer);
        }
    }
}
