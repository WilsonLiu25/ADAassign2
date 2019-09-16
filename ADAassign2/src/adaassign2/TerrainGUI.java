/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassign2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.*;

/**
 *
 * @author will2
 */
public class TerrainGUI {
    private JFrame frame = new JFrame("Terrain GUI");
    private JPanel mainPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    public int rows;
    public int columns;
    private String[][] difficulty;
    //private JOptionPane chooseTerrianOption = new JOptionPane();
    public String userTerrain = "";
    private DrawTerrain drawTerrainPanel = new DrawTerrain();
    private JPanel[][] squares;
    
    
    public TerrainGUI() {
        ChooseTerrainOption(); //user picks the Terrain
        System.out.println("The User picked: " + userTerrain);
        
        Database db = new Database();
        db.determineTableSize(userTerrain); //returns us the rows and columns of the selected Terrain
        db.setDifficulty(userTerrain);
        this.rows = db.rows;
        this.columns = db.columns;
        this.difficulty = db.difficulty;
        
        System.out.println("That table has: " + rows + " and " + columns + " columns");
        System.out.println("and difficulty of " + difficulty[1][1]);
        
        Frame();
        MainPanel();
        //ControlPanel();

        frame.setVisible(true);
        
    }
    
    public void Frame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1100);
        
        //frame.add(drawTerrainPanel);
    }
    
    public void MainPanel(){
        
        mainPanel.setLayout(new GridLayout(rows, columns));
        
        squares = new JPanel[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                squares[i][j] = new JPanel();
                squares[i][j].setLayout(new BorderLayout());
                squares[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                JLabel locationLabel = new JLabel("(" + i + ", " + j + ") : " + difficulty[i][j]);
                //Alligning the JLabel
                locationLabel.setHorizontalAlignment(JLabel.CENTER);
                locationLabel.setVerticalAlignment(JLabel.CENTER);
                
                squares[i][j].add(locationLabel);
                mainPanel.add(squares[i][j]);
            }
        }
        

        frame.add(mainPanel);
    }
    
    public void ChooseTerrainOption(){
        Object[] options = {"Illustrated", "Large", "Medium", "Small", "TinyA", "TinyB"};
        
        int n = JOptionPane.showOptionDialog(frame,
            "Please Select your Terrain", 
            "Choose your Terrain",
            2,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (n == 0 ) {
            System.out.println("You picked illustrated");
            userTerrain = "illustrated";
        }
        else if (n == 1) {
            System.out.println("You picked large");
            userTerrain = "large";
        }
        else if (n == 2) {
            System.out.println("You picked medium");
            userTerrain = "medium";
        }
        else if (n == 3) {
            System.out.println("You picked small");
            userTerrain = "small";
        }
        else if (n == 4) {
            System.out.println("You picked tinyA");
            userTerrain = "tinyA";
        }
        else if (n == 5) {
            System.out.println("You picked tinyB");
            userTerrain = "tinyB";
        }
        
    }
    
    public void ControlPanel(){
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBackground(Color.red);
        
        frame.add(controlPanel, BorderLayout.EAST);
        
    }
            
    public static void main(String[] args) {
        TerrainGUI application = new TerrainGUI();
    }
            
    
}
