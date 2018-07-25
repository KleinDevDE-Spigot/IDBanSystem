package de.maxcron.Xylit.GP1.BanSystem.Commands;

import de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite.Delete;
import de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite.Select;
import de.maxcron.Xylit.GP1.BanSystem.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
public class Unwarn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("unwarn")){
            if (sender.hasPermission(main.cfg.getString("Permissions.unwarn"))) {
                if (args.length == 0){

                } else if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        if (Bukkit.getOfflinePlayer(args[0]) != null) {
                            //Kein OnlineSpieler | OfflineSpieler gefunden!  -- TRUE
                            String uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString();
                            Delete.main("Players", uuid);
                            sender.sendMessage(main.message.getString("Unwarn").replaceAll("%player%", args[0]).replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                        } else {
                            //Kein OnlineSpieler | Kein OfflineSpieler | Spieler nicht vorhanden! -- FALSE
                            sender.sendMessage(main.message.getString("UnwarnPlayerNotFound").replaceAll("%player%", args[0]).replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                        }
                    } else {
                        //OnlineSpieler gefunden!
                        if (Select.Search("Players", "UUID", Bukkit.getPlayer(args[0]).getUniqueId().toString())) {
                            //Spieler in Datenbank gefunden! -- Gebe Meldung aus dass Spieler online trotz Warn
                            sender.sendMessage(main.message.getString("UnwarnPlayerOnline").replaceAll("%player%", args[0]).replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                            Delete.main("Players", Bukkit.getPlayer(args[0]).getUniqueId().toString());
                        } else {
                            //SpielerOnline | Nicht in Datenbank gefunden!
                            sender.sendMessage(main.message.getString("UnwarnPlayerNotFound").replaceAll("%player%", args[0]).replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
                        }
                    }
                } else sender.sendMessage(main.message.getString("ToManyArguments").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("%BaseCommand%", "/unwarn <Player>").replaceAll("&", "§"));
            } else sender.sendMessage(main.message.getString("NoPermission").replaceAll("%prefix%", main.message.getString("Prefix")).replaceAll("&", "§"));
        }
        return false;
    }
}
