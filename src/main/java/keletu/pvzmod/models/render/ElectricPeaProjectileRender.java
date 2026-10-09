package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import keletu.pvzmod.entities.projectile.ElectricPeaProjectile;
import keletu.pvzmod.models.ElectricPeaProjectileModel;
import keletu.pvzmod.models.LightningBoltData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector4f;

public class ElectricPeaProjectileRender extends EntityRenderer {

    private final LightningRender lightningRender = new LightningRender();

    private final LightningBoltData.BoltRenderInfo lightningBoltData =
            new LightningBoltData.BoltRenderInfo(
                    1.3F,
                    0.15F,
                    0.5F,
                    0.25F,
                    new Vector4f(0.5F, 1.0F, 0.5F, 0.7F),
                    0.45F
            );

    private final LightningBoltData.BoltRenderInfo groundLightningBoltData =
            new LightningBoltData.BoltRenderInfo(
                    0.3F,
                    0.15F,
                    0.5F,
                    0.25F,
                    new Vector4f(0.5F, 1.0F, 0.5F, 0.7F),
                    0.8F
            );

    EntityModel model;
    EntityModel layerModel;

    public ElectricPeaProjectileRender(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ElectricPeaProjectileModel<>(context.bakeLayer(ElectricPeaProjectileModel.LAYER_LOCATION));
        this.layerModel = new ElectricPeaProjectileModel<>(context.bakeLayer(ElectricPeaProjectileModel.LAYER_LOCATION));
    }

    @Override
    public boolean shouldRender(Entity entity, Frustum frustum, double x, double y, double z) {
        return entity != null && (
                frustum.isVisible(entity.getBoundingBox().inflate(4.0D))
                        || super.shouldRender(entity, frustum, x, y, z)
        );
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, light);

        poseStack.pushPose();
        this.model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.itemEntityTranslucentCull(getTextureLocation(entity))), light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        renderElectricLayer(entity, partialTicks, poseStack, buffer);

        poseStack.popPose();

        if (!(entity instanceof ElectricPeaProjectile pea)) {
            return;
        }

        double px = Mth.lerp(partialTicks, entity.xo, entity.getX());
        double py = Mth.lerp(partialTicks, entity.yo, entity.getY());
        double pz = Mth.lerp(partialTicks, entity.zo, entity.getZ());

        Vec3 start = new Vec3(px, py, pz);
        Vec3 localStart = new Vec3(0.0D, entity.getBbHeight() * 0.5D, 0.0D);

        poseStack.pushPose();

        Vec3 groundWorld = findGroundPoint(entity, new Vec3(px, py + entity.getBbHeight() * 0.5D, pz));
        if (groundWorld != null) {
            Vec3 localGround = new Vec3(
                    groundWorld.x - px,
                    groundWorld.y - py,
                    groundWorld.z - pz
            );
            renderGroundBolt(entity, localStart, localGround, partialTicks, poseStack, buffer);
        }

        poseStack.translate(-px, -py, -pz);
        for (LivingEntity target : pea.getRenderTargets()) {
            if (target == null || !target.isAlive()) {
                continue;
            }

            double tx = Mth.lerp(partialTicks, target.xo, target.getX());
            double ty = Mth.lerp(partialTicks, target.yo, target.getY()) + target.getBbHeight() * 0.5D;
            double tz = Mth.lerp(partialTicks, target.zo, target.getZ());

            Vec3 end = new Vec3(tx, ty, tz);
            renderBolt(entity, start, end, partialTicks, poseStack, buffer);
        }

        poseStack.popPose();
    }

    private void renderBolt(Entity owner, Vec3 start, Vec3 end, float partialTicks, PoseStack poseStack, MultiBufferSource buffer) {
        LightningBoltData bolt = new LightningBoltData(lightningBoltData, start, end, 5)
                .count(1)
                .size(0.08F)
                .lifespan(2)
                .spawn(LightningBoltData.SpawnFunction.NO_DELAY);

        lightningRender.update(owner, bolt, partialTicks);
        lightningRender.render(partialTicks, poseStack, buffer);
    }

    private void renderGroundBolt(Entity owner, Vec3 start, Vec3 end, float partialTicks, PoseStack poseStack, MultiBufferSource buffer) {
        LightningBoltData bolt = new LightningBoltData(groundLightningBoltData, start, end, 2)
                .count(1)
                .size(0.08F)
                .lifespan(1)
                .fade(LightningBoltData.FadeFunction.NONE)
                .spawn(LightningBoltData.SpawnFunction.NO_DELAY);

        lightningRender.update(owner, bolt, partialTicks);
        lightningRender.render(partialTicks, poseStack, buffer);
    }

    private Vec3 findGroundPoint(Entity entity, Vec3 start) {
        BlockPos base = BlockPos.containing(start.x, start.y, start.z);

        for (int i = 1; i <= 3; i++) {
            BlockPos check = base.below(i);
            if (!entity.level().getBlockState(check).isAir()) {
                return new Vec3(start.x, check.getY() + 1.0D, start.z);
            }
        }

        return new Vec3(start.x, start.y - 3.0F, start.z);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ElectricPeaProjectileModel.LAYER_LOCATION.getModel();
    }

    private void renderElectricLayer(Entity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer) {
        float alpha = 0.45F + 0.25F * Mth.sin((entity.tickCount + partialTicks) * 0.6F);

        this.layerModel.renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.eyes(ElectricPeaProjectileModel.LAYER_LOCATION.getModel())),
                LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY,
                0.5F, 1.0F, 0.5F, alpha
        );
    }
}
