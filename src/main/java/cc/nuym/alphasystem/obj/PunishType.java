//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.obj;

import java.util.HashMap;
import java.util.List;

public class PunishType {
    private String identifier;
    private List<String> additional;
    private HashMap<Integer, List<String>> punishments;
    private HashMap<Integer, List<String>> removal;
    private HashMap<Integer, List<String>> manual;

    public PunishType(String var1, List<String> var2, HashMap<Integer, List<String>> var3, HashMap<Integer, List<String>> var4, HashMap<Integer, List<String>> var5) {
        this.identifier = var1;
        this.additional = var2;
        this.punishments = var3;
        this.removal = var4;
        this.manual = var5;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String var1) {
        this.identifier = var1;
    }

    public List<String> getAdditional() {
        return this.additional;
    }

    public int getMaxAditional() {
        return this.punishments.isEmpty() ? 0 : this.punishments.size();
    }

    public int getMaxPunishment() {
        return this.punishments.isEmpty() ? 0 : this.punishments.size();
    }

    public int getMaxRemoval() {
        return this.punishments.isEmpty() ? 0 : this.punishments.size() - 1;
    }

    public int getMaxManual() {
        return this.punishments.isEmpty() ? 0 : this.punishments.size() - 1;
    }

    public List<String> getPunishmentByNumber(int var1) {
        if (this.punishments.isEmpty()) {
            return null;
        } else {
            return this.punishments.containsKey(var1) ? this.punishments.get(var1) : null;
        }
    }

    public List<String> getRemovalByNumber(int var1) {
        if (this.removal.isEmpty()) {
            return null;
        } else {
            return this.removal.containsKey(var1) ? this.removal.get(var1) : null;
        }
    }

    public List<String> getManualByNumber(int var1) {
        if (this.manual.isEmpty()) {
            return null;
        } else {
            return this.manual.containsKey(var1) ? this.manual.get(var1) : null;
        }
    }
}
 