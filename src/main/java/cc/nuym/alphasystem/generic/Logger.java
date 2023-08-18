//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.generic;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.obj.PunishType;
import cc.nuym.alphasystem.data.FlatFile;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class Logger {
    public static void logInfractionAdd(PunishType var0, OfflinePlayer var1, CommandSender var2) {
        FlatFile.logs.add(Msg.format(AlphaSystem.instance.getConfig().getString("Log.addition").replaceAll("%time%", Msg.getFormattedTime()).replaceAll("%sender%", var2.getName()).replaceAll("%player%", var1.getName()).replaceAll("%reason%", var0.getIdentifier()).replaceAll("%infractions%", String.valueOf(AlphaSystem.instance.punishManager.getPlayerByUUID(var1.getUniqueId()).getNumberByPunishment(var0)))));
    }

    public static void logInfractionRemoval(PunishType var0, OfflinePlayer var1, CommandSender var2) {
        if (AlphaSystem.instance.punishManager.getPlayerByUUID(var1.getUniqueId()).getNumberByPunishment(var0) == 0) {
            FlatFile.logs.add(Msg.format(AlphaSystem.instance.getConfig().getString("Log.removal").replaceAll("%time%", Msg.getFormattedTime()).replaceAll("%sender%", var2.getName()).replaceAll("%player%", var1.getName()).replaceAll("%reason%", var0.getIdentifier()).replaceAll("%infractions%", "0")));
        } else {
            FlatFile.logs.add(Msg.format(AlphaSystem.instance.getConfig().getString("Log.removal").replaceAll("%time%", Msg.getFormattedTime()).replaceAll("%sender%", var2.getName()).replaceAll("%player%", var1.getName()).replaceAll("%reason%", var0.getIdentifier()).replaceAll("%infractions%", String.valueOf(AlphaSystem.instance.punishManager.getPlayerByUUID(var1.getUniqueId()).getNumberByPunishment(var0)))));
        }

    }

    public static void logInfractionSet(PunishType var0, OfflinePlayer var1, CommandSender var2) {
        if (AlphaSystem.instance.punishManager.getPlayerByUUID(var1.getUniqueId()).getNumberByPunishment(var0) == 0) {
            FlatFile.logs.add(Msg.format(AlphaSystem.instance.getConfig().getString("Log.set").replaceAll("%time%", Msg.getFormattedTime()).replaceAll("%sender%", var2.getName()).replaceAll("%player%", var1.getName()).replaceAll("%reason%", var0.getIdentifier()).replaceAll("%infractions%", "0")));
        } else {
            FlatFile.logs.add(Msg.format(AlphaSystem.instance.getConfig().getString("Log.set").replaceAll("%time%", Msg.getFormattedTime()).replaceAll("%sender%", var2.getName()).replaceAll("%player%", var1.getName()).replaceAll("%reason%", var0.getIdentifier()).replaceAll("%infractions%", String.valueOf(AlphaSystem.instance.punishManager.getPlayerByUUID(var1.getUniqueId()).getNumberByPunishment(var0)))));
        }

    }
}
