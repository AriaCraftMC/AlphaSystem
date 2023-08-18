//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.obj.APPlayer;
import cc.nuym.alphasystem.obj.PunishType;
import cc.nuym.alphasystem.generic.CategoryUtil;
import cc.nuym.alphasystem.obj.Infraction;

public class PunishManager {
    public List<PunishType> list = new ArrayList();
    public List<APPlayer> players = new ArrayList();
    AlphaSystem inst;

    public PunishManager(AlphaSystem var1) {
        this.inst = var1;

        for(Object var3 : var1.getConfig().getConfigurationSection("Categories").getKeys(false)) {
            List var4 = var1.getConfig().getStringList("Categories." + var3 + ".identifiers");
            HashMap var5 = new HashMap();
            HashMap var6 = new HashMap();
            HashMap var7 = new HashMap();

            for(Object var9 : var1.getConfig().getConfigurationSection("Categories." + var3 + ".addition").getKeys(false)) {
                int var10 = Integer.parseInt((String)var9);
                List var11 = var1.getConfig().getStringList("Categories." + var3 + ".addition." + var9);
                var5.put(var10, var11);
            }

            for(Object var14 : var1.getConfig().getConfigurationSection("Categories." + var3 + ".removal").getKeys(false)) {
                int var16 = Integer.parseInt((String)var14);
                List var18 = var1.getConfig().getStringList("Categories." + var3 + ".removal." + var14);
                var6.put(var16, var18);
            }

            for(Object var15 : var1.getConfig().getConfigurationSection("Categories." + var3 + ".expire.commands").getKeys(false)) {
                int var17 = Integer.parseInt((String)var15);
                List var19 = var1.getConfig().getStringList("Categories." + var3 + ".expire.commands." + var15);
                var7.put(var17, var19);
            }

            this.list.add(new PunishType((String)var3, var4, var5, var6, var7));
        }

    }

    public APPlayer getPlayerByUUID(UUID var1) {
        if (this.players.isEmpty()) {
            return null;
        } else {
            for(Object var3 : this.players) {
                if (((APPlayer)var3).getUuid().equals(var1)) {
                    return (APPlayer)var3;
                }
            }

            return null;
        }
    }

    public APPlayer createEmptyPlayer(UUID var1) {
        APPlayer var2 = new APPlayer(var1);
        this.players.add(var2);
        return var2;
    }

    public void savePlayers() {
        if (!AlphaSystem.instance.useMySQL) {
            this.players.forEach((var0) -> AlphaSystem.instance.dataFile.saveAlphaSystemPlayer(var0));
        } else {
            AlphaSystem.instance.database.save();
        }

    }

    public void load() {
        if (!AlphaSystem.instance.useMySQL) {
            for(Object var2 : AlphaSystem.instance.dataFile.getConfig().getKeys(false)) {
                UUID var3 = UUID.fromString((String)var2);
                HashMap var4 = new HashMap();

                for(Object var6 : AlphaSystem.instance.dataFile.getConfig().getConfigurationSection((String)var2).getKeys(false)) {
                    PunishType var7 = CategoryUtil.getTypeByIdentifier((String)var6);
                    int var8 = AlphaSystem.instance.dataFile.getConfig().getInt(var2 + "." + var6 + ".amount");
                    long var9 = AlphaSystem.instance.dataFile.getConfig().getLong(var2 + "." + var6 + ".addedAt");
                    var4.put(var7, new Infraction(var7, var8, var9));
                }

                this.players.add(new APPlayer(var3, var4));
            }
        } else {
            AlphaSystem.instance.database.load();
        }

    }
}
 