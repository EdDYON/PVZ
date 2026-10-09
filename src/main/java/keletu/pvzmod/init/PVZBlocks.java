package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.blocks.*;
import keletu.pvzmod.items.ItemLootChest;
import keletu.pvzmod.models.render.LootChestBlockRenderer;
import keletu.pvzmod.models.render.PotBlockRender;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PVZBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PVZMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PVZMod.MODID);
    public static final RegistryObject<Block> POT = BLOCKS.register("pot", () -> new Pot(BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_BROWN)
            .strength(0.5F)
            .sound(SoundType.DECORATED_POT)
            .noOcclusion()
    ));
    public static final RegistryObject<Block> PLANT_BOX = BLOCKS.register("plant_box", () -> new PlantBoxBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()
    ));
    public static final RegistryObject<Block> CARTON = BLOCKS.register("carton", () -> new Carton(BlockBehaviour.Properties.of()
            .mapColor(MapColor.GRASS)
            .strength(0.1F)
            .sound(SoundType.GRASS)
            .noOcclusion()
    ));
    public static final RegistryObject<Block> COMMON_CHEST = BLOCKS.register("common_chest", () -> new LootChestBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .strength(1.0F)
            .sound(SoundType.WOOD)
            .noOcclusion(), ItemLootChest.Quality.COMMON
    ));
    public static final RegistryObject<Block> EPIC_CHEST = BLOCKS.register("epic_chest", () -> new LootChestBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .strength(1.25F)
            .sound(SoundType.WOOD)
            .noOcclusion(), ItemLootChest.Quality.EPIC
    ));
    public static final RegistryObject<Block> LEGENDARY_CHEST = BLOCKS.register("legendary_chest", () -> new LootChestBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.GOLD)
            .strength(1.5F)
            .sound(SoundType.WOOD)
            .noOcclusion(), ItemLootChest.Quality.LEGENDARY
    ));
    public static final RegistryObject<BlockEntityType<PotBlockEntity>> POT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("pot_entity",
                    () -> BlockEntityType.Builder.of(
                            PotBlockEntity::new,
                            PVZBlocks.POT.get()
                    ).build(null));
    public static final RegistryObject<BlockEntityType<PlantBoxBlockEntity>> PLANT_BOX_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("plant_box_entity",
                    () -> BlockEntityType.Builder.of(
                            PlantBoxBlockEntity::new,
                            PVZBlocks.PLANT_BOX.get()
                    ).build(null));
    public static final RegistryObject<BlockEntityType<LootChestBlockEntity>> LOOT_CHEST_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("loot_chest_entity",
                    () -> BlockEntityType.Builder.of(
                            LootChestBlockEntity::new,
                            PVZBlocks.COMMON_CHEST.get(),
                            PVZBlocks.EPIC_CHEST.get(),
                            PVZBlocks.LEGENDARY_CHEST.get()
                    ).build(null));


    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(POT_BLOCK_ENTITY.get(), PotBlockRender::new);
        event.registerBlockEntityRenderer(LOOT_CHEST_BLOCK_ENTITY.get(), LootChestBlockRenderer::new);
    }
}
