package xyz.tbvns.flagshuntersv2.Save;

import xyz.tbvns.flagshuntersv2.FlagsHuntersV2;

public class File {
    public static java.io.File file() {

        return new java.io.File(FlagsHuntersV2.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    }
}
