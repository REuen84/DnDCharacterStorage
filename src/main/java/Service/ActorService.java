package Service;

import DAO.ActorRepository;
import Model.Actor;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ActorService {
    ActorRepository ar;
    Connection conn = ConnectionUtil.getConnection();

    public ActorService() throws SQLException {
        ar = new ActorRepository();
    }

    public ActorService(ActorRepository ar) throws SQLException {
        this.ar = ar;
    }

    public List<Actor> getAllActors() { return ar.getAllActors(); }

    public void addActor(String name, String cla, int level) throws SQLException {
        Actor existingActor = ar.getActorByClass(cla);
        if(existingActor == null) {
            Actor newActor = new Actor(name, cla, level);
            ar.addActor(newActor);
        }else{}
        conn.commit();
    }
}
