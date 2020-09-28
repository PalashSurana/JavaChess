package assignment2018;

import java.util.ArrayList;

import assignment2018.codeprovided.*;
/**
 * Knight.java
 *
 * A subclass of Piece in which functionality of the knight is represented
 *
 * @author Palash Surana
 * 
 */
public class Knight extends Piece {

    public Knight (int ix, int iy, int c, Board b) {
        super(PieceCode.KNIGHT, ix, iy, c, b);
    }

    // Method implements abstract method in Piece class and gets available moves
    public ArrayList<Move> availableMoves() {
    		if (getColour()==PieceCode.WHITE) {
    			return whiteKnight();
    		}
    		else {
    			return blackKnight();
    		}
    }
   
    // Method which takes in the directions and coordinates, and adds the move in the corresponding direction
    private void moveKnight(int xFrom, int yFrom, int xTo, int yTo, Move theMove, ArrayList<Move> pieceMoves) {
	    	if (getBoard().outOfRange(xTo, yTo))
	            pieceMoves.add(null);
	    else {
	    		if (!getBoard().occupied(xTo, yTo)) {
	    			theMove = new Move(this, xFrom,yFrom, xTo,yTo, false);
	    			pieceMoves.add(theMove);
	            	}
	        if (getBoard().occupied(xTo, yTo)&&(getBoard().getPiece(xTo, yTo).getColour()!=this.getColour())) {
	        		theMove = new Move(this, xFrom, yFrom, xTo, yTo, true);
	        		pieceMoves.add(theMove);
	            	}
	     }
    }    
    // Method that returns list of legal moves for a white Knight
    private ArrayList<Move> whiteKnight() {
        int x = getX();
        int y = getY();

        ArrayList<Move> wMoves = new ArrayList<Move>();
        Move m = null;

        //First set of legal moves - from x,y, to x+1,y+2, if cell is/isn't occupied - forward 2 left 1
        moveKnight(x,y,x+1,y+2,m,wMoves);
        
        //Second set of legal moves - from x,y, to x+2,y+1, if cell is/isn't occupied - forward 1 left 2
        moveKnight(x,y,x+2,y+1,m,wMoves);
        
        //Third set of legal moves - from x,y, to x+2,y-1, if cell is/isn't occupied - backward 1 left 2
        moveKnight(x,y,x+2,y-1,m,wMoves);
        
        //Fourth set of legal moves - from x,y, to x+1,y-2, if cell is/isn't occupied - backward 2 left 1
        moveKnight(x,y,x+1,y-2,m,wMoves);
        
        //Fifth set of legal moves - from x,y, to x-1,y-2, if cell is/isn't occupied - backward 2 right 1
        moveKnight(x,y,x-1,y-2,m,wMoves);
        
        //Sixth set of legal moves - from x,y, to x-2,y-1, if cell is/isn't occupied - backward 1 right 2
        moveKnight(x,y,x-2,y-1,m,wMoves);  
        
        //Seventh set of legal moves - from x,y, to x-2,y+1, if cell is/isn't occupied - forward 1 right 2
        moveKnight(x,y,x-2,y+1,m,wMoves);
        
        //Eighth set of legal moves - from x,y, to x-1,y+2, if cell is/isn't occupied - forward 2 right 1
        moveKnight(x,y,x-1,y+2,m,wMoves);
        
        return wMoves;
    }

    // Method that returns list of legal moves for a black Knight
    private ArrayList<Move> blackKnight() {
        int x = getX();
        int y = getY();

        ArrayList<Move> bMoves = new ArrayList<Move>();
        Move m = null;

        //First set of legal moves - from x,y, to x+1,y+2, if cell is/isn't occupied - backward 2 right 1
        moveKnight(x,y,x+1,y+2,m,bMoves);
        
        //Second set of legal moves - from x,y, to x+2,y+1, if cell is/isn't occupied - backward 1 right 2
        moveKnight(x,y,x+2,y+1,m,bMoves);
        
        //Third set of legal moves - from x,y, to x+2,y-1, if cell is/isn't occupied - forward 1 right 2
        moveKnight(x,y,x+2,y-1,m,bMoves);
        
        //Fourth set of legal moves - from x,y, to x+1,y-2, if cell is/isn't occupied - forward 2 right 1
        moveKnight(x,y,x+1,y-2,m,bMoves);
        
        //Fifth set of legal moves - from x,y, to x-1,y-2, if cell is/isn't occupied - forward 2 left 1
        moveKnight(x,y,x-1,y-2,m,bMoves);
        
        //Sixth set of legal moves - from x,y, to x-2,y-1, if cell is/isn't occupied - forward 1 left 2
        moveKnight(x,y,x-2,y-1,m,bMoves);  
        
        //Seventh set of legal moves - from x,y, to x-2,y+1, if cell is/isn't occupied - backward 1 left 2
        moveKnight(x,y,x-2,y+1,m,bMoves);
        
        //Eighth set of legal moves - from x,y, to x-1,y+2, if cell is/isn't occupied - backward 2 left 1
        moveKnight(x,y,x-1,y+2,m,bMoves);
        
        return bMoves;
    }
}
