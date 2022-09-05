package Service;

import DAO.JobRepository;
import Model.Job;

import java.sql.SQLException;

public class JobService {
    JobRepository jr;

    public JobService() throws SQLException {
        jr = new JobRepository();
    }

    public JobService(JobRepository jr) { this.jr = jr; }

    public Job getDescriptionFromClass(String cla) { return jr.getDescriptionFromClass(cla); }

    public boolean checkClass(String cla){
        Job j = jr.getDescriptionFromClass(cla);
        if (j == null){
            return false;
        }else{ return true;}
        }

    }
