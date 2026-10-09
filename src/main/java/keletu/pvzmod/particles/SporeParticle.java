package keletu.pvzmod.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class SporeParticle extends TextureSheetParticle {

    protected SporeParticle(ClientLevel level, double x, double y, double z,
                            double xd, double yd, double zd, SpriteSet sprites) {
        super(level, x, y, z, xd, yd, zd);

        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.lifetime = 15 + this.random.nextInt(6);
        this.gravity = 0.0F;

        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;

        this.quadSize = 0.8F;
        this.pickSprite(sprites);
    }

    @Override
    public void tick() {
        super.tick();

        float lifeProgress = (float) this.age / (float) this.lifetime;
        this.quadSize = (this.random.nextInt(50) < 1 ? 1.0F : 0.5F) - lifeProgress * 0.5F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xd, double yd, double zd) {
            return new SporeParticle(level, x, y, z, xd, yd, zd, this.sprites);
        }
    }
}