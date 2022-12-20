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

            BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter Username : ");
            String name = myKeyboard.readLine().trim();
            
            System.out.print("Enter your Password : ");
            String pass = myKeyboard.readLine().trim();
           
            //prepared stmt
            PreparedStatement stmt = null;
            ResultSet results = null;
            conn = DBconnection.connect(); // Calling the Connection
            
            //Querying records
            String select = "SELECT * FROM " + TB_NAME + " WHERE name = '" + name +"' AND password = '" + pass + "'";
            stmt = conn.prepareStatement(select);
            results = stmt.executeQuery();

            // Variables out of SCOPE while loop to be validated with if else, then initialize within loop as follow
            int id=0;
            String userNm = "";
            String email = "";
            String userPass = "";
            
            // Reads from databse and check whether user email exists or not
            while (results.next()) {
                id = results.getInt(1);
                userNm = results.getString(2);
                email = results.getString(4);
                userPass = results.getString(5);
            }
             
            if ((name.equals("CCT")) && (userPass.equals(pass)) && (email.contains("@admin.com"))) { 
                
                //ADMIN MENU
                System.out.println("\n");
                System.out.println("ID: "+ id);
                System.out.println("Username: " + userNm);
                System.out.println("Email: " + email);
                System.out.println("------------------------------");
                System.out.println("Logged-in Successfully");
                System.out.println("Welcome " + userNm + ",");
                System.out.println("What would like to be doing today:");
                System.out.println("Please Enter: " + "\n");
                System.out.println("1: Modify your Profile");
                System.out.println("2: Access list of Users");
                System.out.println("3: Remove other Users");
                System.out.println("4: Review the operations performed by other Users");
            
                int option = Integer.parseInt(myKeyboard.readLine().trim()); // converting int to string and REMOVES SPACE

            // SWITCH statement validation for the ADMIN USER
            switch (option) {
                case 1:
                    Admin modifyAdminProfile = new Admin(); // Call Update() method
                    modifyAdminProfile.Update();
                    break;

                case 2:
                   // Admin remove = new Admin(); // Call remove() method
                  //  remove.removeUser("", "", "");
                    break;

                case 3:
                    //Admin listUser = new Admin(); // Call userList() method
                    //listUser.listOfUser("", "", "");
                    break;

                case 4:
                    //Admin history = new Admin(); // Call history() method
                    //history.userHistory("", "", "");
                    break;
                default:// Do nothing          
            }
        }  
            
            else if ((name.equals(userNm)) && (userPass.equals(pass))){ 
            
            //REGULAR MENU
            System.out.println("\n");
            System.out.println("Logged-in Successfully");
            System.out.println("Welcome " + name + ", ");
            System.out.println("What would like to be doing today:");
            System.out.println("------------------------------");
            System.out.println("Please Enter: " + "\n");
            System.out.println("1: Modify your Profile");
            System.out.println("2: Solve System of Equations");

            int optionII = Integer.parseInt(myKeyboard.readLine().trim()); // converting int to string and REMOVES SPACE

            // SWITCH statement validation for REGULAR USER 
            switch (optionII) {
                case 1:
                    Admin modifyRegularUserProfile = new Admin(); // Call Update() method
                    modifyRegularUserProfile.Update();
                    break;

                case 2:
                    User solve = new User(); // Call solveEquation() Method
                    //solve.solveEquation("", "", "");
                    break;
                default:// Do nothing          
            }
           
        }
                else{System.out.println("USER NOT VALID!!!");

                }
    }

}
