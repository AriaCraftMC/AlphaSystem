//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.generic.Msg;
import org.bukkit.Bukkit;

public class FlatFile {
    public static transient FlatFile instance = new FlatFile();
    public static List<String> logs = new ArrayList();

    public static void init() {
        try {
            AlphaSystem.instance.storage.loadNotNull(instance, FlatFile.class, new File(AlphaSystem.instance.getDataFolder() + "/log.json"));
        } catch (Exception var1) {
            Bukkit.broadcast(Msg.format("&6&l** &e&lSomething went wrong while loading log.json from AlphaSystem &6&l**"), "alphasystem.error");
        }

    }

    public static void terminate() {
        try {
            AlphaSystem.instance.storage.save(instance, new File(AlphaSystem.instance.getDataFolder() + "/log.json"));
        } catch (Exception var1) {
            Bukkit.broadcast(Msg.format("&6&l** &e&lSomething went wrong while saving log.json from AlphaSystem &6&l**"), "alphasystem.error");
        }

    }
}
 