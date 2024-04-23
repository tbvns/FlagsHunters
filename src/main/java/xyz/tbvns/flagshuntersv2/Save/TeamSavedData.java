package xyz.tbvns.flagshuntersv2.Save;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

public class RedTeamSavedData extends SavedData {
    private boolean isAlive = true;

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
        this.setDirty();
    }

    public static RedTeamSavedData create() {
        return new RedTeamSavedData();
    }

    public static RedTeamSavedData load(CompoundTag tag) {
        RedTeamSavedData data = create();
        boolean testInt = tag.getBoolean("redAlive");
        data.isAlive = testInt;
        return data;
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("redAlive", isAlive);
        return tag;
    }

    public static void generateFile(MinecraftServer server) {
        server.overworld().getDataStorage().computeIfAbsent(RedTeamSavedData::load, RedTeamSavedData::create, "faction");
    }
}
