
package integratedca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Student
 */
public class IntegratedCA {

    
    public static void main(String[] args) {
        
       int userOption=0;
       User userMenu = new User(); // CALLING UserMethod from Class User where all methods are created
       userOption=userMenu.userMenu(); // will display a menu for user to choose what to operate
        
        DBconnection testing =new DBconnection();
        testing.DBsetUp(userMenu);
       
         
       
        
        
        
     
            }
    
    
}
