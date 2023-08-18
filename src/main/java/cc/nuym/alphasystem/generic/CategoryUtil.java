//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: CFR 0.152
// Class Version: 8
package cc.nuym.alphasystem.generic;

import java.util.ArrayList;
import java.util.List;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.obj.PunishType;

public class CategoryUtil {
    public static PunishType getTypeByIdentifier(String string) {
        if (AlphaSystem.instance.punishManager.list.isEmpty()) {
            return null;
        }
        for (PunishType punishType : AlphaSystem.instance.punishManager.list) {
            if (!punishType.getIdentifier().equals(string) && !punishType.getAdditional().contains(string)) continue;
            return punishType;
        }
        return null;
    }

    public static List<String> getPossiblePunishments() {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (AlphaSystem.instance.punishManager.list.isEmpty()) {
            return null;
        }
        for (PunishType punishType : AlphaSystem.instance.punishManager.list) {
            arrayList.add(punishType.getIdentifier());
        }
        return arrayList;
    }
}
 