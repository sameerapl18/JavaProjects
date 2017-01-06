/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banksystem2014fall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.*;

/**
 *
 * @author LinJian
 */

//this is a class for BankAccount
public class BankAccount {
    //attributes
    private String ssn;
    private String accountNumber;
    private double balance;
    private String statement;
    
    //a static attribute to generate the account number
    private static int nextAccountNumber = 100000;
    
    //constructor
    public BankAccount(String n, String s, double b, String st)
    {
        //generate the account number
        accountNumber = n;
        ssn = s;
        if(b<0.0)
        {
            balance = 0;
        }
        else
        {
            balance = b;
        }
        
        statement = st;
        
        /*
        DecimalFormat df = new DecimalFormat("##.00");
        statement.add(DateAndTime.DateTime()+": Account opened with an "
                + "initial balance $" + df.format(balance)); */
    }
    
    //a method for deposit
    public void deposit(double da)
    {
        if(da > 0.0)
        {
            balance = balance + da;
            DecimalFormat df = new DecimalFormat("##.00");
            statement = statement + DateAndTime.DateTime() + ": Deposit " 
                    + df.format(da) + ". Balance: $" + df.format(balance) + "\n";
            
            //Access the database and update the related record
            
            
             
        }
    }
    
    public void withdraw(double wa)
    {
        if((balance - wa) > 0.0)
        {
            balance = balance - wa;
            DecimalFormat df = new DecimalFormat("##.00");
            statement = statement + DateAndTime.DateTime() + ": Withdraw " 
                    + df.format(wa) + ". Balance: $" + df.format(balance) + "\n";
            
            //Access the database and update the related record
            
            
            
             
        }
    }
    
    public void showStatement()
    {
        System.out.println();
        System.out.printf("Account(%s) Activities:\n", accountNumber);
        System.out.println(statement);
        System.out.println();
    }

    
    
    //get methods and set methods
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

  
     
    
    
}
