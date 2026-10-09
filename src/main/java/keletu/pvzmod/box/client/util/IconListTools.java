package keletu.pvzmod.box.client.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

public final class IconListTools {
    private static final ResourceLocation GOLD_ITEM = new ResourceLocation(PVZMod.MODID, "textures/screens/gold_item.png");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(PVZMod.MODID, "textures/screens/csgo_background.png");

    private IconListTools() {
    }

    public static ResourceLocation backgroundTexture() {
        return BACKGROUND;
    }

    public static void renderItemFrame(LivingEntity entity, GuiGraphics guiGraphics, ItemStack itemStack, int x, int y, int width, int height, int grade) {
        int color = ColorTools.colorItems(grade);
        int frameWidth = width * 8 / 100;
        int frameHeight = height * 11 / 100;
        float scale = frameWidth * 60F / 100F / 16F;
        int toX = x + frameWidth;
        int toY = y + frameHeight;
        int itemX = x + frameWidth * 20 / 100;
        int itemY = y + frameHeight * 10 / 100;

        if (grade == 5) {
            guiGraphics.fillGradient(x, y, toX, toY, 0xFF533c00, 0xFFb69008);
            guiGraphics.fill(x, y, x + 2, toY, color);
            guiGraphics.blit(GOLD_ITEM, x + 2, y + 2, 0, 0, frameWidth - 4, frameHeight - 4, frameWidth - 4, frameHeight - 4);
            return;
        }

        guiGraphics.fillGradient(x, y, toX, toY, 0xFF696969, 0xFFD3D3D3);
        guiGraphics.fill(x, y, x + 2, toY, color);
        renderGuiItem(entity, entity.level(), guiGraphics, itemStack, itemX, itemY, scale);
    }

    public static void renderItemProgress(LivingEntity entity, GuiGraphics guiGraphics, ItemStack itemStack, float x, float y, float width, float height, int grade) {
        int color = ColorTools.colorItems(grade);
        float frameWidth = width * 18 / 100F;
        float frameHeight = height * 25 / 100F;
        float scale = frameWidth * 60F / 100F / 16F;
        float toX = x + frameWidth;
        float toY = y + frameHeight;
        float itemX = x + frameWidth * 20 / 100F;
        float itemY = y + frameHeight * 10 / 100F;

        if (grade == 5) {
            fillGradient(guiGraphics, x, y, toX, toY, 0xFF533c00, 0xFFb69008);
            blit(guiGraphics, GOLD_ITEM, x + 2F, y + 2F, 0, 0, frameWidth - 4, frameHeight - 4, frameWidth - 4, frameHeight - 4);
            fill(guiGraphics, x, toY, toX, toY + 2, color);
            return;
        }

        fillGradient(guiGraphics, x, y, toX, toY, 0xFF696969, 0xFFA9A9A9);
        fillGradient(guiGraphics, x, y + frameHeight * 2 / 3F, toX, toY, ColorTools.argbColor(0, 128, 128, 128), ColorTools.deepColor(color));
        renderGuiItem(entity, entity.level(), guiGraphics, itemStack, itemX, itemY, scale);
        RenderSystem.enableBlend();
        fill(guiGraphics, x, toY, toX, toY + 2, color);
    }

    public static void renderGuiItem(LivingEntity entity, Level world, GuiGraphics guiGraphics, ItemStack itemStack, float x, float y, float scale) {
        BakedModel model = Minecraft.getInstance().getItemRenderer().getModel(itemStack, world, entity, 0);
        renderGuiItem(guiGraphics.pose(), itemStack, x, y, model, scale);
    }

