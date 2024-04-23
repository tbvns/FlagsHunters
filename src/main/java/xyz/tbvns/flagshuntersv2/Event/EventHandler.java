package xyz.tbvns.flagshuntersv2.Event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import xyz.tbvns.flagshuntersv2.FlagsHuntersV2;
import xyz.tbvns.flagshuntersv2.Load.LoadBlockAndItem;
import xyz.tbvns.flagshuntersv2.Load.LoadCommand;
import xyz.tbvns.flagshuntersv2.Utils.SendServerMessage;
import xyz.tbvns.flagshuntersv2.Value;
import xyz.tbvns.flagshuntersv2.Utils.SendSound;

public class EventHandler {
    @SubscribeEvent
    public void configReadEvent(ModConfigEvent.Loading event){
        event.getConfig().getConfigData();
    }


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
        LoadCommand.register(event.getDispatcher());

    }

    @SubscribeEvent
    public void serverStartEvent(ServerStartingEvent event) {
        Value.server = event.getServer();

    }

    @SubscribeEvent
    public void DeathEvent(LivingDeathEvent event) {
        if (event.getEntity().getMobType().equals(EntityType.PLAYER)) {
            if (!Minecraft.getInstance().level.isClientSide) {
                ServerPlayer player = ((ServerPlayer) event.getEntity());




            }
        }
    }

    @SubscribeEvent
    public void BlockBreakEvent(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().getDescriptionId().equalsIgnoreCase(LoadBlockAndItem.BLUE_FLAG.get().getDescriptionId())) {
            if (event.getPlayer().getTeam().getColor().equals(ChatFormatting.AQUA)) {
                SendServerMessage.sendToPlayer("§4[§cHunter's flags§4] §cYou cannot break your own flag !", (ServerPlayer) event.getPlayer());
                event.setCanceled(true);
            } else {
                SendServerMessage.send("§4[§cHunter's flags§4] §3Blue team's §cflag as been destroyed ! They are now eliminated !");
                new SendSound().TeamEliminated();
                Value.ServerTeams.forEach(st -> {
                    if (st.name.equalsIgnoreCase("Blue")) {
                        st.isAlive = false;
                    }
                });
            }
        } else if (event.getState().getBlock().getDescriptionId().equalsIgnoreCase(LoadBlockAndItem.RED_FLAG.get().getDescriptionId())) {
            if (event.getPlayer().getTeam().getColor().equals(ChatFormatting.RED)) {
                SendServerMessage.sendToPlayer("§4[§cHunter's flags§4] §cYou cannot break your own flag !", (ServerPlayer) event.getPlayer());
                event.setCanceled(true);
            } else {
                SendServerMessage.send("§4[§cHunter's flags§4] §4Red team's §cflag as been destroyed ! They are now eliminated !");
                new SendSound().TeamEliminated();
                Value.ServerTeams.forEach(st -> {
                    if (st.name.equalsIgnoreCase("Red")) {
                        st.isAlive = false;
                    }
                });
            }
        } else if (event.getState().getBlock().getDescriptionId().equalsIgnoreCase(LoadBlockAndItem.GREEN_FLAG.get().getDescriptionId())) {
            if (event.getPlayer().getTeam().getColor().equals(ChatFormatting.GREEN)) {
                SendServerMessage.sendToPlayer("§4[§cHunter's flags§4] §cYou cannot break your own flag !", (ServerPlayer) event.getPlayer());
                event.setCanceled(true);
            } else {
                SendServerMessage.send("§4[§cHunter's flags§4] §2Green team's §cflag as been destroyed ! They are now eliminated !");
                new SendSound().TeamEliminated();
                Value.ServerTeams.forEach(st -> {
                    if (st.name.equalsIgnoreCase("Green")) {
                        st.isAlive = false;
                    }
                });
            }
        } else if (event.getState().getBlock().getDescriptionId().equalsIgnoreCase(LoadBlockAndItem.YELLOW_FLAG.get().getDescriptionId())) {
            if (event.getPlayer().getTeam().getColor().equals(ChatFormatting.YELLOW)) {
                SendServerMessage.sendToPlayer("§4[§cHunter's flags§4] §cYou cannot break your own flag !", (ServerPlayer) event.getPlayer());
                event.setCanceled(true);
            } else {
                SendServerMessage.send("§4[§cHunter's flags§4] §6Yellow team's §cflag as been destroyed ! They are now eliminated !");
                new SendSound().TeamEliminated();
                Value.ServerTeams.forEach(st -> {
                    System.out.println(st.name);
                    if (st.name.equalsIgnoreCase("Yellow")) {
                        st.isAlive = false;
                    }
                });
            }
        }
    }

    @Mod.EventBusSubscriber(modid = FlagsHuntersV2.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvent {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            //event.registerAboveAll("testhudforredteam", Hud.RedHUD);
        }
    }
}
