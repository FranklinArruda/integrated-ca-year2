package integratedca;

import static integratedca.DBconnection.DB_NAME;
import static integratedca.DBconnection.TB_NAME;
import static integratedca.DBconnection.TB_NAME_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {

   Connection conn = null; // CONNECTION OBJECT

   public Admin() {}; // DEFAULT Constructor

   /**
    * Update method, display menu using Switch statement to validate users input 
    * Selections of options with do while loop that keeps going until user enters 4
    * Once the Select the Main menu, they will be displayed with the for options which is:
    * @Change Full Name
    * @Change Email
    * @Change Password
    * @throws ClassNotFoundException
    * @throws SQLException
    * @throws IOException 
    */
    public void Update() throws ClassNotFoundException, SQLException, IOException {

      BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
      int userOption = 0;

      // USER MENU
      System.out.println("Changes will only take effect by confirming your password created when REGISTERING or LOGGING-IN:");
      System.out.println("What would you like to modify:" + "\n");

      do { // will keep going until user hits the FORTH option 4
         System.out.println("Please Enter :");
         System.out.println("-----------------------");
         System.out.println("1: Modify your Full Name");
         System.out.println("2: Modify your Email");
         System.out.println("3: Modify your Password");
         System.out.println("4: Exit");

         userOption = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE

         // USER CHOICE
         switch (userOption) {
            case 1:
               // Updating First Name
               System.out.print("Enter First Name : ");
               String firstName = kb.readLine();

               // Updating Surname
               System.out.print("Enter Surname : ");
               String surName = kb.readLine();

               String pass = "", confirmPass = ""; //Out of scope for validation
               do {
                  // PASS
                  System.out.print("Enter your PASSWORD to confirm changes : ");
                  pass = kb.readLine();

                  // Confirm PASS
                  System.out.print("Confirm your PASSWORD : ");
                  confirmPass = kb.readLine();

                  // boolean to validade email. Loop will continue while if is FALSE;
                  boolean check = true;
                  if (!pass.equals(confirmPass)) {
                     System.out.println("\n");
                     System.out.println("Password doesn't macth, please try again!");
                     check = false;
                  }
               } while (!pass.equals(confirmPass));

               //Prepared statement 1
               PreparedStatement stmt1 = null;
               conn = DBconnection.connect();
               String updateName = "UPDATE " + DB_NAME + "." + TB_NAME + " SET name = '" + firstName + "'," +
                  " surname = '" + surName + "' WHERE password ='" + confirmPass + "';";

               stmt1 = conn.prepareStatement(updateName);
               int status1 = stmt1.executeUpdate();

               // if statement to validate data criation progress
               // will return true if the statement process table not in the SQL server ok and is created
               if (status1 > 0) {
                  System.out.println("\n");
                  System.out.println("Records Updated Succesfully!!!");
                  System.out.println("First Name : " + firstName);
                  System.out.println("Surname: " + surName);
                  System.out.println("\n");

                  stmt1.close(); // closing both
                  conn.close();
               } else {
                  System.out.println("Failure to chenge Name.Please try again later...");
               }
               break;


               // CASE 2 > CHANGING EMAIL
            case 2:
               try {
                  String email = "", emailConfirm = "", confPass1 = "", confPass2 = "";
                  do {
                     // Updating Email
                     System.out.print("Enter New Email : ");
                     email = kb.readLine();

                     // Confirming email
                     System.out.print("Confrim Email : "); // (\n)  will go to a new line if email is fine
                     emailConfirm = kb.readLine();

                     boolean check2 = true;
                     if (!email.equals(emailConfirm)) {
                        System.out.println("\n");
                        System.out.println("You must enter a VALID email, and both must macth. Please try again!");
                        System.out.println("> Exemple: franklin@student.cct.ie");
                        System.out.println("> Matches: franklin@student.cct.ie" + "\n");
                        check2 = false;
                     }
                     // will keep going while either emails doesn't macth or password incorrect
                     // confirm1 will ask the user pass and confirm 2 will holds the value and pass to the template
                     // template will read from database and execute update if pass =ok!
                  } while (!email.equals(emailConfirm)); //&& (!email.contains("@")));


                  //CONFIRM Pass
                  do {
                     System.out.print("Enter your Password to confirm changes : ");
                     confPass1 = kb.readLine();

                     System.out.print("Confirm Password : ");
                     confPass2 = kb.readLine();

                     boolean check = true;
                     if (!confPass1.equals(confPass2)) {
                        System.out.println("\n");
                        System.out.println("Password doesn't macth, please try again!");
                        check = false;
                     }
                  } while (!confPass1.equals(confPass2));

                  //Prepared statement 2
                  PreparedStatement stmt2 = null;
                  conn = DBconnection.connect();

                  // then the confirmPass2 which holds the value of confrimPass1 if both macthes 
                  // then will read from database through the query below and check pass,if true=status, else= not 
                  String updateEmail = "UPDATE " + DB_NAME + "." + TB_NAME + " SET email = '" + email + "' WHERE password ='" + confPass1 + "';";
                  stmt2 = conn.prepareStatement(updateEmail);
                  int status2 = stmt2.executeUpdate();

                  //If pass check is ok and update record in database ok
                  if (status2 > 0) {
                     System.out.println("\n");
                     System.out.println("Records Updated Succesfully!!!");
                     System.out.println("Your new Email : ( " + email + " )");
                     System.out.println("\n");

                     stmt2.close(); // closing both
                     conn.close();
                  } else {
                     System.out.println("Failure to update record...");
                  }
               } catch (Exception e) {
                  System.out.println("INVALID email validation! Try again later...");;
               }
               break;


               // CASE 3 > CHANGING PASSWORD
            case 3:
               try {
                  String newPass = "", newPassConfirm = "", oldPassConfirm = "";
                  do {
                     // Updating Email
                     System.out.print("Enter New Password : ");
                     newPass = kb.readLine();

                     // Confirming email
                     System.out.print("Confrim Password : ");
                     newPassConfirm = kb.readLine();

                     // boolean to validade email. Loop will continue while if is FALSE;
                     boolean check3 = true;
                     if (!newPass.equals(newPassConfirm)) {
                        check3 = false;
                        System.out.println("\n");
                        System.out.println("Password doesn't macth, please try again!");
                     }
                  } while (!newPass.equals(newPassConfirm));

                  // this part will ask for the OLD pass which will read WHERE password and return true if matches the one
                  //it is in there, only then the SET password in the query takes place. 
                  // If successful will return a nice message, if not something is wrong.
                  System.out.print("Enter your OLD password to confirm changes :");
                  System.out.println("\n");
                  oldPassConfirm = kb.readLine();

                  //Prepared statement 3
                  PreparedStatement stmt3 = null;
                  conn = DBconnection.connect();
                  //TEMPLATE
                  String updatePass = "UPDATE " + DB_NAME + "." + TB_NAME + " SET password = '" + newPass + "' WHERE password ='" + oldPassConfirm + "';";
                  stmt3 = conn.prepareStatement(updatePass);
                  int status3 = stmt3.executeUpdate();

                  //checking records...
                  if (status3 > 0) {
                     System.out.println("Password :" + " *******" + " Updated Succesfully!!!");
                     System.out.println("Please check your Email to see you Password");
                     System.out.println("\n");

                     stmt3.close(); // closing both
                     conn.close();
                  } else {
                     System.out.println("Sorry!!! Failure to chenge Password. Please try again later...");
                  }
               } catch (Exception e) {
                  System.out.println("Something went wrong.Please try change Password later...");
               }
               break;


               // CASE 4 > FORCING USER TO SIGN UP SO CHANGES CAN BE DONE...Actually, it is just for testing. If later
               // I try to login once I sigh out and doesn't work I would investigate
            case 4:
               System.out.println("You must Sign out for the changes you have made takes effect! ");
               System.out.println("Remeber to use your most up to date Credentials...");
               System.out.println("Please Select,");
               System.out.println("-----------------");
               System.out.println("1: Sign out");
               int option = Integer.parseInt(kb.readLine().trim());

               // SWITCH statement to sign out option
               switch (option) {
                  case 1:
                     System.out.println("You may enjoy your day!");
                     break;
                  default: // Do nothing
               }
               break;
            default: // Do nothing       
         }
      } while (userOption != 4); // Will keep going while user until user hits 4   
  } 
  
  
    /**
     * List of Users method to retrieve all users from database 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
      public void ListOfUsers() throws SQLException, ClassNotFoundException, IOException{
          
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            int userOption = 0;
            
         do { 
                System.out.println("Please Enter :");
                System.out.println("-----------------------");
                System.out.println("1: Users List");
                System.out.println("2: Exit");
                userOption = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE
                
                switch(userOption){
                    case 1:
                        PreparedStatement stmt = null;
                        ResultSet results = null;
                        conn = DBconnection.connect(); // Calling the Connection

                        //Template
                        String SQL = "SELECT * FROM " + DB_NAME + "." + TB_NAME + ";";
                        stmt = conn.prepareStatement(SQL);
                        results = stmt.executeQuery();

                        int id = 0;
                        String userName = "";
                        String surName = "";
                        String email = "";

                        // Link for the following FORMAT I took reference from
                        //https://www.javatpoint.com/how-to-print-table-in-java-using-formatter

                        System.out.printf("USERS LIST :    ID    |  First Name   |    Surname   |         Email     |");
                        System.out.println();
                        System.out.println("-------------------------------------------------------------------------");  
     
                        while (results.next()){ 
                          id = results.getInt(1);
                          userName = results.getString(2);
                          surName = results.getString(3);
                          email = results.getString(4);

                          System.out.print("|");
                          System.out.printf("%17s %15s %15s %22s ",id , userName ,surName,email );  
                          System.out.println(); 
                          }  
                             stmt.close(); // closing both
                             conn.close();
                    break;
                    
                    case 2:
                        System.out.println("Please Select,");
                        System.out.println("-----------------");
                        System.out.println("1: Sign out");
                        int option = Integer.parseInt(kb.readLine().trim());

                            // SWITCH statement to sign out option
                            switch (option) {
                               case 1:
                                  System.out.println("Have a good day!");
                                  break;
                               default: // Do nothing
                            }
                     break;
            }   
        } while(userOption !=2);   
    }

 
     /**
     * First, the List of users is listed so the user can see who is who and what ID the user hold
     * Remove USERS method by using prepared statement
     * Gets user input and stores into (userID) variable
     * Use user Input to validate while loop and will continue until users input greater than 0
     * If users Input is greater than 0, using the same variable into the prepared statement to remove selected user 
     * Select the users ID and storing into userID variable which would be the users input
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
      public void RemoveUser() throws SQLException, ClassNotFoundException, IOException{
        
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            int userOption = 0;

                //Connection
                PreparedStatement stmt = null;
                ResultSet results = null;
                conn = DBconnection.connect(); // Calling the Connection

                //Showing the Listof USERS FIRST
                //Template
                String SQL = "SELECT * FROM " + DB_NAME + "." + TB_NAME + ";";
                stmt = conn.prepareStatement(SQL);
                results = stmt.executeQuery();

                int id = 0;
                String userName = "";
                String surName = "";
                String email = "";

                // Link for the following FORMAT I took reference from
                //https://www.javatpoint.com/how-to-print-table-in-java-using-formatter

                System.out.printf("USERS LIST :    ID    |  First Name   |    Surname   |         Email     |");
                System.out.println();
                System.out.println("-------------------------------------------------------------------------");  

                while (results.next()){ 
                  id = results.getInt(1);
                  userName = results.getString(2);
                  surName = results.getString(3);
                  email = results.getString(4);

                  System.out.print("|");
                  System.out.printf("%17s %15s %15s %22s ",id , userName ,surName,email );  
                  System.out.println(); 
                  }  

                //CODE to delete the user
            do { 
                System.out.println("Please Enter :");
                System.out.println("-----------------------");
                System.out.println("1: Remove Users");
                System.out.println("2: Exit");
                userOption = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE

                    switch(userOption){
                        case 1:
                             int userID = 0; //Store users ID 
                             
                            //repeat until user enters a number greater than 0
                    while (userID < 1){
                        try { 
                            System.out.println("Please enter the USER ID number you would like to DELETE");
                            
                            userID = Integer.parseInt(kb.readLine()); //reads number from keyboard

                            // if user input is greater than 0 this code will be executed
                            if (userID>0){

                                //TEMPLATE using prapered statement    
                                String query = "DELETE FROM " + TB_NAME + " WHERE id=?";;
                                PreparedStatement pstmt = conn.prepareStatement(query);

                                pstmt.setInt(1,userID);
                                int deleteCheck = pstmt.executeUpdate();

                                // if inside another if statement to validate whether the user has been deleted or not
                                // will also check if user ID is still exists on the system
                                    if(deleteCheck>0){
                                        System.out.println("User deleted SUCCESSFULLY!!!"+"\n");
                                    }
                                    else{
                                        //if failed to delete the user this message will be displayed
                                        System.out.println("Failed to delete User by ID");
                                        System.out.println("The user may either not exists on your system or ID selected not valid");
                                        System.out.println("Please try again!");
                                    }
                            }
                            else{
                                // If user enter 0 will display this message
                                System.out.println("The USERS IDs starts from 1 onwards. Please choose the Correct ID number!c");                        
                            }
                        }
                        catch(Exception e){

                            //user did not enter an integer
                            System.out.println("ONLY Numbers allowed!!!");
                            kb.readLine(); //Gets rid of the "wrong stuff" on the keyboard
                        }
                    }// while loop finishes here...   
                        break;

                        case 2:
                            System.out.println("Please Select,");
                            System.out.println("-----------------");
                            System.out.println("1: Sign out");
                            int option = Integer.parseInt(kb.readLine().trim());

                                // SWITCH statement to sign out option
                                switch (option) {
                                   case 1:
                                      System.out.println("Have a good day!");
                                      break;
                                   default: // Do nothing
                                }
                         break;
            }   
        } while(userOption !=2);   
        
     }
      
      public void TESTING (int userID) throws IOException, SQLException, ClassNotFoundException{
      
          BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
          
          /*  Connection conn = null;
            PreparedStatement preparedStmt = null;
            ResultSet result = null;
         /*   
          System.out.println("ENTER YOUR FIRST NAME");
            String firstName = kb.readLine();
            
            System.out.println("ENTER YOUR LAST NAME");
            String lastName = kb.readLine();
            
            // the mysql insert statement
            String query = " INSERT INTO " + TB_NAME_2 + " (first_name, last_name)"
              + " VALUES (?, ?)";
            
            // create the mysql insert preparedstatement
            
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, firstName);
            preparedStmt.setString (2, lastName);
            
          int status = preparedStmt.executeUpdate();
    
           
          // if successfull
              if(status>0){
               System.out.println("User details Added Successfully!!!");
               System.out.println("------------------------------");
              }

              else{
                  System.err.println("Failed to insert values. Please try one more time later!!!");
              } 
         
         
           
            String SQL = "SELECT * FROM " + DB_NAME + "." + TB_NAME + ";";
            preparedStmt = conn.prepareStatement(SQL);
            result = preparedStmt.executeQuery();
          
            
            int ID=0;
            String FN = "";
            String LN = "";
           
             // Reads from databse and check whether user email exists or not
           while (result.next()) {
              ID = result.getInt(1);
              FN = result.getString(2);
              LN = result.getString(3);
              System.out.format("%s, %s, %s\n", ID, FN, LN);
              
           }
           */
                 System.out.println("ENTER transaciont number 1");
                double amt = Integer.parseInt(kb.readLine());
                
                //Connection
                PreparedStatement preparedStmt = null;
                ResultSet results = null;
                conn = DBconnection.connect(); // Calling the Connection

                // the mysql insert statement
                String INSERT = " INSERT INTO " + TB_NAME_2 + " (amount, customer_id)"
              + " VALUES (?, ?)";  
                
                // create the mysql insert preparedstatement
                preparedStmt = conn.prepareStatement(INSERT);
                preparedStmt.setDouble (1, amt);
                preparedStmt.setInt (2, userID);
            
                int status1 = preparedStmt.executeUpdate();
    
           
          // if successfull
              if(status1>0){
               System.out.println("TRANSACTION Added Successfully!!!");
               System.out.println("------------------------------");
              }

              else{
                  System.err.println("Failed to insert values. Please try one more time later!!!");
              } 
          
              
              String SELECT = "SELECT * FROM " + DB_NAME + "." + TB_NAME_2 + ";";
            preparedStmt = conn.prepareStatement(SELECT);
            results = preparedStmt.executeQuery();
          
            
            double transaction_ID=0;
            int amount=0;
            int customer_ID=0;
           
             // Reads from databse and check whether user email exists or not
           while (results.next()) {
              transaction_ID = results.getInt(1);
               amount = results.getInt(2);
                customer_ID = results.getInt(3);
              
              System.out.format("%s, %s, %s\n", transaction_ID, amount, customer_ID);
              
           }
                             preparedStmt.close(); // closing both
                             conn.close();
      
      }
   }








































    /**
     * Array list method to store all user from database when retrieved
     * @return List of Users
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    /*public List<User> List() throws ClassNotFoundException, SQLException  {
              
                // Create an ArrayList object and Assigned the User class as my datatype
                List<User> listOfUsers = new ArrayList<>(); 
                
                //Making connection
                PreparedStatement stmt = null;
                ResultSet results = null;
                
                // Calling the Connection
                conn = DBconnection.connect(); 

                //Template that selects all USERS from DB
                String select = "SELECT * FROM " + DB_NAME + "." + TB_NAME + ";";
                stmt = conn.prepareStatement(select);
                results = stmt.executeQuery();
                
                //Reading all users from DB and adding into my ArrayList by using the CONSTRUCTOR and getters & setter 
                // in User class
                
                String name="", surnNm="",email="";
                int id=0;
                User list = new User(); 
                
                while (results.next()) {
                    //Object of User so I can actually assigns values into attributes
                    //Storing resultSet into variables
                    id = results.getInt(1);
                    name = results.getString(2);
                    surnNm = results.getString(3);
                    email = results.getString(4);  
                }
                 // Setting variables into array list using Setters parameters
                    list.setId(id);
                    list.setFirstName(name);
                    list.setSurName(surnNm);
                    list.setEmail(email);
                    
                    listOfUsers.add(list); 
                    System.out.println(id);
                    System.out.println(name);
                    System.out.println(surnNm);
                    System.out.println(email);
                    
            return listOfUsers;
            }
}*/

        

