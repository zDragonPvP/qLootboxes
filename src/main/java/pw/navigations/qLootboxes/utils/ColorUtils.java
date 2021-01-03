package pw.navigations.qLootboxes.utils;

import org.bukkit.ChatColor;

public class ColorUtils {

    public static String Color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String PREFIX = Color(" &6» &e");

}
