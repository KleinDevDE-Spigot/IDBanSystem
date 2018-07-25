package de.kleindev.idbansystem.commands;

import de.kleindev.idbansystem.Main;
import de.kleindev.idbansystem.tools.SQLite.Select;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Warns implements CommandExecutor{

    /*  Optional:
    *  /warns - zeigt nur Spielernamen an
    *  /warns <Spieler> - zeigt mehr Informationen zum Spieler an
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warns")){
            if (sender.hasPermission(Main.cfg.getString("Permissions.warns"))) {
                if (args.length == 0) {
                    List<String> list = Select.List("Players");
                    sender.sendMessage(Main.message.getString("WarnsBase").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%size%", String.valueOf(list.size())).replaceAll("&", "§"));
                    // 0 = Name
                    // 1 = UUID
                    // 2 = Aussteller
                    // 3 = BanID
                    // 4 = Zeitraum
                    for (String s : list) {
                        String[] array = s.split(" ");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
                        Date date = new Date(array[4]);
                        String datum = sdf.format(date);
                        sender.sendMessage(Main.message.getString("WarnsList").replaceAll("%Name%", array[0]).replaceAll("%UUID%", array[1]).replaceAll("%Aussteller%", array[2]).replaceAll("%BanID%", array[3]).replaceAll("%Zeitraum%", datum).replaceAll("%Grund%", Main.id.getString(args[3] + ".BanGrund")));
                    }
                } else sender.sendMessage(Main.message.getString("ToManyArguments").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("%BaseCommand%", "/warns").replaceAll("&", "§"));
            } else sender.sendMessage(Main.message.getString("NoPermission").replaceAll("%prefix%", Main.message.getString("Prefix")).replaceAll("&", "§"));

        }
        return false;
    }
}
