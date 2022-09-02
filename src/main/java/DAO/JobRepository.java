package DAO;

import Model.Job;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRepository {
    Connection conn;

    public JobRepository() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public Job getDescriptionFromClass(String cla) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from Class where class_name = ?");
            statement.setString(1, cla);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return new Job(rs.getString("class_name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
