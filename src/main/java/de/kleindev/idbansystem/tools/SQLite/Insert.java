package de.kleindev.idbansystem.tools.SQLite;

import org.bukkit.ChatColor;

import java.sql.Statement;

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
