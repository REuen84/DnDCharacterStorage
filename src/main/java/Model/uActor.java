package Model;

public class uActor {
    public int actorID;
    public int newLevel;

    public uActor(){}

    public uActor(int actorID, int newLevel){
        this.actorID = actorID;
        this.newLevel = newLevel;
    }

    public int getActorID() { return actorID; }

    public int getNewLevel() { return newLevel; }
}
