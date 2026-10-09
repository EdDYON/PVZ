package keletu.pvzmod.box.client;

import com.mojang.blaze3d.systems.RenderSystem;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.box.client.util.BlurHandler;
import keletu.pvzmod.box.client.util.ColorTools;
import keletu.pvzmod.box.client.util.GuiItemMove;
import keletu.pvzmod.box.client.util.PlantPreviewRenderer;
import keletu.pvzmod.box.client.util.RenderFontTool;
import keletu.pvzmod.init.PVZSounds;
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
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlantBoxLookItemScreen extends Screen {
    private static final ResourceLocation BACK_BOX = new ResourceLocation(PVZMod.MODID, "textures/screens/atlas/back_box.png");

    private final Level world;
    private final Player entity;
    private final ItemStack openItem;
    private final int grade;
    private boolean openSwitch = true;
    private LivingEntity previewPlant;
    private float itemRotX;
    private float itemRotY;

    public PlantBoxLookItemScreen(ItemStack openItem, int grade) {
        super(Component.literal("plant_box_look_item"));
        this.minecraft = Minecraft.getInstance();
        this.entity = this.minecraft.player;
        this.world = this.entity == null ? null : this.entity.level();
        this.openItem = openItem.copy();
        this.grade = grade;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        this.renderLabels(guiGraphics);
        this.renderBg(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0 && isInPreview(mouseX, mouseY)) {
            this.itemRotX = GuiItemMove.renderRotAngleX(dragX, this.itemRotX);
            this.itemRotY = GuiItemMove.renderRotAngleY(dragY, this.itemRotY);
        }
        super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        return true;
    }

    protected void renderBg(GuiGraphics guiGraphics) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        BlurHandler.updateShader(false);
        if (this.minecraft != null) {
            this.minecraft.options.hideGui = true;
        }

        int frameWidth = this.width * 26 / 100;
        float scale = frameWidth / 16F;
        guiGraphics.fill(this.width * 25 / 100, this.height * 92 / 100, this.width * 75 / 100, this.height * 92 / 100 + 1, 0xFFD3D3D3);
        guiGraphics.fill(this.width * 37 / 100, this.height * 16 / 100, this.width * 63 / 100, this.height * 16 / 100 + 2, ColorTools.colorItems(this.grade));
        LivingEntity plant = previewPlant();
        if (plant != null) {
            PlantPreviewRenderer.render(guiGraphics, plant, this.width / 2, this.height * 76 / 100, previewModelScale(), this.itemRotX, this.itemRotY);
        } else if (this.entity != null) {
            GuiItemMove.renderItemInInventoryFollowsMouse(guiGraphics, this.width * 37 / 100, this.height * 30 / 100, this.itemRotX, this.itemRotY, this.openItem, this.entity, scale);
        }
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            closeScreen();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        if (this.minecraft == null) {
            return;
        }
        if (this.minecraft.level != null) {
            guiGraphics.fillGradient(0, 0, this.width, this.height, BlurHandler.getBackgroundColor(), BlurHandler.getBackgroundColor());
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, guiGraphics));
        } else {
            this.renderDirtBackground(guiGraphics);
        }
    }

    @Override
    public final void tick() {
        super.tick();
        if (this.minecraft == null || this.minecraft.player == null) {
            return;
        }
        if (this.minecraft.player.isAlive() && !this.minecraft.player.isRemoved()) {
            this.containerTick();
            PlantPreviewRenderer.tickPreviewEntity(this.previewPlant);
        } else {
            this.minecraft.player.closeContainer();
        }
    }

    public void containerTick() {
        if (!this.openSwitch || this.entity == null) {
            return;
        }
        this.entity.playSound(PVZSounds.PLANT_BOX_FINISH.get(), 10F, 1F);
        this.openSwitch = false;
    }

    protected void renderLabels(GuiGraphics guiGraphics) {
        Style bold = Style.EMPTY.withBold(true);
        renderText(guiGraphics, this.openItem.getHoverName().getVisualOrderText(), this.width * 45F / 100F, this.height * 5F / 100F, 1.8F);
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.grade" + this.grade).getVisualOrderText(), this.width * 45F / 100F, this.height * 11F / 100F, 1F);
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.back_box").withStyle(bold).getVisualOrderText(), this.width * 72.5F / 100F, this.height * 95F / 100F, 0.8F);
    }

    private void renderText(GuiGraphics guiGraphics, FormattedCharSequence text, float x, float y, float scale) {
        RenderFontTool.drawString(guiGraphics, this.font, text, x, y, 0, 0, scale, 0xFFFFFFFF);
    }

    @Override
    public void init() {
        super.init();
        this.addRenderableWidget(new ImageButton(this.width * 72 / 100, this.height * 94 / 100, this.width * 4 / 100, this.height * 5 / 100, 0, 0, 64, BACK_BOX, 82, 128, button -> closeScreen()));
    }

    private void closeScreen() {
        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.closeContainer();
            BlurHandler.updateShader(true);
            this.minecraft.options.hideGui = false;
        }
    }

    private LivingEntity previewPlant() {
        if (this.previewPlant == null) {
            this.previewPlant = PlantPreviewRenderer.createPreviewEntity(this.openItem, this.world);
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
