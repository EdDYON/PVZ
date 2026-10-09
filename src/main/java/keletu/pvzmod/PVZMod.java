package keletu.pvzmod;

import com.mojang.logging.LogUtils;
import keletu.pvzmod.box.PlantBoxManager;
import keletu.pvzmod.init.*;
import keletu.pvzmod.network.PVZNetworking;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PVZMod.MODID)
public class PVZMod {
    public static final String MODID = "pvz_myh";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PVZMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PVZBlocks.BLOCKS.register(modEventBus);
        PVZBlocks.BLOCK_ENTITIES.register(modEventBus);
        PVZItems.ITEMS.register(modEventBus);
        PVZMenus.MENUS.register(modEventBus);
        PVZEntities.ENTITIES.register(modEventBus);
        PVZEffects.EFFECTS.register(modEventBus);
        PVZSounds.SOUNDS.register(modEventBus);
        PVZParticles.PARTICLES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(PVZEntities::registerEntityAttributes);

        PVZTabs.TABS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PVZConfig.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PVZNetworking.register();
        event.enqueueWork(() -> {
            PlantBoxManager.loadOrCreateDefaults();
            PlantStatManager.loadOrCreateDefaults();
        });
    }
}
