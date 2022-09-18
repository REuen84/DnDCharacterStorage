package Model;

public class Job {
    public String cla;
    public String desc;
    public String url;

    public Job(String cla, String desc, String url){
        this.cla = cla;
        this.desc = desc;
        this.url = url;
    }

    public String getCla() { return cla; }

    public String getDesc() { return desc; }

    public String getUrl() { return url; }

    @Override
    public String toString() {
        return desc;
    }
}
