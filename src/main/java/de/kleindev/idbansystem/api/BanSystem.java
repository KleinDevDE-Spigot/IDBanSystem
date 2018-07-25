package de.kleindev.idbansystem.api;

import de.kleindev.idbansystem.tools.SQLite.Delete;
import de.kleindev.idbansystem.tools.SQLite.Insert;
import de.kleindev.idbansystem.tools.SQLite.Select;
import de.kleindev.idbansystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
public class BanSystem {
    private List<BannedPlayer> bannedPlayers = new ArrayList<BannedPlayer>();

    public static void addBan(Player player, CommandSender sender, Integer BanID){
        // ========================================
        String argument = Main.id.getString(BanID + ".BanZeit");
        long milli = System.currentTimeMillis();
        long min = 0;
        long hour = 0;
        long day = 0;
        long week = 0;
        if (argument.endsWith("m") || argument.endsWith("min")) min = Long.valueOf(argument.replaceAll("m", "").replaceAll("min", ""))*1000*60;
        if (argument.endsWith("h") || argument.endsWith("hour")) min = Long.valueOf(argument.replaceAll("h", "").replaceAll("hour", ""))*1000*60*60;
        if (argument.endsWith("d") || argument.endsWith("day")) min = Long.valueOf(argument.replaceAll("d", "").replaceAll("day", ""))*1000*60*60*24;
        if (argument.endsWith("w") || argument.endsWith("week")) min = Long.valueOf(argument.replaceAll("w", "").replaceAll("week", ""))*1000*60*60*24*7;
        long time = milli+min+hour+day+week;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
        Date date = new Date(time);
        String datum = sdf.format(date);
        // ========================================
        Insert.main("Players", "Name, UUID, Aussteller, Grund, Zeitraum", player.getName() + ", " + player.getUniqueId() + ", " + sender.getName() + ", " + BanID + ", " + time);
        for (String s : Main.message.getStringList("WarnBroadcast")) {
            sender.sendMessage(s.replaceAll("%prefix%", Main.message.getString("Prefix"))
                    .replaceAll("%player%", player.getName()).replaceAll("%sender%", sender.getName()
                            .replaceAll("%grund%", Main.id.getString(BanID+".BanGrund")).replaceAll("&", "§")));
        }
        player.kickPlayer("§3Du wurdest verwarnt!\n\n§3Grund: §f" + Main.id.getString(BanID + ".BanGrund") + "\n\n§3Ablauf: " + datum);
    }

    public static void removeBan(String uuid){
        Delete.main("Players", uuid);
    }

    public static List<String> listBans(){
        return Select.List("Players");
    }

    public static boolean isBanned(Player player){
        return Select.Search("Players", "UUID", player.getUniqueId().toString());
    }

    public static boolean isBanned(OfflinePlayer player){
        return Select.Search("Players", "UUID", player.getUniqueId().toString());
    }

    public static BannedPlayer getBannedPlayerbyName(String name){
        try {
            UUID uuid = UUID.fromString(Select.main("Players", "Name", name, "UUID"));
            Integer ID = Integer.parseInt(Select.main("Players", "UUID", uuid.toString(), "BanID"));
            Long time = Long.parseLong(Select.main("Players", "UUID", uuid.toString(), "time"));
            String BanReason = Main.id.getString(ID + ".BanGrund");
            String Creator = Select.main("Players", "UUID", uuid.toString(), "Aussteller");
            OfflinePlayer OfflinePlayer = Bukkit.getOfflinePlayer(uuid);
            BannedPlayer bannedPlayer = new BannedPlayer(OfflinePlayer, name, uuid, BanReason, Creator, ID, time);
            return bannedPlayer;
        } catch (NullPointerException e){
            System.err.print("~api~  -->  Der Spieler \"" + name + "\" konnte nicht im BanSystem gefunden werden!");
            return null;
        } catch (NumberFormatException e){
            System.err.print("~api~  -->  Aus irgendeinem Grund ist die BanID keine Zahl! - Weitere Infos im Log");
            System.out.print("Eigentlich sollte dieser Fehler nicht auftreten.\nDaher bitte ich dich mich zu kontaktieren: \nDiscord: Xylit#2979\nSkype: mati.klein");
            return  null;
        }
    }

    public List<BannedPlayer> getBannedPlayers(){
        return bannedPlayers;
    }
}
