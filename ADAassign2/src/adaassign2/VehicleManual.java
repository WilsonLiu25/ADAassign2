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
public class VehicleManual {
    private int rowCount = 0;
    private int colCount = 0;
    
    private TerrainGUI terrain; //GUI
    
    public VehicleManual(TerrainGUI terrain) {
        this.terrain = terrain;
    }
    
    public void setColCount(int colCount){
        this.colCount = colCount;
    }

    public void left() {
        if ((rowCount >= 0 && rowCount < terrain.rows - 1)
                && (colCount > 0 && colCount <= terrain.columns - 1)) {
            rowCount++;
            colCount--;
            notifyGUI();
        }
    }
    
    public void forwards() {
        if (rowCount >= 0 && rowCount < terrain.rows - 1) {
            rowCount++;
            notifyGUI();
        } 
    }
    
    public void right() {
        if ((rowCount >= 0 && rowCount < terrain.rows - 1)
                && (colCount >= 0 && colCount < terrain.columns - 1)) {
            rowCount++;
            colCount++;
            notifyGUI();
        }
    }
    
    public void notifyGUI() {
        terrain.updateVehiclePosition(rowCount, colCount);
    }
}
