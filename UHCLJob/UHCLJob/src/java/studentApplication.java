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
public class studentApplication implements Serializable{
    
    private StudentDetails student;
    private int jobId;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public StudentDetails getStudent() {
        return student;
    }

    public void setStudent(StudentDetails student) {
        this.student = student;
    }
    
    /**
     * Creates a new instance of studentApplication
     */
    public studentApplication() {
    }
    
    public String getStudentApplication(int studentID, int jobID){
        System.out.println("inside");
    Dao dao = new Dao();
    student = dao.getStudentApplication(studentID, jobID);
       jobId = jobID; 
       System.out.println(student.isShowSelect());
    return "studentProfileHR";
    }
   
    
    
}
