package de.kleindev.idbansystem.tools.SQLite;

import de.kleindev.idbansystem.Main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectSQLite {
    public static Connection c;
    public static boolean start() {
        boolean status = true;
        boolean failure = false;
        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Main.plugin.getDataFolder().getPath() + File.separator +  "Bans.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            status = false;
        }
        return status;
    }

    public static boolean check(){
        boolean result = Boolean.parseBoolean(Select.main("Test", "Check", "connect", "Result"));
        return result;
    }
}