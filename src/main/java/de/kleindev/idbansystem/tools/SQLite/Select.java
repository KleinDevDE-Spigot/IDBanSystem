package de.kleindev.idbansystem.tools.SQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {
    public static String main(String TableName, String Name, String Content, String Gesucht) {

        Statement stmt = null;
        String output = "";
        try {
            ConnectSQLite.c.setAutoCommit(false);

            stmt = ConnectSQLite.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TableName + " WHERE " + Name + "='" + Content + "';");
            while (rs.next()) {
                output = rs.getString(Gesucht);
            }
            rs.close();
            stmt.close();
            return output;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> List(String TableName) {
        Statement stmt = null;
        List<String> output = new ArrayList<String>();
        try {
            ConnectSQLite.c.setAutoCommit(false);
            stmt = ConnectSQLite.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TableName + ";");
            while(rs.next()){
                output.add(rs.getString("Name") + " " + rs.getString("UUID") + " " + rs.getString("Aussteller") + " " + rs.getString("BanID") + " " + rs.getString("Zeitraum"));
            }
            rs.close();
            stmt.close();
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean Search(String TableName, String Name, String Content) {

        Statement stmt = null;
        String[] output = new String[0];
        try {
            ConnectSQLite.c.setAutoCommit(false);

            stmt = ConnectSQLite.c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TableName + " WHERE " + Name + "='" + Content + "';");
            if (rs.next()){
                return true;
            }
            rs.close();
            stmt.close();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
