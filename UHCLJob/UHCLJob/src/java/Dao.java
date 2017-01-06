/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PeyyalamittaL6642
 */
public class Dao {
   final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kurlas8625";
   final String USERNAME = "kurlas8625";
   final String PASSWORD = "1370964";
   
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   String error = "error";
   public Statement openConnection(){
       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            
        }
       try {
           //connect to database
           connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
           //open statement
           statement = connection.createStatement();
         
           
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return statement;
   
   }
   
   public void closeConnection(){
   
       try {
           resultSet.close();
            statement.close();
            connection.close();
       } catch (SQLException ex) {
           System.out.println("exception - "+ex);
       }
  
  
       
   
   }
   
   public String userRegistration(String userName,String userID,String type,String password){
   String result = null;
   statement = openConnection();
   String query = "insert into t_user(name, userid, password, type) values('"
           + userName
           +"', '"
           +userID+"', '"
           +password+"', '"
           +type        
           + "')";
    String checkUser = "select * from t_user where userId = '" + userID + "'";
    
    
       try {
           //System.out.println("statement - "+st);
        
           resultSet = statement.executeQuery(checkUser);
           if(resultSet.next())
           {
               return "registrationNotOK";
           }
           else
           {
               int i = statement.executeUpdate(query);
               return "registrationSuccess";
           }

           
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            return error;
       }
       
//       finally{
//       closeConnection();
//       }
   
   
  
   
   }

    String Login(String userID, String password) {
    String result = null;
   statement = openConnection();
   System.out.println("Userid in dao -"+userID);
     
     String checkUser = "select * from t_user where userId = '" + userID + "'";


        try {
            resultSet = statement.executeQuery(checkUser);
            if(resultSet.next())
            {
                if(resultSet.getString(4).equals(password))
                {
                    if(resultSet.getString(5).equals("HR"))
                    {
                                              
                        return "HR";
                    }
                    else
                    {
                        return "student";
                    }
                }
                else
                {
                    return "loginNotOK";
                }
            }
            else
            {
            return "loginNotOK";
            }


        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
             return error;
        }
        
//        finally{
//       closeConnection();
//       }
//   
   
    }

    ArrayList<JobsApplication> getJobApplications(String status) {
        System.out.println("inside dao");
        
        ArrayList<JobsApplication> jobList = new ArrayList<JobsApplication>();
       // jobList = null;
        
        statement = openConnection();
        
        
        String query = "select j.jobid, j.jobtitle, j.jobdescription, j.vacancies, j.jobstatus, j.updatedon, "
                + "(select count(*) from t_studentjobapplications where jobid=j.jobid) as total, "
                + "(select count(*) from t_studentjobapplications where jobstatus='open' and jobid=j.jobid) "
                + "as new from t_jobdetails as j  where j.jobStatus='"+status+"'";
       try {
           resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                System.out.println("inside if");
        resultSet.previous();
        while(resultSet.next()){
            System.out.println("inside while");
            JobsApplication jobs = new JobsApplication();
        jobs.setJobID(resultSet.getInt(1));
        jobs.setJobTitle(resultSet.getString(2));
        jobs.setJobDescription(resultSet.getString(3));
        jobs.setVacancies(resultSet.getInt(4));
        jobs.setJobStatus(resultSet.getString(5));
        jobs.setJobDate(resultSet.getString(6));
        jobs.setTotalApplicants(resultSet.getInt(7));
        jobs.setNewApplicants(resultSet.getInt(8));
        System.out.println("jobs - "+jobs.getJobTitle());
       
        
        
        jobList.add(jobs);
        }
        }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       
       // System.out.println("jobs in dao - "+jobList.get(0).getJobTitle());
        return jobList;
    }

