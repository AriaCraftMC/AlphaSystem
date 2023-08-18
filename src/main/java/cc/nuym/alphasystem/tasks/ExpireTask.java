//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: CFR 0.152
// Class Version: 8
package cc.nuym.alphasystem.tasks;

import java.util.Map;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.obj.Infraction;
import cc.nuym.alphasystem.obj.APPlayer;
import cc.nuym.alphasystem.obj.PunishType;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ExpireTask {
    public ExpireTask(final AlphaSystem alphaSystem) {
        new BukkitRunnable(){

            public void run() {
                if (AlphaSystem.instance.punishManager.players.isEmpty()) {
                    return;
                }
                for (APPlayer APPlayer : AlphaSystem.instance.punishManager.players) {
                    if (APPlayer.getMap().isEmpty()) {
                        return;
                    }
                    for (Map.Entry<PunishType, Infraction> entry : APPlayer.getMap().entrySet()) {
                        if (entry.getValue().getAmount() == 0) continue;
                        block12: for (String string : alphaSystem.categories) {
                            String string2;
                            if (!alphaSystem.getConfig().getBoolean("Categories." + string + ".expire.active") || !string.toLowerCase().contains(entry.getKey().getIdentifier()) || System.currentTimeMillis() < entry.getValue().getLastAdded() + (long)(alphaSystem.getConfig().getInt("Categories." + string + ".expire.cooldown") * 1000)) continue;
                            switch (string2 = alphaSystem.getConfig().getString("Categories." + string + ".expire.mode").toLowerCase()) {
                                case "fullreset": {
                                    APPlayer.setInfraction(entry.getKey(), Bukkit.getConsoleSender(), 0);
                                    entry.getValue().setLastAdded(System.currentTimeMillis());
                                    continue block12;
                                }
                                case "manual": {
                                    APPlayer.ManualRemoveInfraction(entry.getKey(), Bukkit.getConsoleSender(), 1);
                                    entry.getValue().setLastAdded(System.currentTimeMillis());
                                    continue block12;
                                }
                                case "decrease": {
                                    APPlayer.removeInfraction(entry.getKey(), Bukkit.getConsoleSender(), 1);
                                    entry.getValue().setLastAdded(System.currentTimeMillis());
                                    continue block12;
                                }
                            }
                            APPlayer.removeInfraction(entry.getKey(), Bukkit.getConsoleSender(), 1);
                            entry.getValue().setLastAdded(System.currentTimeMillis());
                        }
                    }
                }
            }
        }.runTaskTimer(alphaSystem, 1200L, 1200L);
    }
}
 