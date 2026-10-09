package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.*;
import keletu.pvzmod.entities.dave.Dave;
import keletu.pvzmod.entities.dave.Penny;
import keletu.pvzmod.entities.projectile.*;
import keletu.pvzmod.models.render.*;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PVZEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PVZMod.MODID);
    public static final RegistryObject<EntityType<PeaShooter>> PEA_SHOOTER = PVZEntities.ENTITIES.register("pea_shooter", () -> EntityType.Builder.of(PeaShooter::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".pea_shooter"));
    public static final RegistryObject<EntityType<SnowPea>> SNOW_PEA = PVZEntities.ENTITIES.register("snow_pea", () -> EntityType.Builder.of(SnowPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".snow_pea"));
    public static final RegistryObject<EntityType<Repeater>> REPEATER = PVZEntities.ENTITIES.register("repeater", () -> EntityType.Builder.of(Repeater::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".repeater"));
    public static final RegistryObject<EntityType<GatlingPea>> GATLING_PEA = PVZEntities.ENTITIES.register("gatling_pea", () -> EntityType.Builder.of(GatlingPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".gatling_pea"));
    public static final RegistryObject<EntityType<PrimalPeashooter>> PRIMAL_PEASHOOTER = PVZEntities.ENTITIES.register("primal_peashooter", () -> EntityType.Builder.of(PrimalPeashooter::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".primal_peashooter"));
    public static final RegistryObject<EntityType<Walnut>> WALNUT = PVZEntities.ENTITIES.register("walnut", () -> EntityType.Builder.of((EntityType<Walnut> event, Level level) -> new Walnut(event, level, 10F), MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".walnut"));
    public static final RegistryObject<EntityType<Tallnut>> TALL_NUT = PVZEntities.ENTITIES.register("tallnut", () -> EntityType.Builder.of(Tallnut::new, MobCategory.MISC).sized(1.0F, 1.8F).clientTrackingRange(8).build(PVZMod.MODID + ".tallnut"));
    public static final RegistryObject<EntityType<SuperGatlingPea>> SUPER_GATLING_PEA = PVZEntities.ENTITIES.register("super_gatling_pea", () -> EntityType.Builder.of(SuperGatlingPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".super_gatling_pea"));
    public static final RegistryObject<EntityType<SuperSnowGatlingPea>> SUPER_SNOW_GATLING_PEA = PVZEntities.ENTITIES.register("super_snow_gatling_pea", () -> EntityType.Builder.of(SuperSnowGatlingPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".super_snow_gatling_pea"));
    public static final RegistryObject<EntityType<SuperPrimalGatlingPea>> SUPER_PRIMAL_GATLING_PEA = PVZEntities.ENTITIES.register("super_primal_gatling_pea", () -> EntityType.Builder.of(SuperPrimalGatlingPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".super_primal_gatling_pea"));
    public static final RegistryObject<EntityType<SuperElectricGatlingPea>> SUPER_ELECTRIC_GATLING_PEA = PVZEntities.ENTITIES.register("super_electric_gatling_pea", () -> EntityType.Builder.of(SuperElectricGatlingPea::new, MobCategory.MISC).sized(1.0F, 1.2F).clientTrackingRange(8).build(PVZMod.MODID + ".super_electric_gatling_pea"));
    public static final RegistryObject<EntityType<PotatoMine>> POTATO_MINE = PVZEntities.ENTITIES.register("potato_mine", () -> EntityType.Builder.of(PotatoMine::new, MobCategory.MISC).sized(1.0F, 0.5F).clientTrackingRange(8).build(PVZMod.MODID + ".potato_mine"));
    public static final RegistryObject<EntityType<PuffShroom>> PUFF_SHROOM = PVZEntities.ENTITIES.register("puff_shroom", () -> EntityType.Builder.of(PuffShroom::new, MobCategory.MISC).sized(1.0F, 0.75F).clientTrackingRange(8).build(PVZMod.MODID + ".puff_shroom"));
    public static final RegistryObject<EntityType<FumeShroom>> FUME_SHROOM = PVZEntities.ENTITIES.register("fume_shroom", () -> EntityType.Builder.of(FumeShroom::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build(PVZMod.MODID + ".fume_shroom"));
    public static final RegistryObject<EntityType<ElectricPeashooter>> ELECTRIC_PEASHOOTER = PVZEntities.ENTITIES.register("electric_peashooter", () -> EntityType.Builder.of(ElectricPeashooter::new, MobCategory.MISC).sized(1.0F, 1.2f).clientTrackingRange(8).build(PVZMod.MODID + ".electric_peashooter"));
    public static final RegistryObject<EntityType<ScaredyShroom>> SCAREDY_SHROOM = PVZEntities.ENTITIES.register("scaredy_shroom", () -> EntityType.Builder.of(ScaredyShroom::new, MobCategory.MISC).sized(1.0F, 1.1f).clientTrackingRange(8).build(PVZMod.MODID + ".scaredy_shroom"));
    public static final RegistryObject<EntityType<SunFlower>> SUN_FLOWER = PVZEntities.ENTITIES.register("sun_flower", () -> EntityType.Builder.of(SunFlower::new, MobCategory.MISC).sized(1.0F, 1.3F).clientTrackingRange(8).build(PVZMod.MODID + ".sun_flower"));
    public static final RegistryObject<EntityType<Pumpkin>> PUMPKIN = PVZEntities.ENTITIES.register("pumpkin", () -> EntityType.Builder.of(Pumpkin::new, MobCategory.MISC).sized(1.2F, 0.8F).clientTrackingRange(8).build(PVZMod.MODID + ".pumpkin"));
    public static final RegistryObject<EntityType<BonkChoy>> BONKCHOY = PVZEntities.ENTITIES.register("bonkchoy", () -> EntityType.Builder.of(BonkChoy::new, MobCategory.MISC).sized(1.0F, 1.25F).clientTrackingRange(8).build(PVZMod.MODID + ".bonkchoy"));
    public static final RegistryObject<EntityType<PeaProjectile>> PEA_PROJECTILE = PVZEntities.ENTITIES.register("pea_projectile",
            () -> EntityType.Builder.<PeaProjectile>of(PeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("pea_projectile"));
    public static final RegistryObject<EntityType<SnowPeaProjectile>> ICE_PEA_PROJECTILE = PVZEntities.ENTITIES.register("ice_pea_projectile",
            () -> EntityType.Builder.<SnowPeaProjectile>of(SnowPeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("snow_pea_projectile"));
    public static final RegistryObject<EntityType<PrimalPeaProjectile>> PRIMAL_PEA_PROJECTILE = PVZEntities.ENTITIES.register("primal_pea_projectile",
            () -> EntityType.Builder.<PrimalPeaProjectile>of(PrimalPeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("primal_pea_projectile"));
    public static final RegistryObject<EntityType<ElectricPeaProjectile>> ELECTRIC_PEA_PROJECTILE = PVZEntities.ENTITIES.register("electric_pea_projectile",
            () -> EntityType.Builder.<ElectricPeaProjectile>of(ElectricPeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("electric_pea_projectile"));
    public static final RegistryObject<EntityType<SporeProjectile>> SPORE_PROJECTILE = PVZEntities.ENTITIES.register("spore_projectile",
            () -> EntityType.Builder.<SporeProjectile>of(SporeProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("spore_projectile"));
    public static final RegistryObject<EntityType<Dave>> DAVE = PVZEntities.ENTITIES.register("crazy_dave", () -> EntityType.Builder.of(Dave::new, MobCategory.MISC).sized(1.0F, 2.5F).clientTrackingRange(8).build(PVZMod.MODID + ".crazy_dave"));
    public static final RegistryObject<EntityType<Penny>> PENNY = PVZEntities.ENTITIES.register("penny", () -> EntityType.Builder.of(Penny::new, MobCategory.MISC).sized(3.0F, 3.0F).clientTrackingRange(8).build(PVZMod.MODID + ".penny"));

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(SUN_FLOWER.get(), SunFlower.createAttributes().build());

        event.put(PEA_SHOOTER.get(), PeaShooter.createAttributes().build());
        event.put(SNOW_PEA.get(), SnowPea.createAttributes().build());
        event.put(REPEATER.get(), Repeater.createAttributes().build());
        event.put(GATLING_PEA.get(), GatlingPea.createAttributes().build());
        event.put(PRIMAL_PEASHOOTER.get(), PrimalPeashooter.createAttributes().build());
        event.put(ELECTRIC_PEASHOOTER.get(), ElectricPeashooter.createAttributes().build());

        event.put(WALNUT.get(), Walnut.createAttributes().build());
        event.put(TALL_NUT.get(), Tallnut.createAttributes().build());
        event.put(PUMPKIN.get(), Pumpkin.createAttributes().build());

        event.put(SUPER_GATLING_PEA.get(), SuperGatlingPea.createAttributes().build());
        event.put(SUPER_SNOW_GATLING_PEA.get(), SuperSnowGatlingPea.createAttributes().build());
        event.put(SUPER_PRIMAL_GATLING_PEA.get(), SuperPrimalGatlingPea.createAttributes().build());
        event.put(SUPER_ELECTRIC_GATLING_PEA.get(), SuperElectricGatlingPea.createAttributes().build());

        event.put(POTATO_MINE.get(), PotatoMine.createAttributes().build());
        event.put(BONKCHOY.get(), BonkChoy.createAttributes().build());

        event.put(PUFF_SHROOM.get(), PuffShroom.createAttributes().build());
        event.put(FUME_SHROOM.get(), FumeShroom.createAttributes().build());
        event.put(SCAREDY_SHROOM.get(), ScaredyShroom.createAttributes().build());

        event.put(DAVE.get(), Dave.createAttributes().build());
        event.put(PENNY.get(), Penny.createAttributes().build());
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SUN_FLOWER.get(), SunFlowerRender::new);

        event.registerEntityRenderer(PEA_SHOOTER.get(), PeaShooterRender::new);
        event.registerEntityRenderer(PEA_PROJECTILE.get(), ThrownItemRenderer::new);

        event.registerEntityRenderer(SNOW_PEA.get(), SnowPeaRender::new);
        event.registerEntityRenderer(ICE_PEA_PROJECTILE.get(), RenderIcePea::new);

        event.registerEntityRenderer(REPEATER.get(), RepeaterRender::new);
        event.registerEntityRenderer(GATLING_PEA.get(), GatlingPeaRender::new);

        event.registerEntityRenderer(PRIMAL_PEASHOOTER.get(), PrimalPeashooterRender::new);
        event.registerEntityRenderer(PRIMAL_PEA_PROJECTILE.get(), RenderPrimalPea::new);

        event.registerEntityRenderer(SUPER_GATLING_PEA.get(), SGPShooterRender::new);
        event.registerEntityRenderer(SUPER_SNOW_GATLING_PEA.get(), SuperSnowGatlingPeaRender::new);
        event.registerEntityRenderer(SUPER_PRIMAL_GATLING_PEA.get(), SuperPrimalPeashooterRender::new);
        event.registerEntityRenderer(SUPER_ELECTRIC_GATLING_PEA.get(), SuperElectricPeashooterRender::new);

        event.registerEntityRenderer(ELECTRIC_PEASHOOTER.get(), ElectricPeashooterRender::new);
        event.registerEntityRenderer(ELECTRIC_PEA_PROJECTILE.get(), ElectricPeaProjectileRender::new);

        event.registerEntityRenderer(WALNUT.get(), WalnutRender::new);
        event.registerEntityRenderer(TALL_NUT.get(), TallnutRender::new);
        event.registerEntityRenderer(PUMPKIN.get(), PumpkinRender::new);

        event.registerEntityRenderer(POTATO_MINE.get(), PotatoMineRender::new);
        event.registerEntityRenderer(BONKCHOY.get(), BonkChoyRender::new);

        event.registerEntityRenderer(PUFF_SHROOM.get(), PuffShroomRender::new);
        event.registerEntityRenderer(SPORE_PROJECTILE.get(), SporeRender::new);

        event.registerEntityRenderer(FUME_SHROOM.get(), FumeShroomRender::new);
        event.registerEntityRenderer(SCAREDY_SHROOM.get(), ScaredyShroomRender::new);


        event.registerEntityRenderer(DAVE.get(), DaveRender::new);
        event.registerEntityRenderer(PENNY.get(), PennyRender::new);
    }
}
