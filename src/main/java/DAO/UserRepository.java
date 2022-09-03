package DAO;

import Model.User;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    Connection conn;

    public UserRepository() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public User getUserByUsername(String username){
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Userlist where username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                User u = new User(rs.getString("username"), rs.getString("password"), rs.getInt("user_id"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User u){
        try{
            PreparedStatement statement = conn.prepareStatement("insert into Userlist(username, password, user_id) " + "values (?, ?, ?)");
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            statement.setInt(3, u.getUserID());
            statement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
