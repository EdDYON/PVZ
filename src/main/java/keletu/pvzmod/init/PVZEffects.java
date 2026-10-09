package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.potion.ShockedEffect;
import keletu.pvzmod.potion.SnowEffect;
import keletu.pvzmod.potion.StunEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PVZEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, PVZMod.MODID);

    public static final RegistryObject<MobEffect> SNOW = EFFECTS.register("snow", SnowEffect::new);
    public static final RegistryObject<MobEffect> STUN = EFFECTS.register("stun", StunEffect::new);
    public static final RegistryObject<MobEffect> SHOCKED = EFFECTS.register("shocked", ShockedEffect::new);
}