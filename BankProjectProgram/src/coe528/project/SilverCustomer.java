package coe528.project;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sukhjot Saggu
 */
public class SilverCustomer extends Customer{
        
    public SilverCustomer(String username, String password) throws IOException{
        super(username, password, 'c');
    }
    
    
    public SilverCustomer(String username, String password, double balance) throws IOException{
        super(username, password, balance);
        
        FileWriter file = new FileWriter(username + ".txt");
        file.write(password + " " + balance + " " + this.getRole());
        file.flush();
        file.close();
        
        System.out.println("Hey I Made The File Silver class");
    }
    @Override
    public String purchase(double amount)throws Exception{
        double total;
            total = amount + 20;
        withdraw (total);
        System.out.println("Purchase Total: " + total);
        return String.valueOf(total);
    }
    
    public String getState(){
        return "Silver";
    }
    
    @Override
    public String toString() {
        return "Customer " + super.toString();
    }
}
