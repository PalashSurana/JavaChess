package assignment2018;

import java.util.ArrayList;
import assignment2018.codeprovided.*;
/**
 * King.java
 *
 * A subclass of Piece in which functionality of the king is represented
 *
 * @author Palash Surana
 * 
 */
public class King extends Piece {
    
    // Constructor for the King class
    public King (int ix, int iy, int c, Board b) {
        super(PieceCode.KING, ix, iy, c, b);
    }
    
    // Implementation of an abstract method that returns a list of available moves for a piece
    public ArrayList<Move> availableMoves() {
        if (getColour()==PieceCode.WHITE) 
        	    return whiteKing();
        else 
        	    return blackKing();
    }
    
    // Method which takes in the directions and coordinates, and adds the move in the corresponding direction
    private void kingMove1(int xFrom, int yFrom, int xTo, int yTo, Move theMove, ArrayList<Move> pieceMoves) {
    	    if (getBoard().outOfRange(xTo, yTo))
            pieceMoves.add(null);
        	else {
	    	    if (!getBoard().occupied(xTo, yTo)) 
	    	    {
	            theMove = new Move(this, xFrom,yFrom, xTo,yTo, false);
	            pieceMoves.add(theMove);
	        }
	        if (getBoard().occupied(xTo, yTo) && (getBoard().getPiece(xTo, yTo).getColour() != this.getColour())) 
	        {
	        	    theMove = new Move(this, xFrom,yFrom, xTo,yTo, true);
	            pieceMoves.add(theMove);  
	        }
        	}
    }
    
    // Method that returns list of legal moves for a white King
    private ArrayList<Move> whiteKing() {
        int x = getX();
        int y = getY();

        ArrayList<Move> wMoves = new ArrayList<Move>();
        Move m = null;

        //First set of legal moves - forward, if cell is/isn't occupied
        	kingMove1(x,y,x,y+1,m,wMoves);  
        
        //Second set of legal moves - forward right, if cell is/isn't occupied
        	kingMove1(x,y,x+1,y+1,m,wMoves);
                
        //Third set of legal moves - right, if cell is/isn't occupied
        kingMove1(x,y,x+1,y,m,wMoves);
                
        //Fourth set of legal moves - backward and right, if cell is/isn't occupied
        kingMove1(x,y,x+1,y-1,m,wMoves);
        
        //Fifth set of legal moves - backward, if cell is/isn't occupied
        kingMove1(x,y,x,y-1,m,wMoves);
        
        //Sixth set of legal moves - backward and left, if cell is/isn't occupied
        kingMove1(x,y,x-1,y-1,m,wMoves);
        
        //Seventh set of legal moves - left, if cell is/isn't occupied
        kingMove1(x,y,x-1,y,m,wMoves);
        
        //Eighth set of legal moves - forward and left, if cell is/isn't occupied
        kingMove1(x,y,x-1,y+1,m,wMoves);
        
        return wMoves;
    }

    // Method that returns list of legal moves for a black king
    private ArrayList<Move> blackKing() {
        int x = getX();
        int y = getY();

        ArrayList<Move> bMoves = new ArrayList<Move>();     
        Move m = null;

        //First set of legal moves - backward, if cell is/isn't occupied
        kingMove1(x,y,x,y+1,m,bMoves);
        
        //Second set of legal moves - backward left, if cell is/isn't occupied
        kingMove1(x,y,x+1,y+1,m,bMoves);
        
        //Third set of legal moves - left, if cell is/isn't occupied
        kingMove1(x,y,x+1,y,m,bMoves);
        
        //Fourth set of legal moves - forward and left, if cell is/isn't occupied
        kingMove1(x,y,x+1,y-1,m,bMoves);
        
        //Fifth set of legal moves - forward, if cell is/isn't occupied
        kingMove1(x,y,x,y-1,m,bMoves);
        
        //Sixth set of legal moves - forward and right, if cell is/isn't occupied
        kingMove1(x,y,x-1,y-1,m,bMoves);
        
        //Seventh set of legal moves - right, if cell is/isn't occupied
        kingMove1(x,y,x-1,y,m,bMoves);
        
        //Eighth set of legal moves - backward and right, if cell is/isn't occupied
        kingMove1(x,y,x-1,y+1,m,bMoves);
        
        return bMoves;
    } 
}
