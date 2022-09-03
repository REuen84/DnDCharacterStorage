package Service;

import DAO.UserRepository;
import Model.User;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class UserService {
    UserRepository ur;
    Connection conn = ConnectionUtil.getConnection();

    public UserService() throws SQLException {
        ur = new UserRepository();
    }

    public UserService(UserRepository ur) throws SQLException {
        this.ur = ur;
    }

    public boolean checkLoginInfo(String username, String password) {
        User existingUser = ur.getUserByUsername(username);
        if (existingUser == null) {
            return false;
        } else {
            String existingname = existingUser.getUsername();
            String correctpword = existingUser.getPassword();
            if (existingname.equals(username) && correctpword.equals(password)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public User getUserByUsername(String username){ return ur.getUserByUsername(username); }

    public void addUser(String username, String password) throws SQLException {
        User u = ur.getUserByUsername(username);
        if(u == null){
            User newUser = new User(username, password);
            ur.addUser(newUser);
            System.out.println("New user successfully created! Try logging in now.");
        }else{
            System.out.println("Looks like that username is already taken, try a different one.");
        }
        conn.commit();
    }
}
