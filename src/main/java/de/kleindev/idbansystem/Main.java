package de.kleindev.idbansystem;

import de.kleindev.idbansystem.commands.Unwarn;
import de.kleindev.idbansystem.commands.Warn;
import de.kleindev.idbansystem.commands.Warns;
import de.kleindev.idbansystem.listener.LoginEvent;
import de.kleindev.idbansystem.tools.Config;
import de.kleindev.idbansystem.tools.SQLite.ConnectSQLite;
import de.kleindev.idbansystem.tools.SQLite.CreateTable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    public static Plugin plugin;
    public static FileConfiguration cfg;
    public static FileConfiguration id;
    public static FileConfiguration message;


    public void onEnable(){
        plugin = this;
        cfg  = getConfig();
        saveDefaultConfig();
        Config.copy("BanIDs.yml");
        Config.copy("Messages.yml");
        id = Config.load("BanIDs.yml");
        message = Config.load("Messages.yml");
        check();
        preSetup();
    }

    private void preSetup(){
        this.getCommand("warn").setExecutor(new Warn());
        this.getCommand("warns").setExecutor(new Warns());
        this.getCommand("unwarn").setExecutor(new Unwarn());
        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
        System.out.print(ChatColor.BLUE + "BanSystem LOADED");
        ConnectSQLite.start();
        CreateTable.create();
    }

    private void check(){
        if (cfg.getString("Permissions.warn") == null) {
            System.err.print("[BanSystem] The Permission for /warn are not set!!");
            System.exit(1);
        } else if (cfg.getString("Permissions.warns") == null) {
            System.err.print("[BanSystem] The Permission for /warns are not set!!");
            System.exit(1);
        } else if (cfg.getString("Permissions.bypass") == null) {
            System.err.print("[BanSystem] The Permission for the Bypass are not set!!");
            System.exit(1);
        } else if (cfg.getString("Permissions.unwarn") == null) {
            System.err.print("[BanSystem] The Permission for /unwarn are not set!!");
            System.exit(1);
        }


        if (cfg.getString("MySQL.Host") == null) System.out.append("[BanSystem] MySQL Config invalid, no Host defined! Falling back to SQLite");
        else if (cfg.getString("MySQL.Port") == null) System.out.append("[BanSystem] MySQL Config invalid, no Port defined! Falling back to SQLite");
        else if (cfg.getString("MySQL.Database") == null) System.out.append("[BanSystem] MySQL Config invalid, no Database defined! Falling back to SQLite");
        else if (cfg.getString("MySQL.Username") == null) System.out.append("[BanSystem] MySQL Config invalid, no Username defined! Falling back to SQLite");
        else if (cfg.getString("MySQL.Password") == null) System.out.append("[BanSystem] MySQL Config invalid, no Password defined! Falling back to SQLite");
    }
}
