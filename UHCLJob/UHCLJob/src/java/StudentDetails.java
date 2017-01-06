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

/**
 *
 * @author sameerapl18
 */
@ManagedBean
@SessionScoped
public class StudentDetails implements Serializable{
    
    private int studentID;
    private String name;
    private String gender;
    private long contactNo;
    private String emailID;
    private String highestDegree;
    private String skills;
    private double experience;
    private String date;
    private String status;
    private StudentDetails student;
    private boolean showSelect;

    public boolean isShowSelect() {
        return showSelect;
    }

    public void setShowSelect(boolean showSelect) {
        this.showSelect = showSelect;
    }
    

    public StudentDetails getStudent() {
        return student;
    }

    public void setStudent(StudentDetails student) {
        this.student = student;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    private int jobID;
 private ArrayList<StudentDetails> students;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public ArrayList<StudentDetails> getStudents() {
        return students;
    }

    public void setStudentIDs(ArrayList<StudentDetails> students) {
        this.students = students;
    }
    
   
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    /**
     * Creates a new instance of StudentDetails
     */
    
     public String getStudentsWithJobID(){
        Dao dao = new Dao();
        System.out.println("hello");
        
       //  students = dao.getStudentsWithJobID(jobID);
         //System.out.println("students list - "+students.get(0).getName());
    return "jobapplicationsstudent";
    }

    
    public StudentDetails() {
    }
    public String getStudentDetails(String username){
    
        Dao dao = new Dao();
        int studentID = dao.getStudentID(username);
      System.out.println("student id - "+studentID);
        student = dao.getStudentDetails(studentID);
        if(!(student == null)){
        name = student.getName();
        gender = student.getGender();
        contactNo = student.getContactNo();
        emailID = student.getEmailID();
        highestDegree = student.getHighestDegree();
        skills = student.getSkills();
        experience = student.getExperience();
        }
       // System.out.println("inside studentdetails - "+student.getName());
        return "myResume";
    }
    
    public String updateResume(String username){
    Dao dao = new Dao();
        int studentID = dao.getStudentID(username);
        boolean userExist = dao.checkStudentExist(studentID);
        boolean success = false;
       
        StudentDetails studentObj = new StudentDetails();
        System.out.println("name - "+name);
        studentObj.setName(name);
        studentObj.setGender(gender);
        studentObj.setContactNo(contactNo);
        studentObj.setEmailID(emailID);
        studentObj.setHighestDegree(highestDegree);
        studentObj.setSkills(skills);
        studentObj.setExperience(experience);
        studentObj.setStudentID(studentID);
       // System.out.println("student name - "+student.getName());
        System.out.println("student name - "+studentObj.getName());
         success = dao.updateStudentDetails(studentObj,userExist);
        
        if(success){
        return "resumeUpdateSuccess";
        }
        else{
        return "error";
        }
    
    }
    
}
