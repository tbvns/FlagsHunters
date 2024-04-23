package xyz.tbvns.flagshuntersv2.Utils;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public String EncodeStringsAsString(List<String> strings) {
        final String[] list = {""};

        strings.forEach(s -> {
            list[0] = list[0] + ":" + s;
        });

        return list[0];
    }
    public String EncodeLongAsString(List<Long> longs) {
        final String[] list = {""};

        longs.forEach(s -> {
            list[0] = list[0] + ":" + s;
        });

        return list[0];
    }

    public List<String> DecodeStringAsList(String str) {
        String[] list = str.split(":");
        List<String> SList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            SList.add(list[i]);
        }
        return SList;
    }

    public List<Long> DecodeLongAsList(String str) {
        String[] list = str.split(":");
        List<Long> LList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            try {
                LList.add(Long.parseLong(list[i]));
            } catch (Exception e) {}
        }
        return LList;
    }

    public boolean DecodeStringAsBool(String str) {
        if (str.equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }

}
