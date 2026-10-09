package keletu.pvzmod.box.client;

import keletu.pvzmod.box.ItemPlantBox;
import keletu.pvzmod.box.PlantBoxInfo;
import keletu.pvzmod.init.PVZSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public final class PlantBoxClientScreens {
    private PlantBoxClientScreens() {
    }

    public static void openOverviewHeld(ItemStack boxStack) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.playSound(PVZSounds.PLANT_BOX_OPEN.get(), 10F, 1F);
        }
        minecraft.setScreen(new PlantBoxOverviewScreen(boxStack.copy(), ItemPlantBox.getInfoOrDefault(boxStack), false, BlockPos.ZERO));
    }

    public static void openOverviewPlaced(BlockPos pos, PlantBoxInfo info) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            minecraft.player.playSound(PVZSounds.PLANT_BOX_OPEN.get(), 10F, 1F);
        }
        ItemStack boxStack = ItemPlantBox.setBoxInfo(info, new ItemStack(keletu.pvzmod.init.PVZItems.PLANT_BOX.get()));
        minecraft.setScreen(new PlantBoxOverviewScreen(boxStack, info, true, pos));
    }

    public static void openProgress(List<ItemStack> items, List<Integer> grades) {
        Minecraft.getInstance().setScreen(new PlantBoxProgressScreen(items, grades));
    }

    public static void openResult(ItemStack reward, int grade) {
        Minecraft.getInstance().setScreen(new PlantBoxLookItemScreen(reward.copy(), grade));
    }
}