    private static void renderGuiItem(PoseStack poseStack, ItemStack itemStack, float x, float y, BakedModel bakedModel, float scale) {
        poseStack.pushPose();
        poseStack.translate(x, y, 2F);
        poseStack.translate(8.0F * scale, 8.0F * scale, 0.0F);
        poseStack.mulPoseMatrix(new Matrix4f().scaling(1.0F, -1.0F, 0F));
        poseStack.scale(16.0F * scale, 16.0F * scale, 0);

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

    public static void fill(GuiGraphics guiGraphics, float minX, float minY, float maxX, float maxY, int color) {
        float z = 3;
        RenderType renderType = RenderType.gui();
        Matrix4f matrix = guiGraphics.pose().last().pose();
        if (minX < maxX) {
            float swap = minX;
            minX = maxX;
            maxX = swap;
        }
        if (minY < maxY) {
            float swap = minY;
            minY = maxY;
            maxY = swap;
        }

        float alpha = FastColor.ARGB32.alpha(color) / 255.0F;
        float red = FastColor.ARGB32.red(color) / 255.0F;
        float green = FastColor.ARGB32.green(color) / 255.0F;
        float blue = FastColor.ARGB32.blue(color) / 255.0F;
        VertexConsumer consumer = guiGraphics.bufferSource().getBuffer(renderType);
        consumer.vertex(matrix, minX, minY, z).color(red, green, blue, alpha).endVertex();
        consumer.vertex(matrix, minX, maxY, z).color(red, green, blue, alpha).endVertex();
        consumer.vertex(matrix, maxX, maxY, z).color(red, green, blue, alpha).endVertex();
        consumer.vertex(matrix, maxX, minY, z).color(red, green, blue, alpha).endVertex();
        flush(guiGraphics);
    }

    public static void fillGradient(GuiGraphics guiGraphics, float x1, float y1, float x2, float y2, int colorFrom, int colorTo) {
        float z = 1;
        RenderType renderType = RenderType.gui();
        VertexConsumer consumer = guiGraphics.bufferSource().getBuffer(renderType);
        float alphaFrom = FastColor.ARGB32.alpha(colorFrom) / 255.0F;
        float redFrom = FastColor.ARGB32.red(colorFrom) / 255.0F;
        float greenFrom = FastColor.ARGB32.green(colorFrom) / 255.0F;
        float blueFrom = FastColor.ARGB32.blue(colorFrom) / 255.0F;
        float alphaTo = FastColor.ARGB32.alpha(colorTo) / 255.0F;
        float redTo = FastColor.ARGB32.red(colorTo) / 255.0F;
        float greenTo = FastColor.ARGB32.green(colorTo) / 255.0F;
        float blueTo = FastColor.ARGB32.blue(colorTo) / 255.0F;

        Matrix4f matrix = guiGraphics.pose().last().pose();
        consumer.vertex(matrix, x1, y1, z).color(redFrom, greenFrom, blueFrom, alphaFrom).endVertex();
        consumer.vertex(matrix, x1, y2, z).color(redTo, greenTo, blueTo, alphaTo).endVertex();
        consumer.vertex(matrix, x2, y2, z).color(redTo, greenTo, blueTo, alphaTo).endVertex();
        consumer.vertex(matrix, x2, y1, z).color(redFrom, greenFrom, blueFrom, alphaFrom).endVertex();
        flush(guiGraphics);
    }

    public static void blit(GuiGraphics guiGraphics, ResourceLocation atlas, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        float x2 = x + width;
        float y2 = y + height;
        int z = 4;
        float minU = uOffset / textureWidth;
        float maxU = (uOffset + width) / textureWidth;
        float minV = vOffset / textureHeight;
        float maxV = (vOffset + height) / textureHeight;

        RenderSystem.setShaderTexture(0, atlas);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix = guiGraphics.pose().last().pose();
        BufferBuilder builder = Tesselator.getInstance().getBuilder();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.vertex(matrix, x, y, z).uv(minU, minV).endVertex();
        builder.vertex(matrix, x, y2, z).uv(minU, maxV).endVertex();
        builder.vertex(matrix, x2, y2, z).uv(maxU, maxV).endVertex();
        builder.vertex(matrix, x2, y, z).uv(maxU, minV).endVertex();
        BufferUploader.drawWithShader(builder.end());
        flush(guiGraphics);
    }

    public static void flush(GuiGraphics guiGraphics) {
        RenderSystem.disableDepthTest();
        guiGraphics.bufferSource().endBatch();
        RenderSystem.enableDepthTest();
    }
}
