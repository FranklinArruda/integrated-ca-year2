package integratedca;
import static integratedca.DBconnection.DBsetUp;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Student Name: Franklin Arruda Cirino
 *  Student Numb: 2021368
 *  GitHub Link: .........................................
 *  Here you will find commits I've done along the way in building this project.
 */
public class IntegratedCA {

    DBconnection db = new DBconnection();
    
  /*
  public login (){
      ask user name
      ask user pass
      read user pass records from users table in DB
      if ok; = line exists
      create inistance of user class
  }
  //STEP 3: 
  */
  // INTERFACE IS WHEN SEVERAL CLASSES HAS TO BE IMPLEMENT
  //  
  public static void main(String[] args) {

        //start connection here first
        // DBconnection class has to be global so I can use it everyhwere (see how to set up a global vriable fast)
        // rename the fucking DB CLASS CONNECTION TO DB ONLY
        // int userOption=0;

        /**
         * login method as well as register method should be in the FUCKING MAIN. And remove those unused method.
         */

        /**
         * STEP 1: make line 41 works (global connection); and insert in try catch...
         */
        // make it work  
        int userChoice = 0;

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hi there...");
        System.out.println("What would like to do?" + "\n");
        do {
          System.out.println("Enter 1 to login: ");
          System.out.println("Enter 2 to sign-up: ");
          try {
            userChoice = Integer.parseInt(kb.readLine()); // converting int to string
          } catch (Exception e) {
            System.out.println("Please Only Numbers Allowed.." + "\n");
            System.out.println("If already user Please choose 1 to login.");
            System.out.println("If not user yet. Please choose 2 to sign-up" + "\n");
          }
        } while ((userChoice != 1 && userChoice != 2));

        /**
         * switch statement to validate the users choice in either registering or sign-up
         * CALLING LOGGIN Method from DBconnection Class
         * CALLING SIGN-UP Method from User Class
         */
        switch (userChoice) {
        case 1: //STEP 2: show login options (display option 1 =>login

          break;
        case 2:
          DBconnection Register = new DBconnection();
          Register.Register();
          /**
           * Create an connection to the database once the user has finished entering
           * his details and once the data is stored correctly into the database.. 
           * 
           * User successfully created.. THen display menu again with the login so he can
           * login. Once he logged in will have access to what he assgned to do
           */
          break;
        default:
    }

  }

}