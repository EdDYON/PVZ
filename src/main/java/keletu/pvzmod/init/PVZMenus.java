package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.penny.PennyTradeMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PVZMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, PVZMod.MODID);

    public static final RegistryObject<MenuType<PennyTradeMenu>> PENNY_TRADE = MENUS.register("penny_trade",
            () -> IForgeMenuType.create(PennyTradeMenu::new));
}
