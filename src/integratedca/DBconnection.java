package integratedca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Student
 */
public class DBconnection {

    // Admin User Pre-registered Credentials as per Assignment Requirmeents...
    static final String DB_NAME = "integratedca";
    static final String DATABASE_URL = "jdbc:mysql://localhost/" + DB_NAME;
    static final String USERNAME = "CCT";
    static final String PASSWORD = "Dublin";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String TB_NAME = "user";

  // Default constructor 
  public DBconnection() {

        try {
          Class.forName(driver).newInstance();
          Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
          Statement stmt = conn.createStatement();
          stmt.execute("USE " + DB_NAME + ";");
        } catch (Exception e) {
          System.out.println("h.");
          e.printStackTrace();
        }
      };

  /**
   * @Boolean method to load the DRIVER to the database 
   * @return true if connection is successful
   * @return false if connection fails
   */
  static public boolean DBsetUp() {
        System.out.println("DBsetUp");
        //try-catch for driver instance
        try {
          Class.forName(driver).newInstance();
        } catch (Exception e) {
          System.out.println("Connection Failed. Please check your Driver method.");
          e.printStackTrace();
        }

        // try-catch for connection                   
        try {
          Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
          Statement stmt = conn.createStatement();
          // INSERTING data into database
          stmt.execute("USE " + DB_NAME + ";");
          stmt.executeUpdate(

            // FIND A EASIER WAY OF INSERT DATA USING JAVA
            String.format("INSERT INTO " + TB_NAME + "(user_name,user_passWord)"
              //FIX THIS LINE...
              +
              "VALUES (\"%s\",\"%s\") ;"
              // using the 'User' arguments to get the variable attribute
              //in the class 'Users' and set values to be called in the main...
            ));

          //DISPLAYING ALL DATA CREATED FOR TESTING PORPUSES...
          ResultSet results = stmt.executeQuery("SELECT * FROM " + DB_NAME + "." + TB_NAME + ";");

          while (results.next())
            System.out.println("Username: " + results.getString(1) + ", ID " + " " + results.getString(2) + ", Pass " + " " + results.getString(3));
          conn.close();
          // System.out.println(rs);

          return true;
        } catch (SQLException e) {
          System.out.println("Something Went wrong in Insert method. please check the following ERROR!!!");
          e.printStackTrace(); // Will show the exact line in which the method raised the exception
        }
        return false;
      }

  /**
   * @return false if not authentication user is correct
   * @return true if user athentication is successful
   */
  public boolean Login() {

        BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
        String userName = "", password = "";
        do {
          try {
            System.out.print(" Enter user name => ");
            userName = myKeyboard.readLine();

            System.out.print(" Enter password => ");
            password = myKeyboard.readLine();

          } catch (Exception e) {
            System.out.println("PUT SOMETHING USEFUL HERE..."); // DONT FORGET
          }

          if (USERNAME.equals(userName) && PASSWORD.equals(password)) {
            System.out.println(" User successfully logged-in.. ");
            if (true) {
              System.out.println("Say now. WHO IS THE BOSS.. HAHA");
              /*
                  do stuff do tother stuff
                  call the methods and things*/
            }

          } else {
            System.out.println("Invalid userName of password");
            System.out.println("Please try again.");
            System.out.println("If not user yet, sigh-up...");
            DBconnection Register = new DBconnection();
            Register.Register();
          }
        } while (!USERNAME.equals(userName) && (!PASSWORD.equals(password)));
        //}

        return false;
  }

  public String Register() {
        User Register = new User("");
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        String userFName = "", userLName = "", userName = "", password = "";

        try {
          System.out.print("Enter your First Name: ");
          userFName = kb.readLine();
          Register.setUserFirstName(userFName);

          // Setting Last Name
          System.out.print("Enter your Last Name: ");
          userLName = kb.readLine();
          Register.setUserFirstName(userLName);

          // Setting Username
          System.out.print("Enter your Username: ");
          userName = kb.readLine();
          Register.setUserFirstName(userName);

          // Setting password
          System.out.print("Enter your password: ");
          password = kb.readLine();
          Register.setUserFirstName(password);
        } catch (Exception e) {
          System.out.println("something went wrong...");

        }
        return null;
    }
}