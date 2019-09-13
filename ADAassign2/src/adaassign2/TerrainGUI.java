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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author will2
 */
public class TerrainGUI {
    private JFrame frame = new JFrame("Terrain GUI");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    public int rows;
    public int columns;
    private JOptionPane chooseTerrianOption = new JOptionPane();
    public String userTerrain = "";
    
    
    public TerrainGUI() {
        OptionPane(); //user picks the Terrain
        System.out.println("The User picked: " + userTerrain);
        
        Database db = new Database();
        db.determineTableSize(userTerrain);
        this.rows = db.rows;
        this.columns = db.columns;
        
        System.out.println(rows);
        
        Frame();
        MainPanel();
        
        
        frame.setVisible(true);
        
    }
    
    public void Frame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1100);
        

    }
    
    public void MainPanel(){
        
        mainPanel.setLayout(new GridLayout(rows, columns));
        
//        JButton p1 = new JButton();
//        p1.setBackground(Color.red);
//        mainPanel.add(p1);
//        JButton p2 = new JButton();
//        p2.setBackground(Color.red);
//        mainPanel.add(p2);
//        JButton p3 = new JButton("hello");
//        p3.setBackground(Color.red);
//        mainPanel.add(p3);  
//        JButton p4 = new JButton();
//        p4.setBackground(Color.red);
//        mainPanel.add(p4);
//        JButton p5 = new JButton();
//        p5.setBackground(Color.red);
//        mainPanel.add(p5);
        

        frame.add(mainPanel);
    }
    
    public void OptionPane(){
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
    
    
    
    public static void main(String[] args) {
        TerrainGUI application = new TerrainGUI();
    }
            
    
}