    ArrayList<appliedJobs> getStudentsWithJobID(int jobID) {
       ArrayList<appliedJobs> result = new ArrayList<appliedJobs>();
       statement = openConnection();
       Statement st = openConnection();
       System.out.println("job - "+jobID);
        String query = "select j.jobid, j.studentid, j.updatedon, s.name,j.jobstatus, j.studentstatus from t_studentdetails as s, "
                + "t_studentjobapplications as j where j.jobid = "+jobID+" and s.studentuserid=j.studentid";
       
       try {
           resultSet = statement.executeQuery(query);
           System.out.println("no");
           while(resultSet.next()){
              
           appliedJobs appliedJobObject = new appliedJobs();
           appliedJobObject.setStudentName(resultSet.getString(4));
           appliedJobObject.setStudentStaus_HR(resultSet.getString(5));
           appliedJobObject.setStudentID(resultSet.getInt(2));
           appliedJobObject.setStudentStatus(resultSet.getString(6));
           
            result.add(appliedJobObject);
           }
          
           
           
           
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       
       return result;
    }

    StudentDetails getStudentApplication(int studentID, int jobID) {
        StudentDetails student = new StudentDetails();
      statement = openConnection();
      String query = "select s.name, s.gender, s.contactno, s.emailid, s.highestdegree, s.skills, s.experience,j.jobstatus, s.studentuserid  from "
              + "t_studentdetails as s, t_studentjobapplications as j where s.studentuserid = j.studentid and s.studentuserid="+studentID+" and j.jobid="+jobID;
      
      String query2 = "select s.jobid, vacancies, count(s.jobstatus),s.jobstatus from t_studentjobapplications s, "
              + "t_jobdetails j where s.jobid = j.jobid and j.jobid = "+jobID+" group by s.jobstatus, s.jobid, vacancies";
      
       try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next()){
      student.setName(resultSet.getString(1));
      student.setGender(resultSet.getString(2));
      student.setContactNo(resultSet.getLong(3));
      student.setEmailID(resultSet.getString(4));
      student.setHighestDegree(resultSet.getString(5));
      student.setSkills(resultSet.getString(6));
      student.setExperience(resultSet.getInt(7));
      student.setStatus(resultSet.getString(8));
      student.setStudentID(resultSet.getInt(9));
      
      }
            ResultSet resultSet1 = statement.executeQuery(query2);
            while(resultSet1.next())
            {
                if(resultSet1.getString(4).equals("Approved")){
                System.out.println(resultSet1.getInt(2));
                System.out.println(resultSet1.getInt(3));
                if(resultSet1.getInt(2) == resultSet1.getInt(3))
                {
                    student.setShowSelect(false);
                    break;
                }
                else
                {
                    student.setShowSelect(true);
                }
                }
                else{
                 student.setShowSelect(true);
                }
            }
            
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
     
       System.out.println("before return - "+student.isShowSelect());
       return student;
    }

