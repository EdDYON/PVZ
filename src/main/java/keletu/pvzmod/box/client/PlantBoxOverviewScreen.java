package keletu.pvzmod.box.client;

import com.mojang.blaze3d.systems.RenderSystem;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.box.ItemPlantBox;
import keletu.pvzmod.box.PlantBoxInfo;
import keletu.pvzmod.box.client.util.BlurHandler;
import keletu.pvzmod.box.client.util.GuiItemMove;
import keletu.pvzmod.box.client.util.IconListTools;
import keletu.pvzmod.box.client.util.RenderFontTool;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.network.OpenPlantBoxRequestPacket;
import keletu.pvzmod.network.PVZNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlantBoxOverviewScreen extends Screen {
    private static final ResourceLocation OPEN_BOX = new ResourceLocation(PVZMod.MODID, "textures/screens/atlas/open_box.png");
    private static final ResourceLocation BACK_BOX = new ResourceLocation(PVZMod.MODID, "textures/screens/atlas/back_box.png");
    private static final int[] PREVIEW_LIMITS_BY_GRADE = new int[]{0, 4, 4, 4, 4, 1};

    private final Level world;
    private final Player entity;
    private final ItemStack itemMenu;
    private final PlantBoxInfo info;
    private final boolean placed;
    private final BlockPos pos;
    private final List<ItemStack> itemsList;
    private final List<Integer> gradeList;
    private final ItemStack itemKey;
    private int gameTick;
    private int boxKeyCount;
    private float itemRotX;
    private float itemRotY;

    public PlantBoxOverviewScreen(ItemStack boxStack, PlantBoxInfo info, boolean placed, BlockPos pos) {
        super(Component.literal("plant_box_screen"));
        this.minecraft = Minecraft.getInstance();
        this.entity = this.minecraft.player;
        this.world = this.entity == null ? null : this.entity.level();
        this.itemMenu = boxStack.copy();
        this.info = info;
        this.placed = placed;
        this.pos = pos == null ? BlockPos.ZERO : pos.immutable();
        List<PreviewReward> previewRewards = previewRewards(ItemPlantBox.getItemGroup(info));
        this.itemsList = previewItems(previewRewards);
        this.gradeList = previewGrades(previewRewards);
        this.itemKey = keyStack(info.boxKey);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private static List<PreviewReward> previewRewards(Map<ItemStack, Integer> itemList) {
        List<PreviewReward> rewards = new ArrayList<>();
        boolean hasGold = false;
        for (int grade = 1; grade < 5; grade++) {
            int shown = 0;
            for (Map.Entry<ItemStack, Integer> entry : itemList.entrySet()) {
                if (entry.getValue() == grade) {
                    rewards.add(new PreviewReward(entry.getKey().copy(), grade));
                    shown++;
                    if (shown >= PREVIEW_LIMITS_BY_GRADE[grade]) {
                        break;
                    }
                }
            }
        }
        for (Map.Entry<ItemStack, Integer> entry : itemList.entrySet()) {
            if (entry.getValue() == 5) {
                hasGold = true;
                break;
            }
        }
        if (hasGold) {
            rewards.add(new PreviewReward(ItemStack.EMPTY, 5));
        }
        return rewards;
    }

    private static List<ItemStack> previewItems(List<PreviewReward> rewards) {
        List<ItemStack> items = new ArrayList<>();
        for (PreviewReward reward : rewards) {
            items.add(reward.stack().copy());
        }
        return items;
    }

    private static List<Integer> previewGrades(List<PreviewReward> rewards) {
        List<Integer> grades = new ArrayList<>();
        for (PreviewReward reward : rewards) {
            grades.add(reward.grade());
        }
        return grades;
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
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        boolean inRange = mouseX >= this.width * 37F / 100F && mouseX <= this.width * 37F / 100F + 200
                && mouseY >= this.height * 12F / 100F && mouseY <= this.height * 12F / 100F + 176;
        if (button == 0 && inRange) {
            this.itemRotX = GuiItemMove.renderRotAngleX(dragX, this.itemRotX);
            this.itemRotY = GuiItemMove.renderRotAngleY(dragY, this.itemRotY);
        }
        super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        return true;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        this.renderLabels(guiGraphics);
        this.renderBg(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    protected void renderBg(GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();
        guiGraphics.fill(this.width * 3 / 100, this.height * 53 / 100, this.width * 97 / 100, this.height * 53 / 100 + 1, 0xFFD3D3D3);
        guiGraphics.fill(this.width * 25 / 100, this.height * 92 / 100, this.width * 75 / 100, this.height * 92 / 100 + 1, 0xFFD3D3D3);
        BlurHandler.updateShader(false);
        if (this.minecraft != null) {
            this.minecraft.options.hideGui = true;
        }
        RenderSystem.disableBlend();

        if (this.entity == null) {
            return;
        }

        int frameWidth = this.width * 26 / 100;
        float scale = frameWidth / 16F;
        GuiItemMove.renderItemInInventoryFollowsMouse(guiGraphics, this.width * 37 / 100, this.height * 12 / 100, this.itemRotX, this.itemRotY, this.itemMenu, this.entity, scale);

        int x = 0;
        int y = 0;
        boolean hasGold = false;
        for (int i = 0; i < this.itemsList.size(); i++) {
            int py = 55;
            int px = i;
            if (i > 9) {
                py = 73;
                px = i - 10;
            }
            int grade = this.gradeList.get(i);
            x = px;
            y = py;
            if (grade == 5) {
                hasGold = true;
                break;
            }
            IconListTools.renderItemFrame(this.entity, guiGraphics, this.itemsList.get(i), this.width * 4 / 100 + px * this.width * 9 / 100, this.height * py / 100, this.width, this.height, grade);
        }

        if (hasGold) {
            IconListTools.renderItemFrame(this.entity, guiGraphics, ItemStack.EMPTY, this.width * 4 / 100 + x * this.width * 9 / 100, this.height * y / 100, this.width, this.height, 5);
        }

        if (!this.itemKey.isEmpty()) {
            IconListTools.renderGuiItem(this.entity, this.world, guiGraphics, this.itemKey, this.width * 25F / 100F, this.height * 93F / 100F, 1);
        }
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            closeScreen();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    protected void renderLabels(GuiGraphics guiGraphics) {
        Style style = Style.EMPTY.withBold(true);
        int x = 0;
        int y = 0;
        boolean hasGold = false;

        for (int i = 0; i < this.itemsList.size(); i++) {
            int py = 67;
            int px = i;
            if (i > 9) {
                py = 85;
                px = i - 10;
            }
            int grade = this.gradeList.get(i);
            x = px;
            y = py;
            if (grade > 4) {
                hasGold = true;
                break;
            }
            renderText(guiGraphics, this.itemsList.get(i).getHoverName().getVisualOrderText(), this.width * 4F / 100F + px * this.width * 9F / 100F, this.height * py / 100F, 0.6F);
        }
        if (hasGold) {
            renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.label_gold").getVisualOrderText(), this.width * 4F / 100F + x * this.width * 9F / 100F, this.height * y / 100F, 0.6F);
        }

        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.label_box").getVisualOrderText(), this.width * 46F / 100F, this.height * 13F / 100F, 0.8F);
        renderText(guiGraphics, this.itemMenu.getHoverName().getVisualOrderText(), this.width * 50F / 100F, this.height * 13F / 100F, 0.8F);

        if (!this.itemKey.isEmpty()) {
            if (this.boxKeyCount > 0) {
                renderText(guiGraphics, " x " + this.boxKeyCount, this.width * 28F / 100F, this.height * 94F / 100F, 0.8F);
            } else {
                renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.label_open").getVisualOrderText(), this.width * 28F / 100F, this.height * 94F / 100F, 0.8F);
                renderText(guiGraphics, this.itemKey.getHoverName().getVisualOrderText(), this.width * 35F / 100F, this.height * 94F / 100F, 0.8F);
                renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.label_open_1").getVisualOrderText(), this.width * 40F / 100F, this.height * 94F / 100F, 0.8F);
            }
        }

        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.label_items").withStyle(style).getVisualOrderText(), this.width * 3F / 100F, this.height * 50.3F / 100F, 0.8F);
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.title").withStyle(style).getVisualOrderText(), this.width * 47.6F / 100F, this.height * 5.9F / 100F, 2F);
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.open_box").withStyle(style).getVisualOrderText(), this.width * 67.5F / 100F, this.height * 95F / 100F, 0.8F);
        renderText(guiGraphics, Component.translatable("gui.pvz_myh.plant_box.back_box").withStyle(style).getVisualOrderText(), this.width * 72.5F / 100F, this.height * 95F / 100F, 0.8F);
    }

    private int countKeys() {
        if (this.entity == null || this.entity.getAbilities().instabuild) {
            return this.itemKey.isEmpty() ? 0 : 1;
        }
        ResourceLocation keyId = ResourceLocation.tryParse(this.info.boxKey);
        if (keyId == null) {
            return 0;
        }

        int count = 0;
        for (ItemStack stack : this.entity.getInventory().items) {
            if (!stack.isEmpty() && Objects.equals(ForgeRegistries.ITEMS.getKey(stack.getItem()), keyId)) {
                count += stack.getCount();
            }
        }
        for (ItemStack stack : this.entity.getInventory().offhand) {
            if (!stack.isEmpty() && Objects.equals(ForgeRegistries.ITEMS.getKey(stack.getItem()), keyId)) {
                count += stack.getCount();
            }
        }
        return count;
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
        this.gameTick++;
        if (this.gameTick == 1 || this.gameTick % 50 == 0) {
            this.boxKeyCount = countKeys();
        }
        if (this.gameTick > 100000) {
            this.gameTick = 0;
        }
        this.gameTick = Mth.clamp(this.gameTick, 0, 100);
    }

    private void renderText(GuiGraphics guiGraphics, FormattedCharSequence text, float x, float y, float scale) {
        RenderFontTool.drawString(guiGraphics, this.font, text, x, y, 0, 0, scale, 0xD3D3D3);
    }

    private void renderText(GuiGraphics guiGraphics, String text, float x, float y, float scale) {
        RenderFontTool.drawString(guiGraphics, this.font, text, x, y, 0, 0, scale, 0xD3D3D3);
    }

    @Override
    public void init() {
        super.init();
        this.addRenderableWidget(new ImageButton(this.width * 67 / 100, this.height * 94 / 100, this.width * 4 / 100, this.height * 5 / 100, 0, 0, 64, OPEN_BOX, 82, 128, button -> {
            this.boxKeyCount = countKeys();
            if (this.boxKeyCount > 0 && canRequestOpen()) {
                PVZNetworking.sendToServer(new OpenPlantBoxRequestPacket(this.placed, this.pos));
            }
        }));
        this.addRenderableWidget(new ImageButton(this.width * 72 / 100, this.height * 94 / 100, this.width * 4 / 100, this.height * 5 / 100, 0, 0, 64, BACK_BOX, 82, 128, button -> closeScreen()));
    }

    private boolean canRequestOpen() {
        if (this.placed) {
            return true;
        }
        return this.entity != null && this.entity.getMainHandItem().is(PVZItems.PLANT_BOX.get());
    }

    private void closeScreen() {
        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.closeContainer();
            BlurHandler.updateShader(true);
            this.minecraft.options.hideGui = false;
        }
    }

    private static ItemStack keyStack(String itemId) {
        ResourceLocation location = ResourceLocation.tryParse(itemId);
        if (location == null || ForgeRegistries.ITEMS.getValue(location) == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(ForgeRegistries.ITEMS.getValue(location));
    }

    private record PreviewReward(ItemStack stack, int grade) {
    }
}
