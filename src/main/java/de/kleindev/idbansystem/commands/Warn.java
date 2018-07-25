package de.kleindev.idbansystem.commands;

import de.kleindev.idbansystem.Main;
import de.kleindev.idbansystem.tools.SQLite.Insert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Warn implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warn")){
            if (sender.hasPermission(Main.cfg.getString("Permissions.warn"))){
                if (args.length == 0){
                    sender.sendMessage(Main.message.getString("WarnBase").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));
                } else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("list")){
                        sender.sendMessage(Main.message.getString("WarnListID").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));
                        int id = 1;
                        while (id != 0){
                            sender.sendMessage(id + "§7 - " + Main.id.getString(id + ".Name") + " §7- §e" + Main.id.getString(id + ".BanZeit"));
                            id = id+1;
                            if (Main.id.get(String.valueOf(id)) == null) id = 0;
                        }
                    } else sender.sendMessage(Main.message.getString("WarnMissingID").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));
                } else if (args.length == 2){
                    if (sender.hasPermission(Main.id.getString(args[1] + ".Permission"))) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player player = Bukkit.getPlayer(args[0]);
                            if (!player.hasPermission(Main.cfg.getString("Permissions.bypass"))) {
                                // ========================================
                                String argument = Main.id.getString(args[1] + ".BanZeit");
                                long min = 0;
                                long hour = 0;
                                long day = 0;
                                long week = 0;
                                if (argument.endsWith("m") || argument.endsWith("min"))
                                    min = Long.valueOf(argument.replaceAll("m", "").replaceAll("min", "")) * 1000 * 60;
                                if (argument.endsWith("h") || argument.endsWith("hour"))
                                    hour = Long.valueOf(argument.replaceAll("h", "").replaceAll("hour", "")) * 1000 * 60 * 60;
                                if (argument.endsWith("d") || argument.endsWith("day"))
                                    day = Long.valueOf(argument.replaceAll("d", "").replaceAll("day", "")) * 1000 * 60 * 60 * 24;
                                if (argument.endsWith("w") || argument.endsWith("week"))
                                    week = Long.valueOf(argument.replaceAll("w", "").replaceAll("week", "")) * 1000 * 60 * 60 * 24 * 7;
                                long time = System.currentTimeMillis() + min + hour + day + week;
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
                                Date date = new Date(time);
                                String datum = sdf.format(date);
                                // ========================================
                                Insert.main("Players", "Name, UUID, Aussteller, ID, Zeitraum", "'" + player.getName() + "', '" + player.getUniqueId().toString() + "', '" + sender.getName() + "', '" + args[1] + "', '" + time + "'");
                                for (String s : Main.message.getStringList("WarnBroadcast")) {
                                    for (Player p : Bukkit.getOnlinePlayers()){
                                        p.sendMessage(s.replaceAll("%prefix%", Main.message.getString("Prefix"))
                                                .replaceAll("%player%", player.getName()).replaceAll("%sender%", sender.getName()
                                                        .replaceAll("%grund%", Main.id.getString(args[1] + ".BanGrund")).replaceAll("&", "§")));
                                    }
                                }
                                player.kickPlayer(Main.message.getString("KickMessage").replaceAll("%newline%", "\n").replaceAll("%BanGrund%", Main.id.getString(args[1] + ".BanGrund")).replaceAll("%Zeit%", datum).replaceAll("%Aussteller%", sender.getName()).replaceAll("&", "§"));
                            } else {
                                sender.sendMessage(Main.message.getString("WarnPlayerBypass").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%player%", player.getName()).replaceAll("&", "§"));
                                // Spieler hat Bypass Permission
                            }

                        } else if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()){
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                            if (!player.getPlayer().hasPermission(Main.cfg.getString("Permission.Bypass"))) {
                                // ========================================
                                String argument = Main.id.getString(args[1] + ".BanZeit");
                                long min = 0;
                                long hour = 0;
                                long day = 0;
                                long week = 0;
                                if (argument.endsWith("m") || argument.endsWith("min"))
                                    min = Long.valueOf(argument.replaceAll("m", "").replaceAll("min", "")) * 1000 * 60;
                                if (argument.endsWith("h") || argument.endsWith("hour"))
                                    hour = Long.valueOf(argument.replaceAll("h", "").replaceAll("hour", "")) * 1000 * 60 * 60;
                                if (argument.endsWith("d") || argument.endsWith("day"))
                                    day = Long.valueOf(argument.replaceAll("d", "").replaceAll("day", "")) * 1000 * 60 * 60 * 24;
                                if (argument.endsWith("w") || argument.endsWith("week"))
                                    week = Long.valueOf(argument.replaceAll("w", "").replaceAll("week", "")) * 1000 * 60 * 60 * 24 * 7;
                                long time = System.currentTimeMillis() + min + hour + day + week;
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
                                Date date = new Date(time);
                                String datum = sdf.format(date);
                                // ========================================
                                Insert.main("Players", "Name, UUID, Aussteller, ID, Zeitraum", "'" + player.getName() + "', '" + player.getUniqueId().toString() + "', '" + sender.getName() + "', '" + args[1] + "', '" + time + "'");
                                for (String s : Main.message.getStringList("WarnBroadcast")) {
                                    sender.sendMessage(s.replaceAll("%prefix%", Main.message.getString("Prefix"))
                                            .replaceAll("%player%", player.getName()).replaceAll("%sender%", sender.getName()
                                                    .replaceAll("%grund%", Main.id.getString(args[1] + ".BanGrund")).replaceAll("&", "§")));
                                }
                            } else {
                                sender.sendMessage(Main.message.getString("WarnPlayerBypass").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%player%", player.getName()).replaceAll("&", "§"));
                            }
                        } else sender.sendMessage(Main.message.getString("PlayerNotFound").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%Name%", args[0]).replaceAll("&", "§"));
                    } else sender.sendMessage(Main.message.getString("NoPermissionforID").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));
                } else sender.sendMessage(Main.message.getString("ToManyArguments").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%BaseCommand%", "/warn <Player> <ID>").replaceAll("&", "§"));
            } else sender.sendMessage(Main.message.getString("NoPermission").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));
        }
        return false;
    }
}
