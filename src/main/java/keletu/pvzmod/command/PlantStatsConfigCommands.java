package keletu.pvzmod.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.network.OpenPlantStatsConfigPacket;
import keletu.pvzmod.network.PVZNetworking;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class PlantStatsConfigCommands {
    private PlantStatsConfigCommands() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("pvzplantconfig")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> openConfig(context.getSource()))
        );
        event.getDispatcher().register(
                Commands.literal("pvzconfig")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> openConfig(context.getSource()))
        );
    }

    private static int openConfig(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        PlantStatManager.loadOrCreateDefaults();
        PVZNetworking.sendToPlayer(new OpenPlantStatsConfigPacket(PlantStatManager.snapshot()), player);
        return 1;
    }
}
