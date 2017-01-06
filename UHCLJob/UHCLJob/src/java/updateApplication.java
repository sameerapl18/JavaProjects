/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sameerapl18
 */
@ManagedBean
@SessionScoped
public class updateApplication implements Serializable {

    private int studentID;
    private int jobID;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
    
    /**
     * Creates a new instance of updateApplication
     */
    public updateApplication() {
    }
    
    public String select(int studentId, int jobId){
         System.out.println("inside firststep");
    Dao dao = new Dao();
    status = dao.updateApplicantStatus(studentId, jobId, "Approved");
        studentID = studentId;
        jobID = jobId;
         System.out.println("output - "+studentID+", "+jobID+", "+status);
        return "statusHRApplication";
    }
    
    public String reject(int studentId, int jobId){
     Dao dao = new Dao();
     status = dao.updateApplicantStatus(studentId, jobId,"Rejected");
     studentID = studentId;
     jobID = jobId;
     return "statusHRApplication";
    }
    
    public String notifyStudents(int jobId){
    System.out.println("inside method - ");
        Dao dao = new Dao();
        status = dao.NotifyStudents(jobId);
        
    return "notifyResultStatus";
    }
}
