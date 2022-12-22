package integratedca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnection {

   // Creating a Connection object
   Connection conn = null;

   //Global VARIABLES so I don't have to change values name all the time other than here
   static final String DB_NAME = "integratedca";
   static final String DATABASE_URL = "jdbc:mysql://localhost/" + DB_NAME;
   static final String USERNAME = "root";
   static final String PASSWORD = "root";
   static final String TB_NAME = "user";
   static final String DRIVER = "com.mysql.cj.jdbc.Driver";

   /**
    * Static connection so that it can be called everywhere in the program
    * @return connection
    * @throws ClassNotFoundException
    * @throws SQLException
    */
   public static Connection connect() throws ClassNotFoundException, SQLException {
      Class.forName(DRIVER);
      Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
      return conn;
   }

        /**
         * Set up connection and create template that query the database While loop
         * that reads record from DB and returns values
         * from database correctly
         * @throws SQLException
         * @throws ClassNotFoundException
         * @throws IOException 
         */
        public void Login() throws SQLException, ClassNotFoundException, IOException {

           BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

           System.out.print("Enter Username : ");
           String userName = kb.readLine().trim();

           System.out.print("Enter your Password : ");
           String pass = kb.readLine().trim();

           //prepared stmt
           PreparedStatement stmt = null;
           ResultSet results = null;
           conn = DBconnection.connect(); // Calling the Connection

           // Template to get data values from DB and validate LOGIN
           String SQL = "SELECT * FROM " + TB_NAME + " WHERE name='" + userName + "' AND password = '" + pass + "'";
           stmt = conn.prepareStatement(SQL);
           results = stmt.executeQuery();

           // Variables out of SCOPE while loop to be validated with if else, then initialize within loop as follow
           int id = 0;
           String userNm = "";
           String surNm = "";
           String email = "";
           String userPass = "";

           // Reads from databse and check whether user email exists or not
           while (results.next()) {
              id = results.getInt(1);
              userNm = results.getString(2);
              surNm = results.getString(3);
              email = results.getString(4);
              userPass = results.getString(5);
           }
           if ((userNm.equals(userName) && (email.contains("@admin")))) { /// YOU ARE DOING SOMETHING WRONG be careful on this line

              //ADMIN MENU
              System.out.println("Admin Logged-in Successfully!!!" + "\n");
              System.out.println("WELCOME " + userNm + ",");
              System.out.println("What would like to be doing today?");
              System.out.println("Please select:");
              System.out.println("-----------------");
              System.out.println("1: Modify your Profile");
              System.out.println("2: Access list of Users");
              System.out.println("3: Remove other Users");
              System.out.println("4: Review the operations performed by other Users");
              System.out.println("5: Sign out");

              int option = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE

              // SWITCH statement validation for the ADMIN USER
              switch (option) {
                 case 1:
                    Admin modifyAdminProfile = new Admin(); // Call Update() method
                    modifyAdminProfile.Update();
                    break;

                 case 2:
                    Admin list = new Admin(); // Call list of users from Admin class 
                    //list.ListOfUsers();
                    list.ListOfUsers();
                    break;

                 case 3:
                    // Admin remove = new Admin(); // Call remove() method
                    //  remove.removeUser("", "", "");
                    break;

                 case 4:
                    //Admin history = new Admin(); // Call history() method
                    //history.userHistory("", "", "");
                    break;

                 case 5:
                    System.out.println("Sorry to see you go!");
                    stmt.close(); // closing both connection and statement
                    conn.close();
                    break;
                 default: // Do nothing          
              }
           } else if ((userName.equals(userNm))) { // && (userPass.equals(pass))){ 

              //REGULAR MENU
              System.out.println("Regular User Logged-in Successfully!!!" + "\n");
              System.out.println("WELCOME " + userNm + ", ");
              System.out.println("What would like to be doing today?");
              System.out.println("Please Select: ");
              System.out.println("-----------------");
              System.out.println("1: Modify your Profile");
              System.out.println("2: Solve Systems of Equations");
              System.out.println("3: Sign out");

              int optionII = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE

              // SWITCH statement validation for REGULAR USER 
              switch (optionII) {
                 case 1:
                    Admin modifyRegularUserProfile = new Admin(); // Call Update() method
                    modifyRegularUserProfile.Update();
                    break;

                 case 2:
                    System.out.println("\n");
                    System.out.println("My SINSEREST apologies. I could not manage to finish the whole project in time.");
                    System.out.println("I got stuck while coding and planning for a long period of time and I was not able to Solve the given EQUATIONS.");
                    System.out.println("I appreciate you seeing my project. Please, check all the functionalities.");
                    System.out.println("Now I will have to catch up to do well on the EXAMS!!!");
                    System.out.println("Wish you Marry Cristman and a FANTASTIC Happy New Year");
                    System.out.println("------------------------------------------------------");
                    System.out.println("THANK YOU FOR THE ASSIGNMENT! 😊 ");
                    break;

                 case 3:
                    System.out.println("Sorry to see you go!");
                    stmt.close(); // closing both connection and statement
                    conn.close();
                    break;
                 default: // Do nothing           
              }

           } else {
              System.out.println("\n");
              System.out.println("Username or Password Invalid");
              System.out.println("Please Enter: ");
              System.out.println("-----------------");
              System.out.println("1: Try again");
              System.out.println("2: Register");
              System.out.println("3: Exit");
              int tryAgain = Integer.parseInt(kb.readLine());

              // Case 1 create an object and call REGISTER method
              // Case 2 a nice message and CLOSE connection as if they are loggin out out of the system
              switch (tryAgain) {
                 case 1:
                    DBconnection log = new DBconnection();
                    log.Login(); // Call Login() method
                    break;

                 case 2:
                    User register = new User(); //Create an object of DB class
                    register.registerUser(); // Call registerUser() method
                    break;

                 case 3:
                    System.out.println("See you Later!!!");
                    break;
                 default: // do nothing
              }
           }
        }
     }