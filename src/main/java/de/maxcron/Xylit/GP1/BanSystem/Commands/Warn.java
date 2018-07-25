package de.maxcron.Xylit.GP1.BanSystem.Commands;

import de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite.Insert;
import de.maxcron.Xylit.GP1.BanSystem.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
public class Warn implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warn")){
            if (sender.hasPermission(main.cfg.getString("Permissions.warn"))){
                if (args.length == 0){
                    sender.sendMessage(main.message.getString("WarnBase").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                } else if (args.length == 1){
                    if (args[0].equalsIgnoreCase("list")){
                        sender.sendMessage(main.message.getString("WarnListID").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                        int id = 1;
                        while (id != 0){
                            sender.sendMessage(id + "§7 - " + main.id.getString(id + ".Name") + " §7- §e" + main.id.getString(id + ".BanZeit"));
                            id = id+1;
                            if (main.id.get(String.valueOf(id)) == null) id = 0;
                        }
                    } else sender.sendMessage(main.message.getString("WarnMissingID").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                } else if (args.length == 2){
                    if (sender.hasPermission(main.id.getString(args[1] + ".Permission"))) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player player = Bukkit.getPlayer(args[0]);
                            if (!player.hasPermission(main.cfg.getString("Permissions.bypass"))) {
                                // ========================================
                                String argument = main.id.getString(args[1] + ".BanZeit");
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
                                for (String s : main.message.getStringList("WarnBroadcast")) {
                                    for (Player p : Bukkit.getOnlinePlayers()){
                                        p.sendMessage(s.replaceAll("%prefix%", main.message.getString("Prefix"))
                                                .replaceAll("%player%", player.getName()).replaceAll("%sender%", sender.getName()
                                                        .replaceAll("%grund%", main.id.getString(args[1] + ".BanGrund")).replaceAll("&", "§")));
                                    }
                                }
                                player.kickPlayer(main.message.getString("KickMessage").replaceAll("%newline%", "\n").replaceAll("%BanGrund%", main.id.getString(args[1] + ".BanGrund")).replaceAll("%Zeit%", datum).replaceAll("%Aussteller%", sender.getName()).replaceAll("&", "§"));
                            } else {
                                sender.sendMessage(main.message.getString("WarnPlayerBypass").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("%player%", player.getName()).replaceAll("&", "§"));
                                // Spieler hat Bypass Permission
                            }

                        } else if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()){
                            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                            if (!player.getPlayer().hasPermission(main.cfg.getString("Permission.Bypass"))) {
                                // ========================================
                                String argument = main.id.getString(args[1] + ".BanZeit");
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
                                for (String s : main.message.getStringList("WarnBroadcast")) {
                                    sender.sendMessage(s.replaceAll("%prefix%", main.message.getString("Prefix"))
                                            .replaceAll("%player%", player.getName()).replaceAll("%sender%", sender.getName()
                                                    .replaceAll("%grund%", main.id.getString(args[1] + ".BanGrund")).replaceAll("&", "§")));
                                }
                            } else {
                                sender.sendMessage(main.message.getString("WarnPlayerBypass").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("%player%", player.getName()).replaceAll("&", "§"));
                            }
                        } else sender.sendMessage(main.message.getString("PlayerNotFound").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("%Name%", args[0]).replaceAll("&", "§"));
                    } else sender.sendMessage(main.message.getString("NoPermissionforID").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                } else sender.sendMessage(main.message.getString("ToManyArguments").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("%BaseCommand%", "/warn <Player> <ID>").replaceAll("&", "§"));
            } else sender.sendMessage(main.message.getString("NoPermission").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
        }
        return false;
    }
}
