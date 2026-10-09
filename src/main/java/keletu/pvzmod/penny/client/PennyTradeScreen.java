package keletu.pvzmod.penny.client;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.network.BuyPennyTradePacket;
import keletu.pvzmod.network.PVZNetworking;
import keletu.pvzmod.penny.PennyTradeMenu;
import keletu.pvzmod.penny.PennyTradeOffer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PennyTradeScreen extends AbstractContainerScreen<PennyTradeMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(PVZMod.MODID, "textures/gui/penny_trade.png");
    private static final int TEXTURE_WIDTH = 240;
    private static final int TEXTURE_HEIGHT = 240;
    private static final int GUI_WIDTH = 240;
    private static final int GUI_HEIGHT = 240;
    private static final int OFFER_SIZE = 24;

    private static final int[][] OFFER_CENTERS = {
            {76, 60},
            {110, 60},
            {144, 60},
            {66, 121},
            {110, 121},
            {154, 121}
    };

    public PennyTradeScreen(PennyTradeMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = GUI_WIDTH;
        this.imageHeight = GUI_HEIGHT;
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderOffers(graphics, mouseX, mouseY);
        this.renderOfferTooltip(graphics, mouseX, mouseY);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND, this.leftPos, this.topPos, 0.0F, 0.0F,
                GUI_WIDTH, GUI_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int hovered = this.hoveredOffer(mouseX, mouseY);
            if (hovered >= 0) {
                PVZNetworking.sendToServer(new BuyPennyTradePacket(hovered));
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public void applyOfferUpdate(List<PennyTradeOffer> offers) {
        this.menu.replaceOffers(offers);
    }

    private void renderOffers(GuiGraphics graphics, int mouseX, int mouseY) {
        List<PennyTradeOffer> offers = this.menu.getOffers();
        int hovered = this.hoveredOffer(mouseX, mouseY);
        for (int i = 0; i < offers.size() && i < OFFER_CENTERS.length; i++) {
            ItemStack stack = offers.get(i).stack();
            if (stack.isEmpty()) {
                continue;
            }
            this.renderShelfItem(graphics, stack, i, i == hovered);
        }
    }

    private void renderShelfItem(GuiGraphics graphics, ItemStack stack, int index, boolean hovered) {
        int centerX = this.leftPos + OFFER_CENTERS[index][0];
        int centerY = this.topPos + OFFER_CENTERS[index][1];
        float scale = hovered ? 1.08F : 1.0F;

        graphics.pose().pushPose();
        graphics.pose().translate(centerX, centerY, 260.0F);
        graphics.pose().scale(scale, scale, 1.0F);
        graphics.renderItem(stack, -8, -8);
        graphics.renderItemDecorations(this.font, stack, -8, -8);
        graphics.pose().popPose();
    }

    private void renderOfferTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        int index = this.hoveredOffer(mouseX, mouseY);
        if (index < 0 || index >= this.menu.getOffers().size()) {
            return;
        }

        PennyTradeOffer offer = this.menu.getOffers().get(index);
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(offer.stack().getHoverName());
        tooltip.add(Component.translatable("tooltip.pvz_myh.penny.price", offer.price(), new ItemStack(PVZItems.DIAMOND.get()).getHoverName()));
        tooltip.add(Component.translatable("tooltip.pvz_myh.penny.daily_limit"));
        graphics.renderComponentTooltip(this.font, tooltip, this.offerTooltipX(index), this.offerTooltipY(index));
    }

    private int hoveredOffer(double mouseX, double mouseY) {
        for (int i = 0; i < OFFER_CENTERS.length; i++) {
            if (i < this.menu.getOffers().size() && !this.menu.getOffers().get(i).stack().isEmpty() && this.isInOffer(mouseX, mouseY, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isInOffer(double mouseX, double mouseY, int index) {
        int centerX = this.leftPos + OFFER_CENTERS[index][0];
        int centerY = this.topPos + OFFER_CENTERS[index][1];
        return mouseX >= centerX - OFFER_SIZE / 2.0D
                && mouseX < centerX + OFFER_SIZE / 2.0D
                && mouseY >= centerY - OFFER_SIZE / 2.0D
                && mouseY < centerY + OFFER_SIZE / 2.0D;
    }

    private int offerTooltipX(int index) {
        int centerX = this.leftPos + OFFER_CENTERS[index][0];
        if (centerX > this.leftPos + GUI_WIDTH / 2) {
            return Math.max(8, centerX - 132);
        }
        return Math.min(this.width - 100, centerX + 18);
    }

    private int offerTooltipY(int index) {
        int centerY = this.topPos + OFFER_CENTERS[index][1];
        if (index < 3) {
            return Math.min(this.height - 36, centerY + 18);
        }
        return Math.max(8, centerY - 34);
    }
}
