package coe528.project;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sukhjot Saggu
 */
public class PlatinumCustomer extends Customer{
            
    public PlatinumCustomer(String username, String password) throws IOException{
        super(username, password, 'c');
    }
    
    
    public PlatinumCustomer(String username, String password, double balance) throws IOException{
        super(username, password, balance);
        
        FileWriter file = new FileWriter(username + ".txt");
        file.write(password + " " + balance + " " + this.getRole());
        file.flush();
        file.close();
        
        System.out.println("Hey I Made The File2");
    }
    @Override
    public String purchase(double amount)throws Exception{
        withdraw (amount);
        System.out.println("Purchase Total: " + amount);
        return String.valueOf(amount);
    }
    
    public String getState(){
        return "Platinum";
    }
    
    @Override
    public String toString() {
        return "Customer " + super.toString();
    }
}
