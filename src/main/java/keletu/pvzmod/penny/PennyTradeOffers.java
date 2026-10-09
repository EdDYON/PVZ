package keletu.pvzmod.penny;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class PennyTradeOffers {
    public static final int OFFER_COUNT = 6;

    private static final List<CardEntry> CARD_POOL = List.of(
            card(PVZItems.SNOWPEA_CARD, 10),
            card(PVZItems.REPEATER_CARD, 10),
            card(PVZItems.SCAREDY_SHROOM_CARD, 12),
            card(PVZItems.FUME_SHROOM_CARD, 12),
            card(PVZItems.GATLING_PEA_CARD, 14),
            card(PVZItems.PUMPKIN_CARD, 14),
            card(PVZItems.PRIMAL_PEASHOOTER_CARD, 18),
            card(PVZItems.ELECTRIC_PEASHOOTER_CARD, 18),
            card(PVZItems.TALL_NUT_CARD, 18)
    );

    private PennyTradeOffers() {
    }

    public static List<PennyTradeOffer> roll(RandomSource random) {
        List<PennyTradeOffer> offers = new ArrayList<>(OFFER_COUNT);
        offers.add(new PennyTradeOffer(new ItemStack(PVZItems.COMMON_CHEST.get()), 8));
        offers.add(new PennyTradeOffer(new ItemStack(PVZItems.EPIC_CHEST.get()), 16));
        offers.add(new PennyTradeOffer(new ItemStack(PVZItems.LEGENDARY_CHEST.get()), 32));

        List<CardEntry> cards = new ArrayList<>(CARD_POOL);
        for (int i = 0; i < 3; i++) {
            CardEntry chosen = cards.remove(random.nextInt(cards.size()));
            offers.add(new PennyTradeOffer(new ItemStack(chosen.item().get()), chosen.price()));
        }

        return offers;
    }

    public static List<PennyTradeOffer> roll(RandomSource random, List<PennyTradeOffer> previousOffers) {
        return roll(random);
    }

    private static CardEntry card(Supplier<? extends Item> item, int price) {
        return new CardEntry(item, price);
    }

    private record CardEntry(Supplier<? extends Item> item, int price) {
    }
}
