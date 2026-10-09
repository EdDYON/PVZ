package keletu.pvzmod.penny;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public record PennyTradeOffer(ItemStack stack, int price) {
    public PennyTradeOffer {
        stack = stack.copy();
    }

    public static void encode(PennyTradeOffer offer, FriendlyByteBuf buffer) {
        buffer.writeItem(offer.stack());
        buffer.writeVarInt(offer.price());
    }

    public static PennyTradeOffer decode(FriendlyByteBuf buffer) {
        return new PennyTradeOffer(buffer.readItem(), buffer.readVarInt());
    }

    public static void encodeList(List<PennyTradeOffer> offers, FriendlyByteBuf buffer) {
        buffer.writeVarInt(offers.size());
        for (PennyTradeOffer offer : offers) {
            encode(offer, buffer);
        }
    }

    public static List<PennyTradeOffer> decodeList(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        List<PennyTradeOffer> offers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            offers.add(decode(buffer));
        }
        return offers;
    }
}
