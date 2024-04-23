package xyz.tbvns.flagshuntersv2.Save;

import xyz.tbvns.flagshuntersv2.Value;
import xyz.tbvns.flagshuntersv2.Utils.Parser;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaveTeams {
    public void Save() throws IOException, SQLException {
        String spath = File.file().getParent();
        spath = spath + "/TBotSave.db";
        Path path = Path.of(spath);
        System.out.println(spath);

        if (!path.toFile().exists()) {
            path.toFile().createNewFile();
        }
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + spath);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS teams (name text, isAlive text, players text, flagPos text);");
            Value.ServerTeams.forEach(t -> {
                List<Long> position = new ArrayList<>();
                position.add((long) t.flagPos.getX());
                position.add((long) t.flagPos.getY());
                position.add((long) t.flagPos.getZ());
                try {
                    ResultSet rs = statement.executeQuery("SELECT * FROM teams");
                    boolean found = false;
                    while (rs.next()) {
                        if (rs.getString(1).equalsIgnoreCase(t.name)) {
                            int row = rs.getRow();
                            statement.execute("UPDATE events SET name='" + t.name + "', isAlive='" + t.isAlive + "', players='" + new Parser().EncodeStringsAsString(t.players) + "', flagPos='" + new Parser().EncodeLongAsString(position) + "' WHERE ROWID = " + row + ";");
                            found = true;
                        }
                    }
                    if (!found) {
                        statement.execute("insert into events values ('" + t.name + "','" + t.isAlive + "','" + new Parser().EncodeStringsAsString(t.players) + "','" + new Parser().EncodeLongAsString(position) + "');");
                    }
                } catch (SQLException E) {
                    throw new RuntimeException(E);
                }
            });
        } catch (Exception e) {
            System.err.println(e);
        }
        statement.close();
        connection.close();
    }
}