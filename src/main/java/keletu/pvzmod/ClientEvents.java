package keletu.pvzmod;

import keletu.pvzmod.init.PVZBlocks;
import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.init.PVZMenus;
import keletu.pvzmod.init.PVZParticles;
import keletu.pvzmod.models.*;
import keletu.pvzmod.particles.SporeParticle;
import keletu.pvzmod.particles.TextureBreakParticle;
import keletu.pvzmod.penny.client.PennyTradeScreen;
import keletu.pvzmod.potion.SlowBlueLayer;
import keletu.pvzmod.potion.StunStarsLayer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = PVZMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(PVZMenus.PENNY_TRADE.get(), PennyTradeScreen::new));
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES.getValues()) {
            addLayerToEntity(event, type);
        }

        addLayerToPlayers(event);
    }

    private static void addLayerToPlayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            LivingEntityRenderer<?, ?> playerRenderer = event.getSkin(skin);
            if (playerRenderer != null) {
                addLayerSafe(playerRenderer);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends LivingEntity> void addLayerToEntity(EntityRenderersEvent.AddLayers event, EntityType<?> type) {
        try {
            EntityRenderer<?> renderer = event.getRenderer((EntityType<T>) type);

            if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
                addLayerSafe(livingRenderer);
            }
        } catch (Exception ignore) {
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addLayerSafe(LivingEntityRenderer renderer) {
        renderer.addLayer(new SlowBlueLayer(renderer));
        renderer.addLayer(new StunStarsLayer(renderer));
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PVZParticles.SPORE.get(), SporeParticle.Provider::new);
        event.registerSpecial(PVZParticles.TEXTURE_BREAK.get(), new TextureBreakParticle.Provider());
    }

    @SubscribeEvent
    public static void registerParticleFactories(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SunFlowerModel.LAYER_LOCATION, SunFlowerModel::createBodyLayer);

        event.registerLayerDefinition(PuffShroomModel.LAYER_LOCATION, PuffShroomModel::createBodyLayer);
        event.registerLayerDefinition(FumeShroomModel.LAYER_LOCATION, FumeShroomModel::createBodyLayer);
        event.registerLayerDefinition(ScaredyShroomModel.LAYER_LOCATION, ScaredyShroomModel::createBodyLayer);

        event.registerLayerDefinition(PotatoMineModel.LAYER_LOCATION, PotatoMineModel::createBodyLayer);
        event.registerLayerDefinition(BonkChoyModel.LAYER_LOCATION, BonkChoyModel::createBodyLayer);

        event.registerLayerDefinition(PeaShooterModel.LAYER_LOCATION, PeaShooterModel::createBodyLayer);
        event.registerLayerDefinition(SnowPeaModel.LAYER_LOCATION, SnowPeaModel::createBodyLayer);
        event.registerLayerDefinition(RepeaterModel.LAYER_LOCATION, RepeaterModel::createBodyLayer);
        event.registerLayerDefinition(GatlingPeaModel.LAYER_LOCATION, GatlingPeaModel::createBodyLayer);
        event.registerLayerDefinition(PrimalPeashooterModel.LAYER_LOCATION, PrimalPeashooterModel::createBodyLayer);
        event.registerLayerDefinition(ElectricPeashooterModel.LAYER_LOCATION, ElectricPeashooterModel::createBodyLayer);

        event.registerLayerDefinition(SuperGatlingPeaModel.LAYER_LOCATION, SuperGatlingPeaModel::createBodyLayer);
        event.registerLayerDefinition(SnowySuperGatlingPeaModel.LAYER_LOCATION, SnowySuperGatlingPeaModel::createBodyLayer);
        event.registerLayerDefinition(PrimalSuperGatlingPeaModel.LAYER_LOCATION, PrimalSuperGatlingPeaModel::createBodyLayer);
        event.registerLayerDefinition(SuperElectricGatlingPeaModel.LAYER_LOCATION, SuperElectricGatlingPeaModel::createBodyLayer);

        event.registerLayerDefinition(TallNutModel0.LAYER_LOCATION, TallNutModel0::createBodyLayer);
        event.registerLayerDefinition(TallNutModel1.LAYER_LOCATION, TallNutModel1::createBodyLayer);
        event.registerLayerDefinition(TallNutModel2.LAYER_LOCATION, TallNutModel2::createBodyLayer);

        event.registerLayerDefinition(WalnutModel0.LAYER_LOCATION, WalnutModel0::createBodyLayer);
        event.registerLayerDefinition(WalnutModel1.LAYER_LOCATION, WalnutModel1::createBodyLayer);
        event.registerLayerDefinition(WalnutModel2.LAYER_LOCATION, WalnutModel2::createBodyLayer);

        event.registerLayerDefinition(PumpkinModel.LAYER_LOCATION, PumpkinModel::createBodyLayer);

        event.registerLayerDefinition(PotBlockModel.LAYER_LOCATION, PotBlockModel::createBodyLayer);
        event.registerLayerDefinition(GardenShovelModel.LAYER_LOCATION, GardenShovelModel::createBodyLayer);
        event.registerLayerDefinition(SunItemModel.LAYER_LOCATION, SunItemModel::createBodyLayer);

        event.registerLayerDefinition(PrimalPeaModel.LAYER_LOCATION, PrimalPeaModel::createBodyLayer);
        event.registerLayerDefinition(IcePeaModel.LAYER_LOCATION, IcePeaModel::createBodyLayer);
        event.registerLayerDefinition(ElectricPeaProjectileModel.LAYER_LOCATION, ElectricPeaProjectileModel::createBodyLayer);


        event.registerLayerDefinition(DaveModel.LAYER_LOCATION, DaveModel::createBodyLayer);
        event.registerLayerDefinition(PennyModel.LAYER_LOCATION, PennyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ItemBlockRenderTypes.setRenderLayer(PVZBlocks.CARTON.get(), RenderType.translucent());
        PVZBlocks.registerRenderers(event);
        PVZEntities.registerRenderers(event);
    }
}
