package xyz.tbvns.flagshuntersv2;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.scores.Team;
import xyz.tbvns.flagshuntersv2.Objects.TeamObject;

import java.util.ArrayList;
import java.util.List;

public class Value {
    public static MinecraftServer server;

    public static Team PlayerTeam;
    public static List<TeamObject> ServerTeams = new ArrayList<>();
}
