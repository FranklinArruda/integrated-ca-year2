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

public class User {
    
    private String firstName;
    private String surName;
    private String email;
    private String password;
    private int id;
    
    //Parametised Constructor to add values into the (array list) when Getting LIST OF USERS 
    public User(String firstName, String surName,String email,String password,int id) {
        this.firstName=firstName;
        this.surName=surName;
        this.email=email;
        this.password=password;
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {}; // default Construcor
    
    Connection conn = null; // CONNECTION OBJECT
    
   /**
    * Get user input Establishes Connection Create template that select and
    * insert into database While loop that reads from database If else
    * statements to validate whether user is real or not.
    * @return true if user does not exists in database
    * @returns false if user records exists
    * @throws SQLException
    * @throws IOException
    * @throws ClassNotFoundException
    */
    public boolean registerUser() throws SQLException, IOException, ClassNotFoundException {

            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            
            String firstName = "", surName = "", userEmail = "", confirmEmail="", pass = "",passConfirm="";

            // Setting First Name
            System.out.print("Enter your First Name : ");
            firstName = kb.readLine();

            // Setting Last Name
            System.out.print("Enter your Surname : ");
            surName = kb.readLine();
       
        boolean emailCheck = true;
         //EMAIL Validation
        do {
            // Setting email
            System.out.print("Create your email : ");
            userEmail = kb.readLine();

            // Confirmin email
            System.out.print("Confirm email : ");
            confirmEmail = kb.readLine();

            if (!userEmail.equals(confirmEmail)) {
               emailCheck = false;
               System.out.println("\n");
               System.out.println("You must enter a VALID email, and both must macth. Please try again!");
               System.out.println("> Exemple: franklin@student.cct.ie");
               System.out.println("> Matches: franklin@student.cct.ie" + "\n");
            }
        } while (!userEmail.equals(confirmEmail)); //&& (!email.contains("@")));
         
         //PASSWORD Validation
        do {
            System.out.print("Create your Password : ");
            pass = kb.readLine();

            System.out.print("Confirm Password : ");
            passConfirm = kb.readLine();

            boolean check = true;
            if (!pass.equals(passConfirm)) {
               System.out.println("\n");
               System.out.println("Password doesn't macth, please try again!");
               check = false;
            }
        } while (!pass.equals(passConfirm));

            //making connection
            PreparedStatement stmt = null;
            ResultSet results = null;

            conn = DBconnection.connect(); // Calling the Connection

            // Select satatment to query where user and pass is located
            String select = "SELECT * FROM " + TB_NAME + " WHERE email = '" + userEmail + "';";
            stmt = conn.prepareStatement(select);
            results = stmt.executeQuery();

            // Variables out of SCOPE while loop to be validated, then initialized within loop as follow
            String email = "";

            // Reads from databse and check whether user exists or not
            while (results.next()) {
               email = results.getString(4);
            }

            //checking the user user email 
            //if doesn't match add email, if exists choose a different email
            if (!userEmail.equals(email) && (userEmail.contains("@"))) {

               // Creating template for query the database
              int status = stmt.executeUpdate("INSERT INTO " + TB_NAME + " (name, surname, email, password) " +
                  "VALUES ('" + firstName + "', '" + surName + "', '" + userEmail + "', '" + pass + "')");

              // if successfull
              if(status>0){
               System.out.println("User details Added Successfully!!!");
               System.out.println("------------------------------");
              }

              else{
                  System.out.println("Failed to insert values. Please try one more time later!!!");
              }

               // if inserted correctly. Reads from the from DB again and dispplay changes made
               ResultSet rs = stmt.executeQuery("SELECT * FROM " + DB_NAME + "." + TB_NAME + ";");
               String email2 = "";
               String first_name2 = "";

               while (rs.next()) {
                  first_name2 = rs.getString(2);
                  email2 = rs.getString(4);
               }
               // Displaying data INSERTED 
               System.out.println("Your email is : " + email2);
               System.out.println("Your User Name : " + first_name2);
               System.out.println("CHECK your email : (" + email2 + ") to see your Password : *******");
               System.out.println("( Make sure to Log-in using your User Name and Password )"+"\n");
               System.out.println("Please Enter,");
               System.out.println("1: Login : ");
               System.out.println("2: EXIT : ");
               
               stmt.close();//Closing both Statement and Connection
               conn.close();

               int option = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE

               // 1 to login
               // 2 to exit = close connection...
               switch (option) {
                  case 1:
                     DBconnection log = new DBconnection();
                     log.Login(); // Call Login() method
                     break;

                  case 2:
                     System.out.println("Sorry to see you go!");

                     break;
                  default: // do nothing
               }
            }

            // User already taken
            else {
               System.out.println("\n"); // new line to format menu
               System.out.println("Username Already Taken");
               System.out.println("----------------------");
               System.out.println("Please choose:");
               System.out.println("1: Register");
               System.out.println("2: Exit");

               int registerUser = Integer.parseInt(kb.readLine().trim());

               // Case 1 create an object and call REGISTER method
               // Case 2 a nice message and CLOSE connection as if they are loggin out out of the system
               switch (registerUser) {
                  case 1:
                     User register = new User(); //Create an object of DB class
                     register.registerUser(); // Call registerUser() method
                     break;

                  case 2:
                     System.out.println("See you Later!!!");
               }
            }
            return false; // do nothing  
         }
      }

