package keletu.pvzmod.box.client.util;

import com.mojang.blaze3d.shaders.Uniform;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = PVZMod.MODID)
public final class BlurHandler {
    private static final ResourceLocation FADE_IN_BLUR = new ResourceLocation("shaders/post/fade_in_blur.json");
    public static boolean isShaderOn;
    private static float prevProgress = -1;
    private static long start = -1;
    private static Field listShaders;
    private static String lastShader;

    private BlurHandler() {
    }

    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.Init.Post event) {
        updateShader(true);
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getInstance().screen != null && Minecraft.getInstance().gameRenderer.currentEffect() != null) {
            float progress = getProgress();
            if (progress != prevProgress) {
                prevProgress = progress;
                updateUniform("Progress", progress);
            }
        }
    }

    public static void updateUniform(String name, float value) {
        if (listShaders == null) {
            return;
        }

        PostChain postChain = Minecraft.getInstance().gameRenderer.currentEffect();
        if (postChain == null) {
            return;
        }

        try {
            @SuppressWarnings("unchecked")
            List<PostPass> shaders = (List<PostPass>) listShaders.get(postChain);
            for (PostPass shader : shaders) {
                Uniform uniform = shader.getEffect().getUniform(name);
                if (uniform != null) {
                    uniform.set(value);
                }
            }
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException(exception);
        }
    }

    public static void updateShader(boolean excluded) {
        isShaderOn = !excluded;
        if (listShaders == null) {
            listShaders = ObfuscationReflectionHelper.findField(PostChain.class, "f_110009_");
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            start = -1;
            prevProgress = -1;
            return;
        }

        GameRenderer renderer = minecraft.gameRenderer;
        PostChain postChain = renderer.currentEffect();
        if (!excluded) {
            boolean setShader = postChain == null || !FADE_IN_BLUR.toString().equals(postChain.getName());
            if (setShader) {
                if (postChain != null) {
                    lastShader = postChain.getName();
                }
                renderer.loadEffect(FADE_IN_BLUR);
                updateUniform("Radius", 50);
                if (start == -1) {
                    start = System.currentTimeMillis();
                } else {
                    updateUniform("Progress", getProgress());
                }
            }
            return;
        }

        if (postChain != null && FADE_IN_BLUR.toString().equals(postChain.getName())) {
            if (lastShader != null) {
                renderer.loadEffect(new ResourceLocation(lastShader));
                lastShader = null;
            } else {
                renderer.shutdownEffect();
            }
            start = -1;
            prevProgress = -1;
        }
    }

    private static float getProgress() {
        if (start == -1) {
            return 0.0F;
        }
        return Math.min((System.currentTimeMillis() - start) / 100.0F, 1.0F);
    }

    public static int getBackgroundColor() {
        int color = (128 << 24) | (90 << 16) | (90 << 8) | 90;
        int alpha = color >> 24;
        int red = (color >> 16) & 0xFF;
        int blue = (color >> 8) & 0xFF;
        int green = color & 0xFF;
        float progress = getProgress();
        alpha *= progress;
        red *= progress;
        green *= progress;
        blue *= progress;
        return alpha << 24 | red << 16 | blue << 8 | green;
    }
}
