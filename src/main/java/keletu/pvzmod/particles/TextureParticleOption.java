package keletu.pvzmod.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class TextureParticleOption implements ParticleOptions {
    public static final Deserializer<TextureParticleOption> DESERIALIZER = new Deserializer<>() {
        @Override
        public TextureParticleOption fromCommand(ParticleType<TextureParticleOption> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            return new TextureParticleOption(type, ResourceLocation.read(reader));
        }

        @Override
        public TextureParticleOption fromNetwork(ParticleType<TextureParticleOption> type, FriendlyByteBuf buffer) {
            return new TextureParticleOption(type, buffer.readResourceLocation());
        }
    };

    private final ParticleType<TextureParticleOption> type;
    private final ResourceLocation texture;

    public TextureParticleOption(ParticleType<TextureParticleOption> type, ResourceLocation texture) {
        this.type = type;
        this.texture = texture;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public ParticleType<TextureParticleOption> getType() {
        return this.type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(this.texture);
    }

    @Override
    public String writeToString() {
        return BuiltInRegistries.PARTICLE_TYPE.getKey(this.type) + " " + this.texture;
    }

    public static Codec<TextureParticleOption> codec(ParticleType<TextureParticleOption> type) {
        return ResourceLocation.CODEC.xmap(
                texture -> new TextureParticleOption(type, texture),
                TextureParticleOption::getTexture
        );
    }
}