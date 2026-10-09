package keletu.pvzmod.plantconfig.client;

import com.mojang.blaze3d.systems.RenderSystem;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.box.client.util.BlurHandler;
import keletu.pvzmod.box.client.util.GuiItemMove;
import keletu.pvzmod.box.client.util.PlantPreviewRenderer;
import keletu.pvzmod.box.client.util.RenderFontTool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlantStatsPreviewScreen extends Screen {
    private static final ResourceLocation BACK_BOX = new ResourceLocation(PVZMod.MODID, "textures/screens/atlas/back_box.png");

    private final Screen parent;
    private final ItemStack previewStack;
    private final String plantId;
    private final Component displayName;
    private final Player entity;
    private LivingEntity previewPlant;
    private float itemRotX;
    private float itemRotY;

    public PlantStatsPreviewScreen(Screen parent, ItemStack previewStack, String displayName) {
        this(parent, previewStack, displayName, "");
    }

    public PlantStatsPreviewScreen(Screen parent, ItemStack previewStack, String displayName, String plantId) {
        super(Component.literal("plant_stats_preview"));
        this.minecraft = Minecraft.getInstance();
        this.parent = parent;
        this.previewStack = previewStack.copy();
        this.plantId = plantId == null ? "" : plantId;
        this.displayName = displayName == null || displayName.isBlank() ? this.previewStack.getHoverName() : Component.literal(displayName);
        this.entity = this.minecraft == null ? null : this.minecraft.player;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new ImageButton(this.width * 72 / 100, this.height * 94 / 100, this.width * 4 / 100, this.height * 5 / 100, 0, 0, 64, BACK_BOX, 82, 128, button -> closeToParent()));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        renderLabels(guiGraphics);
        renderBg(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    protected void renderBg(GuiGraphics guiGraphics) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        BlurHandler.updateShader(false);
        if (this.minecraft != null) {
            this.minecraft.options.hideGui = true;
        }

        guiGraphics.fill(this.width * 25 / 100, this.height * 92 / 100, this.width * 75 / 100, this.height * 92 / 100 + 1, 0xFFD3D3D3);
        LivingEntity plant = previewPlant();
        if (plant != null) {
            PlantPreviewRenderer.render(guiGraphics, plant, this.width / 2, this.height * 76 / 100, previewModelScale(), this.itemRotX, this.itemRotY);
        } else if (this.entity != null && !this.previewStack.isEmpty()) {
            int frameWidth = this.width * 26 / 100;
            float scale = frameWidth / 16F;
            GuiItemMove.renderItemInInventoryFollowsMouse(guiGraphics, this.width * 37 / 100, this.height * 30 / 100, this.itemRotX, this.itemRotY, this.previewStack, this.entity, scale);
        }
        RenderSystem.disableBlend();
    }

    protected void renderLabels(GuiGraphics guiGraphics) {
        Style bold = Style.EMPTY.withBold(true);
        renderText(guiGraphics, this.displayName.getVisualOrderText(), this.width * 45F / 100F, this.height * 5F / 100F, 1.8F);
        if (!this.previewStack.getHoverName().getString().equals(this.displayName.getString())) {
            renderText(guiGraphics, this.previewStack.getHoverName().getVisualOrderText(), this.width * 45F / 100F, this.height * 11F / 100F, 1F);
        }
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.back_box").withStyle(bold).getVisualOrderText(), this.width * 72.5F / 100F, this.height * 95F / 100F, 0.8F);
    }

    private void renderText(GuiGraphics guiGraphics, FormattedCharSequence text, float x, float y, float scale) {
        RenderFontTool.drawString(guiGraphics, this.font, text, x, y, 0, 0, scale, 0xFFFFFFFF);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0 && isInPreview(mouseX, mouseY)) {
            this.itemRotX = GuiItemMove.renderRotAngleX(dragX, this.itemRotX);
            this.itemRotY = GuiItemMove.renderRotAngleY(dragY, this.itemRotY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public void tick() {
        super.tick();
        PlantPreviewRenderer.tickPreviewEntity(this.previewPlant);
    }

    @Override
    public boolean keyPressed(int key, int scanCode, int modifiers) {
        if (key == 256) {
            closeToParent();
            return true;
        }
        return super.keyPressed(key, scanCode, modifiers);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        if (this.minecraft != null && this.minecraft.level != null) {
            guiGraphics.fillGradient(0, 0, this.width, this.height, BlurHandler.getBackgroundColor(), BlurHandler.getBackgroundColor());
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, guiGraphics));
        } else {
            this.renderDirtBackground(guiGraphics);
        }
    }

    @Override
    public void onClose() {
        closeToParent();
    }

    private void closeToParent() {
        if (this.minecraft != null) {
            this.minecraft.options.hideGui = false;
            this.minecraft.setScreen(this.parent);
        }
    }

    private LivingEntity previewPlant() {
        if (this.previewPlant == null && this.minecraft != null) {
            this.previewPlant = PlantPreviewRenderer.createPreviewEntity(this.plantId, this.previewStack, this.minecraft.level);
        }
        return this.previewPlant;
    }

    private int previewModelScale() {
        return Mth.clamp(Math.min(this.width, this.height) / 5, 90, 230);
    }

    private boolean isInPreview(double mouseX, double mouseY) {
        int radius = Math.max(this.width * 18 / 100, previewModelScale());
        int centerX = this.width / 2;
        return mouseX >= centerX - radius && mouseX <= centerX + radius
                && mouseY >= this.height * 16F / 100F && mouseY <= this.height * 82F / 100F;
    }
}
