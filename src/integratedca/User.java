package integratedca;

import static integratedca.IntegratedCA.db;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class User {
    private String email; // attributes
    private String name;
    private String surname;

    public User(String email, String name, String surname) {
        this.email = email; 
        this.name = name; 
        this.surname = surname;
    }
    
   
    public static boolean login() throws SQLException {

        BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));
        String email = "", password = "";

        try {
            System.out.print("Enter email: ");
            email = myKeyboard.readLine();

            System.out.print("Enter password: ");
            password = myKeyboard.readLine();

        } catch (Exception e) {
            System.out.println("PUT SOMETHING USEFUL HERE..."); // DONT FORGET
                 }
        
        db.readUser(email, password);
        
        return true;  
    } 
}
 
