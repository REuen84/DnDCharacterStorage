package Model;

import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actor {
    Connection conn;
    public String name;
    public int level;
    public String cla;
    public int actorID;
    public int userID;
    public String url;

    public Actor() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }
    public Actor(String name, String cla, int level, int actorID, int userID, String url){
        this.name = name;
        this.cla = cla;
        this.level = level;
        this.actorID = actorID;
        this.userID = userID;
        this.url = url;
    }

    public Actor(String name, String cla, int level, int userID) throws SQLException {
        this.name = name;
        this.cla = cla;
        this.level = level;
        this.userID = userID;
        this.url = "https://image.shutterstock.com/image-vector/default-avatar-profile-icon-social-260nw-1913928688.jpg";
        int newID = 0;
        conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Actor where cha_id = ( select max(cha_id) from Actor )");
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int last = rs.getInt("cha_id");
                newID = last + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.actorID = newID;
    }

    @Override
    public String toString() {
        return name + " is a level " + level + " " + cla;
    }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public String getCla() { return cla; }

    public int getActorID() { return actorID; }

    public void setActorID(int actorID) { this.actorID = actorID; }

    public int getUserID() { return userID; }

    public String getUrl() { return url; }

}
