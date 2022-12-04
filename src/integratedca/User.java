/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integratedca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Student
 */
public class User {
    
    private String userFirstName;   // attributes
    private String userLastName;
    private String userName;
    private String password;
    private int id=0;
   
    // 
    
    public User(String userFirstName, int id, String userLastName, String password){
        this.id++;
        this.id=id;
        this.password=password;
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
    }
   public User() {
   }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

      /*@Override
    public String toString() {
        return "Register [firstName=" + userFirstName + ", lastName=" + userLastName + ", userName=" + userName + ", password=" +
            password + "]";
    */
    
    
 
    
    public  String getUserFirstName (String prompt){  // will get user input
         String userInput=this.userFirstName;
         BufferedReader kb =new BufferedReader (new InputStreamReader(System.in));
            try{ 
             System.out.println(prompt);
             userInput=kb.readLine();
            }catch(Exception e){System.out.println("something went wrong...");}       
    return userInput;
        
}
    
     public  String getUserLastName (String prompt){  // will get user input
        
         String userLastName=this.userLastName;
         BufferedReader kb =new BufferedReader (new InputStreamReader(System.in));
            try{ 
             System.out.println(prompt);
             userLastName=kb.readLine();
            }catch(Exception e){System.out.println("something went wrong...");}
    return userLastName;   
}
     
     
     
 
     public  String getUsername (String prompt){  // will get user input
        
         String userNm=this.userName;
         BufferedReader kb =new BufferedReader (new InputStreamReader(System.in));
            try{ 
             System.out.println(prompt);
             userLastName=kb.readLine();
            }catch(Exception e){System.out.println("something went wrong...");}
    return userNm;   
}
 
     
     
     
     public  String getUserPassword (String prompt){  // will get user input
        
         String userPass=this.password;
         BufferedReader kb =new BufferedReader (new InputStreamReader(System.in));
            try{ 
             System.out.println(prompt);
             userLastName=kb.readLine();
            }catch(Exception e){System.out.println("something went wrong...");}
    return userPass;   
}
 
      /**
       * 
       *   REGULAR USER MENU.... Creating a User object inside its on class
       * 
       * @return 
       */  
     
     public static String regularUserMenu (){
     
      String userFN="";
        
        User getUserFN = new User();
        
        userFN=getUserFN.getUserFirstName("Enter your First NAME");
        
        System.out.println(userFN);
     return userFN;
     }
     
}

