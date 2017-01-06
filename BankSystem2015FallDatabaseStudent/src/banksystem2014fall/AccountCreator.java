/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banksystem2014fall;

import java.util.Scanner;

//Import two new classes
import java.sql.*;
import java.text.*;

/**
 *
 * @author LinJian
 */
public class AccountCreator {
    
    //static method to create new bank account
    public static void createNewBankAccount()
    {
        //declare varaibles 
        String ssn, accountNum="";
        double balance;
        Scanner input = new Scanner(System.in);
        
        //prompt and input
        System.out.println("Please enter the SSN:");
        ssn = input.next();
        
        System.out.println("Please enter the initial balance:");
        balance = input.nextDouble();
        
        //Access the database and insert a record into the BankAccount Table
        Dao dao = new Dao();
        
        accountNum = dao.insertUser(ssn, balance);     
        
        
        
        //display msg
        System.out.println("***The new bank account is created!***");
        System.out.println("***The account number is " +  accountNum + "!***");
        System.out.println();
    }
    
}
