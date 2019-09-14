/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassign2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author will2
 */
public class DrawTerrain extends JPanel {
    
    DrawTerrain(){
        
    }
    
//    public void fireupScreen(){
//        JFrame frame = new JFrame();
//        
//        frame.setSize(600,600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(this);
//        frame.setVisible(true);
//    }
    
    public void paintComponent (Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 200, 1000);
    }
    
//    public static void main(String[] args) {
//        DrawTerrain test = new DrawTerrain();
//        test.fireupScreen();
//    }
    
}
