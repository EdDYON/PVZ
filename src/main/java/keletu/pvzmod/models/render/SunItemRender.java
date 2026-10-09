package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.models.SunItemModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SunItemRender extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PVZMod.MODID, "textures/entity/plant/sun.png");

    private SunItemModel model;

    public SunItemRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
    }

    private SunItemModel getModel() {
        if (model == null) {
            model = new SunItemModel(
                    Minecraft.getInstance().getEntityModels().bakeLayer(SunItemModel.LAYER_LOCATION)
            );
        }
        return model;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext,
                             PoseStack poseStack, MultiBufferSource buffer,
                             int packedLight, int packedOverlay) {
        poseStack.pushPose();

        SunItemModel model = getModel();

        float age = 0.0F;
        if (Minecraft.getInstance().level != null) {
            age = Minecraft.getInstance().level.getGameTime() + Minecraft.getInstance().getFrameTime();
        } else {
            age = Minecraft.getInstance().getFrameTime();
        }

        model.setupAnim(age);

        VertexConsumer consumer = buffer.getBuffer(RenderType.itemEntityTranslucentCull(TEXTURE));
        model.render(poseStack, consumer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}