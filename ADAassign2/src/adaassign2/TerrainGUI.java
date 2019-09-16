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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String mode;
    private JButton moveLeft;
    private JButton moveRight;
    private JButton moveForward;
    private DefaultListModel<String> pathTraveled = new DefaultListModel<>();
    private JList pathList;
    
    public TerrainGUI() {
        ChooseTerrainOption(); //user picks the Terrain
        System.out.println("The User picked: " + userTerrain);
        Database db = new Database();
        db.determineTableSize(userTerrain); //sets the rows and columns of the selected Terrain
        db.setDifficulty(userTerrain);
        this.rows = db.rows;
        this.columns = db.columns;
        this.difficulty = db.difficulty;
        System.out.println("That table has: " + rows + " and " + columns + " columns");
        ChooseModeOption();
        System.out.println("The User picked: " + mode + " mode");
        ChooseStartingPoint();
        
        Frame();
        MainPanel();
        ControlPanel();


        frame.setVisible(true);
    }
    
    public void Frame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setLayout(new BorderLayout());
        
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
                
                JLabel locationLabel = new JLabel("(" + i + ", " + j + ") Difficulty : " + difficulty[i][j]);
                //Alligning the JLabel
                locationLabel.setHorizontalAlignment(JLabel.CENTER);
                locationLabel.setVerticalAlignment(JLabel.CENTER);
                
                squares[i][j].add(locationLabel);
                mainPanel.add(squares[i][j]);
            }
        }
        
        frame.add(mainPanel, BorderLayout.CENTER);
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
            userTerrain = "illustrated";
        }
        else if (n == 1) {
            userTerrain = "large";
        }
        else if (n == 2) {
            userTerrain = "medium";
        }
        else if (n == 3) {
            userTerrain = "small";
        }
        else if (n == 4) {
            userTerrain = "tinyA";
        }
        else if (n == 5) {
            userTerrain = "tinyB";
        }
        
    }
    
    public void ChooseModeOption() {
        Object[] options = {"Manual Mode" , "Automatic Mode"};
        
        int n = JOptionPane.showOptionDialog(frame,
            "Please Select your Preferred Mode", 
            "Please Select your Preferred Mode",
            2,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (n == 0) {
            mode = "Manual";
        } else if(n == 1){
            mode = "Automated";
        }
    }   
    
    public void ChooseStartingPoint() {
        Integer[] columnsArray = new Integer[columns];
        for (int i = 0; i < columns; i++) {
            columnsArray[i] = i;
        }
        
        int n = (int)JOptionPane.showInputDialog(
            frame,
            "Please select a starting column:",
            "Starting Column Select",
            JOptionPane.QUESTION_MESSAGE,
            null,
            columnsArray,
            columnsArray[0]
        );
        
        for (int i = 0; i < columns; i++) {
            if (i == n) {
                pathTraveled.addElement(
                    "[" +
                    "0" +
                    ", " +
                    n +
                    "]"
                );
            }
        }
    }
    
    public void ControlPanel(){
        if (mode.equals("Manual")) {
            //controlPanel.setLayout(new BorderLayout());
            controlPanel.setBackground(Color.decode("#CD853F"));
            controlPanel.setPreferredSize(new Dimension(200, controlPanel.getPreferredSize().height));

            moveLeft = new JButton("Move Left");

            moveRight = new JButton("Move Right");

            moveForward = new JButton("Move Forward");
            
            //pathTraveled = new DefaultListModel<>();
            pathList = new JList(pathTraveled);
            JScrollPane scroll = new JScrollPane(pathList);
            scroll.setPreferredSize(new Dimension(175, 750));
            
            controlPanel.add(new JLabel("Manual Movement Buttons"));
            controlPanel.add(moveForward);
            controlPanel.add(moveLeft);
            controlPanel.add(moveRight);
            controlPanel.add(new JLabel("VehicleBot Path History"));
            controlPanel.add(scroll);
            frame.add(controlPanel, BorderLayout.EAST);
            
        } else if (mode.equals("Automated")) {
            
        }
        
    }
    
    class ButtonListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == leftButton) {
                vehicle.left();
            } else if (e.getSource() == forwardButton) {
                vehicle.forwards();
            } else if (e.getSource() == rightButton) {
                vehicle.right();
            }
        }
    }
            
    public static void main(String[] args) {
        TerrainGUI application = new TerrainGUI();
    }
            
    
}
