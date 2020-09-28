package assignment2018;

import java.util.*;

import javax.swing.JOptionPane;

import assignment2018.codeprovided.*;

/**
 * RandomPlayer.java
 *
 * A subclass of Player in which a computerized opponent randomly moves its pieces
 *
 * @author Palash Surana
 * 
 */
public class RandomPlayer extends Player {
	
	// Declaration of variables.
	private ArrayList<Move> moveList = new ArrayList<Move>();
	private int xInit;
	private int yInit;
	private int xGoal;
	private int yGoal;
	private boolean pieceTaken;
	public boolean moveOK = false;
	
	// Constructor for RandomPlayer
	public RandomPlayer(String name, Pieces p, Board b){
        super(name, p, b, null);
    }
	
	// Method that creates a list of all moves which 	are not null and stores them in moveList
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
	
	// Method that randomly chooses a piece and gets its moves
	public void getMove(String moveInput) {
		makeMove();	
		if (moveList.isEmpty())
			JOptionPane.showMessageDialog(null,"Something went wrong, start new game!");
		else {
			setCoors((int)(Math.random()*(moveList.size()-1)));
			moveOK = true;
			if(pieceTaken == true) {
				defeatPiece(this.getOpponent());
			}
			else if(!(pieceTaken == true)) {
				pieceMove();
			}
			moveList.clear();
		}
	}
		 
	// Method that returns true if player takes a piece
    public boolean isPieceTaken() {
    		return pieceTaken;
    	}
    
	// Method that sets coordinates of a move to take opponent's piece
    private void setCoors(int locationOfMove) {
		xInit = moveList.get(locationOfMove).getInitialX();
		yInit = moveList.get(locationOfMove).getInitialY();
		xGoal = moveList.get(locationOfMove).getTargetX();
		yGoal = moveList.get(locationOfMove).getTargetY();
		pieceTaken = moveList.get(locationOfMove).isTaken();
	}
	// Method that moves a piece.
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
