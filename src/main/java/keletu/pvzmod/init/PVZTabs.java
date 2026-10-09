package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PVZTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PVZMod.MODID);

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("pvz_myh",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .icon(() -> new ItemStack(PVZItems.PEA.get()))
                    .title(Component.translatable("creativetab.pvz_myh"))
                    .displayItems((params, output) -> {
                        PVZItems.ITEMS.getEntries().forEach(ro -> output.accept(ro.get()));
                    })
                    .build()
    );
}