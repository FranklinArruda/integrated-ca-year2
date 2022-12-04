/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integratedca;

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
 
    
    // Admin User Pre-registered Credentials
    String DB_NAME="integratedca";
    String DATABASE_URL="jdbc:mysql://localhost/" + DB_NAME;
    String USERNAME="CCT"; 
    String PASSWORD="Dublin";   
    String driver="com.mysql.cj.jdbc.Driver";
    String TB_NAME="user";
    
    
    /**
     * @Boolean method to load the DRIVER to the database 
     * @return true if connection is successful
     * @return false if connection fails
     */
    
    public boolean connection(User user){
        
        //try-catch for driver instance
        try{Class.forName(driver).newInstance(); 
        }catch(Exception e){System.out.println("Connection Failed. Please check your Driver method.");
            e.printStackTrace();
        }
        
        // try-catch for connection                   
        try{ Connection conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
             Statement stmt = conn.createStatement();
        
            // INSERTING data into database
         stmt.execute("USE " + DB_NAME + ";" );
         stmt.executeUpdate(
                // FIND A EASIER WAY OF INSERT DATA USING JAVA
                 String.format("INSERT INTO " + TB_NAME+"(user_name,user_passWord)"
                 
                    //FIX THIS LINE...
                     + "VALUES (\"%s\",\"%s\") ;",  
                      user.getUserName(), user.getUserName()) // using the 'User' arguments to get the variable attribute
                 // in the class 'Users' and set values to be called in the main...
         );
        
         //  DISPLAYING ALL DATA CREATED FOR TESTING PORPUSES...
         
         ResultSet results=stmt.executeQuery("SELECT * FROM " + DB_NAME + "." + TB_NAME +";"); 
         while(results.next())  
            System.out.println("Username: "+results.getString(1)+", ID " + " "+results.getString(2)+", Pass "+" "+results.getString(3)); 
            conn.close();  
         
        // System.out.println(rs);
                
            return true;
        }catch(SQLException e){System.out.println("Something Went wrong in Insert method. please check the following ERROR!!!");
            e.printStackTrace(); // Will show the exact line in which the method raised the exception
        }return false ; 
     }

    
    
}
