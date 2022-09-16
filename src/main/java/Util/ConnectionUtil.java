package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection conn;
    public static final String dbusername = System.getenv("dbusername");
    public static final String dbpassword = System.getenv("dbpassword");

    public static Connection getConnection() throws SQLException {
        if(conn == null){
            try{
                String url = "jdbc:sqlserver://revaeuen.database.windows.net:1433;database=DnDCharacterStorage;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                String username = dbusername;
                String password = dbpassword;
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn.setAutoCommit(false);
        return conn;
    }
}
