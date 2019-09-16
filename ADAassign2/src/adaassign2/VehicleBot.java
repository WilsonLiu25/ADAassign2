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
public class VehicleBot {
    private int row;
    private int column;
    
    private TerrainGUI terrain;
    
    public VehicleBot() {
        
    }
    
    public void notifyGUI() {
        //call gui function called update to add to list
    }
    
    public boolean left() {
        if ((row >= 0 && row < terrain.rows - 1)
                && (column > 0 && column <= terrain.rows - 1)) {
            row++;
            column--;
            notifyGUI();
            
            return true;
        } else return false;
    }
    
    public boolean forwards() {
        return false;
    }
    
    public boolean right() {
        return false;
    }
}
