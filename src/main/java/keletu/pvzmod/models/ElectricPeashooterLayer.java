package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.ElectricPeashooter;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ElectricPeashooterLayer extends EyesLayer<ElectricPeashooter, ElectricPeashooterModel> {

    public static final ResourceLocation ELECTRIC_EMISSIVE = new ResourceLocation(PVZMod.MODID, "textures/entity/plant/electric_peashooter_emissive.png");

    public ElectricPeashooterLayer(RenderLayerParent<ElectricPeashooter, ElectricPeashooterModel> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ElectricPeashooter pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer $$10 = pBuffer.getBuffer(this.renderType());
        this.getParentModel().renderToBuffer(pPoseStack, $$10, 15728640, OverlayTexture.NO_OVERLAY, 0.5f, 1.0f, 0.5f, 1.0f);
    }

    @Override
    public RenderType renderType() {
        return RenderType.eyes(ELECTRIC_EMISSIVE);
    }
}
