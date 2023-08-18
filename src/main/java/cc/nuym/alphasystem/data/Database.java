//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import cc.nuym.alphasystem.AlphaSystem;
import org.bukkit.Bukkit;

public class Database {
    public String host;
    public String database;
    public String user;
    public String pass;
    public int port;
    private AlphaSystem alphaSystem;
    private Connection connection;

    public Database(AlphaSystem var1) {
        this.alphaSystem = var1;
        this.host = var1.getConfig().getString("Options.host");
        this.database = var1.getConfig().getString("Options.database");
        this.user = var1.getConfig().getString("Options.user");
        this.pass = var1.getConfig().getString("Options.pass");
        this.port = var1.getConfig().getInt("Options.port");
        this.makeConnection();
    }

    public void save() {
        // $FF: Couldn't be decompiled
    }

    public void load() {
        // $FF: Couldn't be decompiled
    }

    private void makeConnection() {
        try {
            synchronized(this) {
                if (this.connection != null && !this.connection.isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.pass);
                Bukkit.getLogger().info("[AlphaSystem] CONNECTED TO MYSQL DATABASE");
                this.createTable();
            }
        } catch (Exception var4) {
            Bukkit.getLogger().severe("[AlphaSystem] SOMETHING WENT WRONG WHILE CONNECTING TO DATABASE.");
            Bukkit.getLogger().severe("[AlphaSystem] PLUGIN WILL NOW DISABLE.");
            var4.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this.alphaSystem);
        }

    }

    private void createTable() {
        String var1 = "CREATE TABLE IF NOT EXISTS data (uuid VARCHAR(100), punishments TEXT, PRIMARY KEY (uuid), UNIQUE KEY(uuid))";

        try {
            Connection var2 = this.getConnection();
            Statement var3 = var2.createStatement();
            var3.executeUpdate(var1);
            Bukkit.getLogger().info("[AlphaSystem] Succesfully created TABLE");
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.makeConnection();
                return this.connection;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return this.connection;
    }
}
 