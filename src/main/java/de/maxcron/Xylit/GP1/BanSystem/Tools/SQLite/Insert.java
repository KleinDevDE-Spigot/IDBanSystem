package de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite;

import de.maxcron.Xylit.GP1.BanSystem.main;
import org.bukkit.ChatColor;

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
public class Insert {
    public static void main(String TableName, String Name, String Value) {
        Statement stmt = null;
        System.out.print(ChatColor.BLUE + "DEBUG -- " + "TableName: " + TableName);
        System.out.print(ChatColor.BLUE + "DEBUG -- " + "Name: " + Name);
        System.out.print(ChatColor.BLUE + "DEBUG -- " + "Value: " + Value);

        try {
            ConnectSQLite.c.setAutoCommit(false);

            stmt = ConnectSQLite.c.createStatement();
            String sql = "INSERT INTO " + TableName + " (" + Name + ") " +
                    "VALUES (" + Value + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            ConnectSQLite.c.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
