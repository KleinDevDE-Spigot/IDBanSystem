package de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite;

import de.maxcron.Xylit.GP1.BanSystem.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ===============================
 * Sandra
 * Created by Xylit
 * 2017
 * ==============================
 */

public class ConnectSQLite {
    public static Connection c;
    public static boolean start() {
        boolean status = true;
        boolean failure = false;
        c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + main.plugin.getDataFolder().getPath() + File.separator +  "Bans.db");
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