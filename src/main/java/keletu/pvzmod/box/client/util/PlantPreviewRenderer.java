package keletu.pvzmod.box.client.util;

import keletu.pvzmod.init.PVZEntities;
import keletu.pvzmod.items.ItemPlantCard;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class PlantPreviewRenderer {
    private PlantPreviewRenderer() {
    }

    public static LivingEntity createPreviewEntity(String plantId, ItemStack fallbackStack, Level level) {
        if (level == null) {
            return null;
        }

        EntityType<?> type = typeForPlantId(plantId);
        Entity entity = type == null ? null : type.create(level);
        if (entity == null && fallbackStack.getItem() instanceof ItemPlantCard plantCard) {
            entity = plantCard.createPlantEntity(level);
        }

        if (entity instanceof LivingEntity living) {
            if (living instanceof Mob mob) {
                mob.setNoAi(true);
            }
            living.setInvulnerable(true);
            living.setYRot(180.0F);
            living.yRotO = 180.0F;
            living.setYBodyRot(180.0F);
            living.yBodyRotO = 180.0F;
            living.setYHeadRot(180.0F);
            living.yHeadRotO = 180.0F;
            return living;
        }
        return null;
    }

    public static LivingEntity createPreviewEntity(ItemStack stack, Level level) {
        return createPreviewEntity("", stack, level);
    }

    public static void tickPreviewEntity(LivingEntity entity) {
        if (entity != null) {
            entity.tickCount++;
        }
    }

    public static void render(GuiGraphics guiGraphics, LivingEntity entity, int centerX, int bottomY, int scale, float angleX, float angleY) {
        if (entity == null) {
            return;
        }
        InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, centerX, bottomY, scale, angleX, angleY, entity);
    }

    private static EntityType<?> typeForPlantId(String plantId) {
        if (plantId == null || plantId.isBlank()) {
            return null;
        }
        return switch (plantId) {
            case "sun_flower" -> PVZEntities.SUN_FLOWER.get();
            case "pea_shooter" -> PVZEntities.PEA_SHOOTER.get();
            case "snow_pea" -> PVZEntities.SNOW_PEA.get();
            case "repeater" -> PVZEntities.REPEATER.get();
            case "gatling_pea" -> PVZEntities.GATLING_PEA.get();
            case "primal_peashooter" -> PVZEntities.PRIMAL_PEASHOOTER.get();
            case "electric_peashooter" -> PVZEntities.ELECTRIC_PEASHOOTER.get();
            case "walnut" -> PVZEntities.WALNUT.get();
            case "tallnut" -> PVZEntities.TALL_NUT.get();
            case "pumpkin" -> PVZEntities.PUMPKIN.get();
            case "super_gatling_pea" -> PVZEntities.SUPER_GATLING_PEA.get();
            case "super_snow_gatling_pea" -> PVZEntities.SUPER_SNOW_GATLING_PEA.get();
            case "super_primal_gatling_pea" -> PVZEntities.SUPER_PRIMAL_GATLING_PEA.get();
            case "super_electric_gatling_pea" -> PVZEntities.SUPER_ELECTRIC_GATLING_PEA.get();
            case "potato_mine" -> PVZEntities.POTATO_MINE.get();
            case "bonkchoy" -> PVZEntities.BONKCHOY.get();
            case "puff_shroom" -> PVZEntities.PUFF_SHROOM.get();
            case "fume_shroom" -> PVZEntities.FUME_SHROOM.get();
            case "scaredy_shroom" -> PVZEntities.SCAREDY_SHROOM.get();
            default -> null;
        };
    }
}
