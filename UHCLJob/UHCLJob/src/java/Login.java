/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Karthik
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{
    
    private String userID;
    private String password;
    private List<JobsApplication> jobs;
    private List<ListOfJobsStudent> listJobs;
    private boolean showApplyJob;

    public boolean isShowApplyJob() {
        return showApplyJob;
    }

    public void setShowApplyJob(boolean showApplyJob) {
        this.showApplyJob = showApplyJob;
    }
    

    public List<ListOfJobsStudent> getListJobs() {
        return listJobs;
    }

    public void setListJobs(List<ListOfJobsStudent> listJobs) {
        this.listJobs = listJobs;
    }
    

    public String getUserID() {
        return userID;
    }

    public List<JobsApplication> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsApplication> jobs) {
        this.jobs = jobs;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String login()
    {
        Dao dao = new Dao();
        String result = dao.Login(userID,password);
        String status = "open";
        if(result.equals("HR")){
        
        jobs = dao.getJobApplications(status);
        }
        //System.out.println("jobs - "+jobs.get(0).getJobTitle());
        else if(result.equals("student")){
        listJobs = dao.getListOfJobs(status, userID);
        }
        
        else if(result.equals("loginNotOK"))
        {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return result;

    }
    
    public String logout()
    {
        Dao dao = new Dao();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";

    }
    
    
    
}
