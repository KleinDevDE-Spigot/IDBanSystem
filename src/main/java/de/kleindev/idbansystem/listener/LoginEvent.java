package de.kleindev.idbansystem.listener;

import de.kleindev.idbansystem.Main;
import de.kleindev.idbansystem.tools.SQLite.Delete;
import de.kleindev.idbansystem.tools.SQLite.Select;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginEvent implements Listener {
    @EventHandler (priority = EventPriority.HIGH)
    public void onLogin(PlayerLoginEvent e){
        if (Select.Search("Players", "UUID", e.getPlayer().getUniqueId().toString())){
            String time = Select.main("Players", "UUID", e.getPlayer().getUniqueId().toString(), "Zeitraum");
            String aussteller = Select.main("Players", "UUID", e.getPlayer().getUniqueId().toString(), "Aussteller");
            if (time != null) {
                if (Long.parseLong(time) > System.currentTimeMillis()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
                    Date date = new Date(Long.parseLong(time));
                    String datum = sdf.format(date);
                    String g = Select.main("Players", "UUID", e.getPlayer().getUniqueId().toString(), "ID");
                    String grund = Main.id.getString(g + ".BanGrund")
    ;                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, Main.message.getString("KickMessage").replaceAll("%newline%", "\n").replaceAll("%BanGrund%", grund).replaceAll("%Zeit%", datum).replaceAll("%Aussteller%", aussteller).replaceAll("&", "§"));
                } else {
                    Delete.main("Players", e.getPlayer().getUniqueId().toString());
                }
            }
        }
    }
}
