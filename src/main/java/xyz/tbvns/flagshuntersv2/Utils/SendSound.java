package xyz.tbvns.flagshuntersv2.Utils;

import net.minecraft.sounds.SoundSource;
import xyz.tbvns.flagshuntersv2.Value;
import xyz.tbvns.flagshuntersv2.Load.LoadSounds;

public class SendSound {
    public void GameStart() {
        if (Value.server != null && Value.server.isRunning()) {
            Value.server.getPlayerList().getPlayers().forEach(p ->{
                double x = p.getX();
                double y = p.getY();
                double z = p.getZ();
                p.level().playSound(null, x, y, z, LoadSounds.GAME_START.get(), SoundSource.PLAYERS, 100f, 1f);
            });

        }
    }
    public void Notification() {
        if (Value.server != null && Value.server.isRunning()) {
            Value.server.getPlayerList().getPlayers().forEach(p ->{
                double x = p.getX();
                double y = p.getY();
                double z = p.getZ();
                p.level().playSound(null, x, y, z, LoadSounds.NOTIF_SOUND.get(), SoundSource.PLAYERS, 100f, 1f);
            });

        }
    }
    public void NotificationPosFound() {
        if (Value.server != null && Value.server.isRunning()) {
            Value.server.getPlayerList().getPlayers().forEach(p ->{
                double x = p.getX();
                double y = p.getY();
                double z = p.getZ();
                p.level().playSound(null, x, y, z, LoadSounds.POS_FOUND_SOUND.get(), SoundSource.PLAYERS, 100f, 1f);
            });

        }
    }
    public void TeamEliminated() {
        if (Value.server != null && Value.server.isRunning()) {
            Value.server.getPlayerList().getPlayers().forEach(p ->{
                double x = p.getX();
                double y = p.getY();
                double z = p.getZ();
                p.level().playSound(null, x, y, z, LoadSounds.TEAM_ELIMINATED.get(), SoundSource.PLAYERS, 100f, 1f);
            });

        }
    }
}
