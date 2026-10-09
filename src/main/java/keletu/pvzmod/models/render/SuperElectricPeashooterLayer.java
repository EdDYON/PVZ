package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SuperElectricGatlingPea;
import keletu.pvzmod.models.SuperElectricGatlingPeaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SuperElectricPeashooterLayer extends EyesLayer<SuperElectricGatlingPea, SuperElectricGatlingPeaModel> {

    public static final ResourceLocation ELECTRIC_EMISSIVE = new ResourceLocation(PVZMod.MODID, "textures/entity/plant/super_electric_gatling_pea_emissive.png");

    public SuperElectricPeashooterLayer(RenderLayerParent<SuperElectricGatlingPea, SuperElectricGatlingPeaModel> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, SuperElectricGatlingPea pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer $$10 = pBuffer.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(pPoseStack, $$10, 15728640, OverlayTexture.NO_OVERLAY, 0.5f, 1.0f, 0.5f, 1.0f);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(ELECTRIC_EMISSIVE);
    }
}
