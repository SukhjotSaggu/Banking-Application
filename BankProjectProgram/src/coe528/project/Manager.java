package coe528.project;

import java.io.IOException;
/**
 * @author Sukhjot Saggu
 */
public class Manager extends BasicID{
    
    public Manager (String username, String password) throws IOException{
        super(username, password,'m');
    }

    @Override
    public String toString() {
        return "Manager " + super.toString();
    }
}
