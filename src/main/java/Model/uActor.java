package Model;

public class uActor {
    public int actorID;
    public int newLevel;
    public String newUrl;

    public uActor(){}

    public uActor(int actorID, int newLevel){
        this.actorID = actorID;
        this.newLevel = newLevel;
        this.newUrl = "null";
    }

    public uActor(int actorID, String newUrl){
        this.actorID = actorID;
        this.newLevel = 0;
        this.newUrl = newUrl;
    }

    public int getActorID() { return actorID; }

    public int getNewLevel() { return newLevel; }

    public String getNewUrl() { return newUrl; }
}
