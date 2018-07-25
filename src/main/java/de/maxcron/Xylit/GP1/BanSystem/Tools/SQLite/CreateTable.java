package de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite;

import de.maxcron.Xylit.GP1.BanSystem.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ===============================
 * Sandra
 * Created by Xylit
 * 2017
 * ==============================
 */


public class CreateTable {

    public static void create(){
        Players();
        System.out.print("Created Tables successfully");
    }

    private static void Players() {
        Statement stmt = null;
        try {

            stmt = ConnectSQLite.c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Players " +
                    "(Name               TEXT    NOT NULL," +
                    " UUID              TEXT    PRIMARY KEY NOT NULL, " +
                    " Aussteller        TEXT    NOT NULL, " +
                    " ID                TEXT    REAL    , " +
                    " Zeitraum          TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}