package de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite;

import de.maxcron.Xylit.GP1.BanSystem.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
public class Drop {
    public static void main(String TableName) {
        Statement stmt = null;

        try {
            ConnectSQLite.c.setAutoCommit(false);

            stmt = ConnectSQLite.c.createStatement();
            String sql = "DROP TABLE " + TableName + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            ConnectSQLite.c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
