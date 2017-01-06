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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PeyyalamittaL6642
 */
public class Dao {
   final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/drlin";
   final String USERNAME = "DrLin";
   final String PASSWORD = "UHCL2014";
   
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   public Statement openConnection(){
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
   
   public String insertUser(String ssn, double initbalance){
       Statement statement = openConnection();
       String accNum = null;
         //create statement String
           DecimalFormat df = new DecimalFormat("##.00");
           String s = DateAndTime.DateTime()+": Account opened with an initial balance $"+df.format(initbalance)+"\n";
       try {
           resultSet = statement.executeQuery("select * from nextaccountnumber");
            int nextNum = 0;
            
            while(resultSet.next()){
                accNum = ""+resultSet.getInt(1);
       nextNum = resultSet.getInt(1)+1;       
       
       }
            int t = statement.executeUpdate("update nextAccountNumber set nextID = '"+nextNum+"'");
           int rows = statement.executeUpdate("INSERT INTO bankaccount (AccountNumber, SSN, Balance, Statement) VALUES ('"+accNum+"', '"+ssn+"', "+initbalance+", '"+s+"')");
           
       } catch (SQLException ex) {
           System.out.println("Account creation failed "+ex);
           return ex.toString();
       }
       finally{
       //close the database
           closeConnection();
       }
      
           
           
      return accNum; 
   }
   
   public String onlineRegister(String ssn, String accountID,String password){
   Statement statement = openConnection();
   String message = null;
       try {
           resultSet = statement.executeQuery("select * from onlineaccount where ssn='"+ssn+"' or id='"+accountID+"'");
           if(resultSet.next()){
           message = "Already an account is present with either ssn or id";
           }
           else{
               int rows = statement.executeUpdate("insert into onlineaccount values ('"+accountID+"','"+ssn+"','"+password+"')");
               message = "Successfully created the account";
           }
           
       } catch (SQLException ex) {
          System.out.println("exception occured - "+ ex);
       }
        finally{
       //close the database
           closeConnection();
       }
       
       return message;
   }
   
   public OnlineAccount onlineLogin(String id,String password){
       OnlineAccount theLoginAccount = null;
   
   Statement statement = openConnection();
   try {
           resultSet = statement.executeQuery("select * from onlineaccount where id='"+id+"'");
           if(resultSet.next()){
               closeConnection();
               statement = openConnection();
               resultSet = statement.executeQuery("select * from onlineaccount where id='"+id+"' and password = '"+password+"'");
               if(resultSet.next()){
                   //id is found
              
               System.out.println("Login Successful");
               theLoginAccount = new OnlineAccount(resultSet.getString(2), id, password);
               
               }
               else{
                   //password incorrect
                   
                   System.out.println("Password Incorrect");
               }
           
           }
           else{
               //id is not found
               System.out.println("User ID not available");
               
           }
           
       } catch (SQLException ex) {
          System.out.println("exception occured - "+ ex);
       }
        finally{
       //close the database
           closeConnection();
       }
       
   return theLoginAccount;
   
   }
}
