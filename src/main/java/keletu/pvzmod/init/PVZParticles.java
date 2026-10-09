package keletu.pvzmod.init;

import com.mojang.serialization.Codec;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.particles.TextureParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PVZParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PVZMod.MODID);

    public static final RegistryObject<SimpleParticleType> SPORE = PARTICLES.register("spore", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<TextureParticleOption>> TEXTURE_BREAK =
            PARTICLES.register("texture_break", () -> new ParticleType<TextureParticleOption>(false, TextureParticleOption.DESERIALIZER) {
                @Override
                public Codec<TextureParticleOption> codec() {
                    return TextureParticleOption.codec(this);
                }
            });
}