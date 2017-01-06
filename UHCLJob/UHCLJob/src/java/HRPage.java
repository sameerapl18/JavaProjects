/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sameerapl18
 */
@ManagedBean
@SessionScoped
public class HRPage {

    /**
     * Creates a new instance of HRPage
     */
    private int vacancies;
    private String jobDate;
    private String jobTitle;
    private String jobbDescription;

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobbDescription() {
        return jobbDescription;
    }

    public void setJobbDescription(String jobbDescription) {
        this.jobbDescription = jobbDescription;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    private String jobStatus;
    private int jobID;
   // private AppliedJobs appliedJobs;
    private ArrayList<JobsApplication> jobs;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public ArrayList<JobsApplication> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<JobsApplication> jobs) {
        this.jobs = jobs;
    }
    
    
    
    public HRPage() {
    }
    
    public String getAllJobsHR(){
    Dao dao = new Dao();
    jobs = dao.getAllJobsHR();
        
        return "allJobsHR";
    }
    
    public String modifyJob(int jobID,String jobDate,String jobDescription,String jobStatus,String jobTitle,int vacancies){
    this.jobID = jobID;
     this.jobDate = jobDate;
     this.jobStatus = jobStatus;
     this.jobTitle = jobTitle;
     this.jobbDescription = jobDescription;
     this.vacancies = vacancies;
    return "modifyHRJob";
    }
    
    public String submitModify(){
    Dao dao = new Dao();
    HRPage hrPage = new HRPage();
    hrPage.setJobID(jobID);
    hrPage.setJobStatus(jobStatus);
    hrPage.setJobTitle(jobTitle);
    hrPage.setJobbDescription(jobbDescription);
    hrPage.setVacancies(vacancies);
    System.out.println("in submit modify - "+hrPage.getJobStatus());
    boolean success = dao.SubmitModifyJob(hrPage);
    if(success){
        
    return "successModify";
    }
    else{
    return "error";
    }
    }
    
}
