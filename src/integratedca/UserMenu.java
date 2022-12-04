/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integratedca;

/**
 *
 * @author Student
 */
public class UserMenu {
    
    public  String regularUserMenu (){
     
      String userFN="";
        
        User getUserFN = new User();
        
        userFN=getUserFN.getUserFirstName("Enter your First NAME");
        
        System.out.println(userFN);
     return userFN;
     }
     
    
}
