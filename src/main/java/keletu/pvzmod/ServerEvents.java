package keletu.pvzmod;

import keletu.pvzmod.entities.EntityPlantBase;
import keletu.pvzmod.entities.IPlantWontHurt;
import keletu.pvzmod.entities.Pumpkin;
import keletu.pvzmod.entities.Tallnut;
import keletu.pvzmod.box.PlantBoxDrops;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.init.PVZSounds;
import keletu.pvzmod.items.ItemGardenShovel;
import keletu.pvzmod.items.ItemPlantCard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = PVZMod.MODID)
public class ServerEvents {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) {
            return;
        }

        Entity entity = event.getEntity();

        if (!(entity instanceof Monster monster)) {
            return;
        }

        if (monster.getMobType() != MobType.UNDEAD) {
            return;
        }

        if (hasPlantTargetGoal(monster)) {
            return;
        }

        monster.targetSelector.addGoal(
                2,
                new PlantTargetGoal(monster)
        );
    }

    private static boolean hasPlantTargetGoal(Monster monster) {
        return monster.targetSelector.getAvailableGoals()
                .stream()
                .anyMatch(wrappedGoal -> wrappedGoal.getGoal() instanceof PlantTargetGoal);
    }

    private static class PlantTargetGoal extends NearestAttackableTargetGoal<LivingEntity> {
        public PlantTargetGoal(Monster monster) {
            super(monster, LivingEntity.class, true, (entity) -> entity instanceof IPlantWontHurt);
        }
    }

    @SubscribeEvent
    public static void onServerPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.getEntity().level().isClientSide)
            return;

        if (event.getStack().is(PVZItems.SUN.get())) {
            event.getEntity().level().playSound(null, event.getEntity().blockPosition(), PVZSounds.SUN_PICKUP.get(), SoundSource.PLAYERS, 20.0F, 1.0F);
        }

        if (event.getStack().is(PVZItems.DIAMOND.get())) {
            event.getEntity().level().playSound(null, event.getEntity().blockPosition(), PVZSounds.DIAMOND_PICKUP.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        PlantBoxDrops.dropFor(event.getEntity());
    }


    @SubscribeEvent
    public static void onPlantKnockBack(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof EntityPlantBase) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onChangeHeldItem(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (event.getSlot() != EquipmentSlot.MAINHAND) {
            return;
        }

        ItemStack to = event.getTo();

        if (to.getItem() instanceof ItemPlantCard) {
            player.level().playSound(null, player.blockPosition(), PVZSounds.CARD_SELECT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        } else if (to.getItem() instanceof ItemGardenShovel) {
            player.level().playSound(null, player.blockPosition(), PVZSounds.SHOVEL_SELECT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public static void livingHurtEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Tallnut tallnut && event.getAmount() > tallnut.getMaxHealth() * 1 / 2) {
            event.setAmount(tallnut.getMaxHealth() * 1 / 2);
        }

        if (event.getEntity() instanceof Pumpkin pumpkin && event.getAmount() > pumpkin.getMaxHealth() * 1 / 3) {
            event.setAmount(pumpkin.getMaxHealth() * 1 / 3);
        }
    }
}
