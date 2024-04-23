package xyz.tbvns.flagshuntersv2.Game;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import xyz.tbvns.flagshuntersv2.Objects.TeamObject;
import xyz.tbvns.flagshuntersv2.Save.SaveTeams;
import xyz.tbvns.flagshuntersv2.Utils.SendServerMessage;
import xyz.tbvns.flagshuntersv2.Utils.SendSound;
import xyz.tbvns.flagshuntersv2.Load.LoadBlockAndItem;
import xyz.tbvns.flagshuntersv2.Value;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Start {

    public static int StartGame() throws InterruptedException, SQLException, IOException {
        if (Value.server != null) {
            if (Value.server.isRunning()) {
                MinecraftServer server = Value.server;
                SendServerMessage.send("§4[§cHunter's flags§4] §cStarting the game...");
                SendServerMessage.send("§4[§cHunter's flags§4] §cSetting up teams");
                new SendSound().Notification();
                //Setup team
                Scoreboard scoreboard = server.getScoreboard();
                PlayerTeam red = scoreboard.addPlayerTeam("Red");
                PlayerTeam green = scoreboard.addPlayerTeam("Green");
                PlayerTeam blue = scoreboard.addPlayerTeam("Blue");
                PlayerTeam yellow = scoreboard.addPlayerTeam("Yellow");


                //Change color
                red.setColor(ChatFormatting.RED);
                green.setColor(ChatFormatting.GREEN);
                blue.setColor(ChatFormatting.AQUA);
                yellow.setColor(ChatFormatting.YELLOW);


                //Set sufix
                ArrayList<String> redprefix = new ArrayList<>();
                ArrayList<String> greenprefix = new ArrayList<>();
                ArrayList<String> blueprefix = new ArrayList<>();
                ArrayList<String> yellowprefix = new ArrayList<>();

                redprefix.add("§4[Red]");
                greenprefix.add("§2[Green]");
                blueprefix.add("§3[Blue]");
                yellowprefix.add("§5[Yellow]");

                Component redcomponent = ComponentUtils.formatList(redprefix);
                Component greencomponent = ComponentUtils.formatList(greenprefix);
                Component bluecomponent = ComponentUtils.formatList(blueprefix);
                Component yellowcomponent = ComponentUtils.formatList(yellowprefix);

                red.setPlayerPrefix(redcomponent);
                green.setPlayerPrefix(greencomponent);
                blue.setPlayerPrefix(bluecomponent);
                yellow.setPlayerPrefix(yellowcomponent);

                //Loading flags positions
                SendServerMessage.send("§4[§cHunter's flags§4] §cSearching for valid flag position...");
                new SendSound().Notification();

                ServerLevel level = server.overworld();

                // Team Red
                BlockPos redFlagPos = getValidPos(level);
                SendServerMessage.send("§4[§cHunter's flags§4] §cFound §4Red team's §cspawn location ");
                new SendSound().NotificationPosFound();
                // Team Green
                BlockPos greenFlagPos = getValidPos(level);
                SendServerMessage.send("§4[§cHunter's flags§4] §cFound §aGreen team's §cspawn location ");
                new SendSound().NotificationPosFound();
                // Team Blue
                BlockPos blueFlagPos = getValidPos(level);
                SendServerMessage.send("§4[§cHunter's flags§4] §cFound §3Blue team's §cspawn location ");
                new SendSound().NotificationPosFound();
                // Team Yellow
                BlockPos yellowFlagPos = getValidPos(level);
                SendServerMessage.send("§4[§cHunter's flags§4] §cFound §eYellow team's §cspawn location ");
                new SendSound().NotificationPosFound();

                //Placing flags
                SendServerMessage.send("§4[§cHunter's flags§4] §cPlacing flags ");
                level.setBlock(redFlagPos, LoadBlockAndItem.RED_FLAG.get().defaultBlockState(), 1);
                level.setBlock(greenFlagPos, LoadBlockAndItem.GREEN_FLAG.get().defaultBlockState(), 1);
                level.setBlock(blueFlagPos, LoadBlockAndItem.BLUE_FLAG.get().defaultBlockState(), 1);
                level.setBlock(yellowFlagPos, LoadBlockAndItem.YELLOW_FLAG.get().defaultBlockState(), 1);

                //Placing player into teams:
                SendServerMessage.send("§4[§cHunter's flags§4] §cPlacing player into teams...");

                List<PlayerTeam> teams = new ArrayList<>();
                teams.add(red);
                teams.add(green);
                teams.add(blue);
                teams.add(yellow);

                teams.forEach(t -> {
                    TeamObject to = new TeamObject();
                    to.name = t.getName();
                    to.isAlive = true;

                    if (t.getName().equalsIgnoreCase("Red")) {
                        to.flagPos = redFlagPos;
                    }
                    if (t.getName().equalsIgnoreCase("Green")) {
                        to.flagPos = greenFlagPos;
                    }
                    if (t.getName().equalsIgnoreCase("Blue")) {
                        to.flagPos = blueFlagPos;
                    }
                    if (t.getName().equalsIgnoreCase("Yellow")) {
                        to.flagPos = yellowFlagPos;
                    }

                    Value.ServerTeams.add(to);
                });

                List<ServerPlayer> players = new ArrayList<>();
                players.addAll(server.getPlayerList().getPlayers());
                int selectedteam = 0;

                for (int i = 0; i < players.size(); i++) {
                    Random random = new Random();
                    int index = 0;

                    if (players.size()-1 != 0) {
                        index = random.nextInt(0, players.size() - 1);
                    }

                    ServerPlayer p = players.get(index);
                    scoreboard.addPlayerToTeam(p.getName().getString(), teams.get(selectedteam));
                    players.remove(index);
                    selectedteam+=1;
                    if (selectedteam == 5) {
                        selectedteam = 0;
                    }
                }




                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e10");
                new SendSound().Notification();
                new SendSound().GameStart();
                Thread.sleep(5000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e5");
                new SendSound().Notification();
                Thread.sleep(1000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e4");
                new SendSound().Notification();
                Thread.sleep(1000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e3");
                new SendSound().Notification();
                Thread.sleep(1000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e2");
                new SendSound().Notification();
                Thread.sleep(1000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleportation in §e1");
                new SendSound().Notification();
                Thread.sleep(1000);
                SendServerMessage.send("§4[§cHunter's flags§4] §cTeleporting...");
                new SendSound().Notification();

                //Teleporting player
                server.getPlayerList().getPlayers().forEach(p -> {
                    if (p.getTeam().getName().equalsIgnoreCase("Red")) {
                        p.teleportTo(redFlagPos.getX(), redFlagPos.getY(), redFlagPos.getZ());
                    } else if (p.getTeam().getName().equalsIgnoreCase("Green")) {
                        p.teleportTo(greenFlagPos.getX(), greenFlagPos.getY(), greenFlagPos.getZ());
                    } else if (p.getTeam().getName().equalsIgnoreCase("Blue")) {
                        p.teleportTo(blueFlagPos.getX(), blueFlagPos.getY(), blueFlagPos.getZ());
                    } else if (p.getTeam().getName().equalsIgnoreCase("Yellow")) {
                        p.teleportTo(yellowFlagPos.getX(), yellowFlagPos.getY(), yellowFlagPos.getZ());
                    }
                });

                try {
                    new SaveTeams().Save();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SendServerMessage.send("§4[§cHunter's flags§4] §cThe game has started !");
                SendServerMessage.send("§4[§cHunter's flags§4] §cGood luck !");
            }
        }
        return 1;
    }
    public static BlockPos getValidPos(ServerLevel level) {
        boolean valid = false;
        while (!valid) {
            List<Integer> Lpos = getPos();
            BlockPos pos = checkIfValid(Lpos, level);
            if (pos != null) {
                return pos;
            }
        }
        return null;
    }
    public static List<Integer> getPos() {
        int x = new Random().nextInt(-2000, 2000);
        int z = new Random().nextInt(-2000, 2000);
        List<Integer> pos = new ArrayList<Integer>();
        pos.add(x);
        pos.add(z);
        return pos;
    }
    public static BlockPos checkIfValid(List<Integer> intl, ServerLevel level) {
        int x = intl.get(0);
        int z = intl.get(1);
        System.out.println("Checking: X:" + x + " Z:" + z);
        for (int y = 250; y > 0; y--) {
            BlockPos pos = new BlockPos(x, y, z);
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock().getDescriptionId().equalsIgnoreCase("block.minecraft.air")) {

            } else if (blockState.getBlock().getDescriptionId().equalsIgnoreCase("block.minecraft.grass_block")) {
                BlockPos newpos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                System.out.println("Found valid position on " + newpos.toShortString());
                return newpos;
            } else {
                return null;
            }
        }
        return null;
    }
}
