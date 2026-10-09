package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.models.GardenShovelModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GardenShovelRender extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PVZMod.MODID, "textures/item/garden_shovel.png");

    private GardenShovelModel model;

    public GardenShovelRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
    }

    private GardenShovelModel getModel() {
        if (model == null) {
            model = new GardenShovelModel(
                    Minecraft.getInstance().getEntityModels().bakeLayer(GardenShovelModel.LAYER_LOCATION)
            );
        }
        return model;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext,
                             PoseStack poseStack, MultiBufferSource buffer,
                             int packedLight, int packedOverlay) {
        poseStack.pushPose();

        poseStack.translate(0.5F, 0.5F, 0.5F);

        switch (displayContext) {
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                poseStack.scale(1.0F, 1.0F, 1.0F);
                poseStack.mulPose(Axis.YP.rotationDegrees(90F));
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                poseStack.scale(1.0F, 1.0F, 1.0F);
                poseStack.mulPose(Axis.YP.rotationDegrees(90F));
            }
            case GROUND -> {
                poseStack.scale(0.75F, 0.75F, 0.75F);
                poseStack.mulPose(Axis.YP.rotationDegrees(0F));
            }
            default -> {
                poseStack.scale(0.85F, 0.85F, 0.85F);
                poseStack.mulPose(Axis.XP.rotationDegrees(-15F));
                poseStack.mulPose(Axis.YP.rotationDegrees(180F));
            }
        }

        poseStack.scale(1.0F, -1.0F, -1.0F);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        getModel().render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }
}