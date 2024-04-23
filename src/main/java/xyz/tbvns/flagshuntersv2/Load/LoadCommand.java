package xyz.tbvns.flagshuntersv2.Load;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import xyz.tbvns.flagshuntersv2.Game.Start;

public class LoadCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("yay").then(Commands.literal("yay")).executes(context -> {
            try {
                return Start.StartGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
