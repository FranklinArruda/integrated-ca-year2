/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package integratedca;

import static integratedca.User.regularUserMenu;

/**
 *
 * @author Student
 */
public class IntegratedCA {

    
    public static void main(String[] args) {
      
        //https://www.javaguides.net/2020/03/java-scanner-tutorial-reading-login-and-registration-user-input.html
        //https://stackoverflow.com/questions/53185191/creating-a-sign-up-and-log-in-page-using-java
        
      // static method inside the user class...
       regularUserMenu ();
        //System.out.println(regularUserMenu());
        
        UserMenu ne =new UserMenu();
        ne.regularUserMenu();
        // DBconnection testing =new DBconnection();
         //System.out.println(testing.connection(user1));
           
       //   System.out.println(User.toString());
        
        
    }

    
    
}
