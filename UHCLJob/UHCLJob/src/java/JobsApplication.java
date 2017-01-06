/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
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
public class JobsApplication implements Serializable {

    /**
     * Creates a new instance of JobsApplication
     */
    private String jobTitle;
    private int jobID;
    private String jobDescription;
    private int vacancies;
    private String jobStatus;
    private String jobDate;
    private int totalApplicants;
    private int newApplicants;
    private JobsApplication jobsApplic;
    private String showApply;
private boolean showApplyJob;
private ArrayList<JobsApplication> myJobs;

    public ArrayList<JobsApplication> getMyJobs() {
        return myJobs;
    }

    public void setMyJobs(ArrayList<JobsApplication> myJobs) {
        this.myJobs = myJobs;
    }


    public boolean isShowApplyJob() {
        return showApplyJob;
    }

    public void setShowApplyJob(boolean showApplyJob) {
        this.showApplyJob = showApplyJob;
    }

    public String getShowApply() {
        return showApply;
    }

    public void setShowApply(String showApply) {
        this.showApply = showApply;
    }

    public JobsApplication getJobsApplic() {
        return jobsApplic;
    }

    public void setJobsApplic(JobsApplication jobsApplic) {
        this.jobsApplic = jobsApplic;
    }

    public int getTotalApplicants() {
        return totalApplicants;
    }

    public void setTotalApplicants(int totalApplicants) {
        this.totalApplicants = totalApplicants;
    }

    public int getNewApplicants() {
        return newApplicants;
    }

    public void setNewApplicants(int newApplicants) {
        this.newApplicants = newApplicants;
    }
    

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }
    
    
    public JobsApplication() {
    }
    
    public String getJobDetails(int jobid, String jobstatus, String userID){
    
        Dao dao = new Dao();
         
       jobsApplic = dao.getJobDetails(jobid);
        showApply = jobstatus;
        int studentID = dao.getStudentID(userID);
        showApplyJob = dao.checkStudentExist(studentID);
        System.out.println("show apply job - "+showApplyJob);
        
        return "studentApplyJob";
    }
    
    public String applyJobForStudent(int jobid, String userID, String status){
    
        Dao dao = new Dao();
         int studentID = dao.getStudentID(userID);
       
       boolean successResult = dao.applyJob(jobid, studentID, status);
        if(successResult){
        return "successApplyJobStudent";
        }
        else{
        return "error";
        }
           }
    
    public String withdrawJobForStudent(int jobid, String userid){
    Dao dao = new Dao();
    int studentID = dao.getStudentID(userid);
    
    boolean successResult = dao.withdrawJob(jobid, studentID);
    if(successResult){
    return "withdrawsuccess";
    }
    else{
    return "error";
    }
    
    
    }
    
    public String addNewJob(){
    Dao dao = new Dao();
   boolean success = dao.addNewJob(jobTitle,jobDescription,vacancies,jobStatus);
   if(success){
       jobTitle = null;
       jobDescription = null;
       vacancies = 0;
       jobStatus = null;
   return "newJobSuccess";
   }
   else{
   return "error";
   }
    }
    
    public String studAppliedJobs(String userId)
    {
        Dao dao = new Dao();
        int studentID = dao.getStudentID(userId);
        myJobs = dao.getMyAplliedJobsStud(studentID);
         return "myAppliedJobsStud";       
    }
    
    
    
}
