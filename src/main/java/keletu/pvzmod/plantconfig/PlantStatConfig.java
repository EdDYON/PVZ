package keletu.pvzmod.plantconfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlantStatConfig {
    public String name;
    public LinkedHashMap<String, Double> values = new LinkedHashMap<>();

    public PlantStatConfig() {
    }

    public PlantStatConfig(String name) {
        this.name = name;
    }

    public PlantStatConfig put(String key, double value) {
        this.values.put(key, value);
        return this;
    }

    public double get(String key, double fallback) {
        if (this.values == null) {
            return fallback;
        }
        Double value = this.values.get(key);
        return value == null || !Double.isFinite(value) ? fallback : value;
    }

    public PlantStatConfig copy() {
        PlantStatConfig copy = new PlantStatConfig(this.name);
        if (this.values != null) {
            copy.values.putAll(this.values);
        }
        return copy;
    }

    public void mergeMissingFrom(PlantStatConfig defaults) {
        if (this.name == null || this.name.isBlank()) {
            this.name = defaults.name;
        }
        if (this.values == null) {
            this.values = new LinkedHashMap<>();
        }
        for (Map.Entry<String, Double> entry : defaults.values.entrySet()) {
            this.values.putIfAbsent(entry.getKey(), entry.getValue());
        }
    }
}
