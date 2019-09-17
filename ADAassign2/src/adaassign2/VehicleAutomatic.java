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
            
    public VehicleAutomatic(TerrainGUI gui) {
        rowCount = 0;
        this.terrain = gui;
        
        int[] colDifficulty = new int[terrain.columns];
        for (int i = 0; i < terrain.columns - 1; i++) {
            colDifficulty[i] = searchColumns(i);
            System.out.println("\nPATHED ADDED" + i);
        }

        int lowestDiffColumn = 0;
        
        for (int i = 0; i < terrain.columns - 1; i++) {
            if (colDifficulty[i] < lowestDiffColumn) {
                lowestDiffColumn = i;
            }
        }
        
        navigateColumn(lowestDiffColumn);
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
                leftNode.setParentNode(currentNode);
                leftNode.setDistance(endNode);
                leftNode.setTotalDifficulty();  
            }

            Node rightNode = null;
            if (currentNode.column < terrain.columns) {
                rightNode = new Node(currentNode.row + 1, currentNode.column + 1, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column + 1]));
                rightNode.setParentNode(currentNode);
                rightNode.setDistance(endNode);
                rightNode.setTotalDifficulty();
            }
            
            Node frontNode = new Node(currentNode.row + 1, currentNode.column, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column]));
            frontNode.setParentNode(currentNode);
            frontNode.setDistance(endNode);
            frontNode.setTotalDifficulty();
            
            if (leftNode == null && rightNode == null) {
                currentNode = frontNode;
                System.out.println("FRONT");
            } else if (leftNode == null && rightNode != null) {
                if (rightNode.compareTo(frontNode) < 0) {
                    currentNode = rightNode;
                    System.out.println("RIGHT");
                } else {
                    currentNode = frontNode;
                    System.out.println("FRONT");
                }
            } else if (leftNode != null && rightNode == null) {
                if (leftNode.compareTo(frontNode) < 0) {
                    currentNode = leftNode;
                    System.out.println("LEFT");
                } else {
                    currentNode = frontNode;
                    System.out.println("FRONT");
                }
            } else {
                if (leftNode.compareTo(frontNode) < 0) {
                    if (leftNode.compareTo(rightNode) < 0) {
                        currentNode = leftNode;
                        System.out.println("LEFT");
                    } else {
                        currentNode = rightNode;
                        System.out.println("RIGHT");
                    }
                } else {
                    currentNode = frontNode;
                    System.out.println("FRONT");
                }
            }
            
            difficultyCount += currentNode.difficulty;
            
        } //end of while loop
        
        return difficultyCount;
    }
    
    private void navigateColumn(int column) {
        rowCount = 0;
        rowCount = column;
        notifyGUI();
        
        Node startNode = new Node(0, column, Integer.parseInt(terrain.difficulty[0][column]));
        Node endNode = new Node(terrain.rows, column, Integer.parseInt(terrain.difficulty[0][column]));
        Node currentNode = startNode;
        
        while(currentNode.row != terrain.rows - 1) {
            Node leftNode = null;
            if (currentNode.column > 0) {
                leftNode = new Node(currentNode.row + 1, currentNode.column - 1, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column - 1]));
                leftNode.setParentNode(currentNode);
                leftNode.setDistance(endNode);
                leftNode.setTotalDifficulty();
            }
            
            Node rightNode = null;
            if (currentNode.column < terrain.columns) {
                rightNode = new Node(currentNode.row + 1, currentNode.row + 1, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column + 1]));
                rightNode.setParentNode(currentNode);
                rightNode.setDistance(endNode);
                rightNode.setTotalDifficulty();
            }
            
            Node frontNode = new Node(currentNode.row + 1, currentNode.column, Integer.parseInt(terrain.difficulty[currentNode.row + 1][currentNode.column]));
            frontNode.setParentNode(currentNode);
            frontNode.setDistance(endNode);
            frontNode.setTotalDifficulty();
        
            if (leftNode == null && rightNode == null) {
                currentNode = frontNode;
            } else if (leftNode == null && rightNode != null) {
                if (rightNode.compareTo(frontNode) < 0) {
                    currentNode = rightNode;
                } else currentNode = frontNode;
            } else if (leftNode != null && rightNode == null) {
                if (leftNode.compareTo(frontNode) < 0) {
                    currentNode = leftNode;
                } else currentNode = frontNode;
            } else {
                if (leftNode.compareTo(frontNode) < 0) {
                    if (leftNode.compareTo(rightNode) < 0) {
                        currentNode = leftNode;
                    } else currentNode = rightNode;
                } else currentNode = frontNode;
            }

            rowCount = currentNode.row;
            colCount = currentNode.column;
            if (rowCount <= terrain.rows) {
                notifyGUI();
            }

        } //end of while loop

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
