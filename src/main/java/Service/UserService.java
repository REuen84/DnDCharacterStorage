package Service;

import DAO.UserRepository;
import Model.User;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

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
}
