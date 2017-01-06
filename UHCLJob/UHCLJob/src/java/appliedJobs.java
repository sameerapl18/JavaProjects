/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sameerapl18
 */
@ManagedBean
@SessionScoped
public class appliedJobs implements Serializable {
 
    private ArrayList<appliedJobs> students;
    private String studentName;
    private String studentStaus_HR;
    private int jobID;
    private int studentID;
    private int totalVacancies;
    private int selectedCount;
    private int totalApplied;
    private boolean showPostResult;
    private String studentStatus;
    private String jobTitle;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }
    
    

    public boolean isShowPostResult() {
        return showPostResult;
    }

    public void setShowPostResult(boolean showPostResult) {
        this.showPostResult = showPostResult;
    }
     
    public int getTotalApplied() {
        return totalApplied;
    }

    public void setTotalApplied(int totalApplied) {
        this.totalApplied = totalApplied;
    }

    public int getTotalVacancies() {
        return totalVacancies;
    }

    public void setTotalVacancies(int totalVacancies) {
        this.totalVacancies = totalVacancies;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }
    
   

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentStaus_HR() {
        return studentStaus_HR;
    }

    public void setStudentStaus_HR(String studentStaus_HR) {
        this.studentStaus_HR = studentStaus_HR;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public ArrayList<appliedJobs> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<appliedJobs> students) {
        this.students = students;
    }
    /**
     * Creates a new instance of appliedJobs
     */
    
    
    public appliedJobs() {
    }
    public String appliedJob(int jobid){
    Dao dao = new Dao();
    System.out.println("job id - "+jobid);
    students = dao.getStudentsWithJobID(jobid);
    jobTitle = dao.getJobTitle(jobid);
    jobID = jobid;
    appliedJobs applyjob = new appliedJobs();
    
    showPostResult = dao.getStudentJobStatus(jobID);
    
    applyjob = dao.getJobDetailedCount(jobid);
    totalVacancies = applyjob.getTotalVacancies();
    totalApplied = applyjob.getTotalApplied();
    selectedCount = applyjob.getSelectedCount();
    return "jobapplicationsstudent";
    }
    
}
