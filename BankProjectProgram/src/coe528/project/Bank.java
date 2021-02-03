package coe528.project;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sukhjot Saggu
 */
public class Bank{
    
    private static ArrayList<Manager> managers = new ArrayList<Manager>();
    private static ArrayList<Customer> customers = new ArrayList<Customer>();

    public Bank(Manager manage){
        managers.add(manage);
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    public void addCustomer(Manager manager, Customer customer)throws Exception{
        if(manager.getClass() != Manager.class){
             throw new Exception("This person is not authorized");
        }
        if(customer == null){
             throw new Exception("Customer does not exsit");
        }
        if(customers.contains(customer)){
             throw new Exception("This customer already exists");
        }
        else{
            customers.add(customer);
        }        
    }
    
    public void removeCustomer(Manager manager, Customer customer)throws Exception{
        if(manager.getClass() != Manager.class){
             throw new Exception("This person is not authorized");
        }
        if(customer == null){
             throw new Exception("Customer does not exsit");
        }
        else{
            for(int i = 0; i < customers.size(); i++){
                if(customers.get(i) == customer){
                    customers.remove(i);
                }
            }
        }
    }

    public Customer customerLogin(String name){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getUsername() == name){
                return(customers.get(i));
            }
        }
        return null;
    }
    
    public void dopositMoney(Customer customer,double amount)throws Exception{
        if(customer == null){
             throw new Exception("Customer does not exsit");
        }
        else{
            for(int i = 0; i < customers.size(); i++){
                if(customers.get(i) == customer){
                    if(customers.equals(customer)){
                        customer.deposit(amount);
                    }
                }
            }
        }
    }
}//Class