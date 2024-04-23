package xyz.tbvns.flagshuntersv2;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    Config(ForgeConfigSpec.Builder builder) {
        builder.comment("Server data")
                .push("SavedData");

        ForgeConfigSpec.BooleanValue RedTeamAlive = builder
                .comment("True if the red team is alive")
                .define("RedTeamAlive", true);
        ForgeConfigSpec.BooleanValue GreenTeamAlive = builder
                .comment("True if the green team is alive")
                .define("GreenTeamAlive", true);
        ForgeConfigSpec.BooleanValue BlueTeamAlive = builder
                .comment("True if the blue team is alive")
                .define("BlueTeamAlive", true);
        ForgeConfigSpec.BooleanValue YellowTeamAlive = builder
                .comment("True if the yellow team is alive")
                .define("YellowTeamAlive", true);

        builder.pop();
    }


    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Config COMMON;
    static{
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

    }



}
