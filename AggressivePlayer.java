package assignment2018;

import java.util.*;
import javax.swing.*;

import assignment2018.codeprovided.*;

/**
 * 
 * AggressivePlayer.java
 *
 * A subclass of Player in which a computerized opponent can calculate 
 * the most valuable piece and take it
 *
 * @author Palash Surana
 * 
 */

public class AggressivePlayer extends Player {
	
	/* Declaration of variables:
	 * moveList - contains a list of all possible moves in a turn
	 * highestVal - hold the highest value of the highest valued piece
	 * moveWithVal - holds the index of the moves of the highest valued piece
	 * xInit,yInit - initial coordinates.
	 * xGoal,yGoal - coordinates where the piece moves to
	 * pieceTaken - holds true if piece is taken by opponent
	 */
	private ArrayList<Move> moveList = new ArrayList<Move>();
	private int highestVal = 0;
	private int moveWithVal;
	private int xInit;
	private int yInit;
	private int xGoal;
	private int yGoal;
	private boolean pieceTaken;
	public boolean moveOK = false;
	
	
	// Constructor for AggressivePlayer.
	public AggressivePlayer(String name, Pieces p, Board b){
		super(name, p, b, null);
	}
	
	// Method that creates a list of all moves which are not null and stores them in movesList
	public boolean makeMove() {
		for(int i=0;i<getPieces().getNumPieces();++i) {
			for(Move theMove : getPieces().getPiece(i).availableMoves()) {
				if (!(theMove == null))
					moveList.add(theMove);
				else
					continue;
			}
		}
		return false;
	}
		
	// Method that searches for the highest valued piece that could be taken. If all values
	// are the same, then randomly decided
	public void getMove(String moveInput) {
		makeMove();
		int sizeOfList = moveList.size();
		if (moveList.isEmpty())
			JOptionPane.showMessageDialog(null,"Something went wrong, start new game!");
		else {
			int tempVal = 0;
			for(int i=0;i<sizeOfList;++i) {
				int x = moveList.get(i).getTargetX();
				int y = moveList.get(i).getTargetY();
				pieceTaken = moveList.get(i).isTaken();
				if (pieceTaken == true) {
					tempVal = PieceCode.charToInt((getBoard().getPiece(x, y).getChar()));
					if(tempVal>highestVal) {
						highestVal = tempVal;
						moveWithVal = i;
					}
				}
				else
					continue;
			}    
			if(highestVal==0) {
				setCoors((int)(Math.random()*(moveList.size()-1)));
				moveOK = true;
				if(pieceTaken == true)
					defeatPiece(this.getOpponent());
				else if(!(pieceTaken==true))
					pieceMove();
			}
			else if(highestVal>=1 && highestVal!=0) {
				System.out.println("Aggressive move: "+moveList.get(moveWithVal).toString());
				setCoors(moveWithVal);
				moveOK = true;
				if(pieceTaken == true)
					defeatPiece(this.getOpponent());
				else if(!(pieceTaken == true))
					pieceMove();
			}
			moveWithVal = 0;
			moveList.clear();
		}
	}
	
	// Method that sets coordinates of a move to take oponent's piece
    private void setCoors(int locationOfMove) {
		xInit = moveList.get(locationOfMove).getInitialX();
		yInit = moveList.get(locationOfMove).getInitialY();
		xGoal = moveList.get(locationOfMove).getTargetX();
		yGoal = moveList.get(locationOfMove).getTargetY();
		pieceTaken = moveList.get(locationOfMove).isTaken();
	}
    
	// Method that returns true if the player takes a piece
    public boolean isPieceTaken() {
    		return pieceTaken;
    	}
    
	// Method that moves the player's piece
	private void pieceMove() {	
		this.getBoard().getPiece(xInit, yInit).setPosition(xGoal, yGoal);
        this.getBoard().setPosition(xGoal, yGoal, this.getBoard().getPiece(xInit, yInit));                
        this.getBoard().remove(xInit, yInit);
	}
	// Method that captures opponent's piece.
	private void defeatPiece(Player opponentPiece) {
		opponentPiece.deletePiece(opponentPiece.getBoard().getPiece(this.xGoal, this.yGoal));
        pieceMove();
	}
}