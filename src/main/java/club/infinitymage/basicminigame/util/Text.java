package club.infinitymage.basicminigame.util;

import org.bukkit.ChatColor;

public class Text {

    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
