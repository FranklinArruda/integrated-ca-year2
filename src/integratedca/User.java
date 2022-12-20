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

    static final String SOLVE = "SELECT id, first, last, age FROM Registration";

    public User() {
    }

    /**
     * Get user input Establishes Connection Create template that select and
     * insert into database While loop that reads from database If else
     * statements to validate whether user is real or not
     *
     * @return true if user does not exists in database
     * @returns false if user records exists
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean registerUser() throws SQLException, IOException, ClassNotFoundException {
            
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            String firstName = "", surName = "", userEmail = "", pass="";

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
            ResultSet results=null;
         
            conn = DBconnection.connect(); // Calling the Connection
        
            // Select satatment to query where user and pass is located
            String select = "SELECT * FROM " + TB_NAME + " WHERE email = '" + userEmail + "'";
            stmt = conn.prepareStatement(select);
            results = stmt.executeQuery();
            
            // Variables out of SCOPE while loop to be validated, then initialized within loop as follow
            String email="";
            
        // Reads from databse and check whether user exists or not
        while (results.next()){
            email = results.getString(4);
            }
        
        //checking the user user email 
        //if doesn't match add email, if exists choose a different email
        if (!userEmail.equals(email)){
            
            // Creating template for query the database
            stmt.executeUpdate("INSERT INTO " + TB_NAME + " (email, name, password, surName) "
                   +"VALUES ('" + userEmail +"', '" + firstName +"', '" + pass +"', '" + surName +"')");
    
                // if successfull
                System.out.println("User details Added Successfully");
                
                // Template
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + DB_NAME + "." + TB_NAME + ";");
                
                // Stores data from database so I can use to manipulate while coding
                String email2 = "";
                String first_name2 = "";
                
            while (rs.next()) {
                email2=rs.getString(1);
                first_name2=rs.getString(2);
            }
                // Displaying data INSERTED 
                System.out.println("Your email is : " + email2 + " & your Username is : " + first_name2 );    
                conn.close(); // Closing Connection
            }
            else {
                System.out.println("Username Already Taken");
            }                
      return false;
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