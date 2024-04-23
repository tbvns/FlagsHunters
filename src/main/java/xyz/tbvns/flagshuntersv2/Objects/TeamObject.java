package xyz.tbvns.flagshuntersv2.Objects;

import net.minecraft.core.BlockPos;

import java.util.List;

public class TeamObject {
    public String name;
    public List<String> players;
    public Boolean isAlive = true;
    public BlockPos flagPos;
}
