package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import keletu.pvzmod.entities.projectile.SnowPeaProjectile;
import keletu.pvzmod.models.IcePeaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RenderIcePea extends EntityRenderer<SnowPeaProjectile> {

    IcePeaModel model;

    public RenderIcePea(EntityRendererProvider.Context context) {
        super(context);
        this.model = new IcePeaModel<>(context.bakeLayer(IcePeaModel.LAYER_LOCATION));
    }

    @Override
    public void render(SnowPeaProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int pPackedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, pPackedLight);
        matrixStackIn.pushPose();
        this.model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(SnowPeaProjectile entity) {
        return IcePeaModel.LAYER_LOCATION.getModel();
    }
}