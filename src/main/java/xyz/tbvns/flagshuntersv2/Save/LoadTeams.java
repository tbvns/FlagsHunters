package xyz.tbvns.flagshuntersv2.Save;

import net.minecraft.core.BlockPos;
import xyz.tbvns.flagshuntersv2.Objects.TeamObject;
import xyz.tbvns.flagshuntersv2.Value;
import xyz.tbvns.flagshuntersv2.Utils.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class LoadTeams {
    public void Load() throws IOException, SQLException {
        String spath = File.file().getParent();
        spath = spath + "/TBotSave.db";
        Path path = Path.of(spath);
        System.out.println(spath);

        if (path.toFile().exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
            List<String> lines = reader.lines().collect(Collectors.toList());

            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + spath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.execute("CREATE TABLE IF NOT EXISTS teams (name text, isAlive text, players text, flagPos text);");

            ResultSet rs = statement.executeQuery("SELECT * FROM teams");
            while (rs.next()) {
                String name = rs.getString(1);
                String isAlive = rs.getString(2);
                String players = rs.getString(3);
                String flagPos = rs.getString(3);

                TeamObject to = new TeamObject();

                List<Long> position = new Parser().DecodeLongAsList(flagPos);

                to.name = name;
                to.isAlive = new Parser().DecodeStringAsBool(isAlive);
                to.players = new Parser().DecodeStringAsList(players);
                to.flagPos = new BlockPos(Math.toIntExact(position.get(0)), Math.toIntExact(position.get(1)), Math.toIntExact(position.get(2)));

                Value.ServerTeams.add(to);
            }
        }
    }
}
