package assignment2018;

import java.util.ArrayList;

import assignment2018.codeprovided.*;

/**
 * Rook.java
 *
 * A subclass of Piece in which functionality of the rook is represented
 *
 * @author Palash Surana
 * 
 */

public class Rook extends Piece {
    public int flag=0;
    public Rook (int ix, int iy, int c, Board b) {
        super(PieceCode.ROOK, ix, iy, c, b);
    }

    // Method implements abstract method in Piece class and gets all available moves
    public ArrayList<Move> availableMoves() {
        if (getColour()==PieceCode.WHITE) {
        	    return whiteRook();
        }
        else {
        	    return blackRook();
        }
    }
   
    // Method which takes in the directions and coordinates, and adds the move in the corresponding direction
    private void moveRook(int x1, int y1, Move theMove, ArrayList<Move> pieceMoves, char movemt)
    {
    		int i1=0;
    		int i2=0;
    		for(int i=1; i<=7; i++) {
    			if (movemt == 'b' || movemt == 'f') {
    				i1 = i;
    				if (movemt == 'b')
    					i1=-i1;
    			}
    			else if (movemt == 'l' || movemt == 'r'){
    				i2 = i;
    				if(movemt == 'r')
    					i2=-i2;
    			}
    				
		    if (getBoard().outOfRange(x1+i2,y1+i1))
		   		break;       
		    else {
		    		if (getBoard().occupied(x1+i2,y1+i1)&&(getBoard().getPiece(x1+i2,y1+i1).getColour()==this.getColour()))
		   	    	    break;
		    		else if (!getBoard().occupied(x1+i2,y1+i1)) {
		            theMove = new Move(this, x1,y1, x1+i2,y1+i1, false);
		            pieceMoves.add(theMove);
		    	     }
		    	     else if (getBoard().occupied(x1+i2,y1+i1)&&(getBoard().getPiece(x1+i2,y1+i1).getColour()!=this.getColour())) {
		            theMove = new Move(this, x1,y1, x1+i2,y1+i1, true);
		            pieceMoves.add(theMove);
		            break;
		    	     }
		     }
    		}
    }
    // Method that returns list of legal moves for a white Rook
    private ArrayList<Move> whiteRook() {
        int x = getX();
        int y = getY();
        char movement;
        
        ArrayList<Move> wMoves = new ArrayList<Move>();
        Move m = null;

        // First set of legal moves - from x,y, to x,y+i, if cell is/isn't occupied - forward
        movement = 'f';
  	    moveRook(x,y,m,wMoves,movement);
        
        // Second set of legal moves - from x,y, to x+i,y, if cell is/isn't occupied - left
  	    movement = 'l';
 	    moveRook(x,y,m,wMoves,movement);
         
        // Third set of legal moves - from x,y, to x,y-i, if cell is/isn't occupied - backward
        movement = 'b';
   	    moveRook(x,y,m,wMoves,movement);
   	    
        // Fourth set of legal moves - from x,y, to x-i,y, if cell is/isn't occupied - right
   	    movement = 'r';
	    moveRook(x,y,m,wMoves,movement);
	    
        // Adds a null move if list is empty
        if (wMoves.isEmpty()) wMoves.add(null);
        return wMoves;
    }
  
    // Method to return list of legal moves for a black Rook
    private ArrayList<Move> blackRook() {
        int x = getX();
        int y = getY();
        char movement;
        
        ArrayList<Move> bMoves = new ArrayList<Move>();
        Move m = null;

        // First set of legal moves - from x,y, to x,y-i, if cell is/isn't occupied - forward
        movement = 'b';
   	    moveRook(x,y,m,bMoves,movement);
   	    
        // Second set of legal moves - from x,y, to x,y+i, if cell is/isn't occupied - backward
   	    movement = 'f';
	    moveRook(x,y,m,bMoves,movement);
	    
        // Third set of legal moves - from x,y, to x+i,y, if cell is/isn't occupied - right
	    movement = 'l';
   	    moveRook(x,y,m,bMoves,movement);
   	    
        // Fourth set of legal moves - from x,y, to x-i,y, if cell is/isn't occupied - left
   	    movement = 'r';
	    moveRook(x,y,m,bMoves,movement);
	    
        // Adds a null move if list is empty.
        if (bMoves.isEmpty()) bMoves.add(null);
        return bMoves;
    }
}
