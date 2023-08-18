//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import cc.nuym.alphasystem.commands.InfractionCommand;
import cc.nuym.alphasystem.commands.AlphaCommand;
import cc.nuym.alphasystem.data.DataYamlFile;
import cc.nuym.alphasystem.data.Database;
import cc.nuym.alphasystem.data.FlatFile;
import cc.nuym.alphasystem.generic.Storage;
import cc.nuym.alphasystem.manager.PunishManager;
import cc.nuym.alphasystem.tab.TabCompletionListener;
import cc.nuym.alphasystem.tasks.ExpireTask;
import cc.nuym.alphasystem.tasks.SaveTask;
import org.bukkit.plugin.java.JavaPlugin;

public class AlphaSystem extends JavaPlugin {
    public static AlphaSystem instance;
    public Gson gson;
    public Storage storage;
    public PunishManager punishManager;
    public DataYamlFile dataFile;
    public Database database;
    public boolean useMySQL;
    public boolean connected;
    public SaveTask saveTask;
    public ExpireTask expireTask;
    public List<String> categories = new ArrayList();

    public void onEnable() {
        getConfig();
        saveConfig();
        instance = this;
        this.gson = this.getGsonBuilder().create();
        this.storage = new Storage(this.gson);
        FlatFile.init();
        this.saveDefaultConfig();
        this.dataFile = new DataYamlFile(this);
        this.punishManager = new PunishManager(this);
        this.getCommand("infraction").setExecutor(new InfractionCommand());
        this.getCommand("infraction").setTabCompleter(new TabCompletionListener());
        this.getCommand("alphasystem").setExecutor(new AlphaCommand());
        this.useMySQL = this.getConfig().getBoolean("Options.mysql");
        if (this.useMySQL) {
            this.database = new Database(this);
        }

        this.punishManager.load();
        this.saveTask = new SaveTask(this);
        this.expireTask = new ExpireTask(this);

        for(String var2 : this.getConfig().getConfigurationSection("Categories").getKeys(false)) {
            this.categories.add(var2);
        }

    }

    public void onDisable() {
        this.saveData();
        this.getLogger().log(Level.INFO, "Saved all data");
        instance = null;
    }

    public GsonBuilder getGsonBuilder() {
        return (new GsonBuilder()).setPrettyPrinting().excludeFieldsWithModifiers(new int[]{128}).serializeNulls();
    }

    public void saveData() {
        FlatFile.terminate();
        this.punishManager.savePlayers();
    }

    public boolean isConnected() {
        return this.connected;
    }
}
 