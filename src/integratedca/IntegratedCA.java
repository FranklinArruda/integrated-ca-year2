package integratedca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author name: Franklin Arruda Cirino
 * Student Number: 2021368
 * GitHub link: https://github.com/Franklin-93/IntegratedCA.git
 * 
 * 15 minutes (presentation) ONLY running the application
 * https://drive.google.com/file/d/1cGFWc_rWlA51GUrg0RqvR1W_Wv-VBQV3/view?usp=sharing
 * 
 * 40 minutes (presentation) EXPLAINS the entire Project
 * https://drive.google.com/file/d/1_ihnXB4I9CaYRDdKwO9huLC-ncdFp5cS/view?usp=sharing
 */

public class IntegratedCA {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        
            int userChoice = 0; // Gets user input
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));          
            
            // getting current time to greeting the user
            GregorianCalendar time = new GregorianCalendar();
            int hour = time.get(Calendar.HOUR_OF_DAY);
             
                if (hour < 12){
                 System.out.println("Good Morning!");
                 System.out.println("Please Enter: ");
                 System.out.println("------------");
                }

                 else if (hour < 17 && !(hour == 12)){
                 System.out.println("Good Afternoon");
                 System.out.println("Please Enter: ");
                 System.out.println("------------");
                 }

                 else if (hour == 12){
                 System.out.println("Good Noon");
                 System.out.println("Please Enter: ");
                 System.out.println("------------");
                 }

                 else{
                 System.out.println("Good Evening");
                 System.out.println("Please Enter: ");
                 System.out.println("------------");
                 }
             
            // will continue until user chooses either 3
            do {
                System.out.println("1: LOGIN");
                System.out.println("2: REGISTER ");
                System.out.println("3: EXIT");
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
