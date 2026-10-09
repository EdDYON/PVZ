package keletu.pvzmod.network;

import keletu.pvzmod.box.ItemPlantBox;
import keletu.pvzmod.box.client.PlantBoxClientScreens;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class StartPlantBoxOpenPacket {
    private final List<ItemStack> items;
    private final List<Integer> grades;

    public StartPlantBoxOpenPacket(List<ItemStack> items, List<Integer> grades) {
        this.items = items;
        this.grades = grades;
    }

    public static StartPlantBoxOpenPacket fromRewards(List<ItemPlantBox.PlantBoxReward> rewards) {
        List<ItemStack> items = new ArrayList<>();
        List<Integer> grades = new ArrayList<>();
        for (ItemPlantBox.PlantBoxReward reward : rewards) {
            items.add(reward.stack().copy());
            grades.add(reward.grade());
        }
        return new StartPlantBoxOpenPacket(items, grades);
    }

    public static void encode(StartPlantBoxOpenPacket packet, FriendlyByteBuf buffer) {
        int size = Math.min(packet.items.size(), packet.grades.size());
        buffer.writeVarInt(size);
        for (int i = 0; i < size; i++) {
            buffer.writeItem(packet.items.get(i));
            buffer.writeVarInt(packet.grades.get(i));
        }
    }

    public static StartPlantBoxOpenPacket decode(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        List<ItemStack> items = new ArrayList<>();
        List<Integer> grades = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(buffer.readItem());
            grades.add(buffer.readVarInt());
        }
        return new StartPlantBoxOpenPacket(items, grades);
    }

    public static void handle(StartPlantBoxOpenPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(
                Dist.CLIENT,
                () -> () -> PlantBoxClientScreens.openProgress(packet.items, packet.grades)
        ));
        context.setPacketHandled(true);
    }
}
