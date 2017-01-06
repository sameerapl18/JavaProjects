/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banksystem2014fall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author LinJian
 */
public class OnlineSystem {
    
    //attribute
    private OnlineAccount theLoginAccount;
    
    //constructor
    public OnlineSystem()
    {
        //at the biginning, there is no login account
        theLoginAccount = null;
    }
    
    public void showMainPage()
    {
        //declare varaibles
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //display the menu
            System.out.println("Welcome to UHCL bank");
            System.out.println("Please make your selection");
            System.out.println("1: Create a new online ID");
            System.out.println("2: Login your online account");
            System.out.println("x: Finish the simulation");
            
            //get the selection from the user
            selection = input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                //register
                register();
                 
            }
            else if(selection.equals("2"))
            {
                //login
                login();
            }
        }
    }
    
    //register method
    public void register()
    {
        //declare varaibles
        String ssn, accountID, password;
        Scanner input = new Scanner(System.in);
        
        //prompts and input
        System.out.println("Please enter your ssn");
        ssn = input.next();
        
        System.out.println("Please enter your new ID");
        accountID = input.next();
        
        System.out.println("Please enter your password");
        password = input.next();
        
        //Access the database and insert a record into OnlineAccount
        Dao dao= new Dao();
        String message = dao.onlineRegister(ssn, accountID,password);
        
        System.out.println(message);
        
    }
    
    public void login()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String id="";
        String password="";
        boolean idFound = false;
        
        //get the login info.
        System.out.println("Please enter your ID");
        id = input.next();
        System.out.println("Please enter your password");
        password = input.next();
        
        //access the database and then login
        Dao dao= new Dao();
         theLoginAccount = dao.onlineLogin(id,password);
       theLoginAccount.welcome();
        
        
        
    }
    
}
