package de.kleindev.idbansystem.tools.SQLite;

import java.sql.Statement;

public class CreateTable {

    public static void create(){
        players();
        System.out.print("Created Tables successfully");
    }

    private static void players() {
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