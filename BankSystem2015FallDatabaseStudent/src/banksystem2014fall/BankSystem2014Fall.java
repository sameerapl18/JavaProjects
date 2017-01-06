/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem2014fall;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author LinJian
 */
public class BankSystem2014Fall {

    /**
     * @param args the command line arguments
     */
    
    //declare two arrayList varaibles for all bankAccount and onlineAccounts
    
    public static ArrayList<BankAccount> allBankAccounts 
            = new ArrayList<BankAccount>();
    public static ArrayList<OnlineAccount> allOnlineAccounts
            = new ArrayList<OnlineAccount>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //declare varaibles
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //display the menu
            System.out.println("Please make your selection");
            System.out.println("1: Open a new bank account");
            System.out.println("2: Go to online system");
            System.out.println("x: Finish the simulation");
            
            //get the selection from the user
            selection = input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                //open a new account
                AccountCreator.createNewBankAccount();
            }
            else if(selection.equals("2"))
            {
                //go to the online system
                OnlineSystem aNew = new OnlineSystem();
                aNew.showMainPage();
            }
        }
    }
}
