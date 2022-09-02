package Model;

public class Actor {
    public String name;
    public int level;
    public String cla;
   // public int actorID;

    public Actor(String name, String cla, int level){
        this.name = name;
        this.cla = cla;
        this.level = level;
      //  this.actorID = actorID;
    }

    @Override
    public String toString() {
        return name + " is a level " + level + " " + cla;
    }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public String getCla() { return cla; }

   // public int getActorID() { return actorID; }

  //  public void setActorID(int actorID) { this.actorID = actorID; }
}
