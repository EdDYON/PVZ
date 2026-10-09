package keletu.pvzmod.box;

import keletu.pvzmod.PVZMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public final class PlantBoxItemParser {
    private static final String WITH_TAGS = ".withTags";

    private PlantBoxItemParser() {
    }

    public static ItemStack parse(String value) {
        return parse(value, null);
    }

    public static ItemStack parse(String value, RandomSource random) {
        if (value == null || value.isBlank()) {
            return ItemStack.EMPTY;
        }

        String itemId = value.trim();
        String tagValue = null;
        String countValue = null;
        int tagIndex = itemId.indexOf(WITH_TAGS);
        if (tagIndex >= 0) {
            tagValue = itemId.substring(tagIndex + WITH_TAGS.length()).trim();
            itemId = itemId.substring(0, tagIndex).trim();
        }

        int countIndex = itemId.lastIndexOf('*');
        if (countIndex >= 0) {
            countValue = itemId.substring(countIndex + 1).trim();
            itemId = itemId.substring(0, countIndex).trim();
        }

        ResourceLocation itemLocation = ResourceLocation.tryParse(itemId);
        if (itemLocation == null) {
            PVZMod.LOGGER.warn("Invalid plant box reward id: {}", value);
            return ItemStack.EMPTY;
        }

        Item item = ForgeRegistries.ITEMS.getValue(itemLocation);
        if (item == null) {
            PVZMod.LOGGER.warn("Unknown plant box reward item: {}", itemLocation);
            return ItemStack.EMPTY;
        }

        ItemStack stack = new ItemStack(item);
        if (tagValue != null && tagValue.startsWith("{")) {
            try {
                CompoundTag tag = TagParser.parseTag(tagValue);
                stack.setTag(tag);
            } catch (Exception exception) {
                PVZMod.LOGGER.warn("Invalid plant box reward tag for {}: {}", itemLocation, tagValue);
            }
        }

        stack.setCount(parseCount(countValue, stack.getMaxStackSize(), random));
        return stack;
    }

    private static int parseCount(String value, int maxStackSize, RandomSource random) {
        if (value == null || value.isBlank()) {
            return 1;
        }

        String[] range = value.split("-", 2);
        try {
            int min = Integer.parseInt(range[0].trim());
            int max = range.length > 1 ? Integer.parseInt(range[1].trim()) : min;
            min = Mth.clamp(min, 1, maxStackSize);
            max = Mth.clamp(max, min, maxStackSize);
            if (random == null || min == max) {
                return min;
            }
            return min + random.nextInt(max - min + 1);
        } catch (NumberFormatException exception) {
            PVZMod.LOGGER.warn("Invalid plant box reward count: {}", value);
            return 1;
        }
    }
}
