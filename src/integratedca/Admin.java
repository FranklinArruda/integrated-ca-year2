
package integratedca;

import static integratedca.DBconnection.DB_NAME;
import static integratedca.DBconnection.TB_NAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
 
    Connection conn = null; // CONNECTION OBJECT

    static final String UPDATE = "SELECT id, first, last, age FROM Registration";
    static final String USER_LIST = "SELECT id, first, last, age FROM Registration";
    static final String REMOVE = "";
    static final String HISTORY = "";
   
    public Admin() {}; // DEFAULT Constructor

    public boolean Update() throws ClassNotFoundException, SQLException, IOException {
                
                BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
                int userOption=0;

                // USER MENU
                System.out.println("Changes will only take effect by confirming your password created when REGISTERING or LOGGED-IN:");
                System.out.println("What would you like to modify:");

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

                    // Confirm PASS
                    System.out.print("Enter your PASSWORD to confirm changes : ");
                    String confirmPass = kb.readLine();

                    //Prepared statement
                    PreparedStatement stmt = null;
                    ResultSet results = null;

                    //Connection
                    conn = DBconnection.connect();

                    //Template for UPDATING
                    //String update = "UPDATE user SET name ='test' WHERE name ='Franklin';";
                    String update = "UPDATE " + DB_NAME + "." + TB_NAME + " SET name = '" + firstName + "',"
                        + " surname = '" + surName + "' WHERE password ='"+ confirmPass +"';";
        
                    //Executing QUERY
                    stmt = conn.prepareStatement(update);
                    stmt.executeUpdate(); 
                    System.out.println("Records Updated Succesfully!!!");
                    System.out.println("\n");
                break;

                case 2:
                    String email="", emailConfirm="", confirmPass2=""; 
                    try {   
                        do {
                            // Updating Email
                            System.out.print("Enter New Email : ");
                            email = kb.readLine();

                            // Confirming email
                            System.out.print("Confrim Email : ");
                            emailConfirm = kb.readLine();

                            // boolean to validade email. Loop will continue while if is FALSE;
                            boolean check = true;
                            if(!email.equals(emailConfirm)){
                                System.out.println("\n");
                                System.out.println("You must enter a VALID email, please try again!");
                                System.out.println("Exemple: franklin@student.cct.ie" + "\n");
                                check=false;
                            }
                            else{
                                // Confirm PASS
                                System.out.print("Enter your PASSWORD to confirm changes : ");
                                confirmPass = kb.readLine();
                                System.out.println("Records Updated Succesfully!!!");
                                System.out.println("\n");
                            }
                            // will keep going while until user types a valid Email using @ and matches the first one he/she entered
                            } while(!email.equals(emailConfirm) && (!email.contains("@")));
                        }  catch(Exception e){
                                e.printStackTrace();
                        }
                            //Connection
                            conn = DBconnection.connect();

                            //Template for UPDATING
                            //String update = "UPDATE user SET name ='test' WHERE name ='Franklin';";
                            String updateEmail = "UPDATE " + DB_NAME + "." + TB_NAME + " SET email = '" + email + "' WHERE password ='"+ confirmPass2 +"';";

                            //Executing QUERY
                            stmt = conn.prepareStatement(updateEmail);
                            stmt.executeUpdate(); 
                        break;

                case 3:
                      String pass="", confirmPass1="", confirmPass3="";
                      try {   
                         do {
                            // Updating Email
                            System.out.print("Enter New Password : ");
                            pass = kb.readLine();

                            // Confirming email
                            System.out.print("Confrim Password : ");
                            confirmPass1 = kb.readLine();

                            // boolean to validade email. Loop will continue while if is FALSE;
                            boolean check = true;
                            if(!pass.equals(confirmPass1)){
                                System.out.println("\n");
                                System.out.println("Password doesn't macth, please try again!");
                                check=false;
                            }
                            else{
                                // Confirm PASS
                                System.out.print("Enter your OLD password to confirm changes : ");
                                confirmPass2 = kb.readLine();
                                System.out.println("Password Updated Succesfully!!!");
                                System.out.println("\n");
                            }
                            // will keep going while until user types a valid Email using @ and matches the first one he/she entered
                             } while(!pass.equals(confirmPass1));
                        } catch(Exception e){
                                e.printStackTrace();
                        }
                            //Connection
                            conn = DBconnection.connect();

                            //Template for UPDATING
                            //String update = "UPDATE user SET name ='test' WHERE name ='Franklin';";
                            String updatePass = "UPDATE " + DB_NAME + "." + TB_NAME + " SET password = '" + pass + "' WHERE password ='"+ confirmPass3 +"';";

                            //Executing QUERY
                            stmt = conn.prepareStatement(updatePass);
                            stmt.executeUpdate();  
                        break;

                case 4:
                    System.out.println("Goodbye for now... ");
                    break;
                default:// Do nothing       
            }
         
        } while(userOption!=4); // Will keep going while user until user hits 5
            
            conn.close(); // Close connection
            
     return false;
    }
  }
    