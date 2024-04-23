package xyz.tbvns.flagshuntersv2.Utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;

import java.util.ArrayList;
import java.util.List;

public class ComponentBuilder {
    public static Component builder(String message) {
        List<String> strings = new ArrayList<>();
        strings.add(message);
        Component component = ComponentUtils.formatList(strings);
        return component;
    }
}
