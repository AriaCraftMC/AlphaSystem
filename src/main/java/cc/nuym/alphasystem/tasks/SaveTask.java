//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.tasks;

import java.util.logging.Level;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.data.FlatFile;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveTask {
    public SaveTask(final AlphaSystem var1) {
        (new BukkitRunnable() {
            public void run() {
                FlatFile.terminate();
                var1.punishManager.savePlayers();
                var1.getLogger().log(Level.INFO, "Saved all data");

            }
        }).runTaskTimerAsynchronously(var1, (long)(var1.getConfig().getInt("Options.save-task-interval") * 20), (long)(var1.getConfig().getInt("Options.save-task-interval") * 20));
    }
}
 