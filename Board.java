package assignment2018;

import assignment2018.codeprovided.*;

/**
 * Board.java
 *
 * Represents the formation of the board
 *
 * @author Palash Surana
 * 
 */
public class Board {

	  private static final int boardSize=8;
	  private static final int EMPTY=0;

	  
	  private Piece[][] boardData;

	  public Board () {
	    boardData = new Piece[boardSize][boardSize];
	    for (int i=0; i<boardSize; i++)
	      for (int j=0; j<boardSize; j++) {
	         boardData[i][j] = null;
	      }
	  }

	  // Method that returns true if a particular location is occupied
	  public boolean occupied(int i, int j) {
	    return (boardData[i][j]!=null);
	  }

	  // method that returns true if a partcular location is out of range
	  public boolean outOfRange(int i, int j) {
	    return (i<0) || (i>=boardSize)
	           || (j<0) || (j>=boardSize);
	  }

	  // method to remove a piece from a particular location
	  public void remove(int i, int j) {
	    boardData[i][j] = null;
	  }

	  // method to place a piece at a particular location
	  public void setPosition(int i, int j, Piece p) {
	    boardData[i][j] = p;
	  }

	  // method to return the chess piece at a particular location
	  public Piece getPiece(int x, int y) {
	    return boardData[x][y];
	  }

	  // method to return the array of chess pieces on the board
	  public Piece[][] getData() {
	    return boardData;
	  }
	}