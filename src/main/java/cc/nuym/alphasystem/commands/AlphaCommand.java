//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.commands;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.generic.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AlphaCommand implements CommandExecutor {
    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        if (var4.length == 0) {
            var1.sendMessage(Msg.format("&aAlphaSystem is an advanced punishment system intended to be used with an anti-cheat plugin."));
            var1.sendMessage(Msg.format("&a/infractions for all the commands."));
            if (var1.hasPermission("alphasystem.admin")) {
                var1.sendMessage(Msg.format("&a/alphasystem reload - reload the config."));
            }

            return false;
        } else if (var4.length == 1) {
            if (var1.hasPermission("alphasystem.admin")) {
                if (var4[0].equalsIgnoreCase("reload")) {
                    AlphaSystem.instance.reloadConfig();
                    var1.sendMessage(Msg.format("&aReloaded config."));
                    return false;
                } else {
                    var1.sendMessage(Msg.format("&aAlphaSystem is an advanced punishment system intended to be used with an anti-cheat plugin."));
                    var1.sendMessage(Msg.format("&a/infractions for all the commands."));
                    if (var1.hasPermission("alphasystem.admin")) {
                        var1.sendMessage(Msg.format("&a/alphasystem reload - reload the config."));
                    }

                    return true;
                }
            } else {
                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                return false;
            }
        } else {
            return false;
        }
    }
}
 