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
import javax.swing.JPanel;

/**
 *
 * @author will2
 */
public class TerrainGUI {
    private JFrame frame = new JFrame("Terrain GUI");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    
    
    public TerrainGUI() {
        Frame();
        MainPanel();
        
        frame.setVisible(true);
        
    }
    
    public void Frame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1100);

    }
    
    public void MainPanel(){
        //mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setLayout(new GridLayout(5,5));
        JButton p1 = new JButton();
        p1.setBackground(Color.red);
        JButton p2 = new JButton();
        p2.setBackground(Color.red);
        mainPanel.add(p1);
        mainPanel.add(p2);
        
        

        frame.add(mainPanel);

    }
    
    public static void main(String[] args) {
        TerrainGUI application = new TerrainGUI();

    }
            
    
}
