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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author LinJian
 */
public class OnlineAccount {
    
    //attributes
    private String ssn;
    private String id;
    private String psw;
    
    //we are coming back
    //a set of bank accounts
    private ArrayList<BankAccount> managedAccounts;
    
    //constructor
    public OnlineAccount(String s, String i, String p)
    {
        ssn = s;
        id = i;
        psw = p;
        
        managedAccounts = new ArrayList<BankAccount>();
        
        //access the database and link the bank accounts with this online 
        //account
        
        
        
    }
    
    //display the welcome msg and options 
    public void welcome()
    {
        //varaibles
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //welcome msg
            System.out.println("\n***Welcome to your online account***");
            //options
            System.out.println("Please select your options");
            //first of all, display all bank accounts numbers
            for(int i=0; i<managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s to view\n", i+1, 
                        managedAccounts.get(i).getAccountNumber());
            }
            //other options
            System.out.println("s: Reset your password");
            System.out.println("t: Account Transfer");
            System.out.println("x: sign out\n");
            
            //after display the menu, we ask the user to input selection
            selection = input.next();
            
            if(selection.equals("s"))
            {
                //reset password
            }
            else if(selection.equals("t"))
            {
                //account transfer
                transfer();
            }
            else if(isInteger(selection))
            {
                //we convert the selection into an integer
                int intSelection = Integer.parseInt(selection);
                //make sure the selection is in a good range
                if(intSelection >= 1 && intSelection<=managedAccounts.size())
                {
                    //now view the statement
                    managedAccounts.get(intSelection-1).showStatement();
                }
            }
        }
    }
    
    //do account transfer
    public void transfer()
    {
        //varaibles
        Scanner input = new Scanner(System.in);
        String accountFrom, accountTo;
        double transferAmount;
        
        //make sure it has more than two bank accounts
        if(managedAccounts.size()>= 2)
        {
            //good to go
            //list the bank accounts
            for(int i=0; i<managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s\n", i+1, 
                        managedAccounts.get(i).getAccountNumber());
            }
            
            //ask the user to enter accountFrom & accountTo
            System.out.println("Please select the account from");
            accountFrom = input.next();
            
            System.out.println("Please select account to");
            accountTo = input.next();
            
            System.out.println("Please indicate the amount of transfer");
            transferAmount = input.nextDouble();
            
            //validate the input
            if(isInteger(accountFrom) && isInteger(accountTo))
            {
                //good to go
                int intFrom = Integer.parseInt(accountFrom);
                int intTo = Integer.parseInt(accountTo);
                
                //in a good range?
                if(intFrom >=1 && intFrom <= managedAccounts.size()
                        && intTo>=1 && intTo <= managedAccounts.size()
                        && intFrom != intTo)
                {
                    //good to go to check balance
                    if(managedAccounts.get(intFrom-1).getBalance() >= transferAmount)
                    {
                        //good to go to do the transfer
                        managedAccounts.get(intFrom-1).withdraw(transferAmount);
                        managedAccounts.get(intTo-1).deposit(transferAmount);
                        System.out.println("The transfer is successful!\n");
                    }
                    else
                    {
                        System.out.println("You do not have enough money to do the "
                                + "transfer");
                    }
                }
                else
                {
                    System.out.println("Please use correct input");
                }
            }
            else
            {
                System.out.println("Please use the correct input\n");
            }
        }
        else
        {
            System.out.println("You must have two bank accounts to do the transfer\n");
        }
        
    }
    
   
    public void resetPassword()
    {
        //varaibles
        Scanner input = new Scanner(System.in);
        String old, new1, new2;
        
        System.out.println("Please enter your current password");
        old = input.next();
        
        System.out.println("Please enter your new password");
        new1 = input.next();
        
        System.out.println("Please confirm your new password");
        new2 = input.next();
        
        //check the current password
        if(old.equals(psw))
        {
            //good to go to check new psw
            if(new1.equals(new2))
            {
                //good to go to change it
                psw = new1;
            }
            else
            {
                System.out.println("The new passwords did not match!\n");
            }
        }
        else
        {
            System.out.println("Your password is not correct!\n");
        }
        
    }
    
    //get methods and set methods
    public String getSsn() {
        return ssn;
    }

     

    public String getId() {
        return id;
    }

    

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    //to determine whether a is an integer
    private boolean isInteger(String a)
    {
        try
        {
            //if a is not an integer, an exception will be thrown out
            int i = Integer.parseInt(a);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
}
