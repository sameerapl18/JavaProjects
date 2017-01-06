/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sameerapl18
 */
@ManagedBean
@RequestScoped
public class UserRegistration {
    
    public String userName;
    public String userID;
    public String type;
    public String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of UserRegistration
     */
    public UserRegistration() {
        
       
    }
    
    public String register(){
        
//        try
//        {
//            Class.forName("com.mysql.jdbc.Driver");
//            
//        }
//        catch (Exception e)
//        {
//            return ("Internal Error! Please try again later.");
//        }
        Dao dao = new Dao();
        String result = dao.userRegistration(userName,userID,type,password);
        
        return result;
    
    }
    
}
