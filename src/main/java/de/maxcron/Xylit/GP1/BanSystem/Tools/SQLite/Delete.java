package de.maxcron.Xylit.GP1.BanSystem.Tools.SQLite;

import java.sql.Connection;
import java.sql.Statement;

/**
 * ===============================
 * GastPlugin - BanSystem - FlexDE
 * Created by Xylit
 * 2017
 * ==============================
 */
public class Delete{
        public static void main(String TableName, String uuid) {
            Connection c = null;
            Statement stmt = null;

            try {
                ConnectSQLite.c.setAutoCommit(false);

                stmt = ConnectSQLite.c.createStatement();
                String sql = "DELETE FROM " + TableName + " WHERE UUID='" + uuid + "'";
                stmt.executeUpdate(sql);

                stmt.close();
                ConnectSQLite.c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
}
