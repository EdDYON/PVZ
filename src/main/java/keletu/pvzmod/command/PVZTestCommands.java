package keletu.pvzmod.command;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SuperGatlingPea;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = PVZMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PVZTestCommands {
    private static final double LOOK_REACH = 16.0D;
    private static final double FALLBACK_RADIUS = 8.0D;

    private PVZTestCommands() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("pvztest")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("super")
                                .executes(context -> triggerSuperMode(context.getSource())))
        );
    }

    private static int triggerSuperMode(CommandSourceStack source) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        SuperGatlingPea target = findTarget(player);

        if (target == null) {
            return 0;
        }

        return target.activateEnergyBean(null) ? 1 : 0;
    }

    private static SuperGatlingPea findTarget(ServerPlayer player) {
        SuperGatlingPea lookedAt = findLookTarget(player);
        if (lookedAt != null) {
            return lookedAt;
        }

        SuperGatlingPea nearest = null;
        double nearestDistance = FALLBACK_RADIUS * FALLBACK_RADIUS;

        for (SuperGatlingPea entity : player.level().getEntitiesOfClass(
                SuperGatlingPea.class,
                player.getBoundingBox().inflate(FALLBACK_RADIUS),
                Entity::isAlive
        )) {
            double distance = player.distanceToSqr(entity);
            if (distance < nearestDistance) {
                nearest = entity;
                nearestDistance = distance;
            }
        }

        return nearest;
    }

    private static SuperGatlingPea findLookTarget(ServerPlayer player) {
        Vec3 start = player.getEyePosition();
        Vec3 direction = player.getViewVector(1.0F);
        Vec3 end = start.add(direction.scale(LOOK_REACH));
        AABB searchBox = player.getBoundingBox().expandTowards(direction.scale(LOOK_REACH)).inflate(1.0D);

        SuperGatlingPea nearest = null;
        double nearestDistance = LOOK_REACH * LOOK_REACH;

        for (SuperGatlingPea entity : player.level().getEntitiesOfClass(
                SuperGatlingPea.class,
                searchBox,
                Entity::isAlive
        )) {
            AABB hitBox = entity.getBoundingBox().inflate(entity.getPickRadius());
            Optional<Vec3> hit = hitBox.clip(start, end);
            if (hit.isEmpty()) {
                continue;
            }

            double distance = start.distanceToSqr(hit.get());
            if (distance < nearestDistance) {
                nearest = entity;
                nearestDistance = distance;
            }
        }

        return nearest;
    }
}
