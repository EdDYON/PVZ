package keletu.pvzmod.box;

import keletu.pvzmod.init.PVZItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public final class PlantBoxDrops {
    private PlantBoxDrops() {
    }

    public static void dropFor(LivingEntity entity) {
        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        if (entityId == null) {
            return;
        }

        String entityName = entityId.toString();
        for (PlantBoxInfo info : PlantBoxManager.boxes()) {
            if (info.dropEntities == null || !info.dropEntities.contains(entityName)) {
                continue;
            }

            if (info.keyDropChance > 0.0F && entity.getRandom().nextFloat() < info.keyDropChance) {
                entity.spawnAtLocation(new ItemStack(PVZItems.PLANT_KEY.get()));
            }

            if (info.boxDropChance > 0.0F && entity.getRandom().nextFloat() < info.boxDropChance) {
                entity.spawnAtLocation(PlantBoxManager.createBoxStack(info));
            }
        }
    }
}
