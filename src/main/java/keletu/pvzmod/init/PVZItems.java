package keletu.pvzmod.init;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.box.ItemPlantBox;
import keletu.pvzmod.box.ItemPlantKey;
import keletu.pvzmod.items.ItemGardenShovel;
import keletu.pvzmod.items.ItemLootChest;
import keletu.pvzmod.items.ItemPlantCard;
import keletu.pvzmod.items.ItemSun;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PVZItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PVZMod.MODID);
    public static final RegistryObject<Item> POT = ITEMS.register("pot",
            () -> new BlockItem(PVZBlocks.POT.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARTON = ITEMS.register("carton",
            () -> new BlockItem(PVZBlocks.CARTON.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MUSIC_DISC_SECRET_LABS = ITEMS.register("music_disc_secret_labs",
            () -> new RecordItem(8, PVZSounds.SECRET_LABS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 20 * 120));
    public static final RegistryObject<Item> MUSIC_DISC_ZOMBIES_ON_YOUR_LAWN = ITEMS.register("music_disc_zombies_on_your_lawn",
            () -> new RecordItem(9, PVZSounds.ZOMBIES_ON_YOUR_LAWN, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 20 * 165));
    public static final RegistryObject<Item> COMMON_CHEST = ITEMS.register("common_chest",
            () -> new ItemLootChest(PVZBlocks.COMMON_CHEST.get(), new Item.Properties().stacksTo(16).rarity(Rarity.COMMON), ItemLootChest.Quality.COMMON));
    public static final RegistryObject<Item> EPIC_CHEST = ITEMS.register("epic_chest",
            () -> new ItemLootChest(PVZBlocks.EPIC_CHEST.get(), new Item.Properties().stacksTo(16).rarity(Rarity.EPIC), ItemLootChest.Quality.EPIC));
    public static final RegistryObject<Item> LEGENDARY_CHEST = ITEMS.register("legendary_chest",
            () -> new ItemLootChest(PVZBlocks.LEGENDARY_CHEST.get(), new Item.Properties().stacksTo(16).rarity(Rarity.EPIC), ItemLootChest.Quality.LEGENDARY));
    public static final RegistryObject<Item> PEA = ITEMS.register("pea", () -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.25F)
                    .fast()
                    .build())
            .stacksTo(64)));
    public static final RegistryObject<Item> SUN = ITEMS.register("sun", () -> new ItemSun(new Item.Properties()
            .stacksTo(64)));
    public static final RegistryObject<Item> DIAMOND = ITEMS.register("diamond", () -> new ItemSun(new Item.Properties()
            .stacksTo(64)
            .rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> PLANT_KEY = ITEMS.register("plant_key", () -> new ItemPlantKey(new Item.Properties()
            .stacksTo(64)
            .rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PLANT_BOX = ITEMS.register("plant_box", () -> new ItemPlantBox(PVZBlocks.PLANT_BOX.get(), new Item.Properties()
            .stacksTo(16)
            .rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> SUN_FLOWER_CARD = ITEMS.register("sun_flower_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.SUN_FLOWER));
    public static final RegistryObject<Item> PEASHOOTER_CARD = ITEMS.register("peashooter_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.PEA_SHOOTER));
    public static final RegistryObject<Item> SNOWPEA_CARD = ITEMS.register("snowpea_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.SNOW_PEA));
    public static final RegistryObject<Item> REPEATER_CARD = ITEMS.register("repeater_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.REPEATER));
    public static final RegistryObject<Item> GATLING_PEA_CARD = ITEMS.register("gatling_pea_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.GATLING_PEA));
    public static final RegistryObject<Item> PRIMAL_PEASHOOTER_CARD = ITEMS.register("primal_peashooter_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.PRIMAL_PEASHOOTER));
    public static final RegistryObject<Item> WALNUT_CARD = ITEMS.register("walnut_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.WALNUT));
    public static final RegistryObject<Item> TALL_NUT_CARD = ITEMS.register("tall_nut_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.TALL_NUT));
    public static final RegistryObject<Item> SUPER_GATLING_PEA_CARD = ITEMS.register("super_gatling_pea_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.SUPER_GATLING_PEA));
    public static final RegistryObject<Item> POTATO_MINE_CARD = ITEMS.register("potato_mine_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.POTATO_MINE));
    public static final RegistryObject<Item> BONKCHOY_CARD = ITEMS.register("bonkchoy_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.BONKCHOY));
    public static final RegistryObject<Item> PUFF_SHROOM_CARD = ITEMS.register("puff_shroom_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.PUFF_SHROOM));
    public static final RegistryObject<Item> FUME_SHROOM_CARD = ITEMS.register("fume_shroom_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.FUME_SHROOM));
    public static final RegistryObject<Item> ELECTRIC_PEASHOOTER_CARD = ITEMS.register("electric_peashooter_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.ELECTRIC_PEASHOOTER));
    public static final RegistryObject<Item> SCAREDY_SHROOM_CARD = ITEMS.register("scaredy_shroom_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.SCAREDY_SHROOM));
    public static final RegistryObject<Item> PUMPKIN_CARD = ITEMS.register("pumpkin_card",
            () -> new ItemPlantCard(new Item.Properties(), PVZEntities.PUMPKIN));
    public static final RegistryObject<Item> GARDEN_SHOVEL = ITEMS.register("garden_shovel", () -> new ItemGardenShovel(new Item.Properties()
            .durability(999)));
}
