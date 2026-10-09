package keletu.pvzmod.box.client.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

public final class GuiItemMove {
    private GuiItemMove() {
    }

    public static float renderRotAngleY(double mouse, float itemRot) {
        float angle = (float) Math.atan(mouse / 40.0F) + itemRot;
        if (angle > 1.5F) {
            return 1.5F;
        }
        if (angle < -1.5F) {
            return -1.5F;
        }
        return angle;
    }

    public static float renderRotAngleX(double mouse, float itemRot) {
        float angle = (float) Math.atan(mouse / 40.0F) + itemRot;
        if (angle > 3.0F) {
            return 3.0F;
        }
        if (angle < -3.0F) {
            return -3.0F;
        }
        return angle;
    }

    public static void renderItemInInventoryFollowsMouse(GuiGraphics guiGraphics, int x, int y, float angleXComponent, float angleYComponent, ItemStack item, LivingEntity player, float scale) {
        BakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getModel(item, player.level(), player, 0);
        renderItemInInventory(guiGraphics.pose(), item, x, y, bakedModel, angleXComponent, angleYComponent, scale);
    }

    private static void renderItemInInventory(PoseStack poseStack, ItemStack itemStack, int x, int y, BakedModel bakedModel, float angleXComponent, float angleYComponent, float scale) {
        poseStack.pushPose();
        poseStack.translate(x, y, 100.0F);
        poseStack.translate(8.0F * scale, 8.0F * scale, 0.0F);
        poseStack.mulPoseMatrix(new Matrix4f().scaling(1.0F, -1.0F, 1.0F));
        poseStack.mulPose(Axis.XP.rotation(angleYComponent));
        poseStack.mulPose(Axis.YP.rotation(angleXComponent));
        Lighting.setupForEntityInInventory();
        poseStack.scale(16.0F * scale, 16.0F * scale, 16.0F * scale);

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        boolean flat = !bakedModel.usesBlockLight();
        if (flat) {
            Lighting.setupForFlatItems();
        }

        PoseStack modelView = RenderSystem.getModelViewStack();
        modelView.pushPose();
        modelView.mulPoseMatrix(poseStack.last().pose());
        RenderSystem.applyModelViewMatrix();
        Minecraft.getInstance().getItemRenderer().render(itemStack, ItemDisplayContext.GUI, false, new PoseStack(), bufferSource, 15728880, OverlayTexture.NO_OVERLAY, bakedModel);
        bufferSource.endBatch();
        RenderSystem.enableDepthTest();
        if (flat) {
            Lighting.setupFor3DItems();
        }
        poseStack.popPose();
        modelView.popPose();
        RenderSystem.applyModelViewMatrix();
    }
}
