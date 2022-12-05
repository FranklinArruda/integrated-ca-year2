/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integratedca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 *blank constructor so you can call this class for login purposes
 * @author Student
 */
public class User {
    
    private String userFirstName;   // attributes
    private String userLastName;
    private String userName;
    private String userPassword;
    private int id;
   
    /**
     * blank constructor so you it can call this class for login purposes
     */
    public User(){  // Default constructor 
        this.id=0;
        this.id++;  // Auto_INCREMENT
        this.id=id;
        this.userFirstName="";
        this.userLastName="";
        this.userName="";
        this.userPassword="";
    };

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
        return userPassword;
    }

    public void setPassWord(String passWord) {
        this.userPassword = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {    // only get id as it is auto increment
        return id;          
    }
    
    
        /**
         * @return 
         */
        public  String userSignUp (){  
         
        User Register = new User(); // Creating an user Object to get user input and set it accordingly
        BufferedReader kb =new BufferedReader (new InputStreamReader(System.in));
        
               // setting attributes to variables to register
               String userFName=this.userFirstName;
               String userLName=this.userLastName;
               String userName=this.userName;
               String passwod=this.userPassword;

            try{ 
                 // Setting First Name
              System.out.print("Enter your First Name: ");
              userFName=kb.readLine();
              Register.setUserFirstName(userFName);
              
                 // Setting Last Name
              System.out.print("Enter your Last Name: ");
              userLName=kb.readLine();
              Register.setUserFirstName(userLName);
              
                 // Setting Username
              System.out.print("Enter your Username: ");
              userName=kb.readLine();
              Register.setUserFirstName(userName);
              
                // Setting password
              System.out.print("Enter your password: ");
              passwod=kb.readLine();
              Register.setUserFirstName(passwod); 
              
            }catch(Exception e){
                System.out.println("something went wrong...");
            }
        return null;
    }
    
    
              
        /**
         * @return the user option 1 to login or 2 to sign-up
         * Loop will continue until user enter either 1 or 2
         * If user type in String will throw an erro message and loop 
         * will display the message until user chooses integer
         */
        public int userMenu (){
            
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            int userChoice=0;
                System.out.println("Hi there...");
                System.out.println("What would like to do?" + "\n");

            do{
                System.out.println("Enter 1 to login: ");
                System.out.println("Enter 2 to sign-up: ");
            try{
               userChoice=Integer.parseInt(kb.readLine());// converting int to string
            }catch(Exception e ){
                System.out.println("Please Only Numbers Allowed.." + "\n");
                System.out.println("If already user Please choose 1 to login.");
                System.out.println("If not user yet. Please choose 2 to sign-up" + "\n");
        }
    } while((userChoice!=1 && userChoice!=2));  
        
            
            
            /**
             * switch statment tovalidate the users choise in either registering or signin-up
             * CALLING LOGGIN Method from DBconnection Class
             * CALLING SIGN-UP Method from User Class
             * @reutns the user option to continue the app fucntions
             */
            switch(userChoice){                
            case 1: 
                DBconnection login =new DBconnection();
                System.out.println("Login");
                login.Login(); 
                break;
                
            case 2:  
                User signUp = new User();
                signUp.userSignUp();
                System.out.println(signUp.toString());
                break;
            default:
        }
            return userChoice; 
    }
}
       

