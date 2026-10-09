package keletu.pvzmod.box.client.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;

public final class RenderFontTool {
    private RenderFontTool() {
    }

    public static int drawString(GuiGraphics guiGraphics, Font font, FormattedCharSequence text, float x, float y, int ox, int oy, float scale, int color) {
        guiGraphics.pose().pushPose();
        Matrix4f matrix = guiGraphics.pose().last().pose();
        matrix.translate(-ox, -oy, 1);
        matrix.translate(x, y, 1);
        matrix.scale(scale);
        int result = font.drawInBatch(text, 0.0F, 0.0F, color, false, matrix, guiGraphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
        guiGraphics.pose().popPose();
        return result;
    }

    public static int drawString(GuiGraphics guiGraphics, Font font, String text, float x, float y, int ox, int oy, float scale, int color) {
        guiGraphics.pose().pushPose();
        Matrix4f matrix = guiGraphics.pose().last().pose();
        matrix.translate(-ox, -oy, 0);
        matrix.translate(x, y, 0);
        matrix.scale(scale);
        int result = font.drawInBatch(text, 0.0F, 0.0F, color, false, matrix, guiGraphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
        guiGraphics.pose().popPose();
        return result;
    }
}
