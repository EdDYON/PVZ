package keletu.pvzmod;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PVZConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HELEMET;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("PVZ_MYH");
        HELEMET = builder.comment("HelmetId;scaleX;scaleY;scaleZ;translateX;translateY;translateZ;Tall-nutTranslateX;Tall-nutTranslateY;Tall-nutTranslateZ;")
                .defineList("SpecialHelmetScales", ObjectArrayList.wrap(new String[]{
                        "iceandfire:armor_red_helmet;3.5;3.5;3.5;0.0;-0.2;0.0;0.0;-0.35;-0.01",
                        "iceandfire:armor_blue_helmet;3.5;3.5;3.5;0.0;-0.2;0.0;0.0;-0.35;-0.01",
                        "iceandfire:armor_black_helmet;3.5;3.5;3.5;0.0;-0.2;0.0;0.0;-0.35;-0.01",
                }), o -> o instanceof String);
        builder.pop();
        COMMON_SPEC = builder.build();
    }
}