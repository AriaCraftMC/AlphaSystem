//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: CFR 0.152
// Class Version: 8
package cc.nuym.alphasystem.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import cc.nuym.alphasystem.generic.CategoryUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompletionListener
        implements TabCompleter {
    List<String> commands = Arrays.asList("check", "remove", "removeall", "add", "set");
    List<String> punishments = CategoryUtil.getPossiblePunishments();

    public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] stringArray) {
        if (command.getName().equalsIgnoreCase("infraction") || command.getName().equalsIgnoreCase("inf") || command.getName().equalsIgnoreCase("infractions")) {
            if (stringArray.length == 1) {
                ArrayList<String> arrayList = new ArrayList<String>();
                if (!stringArray[0].equals("")) {
                    for (String string2 : this.commands) {
                        if (!string2.startsWith(stringArray[0].toLowerCase())) continue;
                        arrayList.add(string2);
                    }
                } else {
                    arrayList.addAll(this.commands);
                }
                Collections.sort(arrayList);
                return arrayList;
            }
            if (stringArray.length == 3) {
                ArrayList<String> arrayList = new ArrayList<String>();
                if (!stringArray[2].equals("")) {
                    for (String string3 : this.punishments) {
                        if (!string3.toLowerCase().startsWith(stringArray[2].toLowerCase())) continue;
                        arrayList.add(string3.toLowerCase());
                    }
                } else {
                    arrayList.addAll(this.punishments);
                }
                Collections.sort(arrayList);
                return arrayList;
            }
        }
        return null;
    }
}
 