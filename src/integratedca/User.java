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
    
   Connection conn = null; // CONNECTION OBJECT
   
   public User() {}; // default Construcor

   /**
    * Get user input Establishes Connection Create template that select and
    * insert into database While loop that reads from database If else
    * statements to validate whether user is real or not.
    *
    * @return true if user does not exists in database
    * @returns false if user records exists
    * @throws SQLException
    * @throws IOException
    * @throws ClassNotFoundException
    */
   public boolean registerUser() throws SQLException, IOException, ClassNotFoundException {

      BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
      String firstName = "", surName = "", userEmail = "", pass = "";

      // Setting First Name
      System.out.print("Enter your First Name : ");
      firstName = kb.readLine();

      // Setting Last Name
      System.out.print("Enter your Surname : ");
      surName = kb.readLine();

      // Setting email
      System.out.print("Create your email : ");
      userEmail = kb.readLine();

      // Setting password
      System.out.print("Choose your password : ");
      pass = kb.readLine();

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
         stmt.executeUpdate("INSERT INTO " + TB_NAME + " (name, surname, email, password) " +
            "VALUES ('" + firstName + "', '" + surName + "', '" + userEmail + "', '" + pass + "')");

         // if successfull
         System.out.println("User details Added Successfully!!!" + "\n");

         // Template
         ResultSet rs = stmt.executeQuery("SELECT * FROM " + DB_NAME + "." + TB_NAME + ";");

         // Stores data from database so I can use to manipulate while coding
         String email2 = "";
         String first_name2 = "";

         while (rs.next()) {
            first_name2 = rs.getString(2);
            email2 = rs.getString(4);
         }
         // Displaying data INSERTED 
         System.out.println("Your email is : " + email2);
         System.out.println("Your Username : " + first_name2);
         System.out.println("CHECK your email : (" + email2 + ") to see your Password : ******" + "\n");
         System.out.println("Please,");
         System.out.println("Enter 1 : Login : ");
         System.out.println("Enter 2 : EXIT : ");

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


      // Validation in case the user already taken
      else {
         System.out.println("\n"); // new line to format menu
         System.out.println("Username Already Taken");
         System.out.println("----------------------");
         System.out.println("Please choose:");
         System.out.println("Enter 1: Register");
         System.out.println("Enter 2: Exit");
         int registerUser = Integer.parseInt(kb.readLine());

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
      return false; // 
   }
}


/*
    public boolean solveEquation(String name, String email, String password)
            throws ClassNotFoundException, SQLException {

        try {
            PreparedStatement stmt = null;
            ResultSet results;

            String sql = "UPDATE Registration "
                    + "SET age = 30 DB_NAME id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}*/