    boolean updateApplicantStatus(int studentID, int jobID, String status) {
         System.out.println("inside method");
        boolean finalStatus = false;
       statement = openConnection();
       String query="update t_studentjobapplications set jobstatus='"+status+"' where jobid="+jobID+" and studentid="+studentID;
       
       try {
           int i = statement.executeUpdate(query);
           if(i>0){
               System.out.println("inside if");
           finalStatus = true;
           }
           
           
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       return finalStatus;
       
    }

    boolean NotifyStudents(int jobID) {
        System.out.println("inside dao - "+jobID);
        statement = openConnection();
        boolean finalStatus = false;
        String query = "update t_studentjobapplications set studentstatus='true' where jobid="+jobID;
        //String query2 = "update t_studentjobapplications set jobstatus='Rejected' where jobstatus ='open' and jobid="+jobID;
        
       try {
           int i=statement.executeUpdate(query);
           //int j=statement.executeUpdate(query2);
           if(i>0){
               System.out.println("inside if");
               finalStatus=true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       return finalStatus; 
    }

    appliedJobs getJobDetailedCount(int jobid) {
        appliedJobs apply = new appliedJobs();
        statement = openConnection();
        int total = 0;
        int selected = 0;
        int totalApplied = 0;
        String queryTotal = "select vacancies from t_jobdetails where jobid="+jobid;
        String querySelected = "select count(*) from t_studentjobapplications where jobstatus = 'Approved' and jobid="+jobid;
        String queryTotalApplied = "select count(*) from t_studentjobapplications where jobid="+jobid;
        
       try {
           resultSet = statement.executeQuery(queryTotal);
           while(resultSet.next()){
        total = resultSet.getInt(1);
       } 
       }catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
      
        
       try {
           resultSet = statement.executeQuery(querySelected);
           while(resultSet.next()){
        selected = resultSet.getInt(1);
       } 
       }catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           resultSet = statement.executeQuery(queryTotalApplied);
           while(resultSet.next()){
        totalApplied = resultSet.getInt(1);
       } 
       }catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
        apply.setTotalVacancies(total);
        apply.setTotalApplied(totalApplied);
        apply.setSelectedCount(selected);
        
        return apply;
    }
    
    //created by LAM
    ArrayList<ListOfJobsStudent> getListOfJobs(String status, String userName) {
        ArrayList<ListOfJobsStudent> listJobs = new ArrayList<ListOfJobsStudent>();
        String stuStatus = "";
        int studentID = 0;
        HashMap<Integer,String> jobInformation = new HashMap<Integer, String>();
       /// HashMap<String, String> studentInformation = new HashMap<String, String>();
        statement = openConnection();
        String jobInfo = ("Select jobId, jobTitle from t_jobdetails where jobStatus =  'open' ");
       try {
           resultSet = statement.executeQuery("select id from t_user where userid = '"+userName+"'");
           while(resultSet.next()){
           studentID = resultSet.getInt(1);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        try {
           resultSet = statement.executeQuery(jobInfo);
            while (resultSet.next()){
                jobInformation.put(resultSet.getInt(1),resultSet.getString(2));                
            }
            statement = openConnection();
            for (Integer key : jobInformation.keySet())
            {      
                ListOfJobsStudent studentJobList = new ListOfJobsStudent();
                String studentInfo = ("Select jobStatus, studentStatus from t_studentjobapplications where jobid = "+ key+" and studentid = "+studentID);
                try
                {
                    resultSet = statement.executeQuery(studentInfo);
                   // studentInformation.put(resultSet.getString(4), resultSet.getString(5));
                    if(resultSet.next()){
                       // resultSet.previous();
                    if (resultSet.getString(2).equals("true"))
                    {
                        stuStatus =resultSet.getString(1);
                    }
                    else{
                    stuStatus = "Under Review";
                    }
                    }
                    else{
                    
                    stuStatus = "open";
                    }
                    
                }
                catch (SQLException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                studentJobList.setJobID(key);
                studentJobList.setJobStatus(stuStatus);
                studentJobList.setJobTitle(jobInformation.get(key));
                listJobs.add(studentJobList);
            }
            
        }
        catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally{
//       closeConnection();
//       }
        return listJobs;
    }

    JobsApplication getJobDetails(int jobid) {
        JobsApplication jobsAppl = new JobsApplication();
        jobsAppl.setJobID(jobid);
        
        statement = openConnection();
        String query = "select jobtitle, vacancies, updatedon, jobdescription from t_jobdetails where jobid="+jobid;
       try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next()){
        jobsAppl.setJobTitle(resultSet.getString(1));
        jobsAppl.setVacancies(resultSet.getInt(2));
        jobsAppl.setJobDate(resultSet.getString(3));
        jobsAppl.setJobDescription(resultSet.getString(4));
        
        }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println(jobsAppl.getJobTitle());
       System.out.println(jobsAppl.getJobDescription());
       System.out.println(jobsAppl.getJobDate());
       System.out.println(jobsAppl.getVacancies());
        
        return jobsAppl;
    }

    int getStudentID(String userID) {
      int studID = 0;
      
      statement = openConnection();
      String query = "select id from t_user where userid='"+userID+"'";
       try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next()){
      studID = resultSet.getInt(1);
      }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
//     
      
      return studID;
    }

    boolean applyJob(int jobid, int studentID, String status) {
     boolean success = false;
     statement = openConnection();
     String query = "insert into t_studentjobapplications (studentID, jobid, jobstatus, appliedon) values "
             + "("+studentID +", "+jobid +", 'open', CURRENT_TIMESTAMP) ";
     int i;
       try {
           i = statement.executeUpdate(query);
           if(i>0){
     success = true;
     }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
     
     return success;
    }

    ArrayList<JobsApplication> getAllJobsHR() {
        ArrayList<JobsApplication> jobsList = new ArrayList<JobsApplication>();
     statement = openConnection();
     String query = "select jobtitle, jobstatus, jobid, vacancies, jobdescription from t_jobdetails";
       try {
           resultSet = statement.executeQuery(query);
           while(resultSet.next()){
               JobsApplication jobs = new JobsApplication();
           jobs.setJobTitle(resultSet.getString(1));
           jobs.setJobStatus(resultSet.getString(2));
           jobs.setJobID(resultSet.getInt(3));
           jobs.setVacancies(resultSet.getInt(4));
           jobs.setJobDescription(resultSet.getString(5));
           jobsList.add(jobs);
       } }catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
     
     return jobsList;
    }

    boolean SubmitModifyJob(HRPage hrPage) {
       boolean success = false;
       statement = openConnection();
       String query = "update t_jobdetails set jobtitle='"+hrPage.getJobTitle()+"', jobdescription='"
               +hrPage.getJobbDescription()+"', vacancies="+hrPage.getVacancies()+", jobstatus='"+hrPage.getJobStatus()
               +"' where jobid="+hrPage.getJobID();
       try {
           int i = statement.executeUpdate(query);
           if(i>0){
           success = true;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       return success;
    }

    StudentDetails getStudentDetails(int studentID) {
        StudentDetails student = new StudentDetails();
        statement = openConnection();
        
        String query="select name, gender, contactNo, emailID, highestdegree, skills, experience, updatedon from t_studentdetails where studentuserid="+studentID;
       try {
           resultSet = statement.executeQuery(query);
           if(resultSet.next()){
           resultSet.previous();
           while(resultSet.next()){
           student.setName(resultSet.getString(1));
           student.setGender(resultSet.getString(2));
           student.setContactNo(resultSet.getLong(3));
           student.setEmailID(resultSet.getString(4));
           student.setHighestDegree(resultSet.getString(5));
           student.setSkills(resultSet.getString(6));
           student.setExperience(resultSet.getInt(7));
           student.setDate(resultSet.getString(8));
           
           }
           
           }
           else{
           student = null;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
        
        
        return student;
    }

    boolean checkStudentExist(int studentID) {
        boolean userExist = false;
        statement = openConnection();
        String query = "select * from t_studentdetails where studentuserid="+studentID;
       try {
           resultSet = statement.executeQuery(query);
           if(resultSet.next()){
        userExist = true;
        }
          
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
        
        return userExist;
    }

    boolean updateStudentDetails(StudentDetails studentObj, boolean userExist) {
       boolean status = false;
       statement = openConnection();
       String query1 = "insert into t_studentdetails (studentuserid, name, gender, contactNo, emailid, highestdegree, skills, experience) values("
               +studentObj.getStudentID()
               + ", '"+studentObj.getName()+"','"
               + studentObj.getGender()+"',"
               +studentObj.getContactNo()+",'"
               +studentObj.getEmailID()+"', '"
               +studentObj.getHighestDegree()+"', '"
               +studentObj.getSkills()+"', "
               +studentObj.getExperience()+               
               ")";
       
       String query2 = "update t_studentdetails set name='"+studentObj.getName() +
               "',gender = '"+ studentObj.getGender()+"',contactNo = "
               +studentObj.getContactNo()+", emailid = '"
               +studentObj.getEmailID()+"',highestdegree = '"
               +studentObj.getHighestDegree()+"',skills = '"
               +studentObj.getSkills()+"',experience = "
               +studentObj.getExperience()+ " where studentuserid = "+studentObj.getStudentID();
       try {
           int i=0;
       if(userExist){
           
                i = statement.executeUpdate(query2);
           
       }
       else{
        i = statement.executeUpdate(query1);
       }
       if(i>0){
       status = true;
       }
       
       } catch (SQLException ex) {
               Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
           }
//       finally{
//       closeConnection();
//       }
       return status;
    }

    boolean addNewJob(String jobTitle, String jobDescription, int vacancies, String jobStatus) {
        boolean success = false;
        statement = openConnection();
        String query = "insert into t_jobdetails (jobtitle, jobdescription, vacancies, jobstatus, createdon) values('"
                +jobTitle+"','"
                +jobDescription+"', "
                +vacancies+", '"
                +jobStatus+"', CURRENT_TIMESTAMP"
                + ")";
        
       try {
           int i = statement.executeUpdate(query);
           if(i>0){
           success = true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
        
        return success;
    }

    ArrayList<JobsApplication> getMyAplliedJobsStud(int studentID) {
        ArrayList<JobsApplication> myJobs = new ArrayList<JobsApplication>();
        statement = openConnection();
        String query = "select s.jobstatus as studJobStatus, j.jobstatus, s.studentstatus, j.jobtitle from t_studentjobapplications s, t_jobdetails j "
                + "where j.jobid = s.jobid and s.studentid = " + studentID;
        
        
        
       try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                JobsApplication job = new JobsApplication();
                
                String studJobStatus = resultSet.getString(1);
                String jobStatus = resultSet.getString(2);
                String studStatus = resultSet.getString(3);
                String jobTitle = resultSet.getString(4);
                
                job.setJobTitle(jobTitle);
                
        
                if(jobStatus.equals("close")){
                if(studStatus.equals("true")){
                
                if(studJobStatus.equals("Approved") || studJobStatus.equals("Rejected")){
                job.setJobStatus(studJobStatus);
                
                }
                else{
                job.setJobStatus("Closed");
                }
                             
                    
                }
                else{
                
                job.setJobStatus("Closed");
                
                }
                               
                }
                else{
                if(studStatus.equals("true")){
                
                if(studJobStatus.equals("Approved") || studJobStatus.equals("Rejected")){
                job.setJobStatus(studJobStatus);
                
                }
                else{
                job.setJobStatus("Rejected");
                }
                             
                    
                }
                else{
                
                job.setJobStatus("Under Review");
                
                }
                           
                
                }
        myJobs.add(job);
        }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
        
       
        
        return myJobs;
    }

    boolean getStudentJobStatus(int jobID) {
        statement = openConnection();
        boolean showPostResult = false;
        String query = "select studentStatus from t_studentjobapplications where jobID = "+jobID;
        
       try {
           resultSet = statement.executeQuery(query);
           while(resultSet.next()){
           if(resultSet.getString(1).equals("false")){
           showPostResult = true;
           }
           
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       
       return showPostResult;
        
    }

    boolean withdrawJob(int jobid, int studentID) {
        statement = openConnection();
        boolean status = false;
        String query = "delete from t_studentjobapplications where studentid="+studentID+" and jobid="+jobid;
        
        int i;
       try {
           i = statement.executeUpdate(query);
            if(i>0){
        status = true;
        }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       
        
        return status;
        
    }

    String getJobTitle(int jobid) {
       String jobTitle = "";
       statement = openConnection();
       
       String query = "select jobtitle from t_jobdetails where jobid="+jobid;
       
       try {
           resultSet = statement.executeQuery(query);
           while(resultSet.next()){
           jobTitle = resultSet.getString(1);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
       }
//       finally{
//       closeConnection();
//       }
       
       
       return jobTitle;
    }
}
   

    

   
   
   


