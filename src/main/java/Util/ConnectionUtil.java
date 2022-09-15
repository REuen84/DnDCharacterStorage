package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if(conn == null){
            try{
                String url = "jdbc:sqlserver://revaeuen.database.windows.net:1433;database=DnDCharacterStorage;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                String username = "Cruzar";
                String password = "P@SSWORD123";
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn.setAutoCommit(false);
        return conn;
    }
}
