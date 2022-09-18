package Service;

import DAO.ActorRepository;
import Model.Actor;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ActorService {
    ActorRepository ar;
    Connection conn = ConnectionUtil.getConnection();

    public ActorService() throws SQLException {
        ar = new ActorRepository();
    }

    public ActorService(ActorRepository ar) throws SQLException {
        this.ar = ar;
    }

    public List<Actor> getAllActors() {
        return ar.getAllActors();
    }

    public List<Actor> getActorsByUser(String username){
        return ar.getActorByUser(username);
    }

    /*public void addActor(String name, String cla, int level, String currentUser, int userID) throws SQLException {
        List<Actor> existingActors = ar.getActorByUser(currentUser);
        boolean unique = true;
        for (int i = 0; i < existingActors.size(); i++) {
            if (Objects.equals(existingActors.get(i).getName(), name)) {
                unique = false;
                break;
            }
        }
        if (unique) {
            Actor newActor = new Actor(name, cla, level, userID);
            ar.addActor(newActor);
            System.out.println("New character successfully saved!");
        } else {
            System.out.println("Hmm, seems like it didn't work. Maybe you already named a character that? Try something else.");
        }*/
    public void addActor(String name, String cla, int level, int userID) throws SQLException {
        Actor newActor = new Actor(name, cla, level, userID);
        ar.addActor(newActor);
        conn.commit();
    }

   /* public void updateActor(String name, int userID, int newLevel) throws SQLException {
        Actor a = ar.getActorByName(name, userID);

        if (a == null) {
            System.out.println("Could not find a character with that name. Verify the name and try again.");
        } else {
            ar.updateActor(a, newLevel);
            System.out.println("Level updated!");
        }
        conn.commit();
    }*/

    public void updateActor(int actorID, int newLevel) throws SQLException {
        Actor a = ar.getActorByID(actorID);
            ar.updateActor(a, newLevel);
            System.out.println("Level updated!");
        conn.commit();
    }

    public void updateArt(int actorID, String newUrl) throws SQLException {
        Actor a = ar.getActorByID(actorID);
        ar.updateArt(a, newUrl);
        System.out.println("Art updated!");
        conn.commit();
    }


    public boolean deleteActor1(String name, int userID) {
        Actor a = ar.getActorByName(name, userID);

        if (a == null) {
            System.out.println("Could not find a character with that name. Verify the name and try again.");
            System.out.println("");
            return false;
        } else {
            //ar.deleteActor(a);
            // System.out.println("Character deleted, they will be missed.");
            return true;
        }
    }

    public void deleteActor2(String name, int userID) throws SQLException {
        Actor a = ar.getActorByName(name, userID);
        ar.deleteActor(a);
        conn.commit();
        System.out.println("Character deleted, they will be missed.");
    }

    public Actor getActorfromID(String chaID) {
        int actorID = Integer.parseInt(chaID);
        return ar.getActorByID(actorID); }

}
