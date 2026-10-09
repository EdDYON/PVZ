package keletu.pvzmod.penny.client;

import keletu.pvzmod.penny.PennyTradeMenu;
import keletu.pvzmod.penny.PennyTradeOffer;
import net.minecraft.client.Minecraft;

import java.util.List;

public final class PennyTradeClientSync {
    private PennyTradeClientSync() {
    }

    public static void apply(List<PennyTradeOffer> offers, int mode) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof PennyTradeScreen screen) {
            screen.applyOfferUpdate(offers);
            return;
        }

        if (minecraft.player != null && minecraft.player.containerMenu instanceof PennyTradeMenu menu) {
            menu.replaceOffers(offers);
        }
    }
}
