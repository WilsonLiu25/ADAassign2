/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassign2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author will2
 */
public class Database {
    public int rows;
    public int columns;
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://raptor2:3306/terrains";
    private String userName = "student";
    private String password = "fpn871";
    public String[][] difficulty;
            
    public void determineTableSize(String tableSelection){
        if (tableSelection.equals("illustrated")) {
            rows = 5;
            columns = 5;
        } 
        else if (tableSelection.equals("large")) {
            rows = 40;
            columns = 50;
        }
        else if (tableSelection.equals("medium")) {
            rows = 20;
            columns = 30;
        }
        else if (tableSelection.equals("small")) {
            rows = 10;
            columns = 10;
        }
        else if (tableSelection.equals("tinyA")) {
            rows = 7;
            columns = 4;
        }
        else if (tableSelection.equals("tinyB")) {
            rows = 3;
            columns = 3;
        }
    }
    
    public void setDifficulty(String tableSelection){
        determineTableSize(tableSelection);
        
        try {
            Class.forName(DRIVER);
            System.out.println("test trying to open connection");
            Connection connection = DriverManager.getConnection(DB_URL, userName, password);
            Statement statement = connection.createStatement();
            System.out.println("Executing SQL statement");
            String command = "SELECT * FROM " + tableSelection;
            ResultSet rs = statement.executeQuery(command);
            System.out.println("this works until here");
            
            difficulty = new String[rows][columns];
            int rowCount = 0, colCount = 0;
            while(rs.next()) {
                difficulty[rowCount][colCount] = rs.getString(3);

                if (colCount == columns - 1) {
                    colCount = 0;
                    rowCount++;
                } else {
                    colCount++;
                }
            }
            System.out.println("Finished looping. CLosing connection to raptor2");
                
        }catch (SQLException e) {
            System.out.println("SQL Exception:" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException:" + e);
        }
        
    }
    
    public static void main(String[] args) {
        Database db = new Database();
        db.setDifficulty("tinyB");
        
        int count = 1;
        for (String row[] : db.difficulty) {
            for (String diff : row) {
                System.out.println("1st difficulty " + count + " : " + diff);
                count++;
            }
        }
        System.out.println(db.difficulty[1][1]); //should be 7
    }
            
}
