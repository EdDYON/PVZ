package keletu.pvzmod.potion;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class StunStarsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation STAR_TEXTURE =
            new ResourceLocation(PVZMod.MODID, "textures/particle/stun_star.png");

    private static final int FULL_BRIGHT = 0xF000F0;

    public StunStarsLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
                       float limbSwing, float limbSwingAmount, float partialTick,
                       float ageInTicks, float netHeadYaw, float headPitch) {
        if (!shouldRender(entity)) {
            return;
        }

        float time = entity.tickCount + partialTick;
        float angularSpeed = 6.0F;
        float radius = Math.max(entity.getBbWidth() * 0.7F, 0.55F);
        float height = entity.getBbHeight();
        float starSize = 1.0F;

        poseStack.pushPose();
        poseStack.translate(0.0D, -height + 1.0F, 0.0D);

        renderOneStarWithTrail(poseStack, buffer, entity, time, 0.0F, angularSpeed, radius, starSize);
        renderOneStarWithTrail(poseStack, buffer, entity, time, 120.0F, angularSpeed, radius, starSize);
        renderOneStarWithTrail(poseStack, buffer, entity, time, 240.0F, angularSpeed, radius, starSize);

        poseStack.popPose();
    }

    private boolean shouldRender(T entity) {
        AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speed != null) {
            AttributeModifier stun = speed.getModifier(StunEffect.MOVEMENT_SPEED_MODIFIER);
            return stun != null;
        }
        return false;
    }

    private void renderOneStarWithTrail(PoseStack poseStack, MultiBufferSource buffer, T entity,
                                        float time, float baseAngle, float angularSpeed,
                                        float radius, float starSize) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(STAR_TEXTURE));

        int trailCount = 5;

        float angleStep = 10.0F;

        for (int i = trailCount; i >= 0; i--) {
            float angle = baseAngle + time * angularSpeed - i * angleStep;
            float rad = (float) Math.toRadians(angle);

            float x = Mth.cos(rad) * radius;
            float z = Mth.sin(rad) * radius;

            float alpha = (i == 0) ? 1.0F : 0.42F - (i - 1) * 0.08F;
            alpha = Mth.clamp(alpha, 0.05F, 1.0F);

            float scale = starSize * (1.0F - i * 0.08F);
            scale = Math.max(scale, starSize * 0.55F);

            float yOffset = i * 0.01F;

            poseStack.pushPose();
            poseStack.translate(x, yOffset, z);

            poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180.0F));
            poseStack.scale(scale, scale, scale);

            PoseStack.Pose pose = poseStack.last();
            Matrix4f matrix4f = pose.pose();
            Matrix3f matrix3f = pose.normal();

            float r = 1.0F;
            float g = (i == 0) ? 1.0F : 0.95F;
            float b = (i == 0) ? 1.0F : 0.65F;

            addQuad(consumer, matrix4f, matrix3f, FULL_BRIGHT, r, g, b, alpha);
            poseStack.popPose();
        }
    }

    private static void addQuad(VertexConsumer consumer, Matrix4f mat, Matrix3f normal, int light,
                                float r, float g, float b, float a) {
        float min = -0.5F;
        float max = 0.5F;

        consumer.vertex(mat, min, min, 0.0F)
                .color(r, g, b, a)
                .uv(0.0F, 1.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 0.0F, 1.0F)
                .endVertex();

        consumer.vertex(mat, max, min, 0.0F)
                .color(r, g, b, a)
                .uv(1.0F, 1.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 0.0F, 1.0F)
                .endVertex();

        consumer.vertex(mat, max, max, 0.0F)
                .color(r, g, b, a)
                .uv(1.0F, 0.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 0.0F, 1.0F)
                .endVertex();

        consumer.vertex(mat, min, max, 0.0F)
                .color(r, g, b, a)
                .uv(0.0F, 0.0F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0.0F, 0.0F, 1.0F)
                .endVertex();
    }
}