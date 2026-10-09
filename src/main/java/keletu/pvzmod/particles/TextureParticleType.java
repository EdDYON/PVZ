package keletu.pvzmod.particles;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class TextureParticleType extends ParticleType<TextureParticleOption> {
    public TextureParticleType(boolean overrideLimiter) {
        super(overrideLimiter, TextureParticleOption.DESERIALIZER);
    }

    @Override
    public Codec<TextureParticleOption> codec() {
        return TextureParticleOption.codec(this);
    }
}