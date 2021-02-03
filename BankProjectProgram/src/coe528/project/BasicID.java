package coe528.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Sukhjot Saggu
 */

public class BasicID {
    private String username, password; //private instance variables
    private char role; //private role
    
    public BasicID(String username, String password, char role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public char getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(char role) {
        this.role = role;
    }
    
    public boolean valid(String username, String password){
        if(username == this.getUsername()&&password == this.getPassword()){
            return true;
        }
        else{
            return false;
        }    
    }
    
    public boolean validFile(String username, String password) throws Exception{
    String data = "", line; 

    BufferedReader in = new BufferedReader(new FileReader(username + ".txt"));

    while((line = in.readLine()) != null){
        data = data + line;
    }
    in.close();

    System.out.println("Text in file:" + data);
    System.out.println(password);
        if((username.equals(this.getUsername())) && (password.equals(data))){
            return true;
        }    
        else{
            return false;
        }      
    }
    
    public boolean validCustomer(String username, String password) throws Exception{
        
    String data = "", line, pass, type; 

    BufferedReader in = new BufferedReader(new FileReader(username + ".txt"));

    while((line = in.readLine()) != null){
        data = data + line;
    }
    in.close();
    
    StringTokenizer text = new StringTokenizer(data);
    
    pass = text.nextToken();
    type = text.nextToken();
    type = text.nextToken();

        if((username.equals(this.getUsername())) && (password.equals(pass)) && (type.equals("c"))){
            return true;
        }    
        else{
            return false;
        }      
    }
    
    public String[] customerInfo(String username) throws Exception{
        
        String data = "", line;
        String[] info = new String[3];

        BufferedReader in = new BufferedReader(new FileReader(username + ".txt"));

        while((line = in.readLine()) != null){
            data = data + line;
        }
        in.close();

        StringTokenizer text = new StringTokenizer(data);
        int i = 0;
        while (text.hasMoreElements()) {
            info[i] = text.nextToken();
            i++;
        }

        for(int j=0;j<3;j++){
            System.out.println(info[j]);
        }
        return info;
    }

    @Override
    public String toString() {
        return "BasicID{" + "username=" + this.username + ", password=" + this.password + ", role=" + this.role + '}';
    }   
}
