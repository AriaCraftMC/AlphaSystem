//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.commands;

import cc.nuym.alphasystem.AlphaSystem;
import cc.nuym.alphasystem.generic.CategoryUtil;
import cc.nuym.alphasystem.generic.Logger;
import cc.nuym.alphasystem.generic.Msg;
import cc.nuym.alphasystem.obj.APPlayer;
import cc.nuym.alphasystem.obj.PunishType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfractionCommand implements CommandExecutor {
    public void help(CommandSender var1) {
        if (var1.hasPermission("alphasystem.infraction.*")) {
            var1.sendMessage(Msg.format("&aHelp Menu for &2AlphaSystem"));
            var1.sendMessage(Msg.format("&d/infraction help"));
            var1.sendMessage(Msg.format("&d/infraction save"));
            var1.sendMessage(Msg.format("&d/infraction add (Player) (Category) <amount>"));
            var1.sendMessage(Msg.format("&d/infraction remove (Player) (Category) <amount>"));
            var1.sendMessage(Msg.format("&d/infraction removeall (Player) (Category)"));
            var1.sendMessage(Msg.format("&d/infraction set (Player) (Category) (Amount)"));
            var1.sendMessage(Msg.format("&d/infraction check (Player) (Category)"));
        } else {
            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
        }

    }

    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        if (var4.length == 0) {
            this.help(var1);
            return true;
        } else if (var4.length == 1) {
            if (var4[0].equalsIgnoreCase("save")) {
                if (var1.hasPermission("alphasystem.infraction.save")) {
                    AlphaSystem.instance.saveData();
                    var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.data_saved")));
                    return true;
                } else {
                    var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                    return true;
                }
            } else {
                this.help(var1);
                return true;
            }
        } else if (var4.length != 2) {
            if (var4.length == 3) {
                if (var4[0].equalsIgnoreCase("add")) {
                    if (var1.hasPermission("alphasystem.infraction.add")) {
                        byte var15 = 1;
                        OfflinePlayer var22 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var22 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var29 = AlphaSystem.instance.punishManager.getPlayerByUUID(var22.getUniqueId());
                            if (var29 == null) {
                                var29 = AlphaSystem.instance.punishManager.createEmptyPlayer(var22.getUniqueId());
                            }

                            PunishType var33 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var33 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var29.addInfraction(var33, var1, var15);
                                Logger.logInfractionAdd(var33, var22, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return false;
                    }
                } else if (var4[0].equalsIgnoreCase("remove")) {
                    if (var1.hasPermission("alphasystem.infraction.remove")) {
                        byte var14 = 1;
                        OfflinePlayer var21 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var21 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var28 = AlphaSystem.instance.punishManager.getPlayerByUUID(var21.getUniqueId());
                            if (var28 == null) {
                                var28 = AlphaSystem.instance.punishManager.createEmptyPlayer(var21.getUniqueId());
                            }

                            PunishType var32 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var32 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var28.removeInfraction(var32, var1, var14);
                                Logger.logInfractionRemoval(var32, var21, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return false;
                    }
                } else if (var4[0].equalsIgnoreCase("removeall")) {
                    if (var1.hasPermission("alphasystem.infraction.remove")) {
                        OfflinePlayer var13 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var13 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var20 = AlphaSystem.instance.punishManager.getPlayerByUUID(var13.getUniqueId());
                            if (var20 == null) {
                                var20 = AlphaSystem.instance.punishManager.createEmptyPlayer(var13.getUniqueId());
                            }

                            PunishType var27 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var27 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var20.setInfraction(var27, var1, 0);
                                Logger.logInfractionSet(var27, var13, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return true;
                    }
                } else if (var4[0].equalsIgnoreCase("check")) {
                    if (var1.hasPermission("alphasystem.infraction.check")) {
                        OfflinePlayer var12 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var12 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var19 = AlphaSystem.instance.punishManager.getPlayerByUUID(var12.getUniqueId());
                            if (var19 == null) {
                                var19 = AlphaSystem.instance.punishManager.createEmptyPlayer(var12.getUniqueId());
                            }

                            PunishType var26 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var26 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.check_points").replaceAll("%player%", var12.getName()).replaceAll("%infractions%", String.valueOf(var19.getNumberByPunishment(var26))).replaceAll("%reason%", var26.getIdentifier())));
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return true;
                    }
                } else {
                    this.help(var1);
                    return true;
                }
            } else if (var4.length == 4) {
                if (var4[0].equalsIgnoreCase("remove")) {
                    if (var1.hasPermission("alphasystem.infraction.remove")) {
                        int var11 = Integer.valueOf(var4[3]);
                        OfflinePlayer var18 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var18 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var25 = AlphaSystem.instance.punishManager.getPlayerByUUID(var18.getUniqueId());
                            if (var25 == null) {
                                var25 = AlphaSystem.instance.punishManager.createEmptyPlayer(var18.getUniqueId());
                            }

                            PunishType var31 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var31 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var25.removeInfraction(var31, var1, var11);
                                Logger.logInfractionRemoval(var31, var18, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return true;
                    }
                } else if (var4[0].equalsIgnoreCase("add")) {
                    if (var1.hasPermission("alphasystem.infraction.add")) {
                        int var10 = Integer.valueOf(var4[3]);
                        OfflinePlayer var17 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var17 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var24 = AlphaSystem.instance.punishManager.getPlayerByUUID(var17.getUniqueId());
                            if (var24 == null) {
                                var24 = AlphaSystem.instance.punishManager.createEmptyPlayer(var17.getUniqueId());
                            }

                            PunishType var30 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var30 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var24.addInfraction(var30, var1, var10);
                                Logger.logInfractionAdd(var30, var17, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return false;
                    }
                } else if (var4[0].equalsIgnoreCase("set")) {
                    if (var1.hasPermission("alphasystem.infraction.set")) {
                        OfflinePlayer var9 = Bukkit.getOfflinePlayer(var4[1]);
                        if (var9 == null) {
                            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                            return true;
                        } else {
                            APPlayer var16 = AlphaSystem.instance.punishManager.getPlayerByUUID(var9.getUniqueId());
                            if (var16 == null) {
                                var16 = AlphaSystem.instance.punishManager.createEmptyPlayer(var9.getUniqueId());
                            }

                            PunishType var23 = CategoryUtil.getTypeByIdentifier(var4[2]);
                            if (var23 == null) {
                                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_type").replaceAll("%reason%", var4[2])));
                                return true;
                            } else {
                                var16.setInfraction(var23, var1, Integer.parseInt(var4[3]));
                                Logger.logInfractionSet(var23, var9, var1);
                                return true;
                            }
                        }
                    } else {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                this.help(var1);
                return true;
            }
        } else if (!var4[0].equalsIgnoreCase("check")) {
            this.help(var1);
            return true;
        } else if (!var1.hasPermission("alphasystem.infraction.check")) {
            var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_perm")));
            return true;
        } else {
            OfflinePlayer var5 = Bukkit.getOfflinePlayer(var4[1]);
            if (var5 == null) {
                var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_player").replaceAll("%player%", var4[1])));
                return true;
            } else {
                APPlayer var6 = AlphaSystem.instance.punishManager.getPlayerByUUID(var5.getUniqueId());
                if (var6 == null) {
                    var6 = AlphaSystem.instance.punishManager.createEmptyPlayer(var5.getUniqueId());
                }

                if (var6.getMap().keySet().isEmpty()) {
                    var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.no_vio").replaceAll("%player%", var4[1])));
                    return true;
                } else {
                    for(Object var8 : var6.getMap().keySet()) {
                        var1.sendMessage(Msg.format(AlphaSystem.instance.getConfig().getString("Messages.check_points").replaceAll("%player%", var5.getName()).replaceAll("%infractions%", String.valueOf(var6.getNumberByPunishment((PunishType)var8))).replaceAll("%reason%", ((PunishType)var8).getIdentifier())));
                    }

                    return true;
                }
            }
        }
    }
}
 