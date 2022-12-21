package integratedca;

import static integratedca.DBconnection.DB_NAME;
import static integratedca.DBconnection.TB_NAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                  } while (false);

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
                  } while (false);

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
                  System.out.println("Something went wrong.Please try chenge Password later...");
               }
               break;


               // CASE 4 > FORCING USER TO SIGN UP SO CHANGES CAN BE DONE...Actually, it is just for testing. If later
               // I try to login once I sigh out and doesn't work I would investigate
            case 4:
               System.out.println("You must Sign out to changes you have made takes place! ");
               System.out.println("Remeber to use your most up to date Credentials...");
               System.out.println("Please Select");
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
}