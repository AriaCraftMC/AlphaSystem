//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.data;

import java.io.File;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.obj.Infraction;
import cc.nuym.alphasystem.obj.APPlayer;
import cc.nuym.alphasystem.obj.PunishType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataYamlFile {
    AlphaSystem inst;
    public FileConfiguration config;
    public File file;

    public DataYamlFile(AlphaSystem var1) {
        this.inst = var1;
        if (!var1.getDataFolder().exists()) {
            var1.getDataFolder().mkdir();
        }

        this.file = new File(var1.getDataFolder() + "/data.yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void saveAlphaSystemPlayer(APPlayer var1) {
        if (!var1.getMap().isEmpty()) {
            for(Object var3 : var1.getMap().keySet()) {
                this.getConfig().set(var1.getUuid().toString() + "." + ((PunishType)var3).getIdentifier() + ".amount", ((Infraction)var1.getMap().get(var3)).getAmount());
                this.getConfig().set(var1.getUuid().toString() + "." + ((PunishType)var3).getIdentifier() + ".addedAt", ((Infraction)var1.getMap().get(var3)).getLastAdded());
            }

            this.saveConfig();
        }
    }
}
 