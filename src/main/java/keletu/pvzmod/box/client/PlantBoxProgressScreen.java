package keletu.pvzmod.box.client;

import com.mojang.blaze3d.systems.RenderSystem;
import keletu.pvzmod.box.client.util.BlurHandler;
import keletu.pvzmod.box.client.util.ColorTools;
import keletu.pvzmod.box.client.util.IconListTools;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class PlantBoxProgressScreen extends Screen {
    private static final Random RANDOM = new Random();

    private final Level world;
    private final Player entity;
    private final List<ItemStack> itemInput;
    private final List<Integer> gradeInput;
    private final List<Float> velocityExport;
    private List<Float> renderExport = List.of();
    private float lastRenderWidth;
    private boolean startSwitch = true;
    private float velocityLerp;
    private float soundWidthAdd;
    private boolean soundSwitch = true;
    private float randomWidth = 93.5F + RANDOM.nextFloat() * (111F - 93.5F);
    private int startTime;
    private float startWidth;
    private int openTime;
    private float renderWidthAdd;
    private boolean resultOpened;

    public PlantBoxProgressScreen(List<ItemStack> items, List<Integer> grades) {
        super(Component.literal("plant_box_progress"));
        this.minecraft = Minecraft.getInstance();
        this.entity = this.minecraft.player;
        this.world = this.entity == null ? null : this.entity.level();
        this.itemInput = normalizeItems(items);
        this.gradeInput = normalizeGrades(grades, this.itemInput.size());
        this.velocityExport = renderCount();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        if (this.minecraft == null || this.minecraft.player == null) {
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderBackground(guiGraphics);
        this.renderBg(guiGraphics, partialTicks);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTicks) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        BlurHandler.updateShader(false);
        if (this.minecraft == null) {
            return;
        }
        this.minecraft.options.hideGui = true;
        if (this.openTime < 5) {
            return;
        }

        float widthNewAdd = this.renderWidthAdd;
        if (this.width != this.startWidth && this.startWidth > 0) {
            widthNewAdd *= this.width / this.startWidth;
        }
        float progress = Math.min(1, partialTicks + this.velocityLerp);
        for (int i = 0; i < this.itemInput.size(); i++) {
            int grade = this.gradeInput.get(i);
            ItemStack itemStack = this.itemInput.get(i);
            IconListTools.renderItemProgress(this.entity, guiGraphics, itemStack, this.width * this.randomWidth / 100F + i * this.width * 20F / 100F - Mth.lerp(progress, this.lastRenderWidth, widthNewAdd), this.height * 37F / 100F, this.width, this.height, grade);
        }
        this.lastRenderWidth = widthNewAdd;

        int goldLineTo = this.height * 37 / 100 + this.height * 25 / 100;
        IconListTools.fill(guiGraphics, this.width / 2F, this.height * 37 / 100F, this.width / 2F + 2, goldLineTo + 2, ColorTools.argbColor(128, 255, 215, 0));
        RenderSystem.disableBlend();

        RenderSystem.enableBlend();
        IconListTools.blit(guiGraphics, IconListTools.backgroundTexture(), 0, 0, 0, 0, this.width, this.height, this.width, this.height);
        RenderSystem.disableBlend();
    }

    public List<Float> renderCount() {
        List<Float> renderMove = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            float time = i / 20F;
            float velocity = (1.6F * time + 0.8F) / ((float) Math.pow(2, 1.5F * time - 5.2F));
            if (velocity < 0.3F) {
                velocity = 0;
            }
            renderMove.add(velocity);
        }
        return renderMove;
    }

    public List<Float> renderMove(List<Float> list) {
        List<Float> renderMove = new ArrayList<>();
        float widthAdd = 0;
        for (float velocity : list) {
            widthAdd += this.startWidth / 173F * velocity;
            renderMove.add(widthAdd);
        }
        return renderMove;
    }

    @Override
    public final void tick() {
        super.tick();
        if (this.minecraft == null || this.minecraft.player == null) {
            return;
        }
        if (this.minecraft.player.isAlive() && !this.minecraft.player.isRemoved()) {
            this.containerTick();
        } else {
            this.minecraft.player.closeContainer();
        }
    }

    public void containerTick() {
        this.openTime++;
        if (this.openTime < 2) {
            return;
        }
        if (this.startSwitch) {
            this.startWidth = this.width;
            this.startSwitch = false;
            this.renderExport = renderMove(this.velocityExport);
        }
        if (this.openTime < 5) {
            return;
        }

        if (this.startTime < 145) {
            this.startTime++;
        }
        if (this.startTime == 145 && !this.resultOpened) {
            this.resultOpened = true;
            int resultIndex = Math.min(45, this.itemInput.size() - 1);
            Minecraft.getInstance().setScreen(new PlantBoxLookItemScreen(this.itemInput.get(resultIndex), this.gradeInput.get(resultIndex)));
            return;
        }

        float velocity = this.velocityExport.get(this.startTime);
        this.velocityLerp = velocity / 35;
        this.renderWidthAdd = this.renderExport.get(this.startTime);
        playTickSound(velocity);
    }

    private void playTickSound(float velocity) {
        if (this.world == null || this.entity == null) {
            return;
        }
        float firstStop = this.startWidth * this.randomWidth / 100F - this.startWidth / 2;
        float secondStop = firstStop + this.startWidth * 20F * 35 / 100F;
        if (this.renderWidthAdd > firstStop && this.renderWidthAdd < secondStop) {
            this.soundWidthAdd += this.startWidth / 173F * velocity;
            if (this.soundWidthAdd > this.startWidth * 20F / 100F) {
                this.soundWidthAdd = 0;
                this.world.playSound(this.entity, this.entity.getX(), this.entity.getY(), this.entity.getZ(), PVZSounds.PLANT_BOX_TICK.get(), SoundSource.NEUTRAL, 10F, 1F);
            }
        } else if (this.renderWidthAdd >= secondStop) {
            if (this.soundSwitch) {
                this.soundWidthAdd = 0;
                this.soundSwitch = false;
            }
            this.soundWidthAdd += this.startWidth / 173F * velocity;
            if (this.soundWidthAdd > this.startWidth * 20F / 100F) {
                this.soundWidthAdd = 0;
                this.world.playSound(this.entity, this.entity.getX(), this.entity.getY(), this.entity.getZ(), PVZSounds.PLANT_BOX_TICK.get(), SoundSource.NEUTRAL, 10F, 1F);
            }
        }
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            if (this.entity != null) {
                this.entity.closeContainer();
            }
            BlurHandler.updateShader(true);
            if (this.minecraft != null) {
                this.minecraft.options.hideGui = false;
            }
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    private static List<ItemStack> normalizeItems(List<ItemStack> items) {
        List<ItemStack> normalized = new ArrayList<>();
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                normalized.add(stack.copy());
            }
        }
        while (normalized.size() < 50 && !normalized.isEmpty()) {
            normalized.add(normalized.get(normalized.size() % Math.max(1, normalized.size())).copy());
        }
        return normalized;
    }

    private static List<Integer> normalizeGrades(List<Integer> grades, int size) {
        List<Integer> normalized = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int grade = i < grades.size() ? grades.get(i) : 1;
            normalized.add(Mth.clamp(grade, 0, 5));
        }
        return normalized;
    }
}
