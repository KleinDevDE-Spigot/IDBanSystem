package de.maxcron.Xylit.GP1.BanSystem.Tools;


/**
 * Created by Klein on 27.11.2016.
 */
import java.sql.*;

public class MySQL {

    public static String username, password, host, database, port;
    private static Connection con;

    public static void connect() {
        try {
            if (!isConnected()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (isConnected()) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void update(String qry) {
        try {
            if (isConnected()) {
                try {
                    con.createStatement().executeUpdate(qry);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void createTable(String tablename, String request) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename + " (" + request + ")");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static String getString(String TableName, String Name, String Content, String Gesucht) {

        Statement stmt = null;
        String output = "";
        try {
            con.setAutoCommit(false);

            stmt = con.createStatement();
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
}
