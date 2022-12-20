package integratedca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * @author name: Franklin Arruda Cirino
 * Student Number: 2021368
 * GitHub link:  
 */
public class IntegratedCA {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        
            int userChoice = 0; // Gets user input
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Hello there!");
            System.out.println("Please," + "\n");
        do {
            System.out.println("Enter 1 to login : ");
            System.out.println("Enter 2 to sign-up : ");
            try {
                userChoice = Integer.parseInt(kb.readLine().trim()); // converting int to string and REMOVES SPACE
            } catch (Exception e) {
                System.out.println("Please Only Numbers Allowed.." + "\n");
                System.out.println("If already user Please choose 1 to login.");
                System.out.println("If not user yet. Please choose 2 to sign-up" + "\n");
            }
            // Will continue looping until user enters either 1 or 2
        } while ((userChoice != 1 && userChoice != 2));

        
        // User Menu
        switch (userChoice) {
            case 1:
                DBconnection log = new DBconnection();
                log.Login(); // Call Login() method
                break;

            case 2:
                User register = new User(); //Create an object of DB class
                register.registerUser(); // Call registerUser() method
                break;
            default:// Do nothing          
        }
    }
}
