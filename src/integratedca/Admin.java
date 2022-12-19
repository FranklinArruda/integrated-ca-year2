
package integratedca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
 
    Connection conn = null; // CONNECTION OBJECT

    static final String UPDATE = "SELECT id, first, last, age FROM Registration";
    static final String USER_LIST = "SELECT id, first, last, age FROM Registration";
    static final String REMOVE = "";
    static final String HISTORY = "";

    public Admin() {
    }

    public boolean Update(String name, String email, String password) throws ClassNotFoundException, SQLException {

        conn = DBconnection.connect();  // CONNECTION

        try {
            PreparedStatement stmt = null;
            ResultSet results;
            // conn = DB.connect();  

            String sql = "UPDATE Registration "
                    + "SET age = 30 WHERE id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeUser(String name, String email, String password)
            throws ClassNotFoundException, SQLException {

        try {
            PreparedStatement stmt = null;
            ResultSet results;

            String sql = "UPDATE Registration "
                    + "SET age = 30 WHERE id in (100, 101)";
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

    public boolean listOfUser(String name, String email, String password)
            throws ClassNotFoundException, SQLException {

        try {
            PreparedStatement stmt = null;
            ResultSet results;
            //conn = DB.connect();  

            String sql = "UPDATE Registration "
                    + "SET age = 30 WHERE id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;   //RETURN USER LIST // USER ARRAY LIST
    }

    public boolean userHistory(String name, String email, String password)
            throws ClassNotFoundException, SQLException {

        try {
            PreparedStatement stmt = null;
            ResultSet results;
            //      conn = DB.connect();  

            String sql = "UPDATE Registration "
                    + "SET age = 30 WHERE id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;   // RETURN USER HISTORY  // RETURN HISTORY TABLE USING ARRAY LIST
    }
}

