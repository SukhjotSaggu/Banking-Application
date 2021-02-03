package coe528.project;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sukhjot Saggu
 */
public class GoldCustomer extends Customer{
            
    public GoldCustomer(String username, String password) throws IOException{
        super(username, password, 'c');
    }
    
    
    public GoldCustomer(String username, String password, double balance) throws IOException{
        super(username, password, balance);
        
        FileWriter file = new FileWriter(username + ".txt");
        file.write(password + " " + balance + " " + this.getRole());
        file.flush();
        file.close();
        
        System.out.println("Hey I Made The File2");
    }
    @Override
    public String purchase(double amount)throws Exception{
        double total;
            total = amount + 10;
        withdraw (total);
        System.out.println("Purchase Total: " + total);
        return String.valueOf(total);
    }
    
    public String getState(){
        return "Gold";
    }
    
    @Override
    public String toString() {
        return "Customer " + super.toString();
    }
}
