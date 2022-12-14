package integratedca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {
    Connection conn=null;

    static final String DB_NAME = "integratedca";
    static final String DATABASE_URL = "jdbc:mysql://localhost/" + DB_NAME;
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String TB_NAME = "user";

    public DBconnection() {

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
        } catch (Exception e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }
    }

     
    
    
    // !!! insert where name=name AND pass=pass
    public boolean readUser(String email_, String pass) throws SQLException {
            
       Statement stmt = conn.createStatement();
 
        String select= "SELECT * FROM user WHERE email="+email_+", password;"+pass;
        String SQL="SELECT * FROM user WHERE email= " + email_ + "password= " + pass + ";";
        System.out.print(select);
        ResultSet rs = stmt.executeQuery(select);
        
        Boolean found = false;
        
        while (rs.next()){
            found = true;
            String email = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(4);
            String city = rs.getString(5);
            String country = rs.getString(6);
            System.out.println(email + "\t" + "\t" + name);
        }
        return found;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    // registering new User
    public String registerUser(String name, String pass) throws SQLException, IOException {

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        String firstName = "", lastName = "", password = "";

        System.out.print("Enter your First Name: ");
        firstName = kb.readLine();

        // Setting Last Name
        System.out.print("Enter your Last Name: ");
        lastName = kb.readLine();

        // Setting password
        System.out.print("Enter your password: ");
        password = kb.readLine();

        // !!! write code here to check if the user already exists meaning there is no other user with firstName, LastName equal.
        // if user doesn't exist insert the record on the database.s
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
                // FIND A EASIER WAY OF INSERT DATA USING JAVA
                String.format("INSERT INTO user (user_name, user_passWord)"
                        //FIX THIS LINE...
                        + "VALUES (\"%s\",\"%s\") ;"
                // using the 'User' arguments to get the variable attribute
                //in the class 'Users' and set values to be called in the main...
                ));
        return null;

    }
}
