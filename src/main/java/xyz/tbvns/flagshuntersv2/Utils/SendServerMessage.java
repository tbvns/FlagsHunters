package xyz.tbvns.flagshuntersv2.Utils;

import net.minecraft.server.level.ServerPlayer;
import xyz.tbvns.flagshuntersv2.Value;

public class SendServerMessage {
    public static void send(String message) {
        if (Value.server != null && Value.server.isRunning()) {
            Value.server.getPlayerList().getPlayers().forEach(p ->{
                p.sendSystemMessage(ComponentBuilder.builder(message));
            });
            Value.server.sendSystemMessage(ComponentBuilder.builder(message));
        }
    }
    public static void sendToPlayer(String message, ServerPlayer player) {
        if (Value.server != null && Value.server.isRunning()) {
            player.sendSystemMessage(ComponentBuilder.builder(message));
        }
    }
}
