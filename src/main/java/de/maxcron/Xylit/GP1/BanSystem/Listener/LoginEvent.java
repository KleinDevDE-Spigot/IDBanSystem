package de.maxcron.Xylit.GP1.BanSystem.Listener;

import de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite.Delete;
import de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite.Select;
import de.maxcron.Xylit.GP1.BanSystem.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
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
                    String grund = main.id.getString(g + ".BanGrund")
    ;                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, main.message.getString("KickMessage").replaceAll("%newline%", "\n").replaceAll("%BanGrund%", grund).replaceAll("%Zeit%", datum).replaceAll("%Aussteller%", aussteller).replaceAll("&", "§"));
                } else {
                    Delete.main("Players", e.getPlayer().getUniqueId().toString());
                }
            }
        }
    }
}
