/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassign2;

import javafx.scene.Node;

/**
 *
 * @author will2
 */
public class VehicleAutomatic {
    private int rowCount;
    private int colCount;
    
    private TerrainGUI terrain;
            
    public VehicleAutomatic() {
        
    }
    
    class Node<E> implements Comparable<Node> {
        private Node parent;
        private int row;
        private int column;
        private int distance;
        private int difficulty;
        private int totalDifficulty;
        
        public Node(int row, int column, int diffuculty) {
            this.row = row;
            this.column = column;
            this.difficulty = difficulty;
        }
        
        public void setDistance(Node target) {
            int distRow = target.row - row;
            int distCol = target.column - column;
            
            distance = distRow + distCol;
        }
        
        public void setTotalDifficulty() {
            totalDifficulty = distance + difficulty;
        }
        
        public void setParentNode(Node parent) {
            this.parent = parent;
        }
        
        public boolean isEqual(Node a, Node b) {
            if (a.row == b.row && a.column == b.column) {
                return true;
            } else {
                return false;
            }
        }
        
        @Override
        public int compareTo(Node c) {
            return this.totalDifficulty - c.totalDifficulty;
        }
    }    

    public int searchColumns(int column) {
        Node startNode = new Node(0, column, Integer.parseInt(terrain.difficulty[0][column]));
        
        Node endNode = new Node(terrain.rows, column, Integer.parseInt(terrain.difficulty[0][column]));
        
        Node currentNode = startNode;
        int difficultyCount = startNode.difficulty;
        
        System.out.println("TERRAIN: " + terrain.rows);
        
        while(currentNode.row != terrain.rows -1) {
            Node leftNode = null;
            System.out.println("COLUMN: " + currentNode.column + " ROW: " + currentNode.row);
            
            if (currentNode.column > 0) {
                leftNode = new Node(currentNode.row + 1, currentNode.column - 1, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column - 1]));
                
            }
            
            leftNode.setParentNode(currentNode);
            leftNode.setDistance(endNode);
            
        }
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    

    public void setRowCount(int row) {
        this.rowCount = row;
    }
    
    public void setColCount(int column) {
        this.colCount = column;
    }
    
    public void notifyGUI() {
        terrain.updateVehiclePosition(rowCount, colCount);
    }
    
}
