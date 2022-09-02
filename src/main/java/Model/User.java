package Model;

import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    Connection conn;
    public String username;
    public String password;
    public int userID;

    public User() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public User(String username, String password, int userID){
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        int newID = 0;
        try{
            PreparedStatement statement = conn.prepareStatement("select MAX(userID) from Userlist");
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int last = rs.getInt("userID");
                newID = last + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userID = newID;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getUserID() { return userID; }
}
