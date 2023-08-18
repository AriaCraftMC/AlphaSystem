//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.generic;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.ChatColor;

public class Msg {
    public static String format(String var0) {
        return ChatColor.translateAlternateColorCodes('&', var0);
    }

    public static String getFormattedTime() {
        SimpleDateFormat var0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date var1 = new Date();
        return var0.format(var1);
    }
}
