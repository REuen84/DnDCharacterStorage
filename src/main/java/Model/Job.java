package Model;

public class Job {
    public String cla;
    public String desc;

    public Job(String cla, String desc){
        this.cla = cla;
        this.desc = desc;
    }

    public String getCla() { return cla; }

    public String getDesc() { return desc; }

    @Override
    public String toString() {
        return desc;
    }
}
