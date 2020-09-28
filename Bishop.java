package assignment2018;

import java.util.ArrayList;

import assignment2018.codeprovided.*;
/**
 * Bishop.java
 *
 * A subclass of Piece in which functionality of the bishop is represented
 *
 * @author Palash Surana
 * 
 */

public class Bishop extends Piece {

    // Constructor for the Bishop class
    public Bishop (int ix, int iy, int c, Board b) {
        super(PieceCode.BISHOP, ix, iy, c, b);
    }

    // Abstract method that returns a list of available moves
    public ArrayList<Move> availableMoves() {
        if (getColour()==PieceCode.WHITE) {
        		return whiteBishop();
        }
        else {
        		return blackBishop();
        }
    }
    
    // Method which takes in the directions and coordinates, and adds the move in the corresponding direction
    private void moveBishop(int x1, int y1, Move theMove, ArrayList<Move> pieceMoves, char movemt)
    {
      	int i1=0;
      	int i2=0;
      	for(int i=1; i<=7; i++) {
      		i1=i;
      		i2=i;
      		if (movemt == 'F')
      			i1=-i1;
      		else if (movemt == 'b')
      			i2=-i2;
      		else if (movemt == 'B') {
      			i1=-i1;
      			i2=-i2;
      		}
      					
    		    if (getBoard().outOfRange(x1+i1,y1+i2))
    		   		break;       
    		    else {
    		    		if (getBoard().occupied(x1+i1,y1+i2)&&(getBoard().getPiece(x1+i1,y1+i2).getColour()==this.getColour()))
    		   	    	    break;
    		    		else if (!getBoard().occupied(x1+i1,y1+i2)) {
    		            theMove = new Move(this, x1,y1, x1+i1,y1+i2, false);
    		            pieceMoves.add(theMove);
    		    	     }
    		    	     else if (getBoard().occupied(x1+i1,y1+i2)&&(getBoard().getPiece(x1+i1,y1+i2).getColour()!=this.getColour())) {
    		            theMove = new Move(this, x1,y1, x1+i1,y1+i2, true);
    		            pieceMoves.add(theMove);
    		            break;
    		    	     }
    		     }
        	}
    }    
    // Method that returns legal moves for a white bishop
    private ArrayList<Move> whiteBishop() {
        int x = getX();
        int y = getY();
        char movement;
        
        // Creation of a new list to store legal moves
        ArrayList<Move> wMoves = new ArrayList<Move>();
        
        Move m = null;
        
        // First set of legal moves - from x,y, to x+i,y+i, if cell is/isn't occupied - forward & left
        movement = 'f';
        moveBishop(x,y,m,wMoves,movement);
        
        // Second set of legal moves - from x,y, to x+i,y-i, if cell is/isn't occupied - backward & left
        movement = 'b';
        moveBishop(x,y,m,wMoves,movement);
        
        // Third set of legal moves - from x,y, to x-i,y-i, if cell is/isn't occupied - backward & right
        movement = 'B';
        moveBishop(x,y,m,wMoves,movement);
        
        // Fourth set of legal moves - from x,y, to x-i,y+i, if cell is/isn't occupied - forward & right
        movement = 'F';
        moveBishop(x,y,m,wMoves,movement);
        
        // Adds a null move if list is empty.
        if (wMoves.isEmpty()) wMoves.add(null);
        return wMoves;
    }

    // Method that returns list of legal moves for a black Bishop
    private ArrayList<Move> blackBishop() {
        int x = getX();
        int y = getY();
        char movement;
        
        ArrayList<Move> bMoves = new ArrayList<Move>();

        Move m = null;

        // First set of legal moves - from x,y, to x+i,y+i, if cell is/isn't occupied - backward & right
        movement = 'f';
        moveBishop(x,y,m,bMoves,movement);
        
        // Second set of legal moves - from x,y, to x+i,y-i, if cell is/isn't occupied - forward & right
        movement = 'b';
        moveBishop(x,y,m,bMoves,movement);
        
        // Third set of legal moves - from x,y, to x-i,y-i, if cell is/isn't occupied - forward & left
        movement = 'B';
        moveBishop(x,y,m,bMoves,movement);
        
        // Fourth set of legal moves - from x,y, to x-i,y+i, if cell is/isn't occupied - backward & left
        movement = 'F';
        moveBishop(x,y,m,bMoves,movement);
          
        // Adds a null move if list is empty
        if (bMoves.isEmpty()) bMoves.add(null);
        return bMoves;
    }
}