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
                Actor loadedActor = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"), rs.getInt("cha_id"), rs.getInt("user_id"));
                allActors.add(loadedActor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allActors;
        }

        public void addActor(Actor a){
        try{
            PreparedStatement statement = conn.prepareStatement("insert into Actor(cha_name, cha_class, cha_level, cha_id, user_id) " + "values (?, ?, ?, ?, ?)");
            statement.setString(1, a.getName());
            statement.setString(2, a.getCla());
            statement.setInt(3, a.getLevel());
            statement.setInt(4, a.getActorID());
            statement.setInt(5, a.getUserID());
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
                Actor a = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"), rs.getInt("cha_id"), rs.getInt("user_id"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        }

        public List<Actor> getActorByUser(String currentUser){
        List<Actor> actors = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Actor inner join Userlist on Actor.user_id = Userlist.user_id where username = ?");
            statement.setString(1, currentUser);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Actor loadedActor = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"), rs.getInt("cha_id"), rs.getInt("user_id"));
                actors.add(loadedActor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
        }

        public Actor getActorByName(String name, int userID){
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Actor where cha_name = ? and user_id = ?");
            statement.setString(1, name);
            statement.setInt(2, userID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Actor a = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"), rs.getInt("cha_id"), rs.getInt("user_id"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        }

        public void updateActor(Actor a, int newLevel){
        try{
            PreparedStatement statement = conn.prepareStatement("update Actor set cha_level = ? where cha_id = ?");
            statement.setInt(1, newLevel);
            statement.setInt(2, a.getActorID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

        public void deleteActor(Actor a){
            try{
                PreparedStatement statement = conn.prepareStatement("delete from Actor where cha_name = ? and user_id = ?");
                statement.setString(1, a.getName());
                statement.setInt(2, a.getUserID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public Actor getActorByID(int actorID){
        try{
            PreparedStatement statement = conn.prepareStatement("select * from Actor where cha_id = ?");
            statement.setInt(1, actorID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Actor a = new Actor(rs.getString("cha_name"), rs.getString("cha_class"), rs.getInt("cha_level"), rs.getInt("cha_id"), rs.getInt("user_id"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    }


