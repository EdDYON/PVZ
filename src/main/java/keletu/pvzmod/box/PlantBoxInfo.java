package keletu.pvzmod.box;

import com.google.gson.annotations.SerializedName;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlantBoxInfo {
    public static final String TAG_NAME = "PlantBoxInfo";
    public static final int[] DEFAULT_GRADE_WEIGHTS = new int[]{5, 5, 10, 25, 50};
    public static final int DEFAULT_JUNK_WEIGHT = 5;
    private static final int[] LEGACY_GRADE_WEIGHTS = new int[]{3, 8, 24, 75, 300};
    private static final int LEGACY_JUNK_WEIGHT = 420;

    @SerializedName("name")
    public String boxName = "Plant Supply Box";

    @SerializedName("key")
    public String boxKey = "pvz_myh:plant_key";

    @SerializedName("drop")
    public float boxDropChance = 0.12F;

    @SerializedName("key_drop")
    public float keyDropChance = 0.22F;

    @SerializedName("random")
    public int[] gradeWeights = DEFAULT_GRADE_WEIGHTS.clone();

    @SerializedName("junk_weight")
    public int junkWeight = DEFAULT_JUNK_WEIGHT;

    @SerializedName("entity")
    public List<String> dropEntities = new ArrayList<>();

    @SerializedName("junk")
    public List<String> junk = new ArrayList<>();

    @SerializedName("grade1")
    public List<String> grade1 = new ArrayList<>();

    @SerializedName("grade2")
    public List<String> grade2 = new ArrayList<>();

    @SerializedName("grade3")
    public List<String> grade3 = new ArrayList<>();

    @SerializedName("grade4")
    public List<String> grade4 = new ArrayList<>();

    @SerializedName("grade5")
    public List<String> grade5 = new ArrayList<>();

    public List<String> rewardsForGrade(int grade) {
        return switch (grade) {
            case 0 -> this.junk == null || this.junk.isEmpty() ? defaultJunkRewards() : this.junk;
            case 1 -> this.grade1;
            case 2 -> this.grade2;
            case 3 -> this.grade3;
            case 4 -> this.grade4;
            case 5 -> this.grade5;
            default -> List.of();
        };
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name", nullToEmpty(this.boxName));
        tag.putString("key", nullToEmpty(this.boxKey));
        tag.putFloat("drop", this.boxDropChance);
        tag.putFloat("key_drop", this.keyDropChance);
        tag.putIntArray("random", normalizedWeights());
        tag.putInt("junk_weight", normalizedJunkWeight());
        putStringList(tag, "entity", this.dropEntities);
        putStringList(tag, "junk", this.rewardsForGrade(0));
        putStringList(tag, "grade1", this.grade1);
        putStringList(tag, "grade2", this.grade2);
        putStringList(tag, "grade3", this.grade3);
        putStringList(tag, "grade4", this.grade4);
        putStringList(tag, "grade5", this.grade5);
        return tag;
    }

    public static PlantBoxInfo load(CompoundTag tag) {
        PlantBoxInfo info = new PlantBoxInfo();
        info.boxName = tag.getString("name");
        info.boxKey = tag.getString("key");
        info.boxDropChance = tag.contains("drop", Tag.TAG_FLOAT) ? tag.getFloat("drop") : 0.0F;
        info.keyDropChance = tag.contains("key_drop", Tag.TAG_FLOAT) ? tag.getFloat("key_drop") : 0.0F;
        info.gradeWeights = tag.contains("random", Tag.TAG_INT_ARRAY) ? tag.getIntArray("random") : info.gradeWeights;
        if (Arrays.equals(info.gradeWeights, LEGACY_GRADE_WEIGHTS)) {
            info.gradeWeights = DEFAULT_GRADE_WEIGHTS.clone();
        }
        info.junkWeight = tag.contains("junk_weight", Tag.TAG_INT) ? tag.getInt("junk_weight") : DEFAULT_JUNK_WEIGHT;
        if (info.junkWeight == LEGACY_JUNK_WEIGHT) {
            info.junkWeight = DEFAULT_JUNK_WEIGHT;
        }
        info.dropEntities = getStringList(tag, "entity");
        info.junk = getStringList(tag, "junk");
        info.grade1 = getStringList(tag, "grade1");
        info.grade2 = getStringList(tag, "grade2");
        info.grade3 = getStringList(tag, "grade3");
        info.grade4 = getStringList(tag, "grade4");
        info.grade5 = getStringList(tag, "grade5");
        return info;
    }

    public int[] normalizedWeights() {
        int[] source = this.gradeWeights == null ? new int[0] : this.gradeWeights;
        int[] normalized = DEFAULT_GRADE_WEIGHTS.clone();
        for (int i = 0; i < Math.min(source.length, normalized.length); i++) {
            normalized[i] = Math.max(0, source[i]);
        }
        return normalized;
    }

    public int normalizedJunkWeight() {
        return Math.max(0, this.junkWeight);
    }

    public static List<String> defaultJunkRewards() {
        return List.of(
                "minecraft:dirt*2-8",
                "minecraft:coarse_dirt*2-6",
                "minecraft:rotten_flesh*1-4",
                "minecraft:bone*1-3",
                "minecraft:string*1-3",
                "minecraft:wheat_seeds*2-6",
                "minecraft:stick*1-4",
                "minecraft:poisonous_potato*1-2"
        );
    }

    private static void putStringList(CompoundTag tag, String key, List<String> values) {
        ListTag listTag = new ListTag();
        if (values != null) {
            for (String value : values) {
                if (value != null && !value.isBlank()) {
                    listTag.add(StringTag.valueOf(value));
                }
            }
        }
        tag.put(key, listTag);
    }

    private static List<String> getStringList(CompoundTag tag, String key) {
        List<String> values = new ArrayList<>();
        if (!tag.contains(key, Tag.TAG_LIST)) {
            return values;
        }

        ListTag listTag = tag.getList(key, Tag.TAG_STRING);
        for (Tag value : listTag) {
            values.add(value.getAsString());
        }
        return values;
    }

    private static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
