package integratedca;


import static integratedca.User.login;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class IntegratedCA {

    static DBconnection db; //DBconnection class has to be global so I can use it everyhwere 

    public static void main(String[] args) throws SQLException {

        db = new DBconnection(); 
        int userChoice = 0;

      
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hi there...");
        System.out.println("What would like to do?" + "\n");
        do {
            System.out.println("Enter 1 to login: ");
            System.out.println("Enter 2 to sign-up: ");
            try {
                userChoice = Integer.parseInt(kb.readLine()); // converting int to string
            } catch (Exception e) {
                System.out.println("Please Only Numbers Allowed.." + "\n");
                System.out.println("If already user Please choose 1 to login.");
                System.out.println("If not user yet. Please choose 2 to sign-up" + "\n");
            }
        } while ((userChoice != 1 && userChoice != 2));
   
        //users menu choice
        switch (userChoice) {
            case 1:
                login();
                

                break;
            case 2:

                break;
            default:
            //db.registerUser(name, pass)  
        }
    }
}
