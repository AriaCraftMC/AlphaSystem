//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.obj;

public class Infraction {
    private PunishType punishType;
    private int amount;
    private long lastAdded;

    public Infraction(PunishType var1, int var2, long var3) {
        this.punishType = var1;
        this.amount = var2;
        this.lastAdded = var3;
    }

    public PunishType getPunishType() {
        return this.punishType;
    }

    public void setPunishType(PunishType var1) {
        this.punishType = var1;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int var1) {
        this.amount = var1;
    }

    public long getLastAdded() {
        return this.lastAdded;
    }

    public void setLastAdded(long var1) {
        this.lastAdded = var1;
    }
}
 