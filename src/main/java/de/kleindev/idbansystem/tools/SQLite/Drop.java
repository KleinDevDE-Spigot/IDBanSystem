package de.kleindev.idbansystem.tools.SQLite;

import java.sql.Statement;

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
