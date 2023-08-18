//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: CFR 0.152
// Class Version: 8
package cc.nuym.alphasystem.obj;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.generic.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class APPlayer {
    private UUID uuid;
    private Map<PunishType, Infraction> map;

    public APPlayer(UUID uUID, Map<PunishType, Infraction> map) {
        this.uuid = uUID;
        this.map = map;
    }

    public APPlayer(UUID uUID) {
        this.uuid = uUID;
        this.map = new HashMap<PunishType, Infraction>();
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uUID) {
        this.uuid = uUID;
    }

    public int getNumberByPunishment(PunishType punishType) {
        if (this.map.containsKey(punishType)) {
            return this.map.get(punishType).getAmount();
        }
        return 0;
    }

    public void addInfraction(PunishType punishType, CommandSender commandSender, int n) {
        if (n < 1) {
            commandSender.sendMessage(Msg.format("&cInvalid number."));
            return;
        }
        if (this.map.containsKey(punishType)) {
            if (punishType.getPunishmentByNumber(this.map.get(punishType).getAmount() + n) == null) {
                commandSender.sendMessage(Msg.format("&cUser has reached maximum infractions."));
                n = punishType.getMaxPunishment();
                this.setInfraction(punishType, commandSender, n);
                return;
            }
            if (punishType.getPunishmentByNumber(n) != null) {
                for (String string : punishType.getPunishmentByNumber(this.map.get(punishType).getAmount() + n)) {
                    if (string.equals("")) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()));
                }
            }
            this.map.get(punishType).setLastAdded(System.currentTimeMillis());
            this.map.get(punishType).setAmount(this.map.get(punishType).getAmount() + n);
            commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.punished_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
            Bukkit.broadcast(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.bc_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier())), "alphasystem.receive");
        } else {
            this.map.put(punishType, new Infraction(punishType, 1, System.currentTimeMillis()));
            if (punishType.getPunishmentByNumber(this.map.get(punishType).getAmount()) == null) {
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.punished_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
                Bukkit.broadcast(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.bc_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier())), "alphasystem.receive");
                return;
            }
            if (punishType.getPunishmentByNumber(n) != null) {
                for (String string : punishType.getPunishmentByNumber(this.map.get(punishType).getAmount())) {
                    String string2 = Bukkit.getOfflinePlayer(this.uuid).getName();
                    if (string.equals("") || string2 == null) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", string2));
                }
            }
            commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.punished_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
            Bukkit.broadcast(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.bc_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier())), "alphasystem.receive");
        }
    }

    public void ManualRemoveInfraction(PunishType punishType, CommandSender commandSender, int n) {
        if (this.map.containsKey(punishType)) {
            if (punishType.getManualByNumber(this.map.get(punishType).getAmount() - n) == null) {
                n = 0;
            }
            if (punishType.getManualByNumber(n) != null) {
                for (String string : punishType.getManualByNumber(this.map.get(punishType).getAmount() - n)) {
                    String string2 = Bukkit.getOfflinePlayer(this.uuid).getName();
                    if (string.equals("") || string2 == null) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", string2));
                }
            }
            if (this.map.get(punishType).getAmount() != 0) {
                this.map.get(punishType).setAmount(this.map.get(punishType).getAmount() - n);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.forgive_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
            } else if (this.map.get(punishType).getAmount() == 0) {
                this.map.get(punishType).setLastAdded(System.currentTimeMillis());
                this.map.get(punishType).setAmount(0);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.forgive_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", "0")));
            }
        } else {
            commandSender.sendMessage(Msg.format("&cUser has no infractions on that category."));
        }
    }

    public void removeInfraction(PunishType punishType, CommandSender commandSender, int n) {
        if (this.map.containsKey(punishType)) {
            if (punishType.getRemovalByNumber(this.map.get(punishType).getAmount() - n) == null) {
                n = 0;
            }
            if (punishType.getRemovalByNumber(n) != null) {
                for (String string : punishType.getRemovalByNumber(this.map.get(punishType).getAmount() - n)) {
                    String string2 = Bukkit.getOfflinePlayer(this.uuid).getName();
                    if (string.equals("") || string2 == null) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", string2));
                }
            }
            if (this.map.get(punishType).getAmount() != 0) {
                this.map.get(punishType).setAmount(this.map.get(punishType).getAmount() - n);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.forgive_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
            } else if (this.map.get(punishType).getAmount() == 0) {
                this.map.get(punishType).setLastAdded(System.currentTimeMillis());
                this.map.get(punishType).setAmount(0);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.forgive_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", "0")));
            }
        } else {
            commandSender.sendMessage(Msg.format("&cUser has no infractions on that category."));
        }
    }

    public void setInfraction(PunishType punishType, CommandSender commandSender, int n) {
        if (this.map.containsKey(punishType)) {
            if (punishType.getRemovalByNumber(n) == null && n <= 0) {
                n = 0;
            } else if (punishType.getRemovalByNumber(n) == null && n > 0) {
                n = punishType.getMaxPunishment();
            }
            if (punishType.getRemovalByNumber(n) != null) {
                for (String string : punishType.getRemovalByNumber(n)) {
                    String string2 = Bukkit.getOfflinePlayer(this.uuid).getName();
                    if (string.equals("") || string2 == null) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", string2));
                }
            }
            if (n == 0) {
                this.map.get(punishType).setLastAdded(System.currentTimeMillis());
                this.map.get(punishType).setAmount(0);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.set_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(n))));
            } else {
                this.map.get(punishType).setLastAdded(System.currentTimeMillis());
                this.map.get(punishType).setAmount(n);
                commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.set_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(n))));
            }
        } else {
            this.map.put(punishType, new Infraction(punishType, n, System.currentTimeMillis()));
            if (punishType.getPunishmentByNumber(n) != null) {
                for (String string : punishType.getPunishmentByNumber(this.map.get(punishType).getAmount())) {
                    String string3 = Bukkit.getOfflinePlayer(this.uuid).getName();
                    if (string.equals("") || string3 == null) continue;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("%player%", string3));
                }
            }
            commandSender.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.punished_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier()).replaceAll("%infractions%", String.valueOf(this.map.get(punishType).getAmount()))));
            Bukkit.broadcast(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.bc_msg").replaceAll("%player%", Bukkit.getOfflinePlayer(this.uuid).getName()).replaceAll("%reason%", punishType.getIdentifier())), "alphasystem.receive");
        }
    }

    public Map<PunishType, Infraction> getMap() {
        return this.map;
    }
}
