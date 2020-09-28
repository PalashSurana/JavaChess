package assignment2018;

import java.util.ArrayList;

import assignment2018.codeprovided.*;
/**
 * Queen.java
 *
 * A subclass of Piece in which functionality of the queen is represented
 *
 * @author Palash Surana
 * 
 */
public class Queen extends Piece {

  public Queen (int ix, int iy, int c, Board b) {
    super(PieceCode.QUEEN, ix, iy, c, b);
  }

  // Method implements abstract method in Piece class and gets its available moves
  public ArrayList<Move> availableMoves() {
	  if (getColour()==PieceCode.WHITE) {
		  return whiteQueen();
	  }
	  else {
		  return blackQueen();
	  }
  }
  // Method which takes in the directions and coordinates, and adds the move in the corresponding direction
  private void moveQueen(int x1, int y1, Move theMove, ArrayList<Move> pieceMoves, char movemt)
  {
	  int i1=0;
    	  int i2=0;
    	  for(int i=1; i<=7; i++) {
    		  i1=i;
    		  i2=i;
    		  if (movemt == 'f' || movemt == 'b') {
    			  i1=0;
    			  if(movemt == 'b')
    				  i2=-i2;
    		  }
    		  else if (movemt == 'l' || movemt == 'r') {
    			  i2=0;
    			  if(movemt == 'r')
    				  i1=-i1;
    		  }
    		  else if (movemt == 'L')
    			  i2=-i2;
    		  else if (movemt == 'R')
    			  i1=-i1;
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
  // Method that returns list of legal moves for a white Queen
  private ArrayList<Move> whiteQueen() {
    int x = getX();
    int y = getY();
    char movement;

    ArrayList<Move> wMoves = new ArrayList<Move>();
    Move m = null;
    
   // First set of legal moves - from x,y, to x,y+i, if cell is/isn't occupied - forward
    movement = 'f';
    moveQueen(x,y,m,wMoves,movement);
    
    // Second set of legal moves - from x,y, to x+i,y, if cell is/isn't occupied - left
    movement = 'l';
    moveQueen(x,y,m,wMoves,movement);
    
    // Third set of legal moves - from x,y, to x,y-i, if cell is/isn't occupied - backward
    movement = 'b';
    moveQueen(x,y,m,wMoves,movement);
    
    //Fourth set of legal moves - from x,y, to x-i,y, if cell is/isn't occupied - right
    movement = 'r';
    moveQueen(x,y,m,wMoves,movement);
    
    //Fifth set of legal moves - from x,y, to x+i,y+i, if cell is/isn't occupied - forward & left
    movement = 'F';
    moveQueen(x,y,m,wMoves,movement);
    
    //Sixth set of legal moves - from x,y, to x+i,y-i, if cell is/isn't occupied - backward & left
    movement = 'L';
    moveQueen(x,y,m,wMoves,movement);
    
    //Seventh set of legal moves - from x,y, to x-i,y-i, if cell is/isn't occupied - backward & right
    movement = 'B';
    moveQueen(x,y,m,wMoves,movement);
    
    //Eighth set of legal moves - from x,y, to x-i,y+i, if cell is/isn't occupied - forward & right
    movement = 'R';
    moveQueen(x,y,m,wMoves,movement);
    
    //Adds a null move if list is empty
    if (wMoves.isEmpty()) wMoves.add(null);
    return wMoves;
  }
  
  // Method that returns list of legal moves for a black Queen
  private ArrayList<Move> blackQueen() {
    int x = getX();
    int y = getY();
    char movement;
    
    ArrayList<Move> bMoves = new ArrayList<Move>();
    Move m = null;
    
    //First set of legal moves - from x,y, to x,y+i, if cell is/isn't occupied - backward
    movement = 'f';
    moveQueen(x,y,m,bMoves,movement);
    
    //Second set of legal moves - from x,y, to x+i,y, if cell is/isn't occupied - right
    movement = 'l';
    moveQueen(x,y,m,bMoves,movement);
    
    //Third set of legal moves - from x,y, to x,y-i, if cell is/isn't occupied - forward
    movement = 'b';
    moveQueen(x,y,m,bMoves,movement);
    
    //Fourth set of legal moves - from x,y, to x-i,y, if cell is/isn't occupied - left
    movement = 'r';
    moveQueen(x,y,m,bMoves,movement);
    
    //Fifth set of legal moves - from x,y, to x+i,y+i, if cell is/isn't occupied - backward & right
    movement = 'F';
    moveQueen(x,y,m,bMoves,movement);
    
    //Sixth set of legal moves - from x,y, to x+i,y-i, if cell is/isn't occupied - forward & right
    movement = 'L';
    moveQueen(x,y,m,bMoves,movement);
    
    //Seventh set of legal moves - from x,y, to x-i,y-i, if cell is/isn't occupied - forward & left
    movement = 'B';
    moveQueen(x,y,m,bMoves,movement);
    
    //Eighth set of legal moves - from x,y, to x-i,y+i, if cell is/isn't occupied - backward & left
    movement = 'R';
    moveQueen(x,y,m,bMoves,movement);
    
    //Adds a null move if list is empty
    if (bMoves.isEmpty()) bMoves.add(null);
    return bMoves;
  }
}