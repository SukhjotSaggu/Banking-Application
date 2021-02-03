package coe528.project;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sukhjot Saggu
 */

//This class is Mutable
public abstract class Customer extends BasicID{
    
    private static int gold = 10000, platinum = 20000;//private instance variables
    protected double userBalance;//protected instance variable
    Customer c;//Customer Object
    
    //Constructor for customer object that takes in username and password
    //EFFECTS: Creates Customer object and sends username and password to super() class
    public Customer(String username, String password) throws IOException{
        super(username, password, 'c');
    }
    
    //Constructor for customer object that takes in username, password, and balance
    //REQUIRES: balance >= 0
    //EFFECTS: Creates Customer object and sends username and password to super() class
    public Customer(String username, String password, double balance) throws IOException{
        super(username, password, 'c');
        if(balance < 0){
            System.out.println("Must Deposit More That $0 No Neagitve Amount");
            balance = 0;
        }
        System.out.println("The balance is " + balance);
        FileWriter file = new FileWriter(username + ".txt");
        file.write(password + " " + balance + " " + this.getRole());
        file.flush();
        file.close();
        
        System.out.println("Hey I Made The File Balance " + balance);
        this.userBalance = balance;
    }
    
    //EFFECTS: Returns the customer object's balance
    public double getUserBalance() {
        return userBalance;
    }
    
    //REQUIRES: Amount > 0
    //EFFECTS: Adds 'amount' into customer object's balance
    public void deposit(double amount)throws Exception{
        if(amount <= 0){
            throw new IllegalArgumentException("Must Deposit More That $0 No Neagitve Amount");
        }
        this.userBalance = this.userBalance + amount;
        
        System.out.println(balanceToString());
    }
    
    //EFFECTS: Return's customer object's balance as a string
    public String balanceToString(){
        return "Account Balance : $" + this.userBalance;
    }
    
    //REQUIRES: Amount > 0
    //EFFECTS: Subtracts 'amount' from customer object's balance
    public void withdraw(double amount)throws Exception{
        if(amount <= 0){
            throw new IllegalArgumentException("Must Withdraw More That $0 No Neagitve Amount");
        }
        if(amount > this.userBalance){
            throw new IllegalArgumentException("Not enough money in account");
        }
        else{
        this.userBalance = this.userBalance - amount;
        }
        System.out.println(balanceToString());
    }
    
    //REQUIRES: Amount >= 0
    //EFFECTS: Subtracts 'amount' from customer object's balance plus additional cost based on the
    //customer object's level eg, silver, gold, platinum based on the balance
    public abstract String purchase(double amount)throws Exception;
    
    //EFFECTS: Sets the customer object's level eg, silver, gold, platinum based on the balance
    public Customer setLevel() throws IOException{

        double total = this.getUserBalance();
        if(total >= platinum){
            c = new PlatinumCustomer(this.getUsername(),this.getPassword(), this.getUserBalance());

            System.out.println("State changed to P");
            return c;
        }
        else if(total >= gold){
            c = new GoldCustomer(this.getUsername(),this.getPassword(), this.getUserBalance());

            System.out.println("State changed to G");
            return c;
        }
        else{
            c = new SilverCustomer(this.getUsername(),this.getPassword(), this.getUserBalance());
            System.out.println("State changed to S");
            return c;
        }
    }
    
    //EFFECTS: Updates the customer object's balance information in the txt file
    public void updateInfo() throws IOException{        
        FileWriter file = new FileWriter(this.getUsername() + ".txt");
        file.write(this.getPassword() + " " + this.getUserBalance() + " " + this.getRole());
        file.flush();
        file.close();
        
        System.out.println("File Updated");
    }
    
    //EFFECTS: Returns 'true' if the balance is >= 0 'rep invariant', else return's false
    public boolean repOK(){
        if(this.getUserBalance() >= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    //AF(c) = a customer C, where u = username of customer and p = password of customer
    //EFFECTS: Uses the Abstraction Function and returns the customer object as a string 
    @Override
    public String toString() {
        return "Customer " + super.toString();
    }
}
