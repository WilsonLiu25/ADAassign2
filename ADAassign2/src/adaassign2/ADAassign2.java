/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassign2;

/**
 *
 * @author will2
 */
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ADAassign2 {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://raptor2:3306/terrains";

    public static void main(String[] args) {
        // obtain user name and password from keyboard
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Please enter user name:");
        
        String userName = keyboardScanner.nextLine();
        System.out.print("Please enter password:");
        
        String password = keyboardScanner.nextLine();
        
        try
        {  
           Class.forName(DRIVER); // load the database driver for MySQL
           System.out.println("Trying to open connection to raptor2");
           Connection con = DriverManager.getConnection(DB_URL, userName, password);
           Statement stmt = con.createStatement();
           // obtain result set holding names of current databases
           System.out.println("Executing SQL statement");
           String command = "..."; // SQL command
           ResultSet rs = stmt.executeQuery(command);
           while (rs.next())
           {  // database name string is in first column of result set
              System.out.println(rs.getString(1));
           }
           System.out.println("Closing connection to raptor2");
           stmt.close();
           con.close();
        }
        catch (SQLException e)
        {  System.out.println("SQL Exception:" + e);
        }
        catch (ClassNotFoundException e)
        {  System.out.println("ClassNotFoundException:" + e);
        }

    }
    
}
