package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PVZSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, PVZMod.MODID);

    public static final RegistryObject<SoundEvent> PUFF_SHROOM_SHOOT = SOUNDS.register("puff_shroom_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "puff_shroom_shoot")));
    public static final RegistryObject<SoundEvent> CARD_SELECT = SOUNDS.register("card_select", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "card_select")));
    public static final RegistryObject<SoundEvent> SHOVEL_SELECT = SOUNDS.register("shovel_select", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "shovel_select")));
    public static final RegistryObject<SoundEvent> PLANT = SOUNDS.register("plant_plant", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "plant_plant")));
    public static final RegistryObject<SoundEvent> REMOVE = SOUNDS.register("remove_plant", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "remove_plant")));
    public static final RegistryObject<SoundEvent> FUME_SHROOM_SHOOT = SOUNDS.register("fume_shroom_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "fume_shroom_shoot")));
    public static final RegistryObject<SoundEvent> SUPERGATLINGPEA_POWER = SOUNDS.register("supergatlingpea_power", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "supergatlingpea_power")));
    public static final RegistryObject<SoundEvent> SUPERGATLINGPEA_SHOOT = SOUNDS.register("supergatlingpea_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "supergatlingpea_shoot")));
    public static final RegistryObject<SoundEvent> PRIMALPEASHOOTER_SHOOT = SOUNDS.register("primalpeashooter_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "primalpeashooter_shoot")));
    public static final RegistryObject<SoundEvent> PRIMALPEA_BOOM = SOUNDS.register("primalpea_boom", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "primalpea_boom")));
    public static final RegistryObject<SoundEvent> PEASHOOTER_SHOOT = SOUNDS.register("peashooter_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "peashooter_shoot")));
    public static final RegistryObject<SoundEvent> PEA_BOOM = SOUNDS.register("pea_boom", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "pea_boom")));
    public static final RegistryObject<SoundEvent> ELECTRICPEASHOOTER_SHOOT = SOUNDS.register("electricpeashooter_shoot", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "electricpeashooter_shoot")));
    public static final RegistryObject<SoundEvent> BONKCHOY_HIT = SOUNDS.register("bonkchoy_hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "bonkchoy_hit")));
    public static final RegistryObject<SoundEvent> BONKCHOY_HIT2 = SOUNDS.register("bonkchoy_hit2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "bonkchoy_hit2")));


    public static final RegistryObject<SoundEvent> DAVE_SPEAK = SOUNDS.register("dave_speak", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "dave_speak")));
    public static final RegistryObject<SoundEvent> DAVE_SPEAK_LONG = SOUNDS.register("dave_speak_long", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "dave_speak_long")));
    public static final RegistryObject<SoundEvent> DAVE_TRADE = SOUNDS.register("dave_trade", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "dave_trade")));
    public static final RegistryObject<SoundEvent> DAVE_HURT = SOUNDS.register("dave_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "dave_hurt")));

    public static final RegistryObject<SoundEvent> PENNY_SPEAK = SOUNDS.register("penny_speak", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "penny_speak")));
    public static final RegistryObject<SoundEvent> PENNY_HURT = SOUNDS.register("penny_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "penny_hurt")));

    public static final RegistryObject<SoundEvent> SUN_PICKUP = SOUNDS.register("sun_pickup", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "sun_pickup")));
    public static final RegistryObject<SoundEvent> DIAMOND_PICKUP = SOUNDS.register("diamond_pickup", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "diamond_pickup")));
    public static final RegistryObject<SoundEvent> PLANT_BOX_OPEN = SOUNDS.register("plant_box_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "plant_box_open")));
    public static final RegistryObject<SoundEvent> PLANT_BOX_TICK = SOUNDS.register("plant_box_tick", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "plant_box_tick")));
    public static final RegistryObject<SoundEvent> PLANT_BOX_FINISH = SOUNDS.register("plant_box_finish", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "plant_box_finish")));
    public static final RegistryObject<SoundEvent> SECRET_LABS = SOUNDS.register("secret_labs", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "secret_labs")));
    public static final RegistryObject<SoundEvent> ZOMBIES_ON_YOUR_LAWN = SOUNDS.register("zombies_on_your_lawn", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PVZMod.MODID, "zombies_on_your_lawn")));

}
