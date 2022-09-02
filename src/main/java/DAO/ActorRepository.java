package DAO;

import Model.Actor;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository {
    Connection conn;

    public ActorRepository() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public List<Actor> getAllActors(){
        List<Actor> allActors = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Actor");
            while(rs.next()){
                Actor loadedActor = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"));
                allActors.add(loadedActor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allActors;
        }

        public void addActor(Actor a){
        try{
            PreparedStatement statement = conn.prepareStatement("insert into Actor(cha_name, cha_class, cha_level) " + "values (?, ?, ?)");
            statement.setString(1, a.getName());
            statement.setString(2, a.getCla());
            statement.setInt(3, a.getLevel());
        //    statement.setInt(4, a.getActorID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

        public Actor getActorByClass(String cla){
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Actor where cha_class = ?");
            statement.setString(1, cla);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Actor a = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        }


    }
