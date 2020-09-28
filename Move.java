package assignment2018;

import assignment2018.codeprovided.*;
/**
 * Move.java
 *
 * This class represents a move for a piece and uses various functions to form the base
 *
 * @author Palash Surana
 * 
 */
public class Move {
    
    // Initialization of variables
    private Piece piece;
    private int x,y;
    private boolean isOccupied;
    private int xGoal, yGoal;
    
    // Constructor for the Move class
    public Move(Piece fig, int xInit, int yInit, int xNew, int yNew, boolean occ){
        piece = fig;
        x = xInit;
        y = yInit;
        xGoal = xNew;
        yGoal = yNew;
        isOccupied = occ;
    }
    
    // Method that returns true if the cell is occupied by some other piece
    public boolean isTaken(){
    		return isOccupied;
    	}
    
    // Methods that return the initial coordinates before the move
    public int getInitialX(){
    		return x;
    	}
    public int getInitialY(){
    		return y;
    	}
    
    // Methods that return the final coordinates after the move
    public int getTargetX(){
    		return xGoal;
    	}
    public int getTargetY(){
    		return yGoal;
    	} 
    
    public String toString(){
        return "Piece moved from:"+x+"|"+y+", to:"+xGoal+"|"+yGoal+" .";
    }
    
}