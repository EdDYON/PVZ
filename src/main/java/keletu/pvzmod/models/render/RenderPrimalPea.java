package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.projectile.PrimalPeaProjectile;
import keletu.pvzmod.models.PrimalPeaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class RenderPrimalPea extends EntityRenderer<PrimalPeaProjectile> {

    PrimalPeaModel model;

    public RenderPrimalPea(EntityRendererProvider.Context context) {
        super(context);
        this.model = new PrimalPeaModel<>(context.bakeLayer(PrimalPeaModel.LAYER_LOCATION));
    }

    @Override
    public void render(PrimalPeaProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int pPackedLight) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(0.0F));
        matrixStackIn.mulPose(Axis.ZN.rotationDegrees((entity.tickCount + partialTicks) * 30.0F));
        this.model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(PrimalPeaProjectile entity) {
        return entity.getProjectileType() == 1 ? new ResourceLocation(PVZMod.MODID, "textures/item/small_stone.png") : new ResourceLocation(PVZMod.MODID, "textures/item/primal_pea.png");
    }
}