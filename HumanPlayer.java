package assignment2018;

import java.util.*;

import javax.swing.*;

import assignment2018.codeprovided.*;
/**
 * HumanPlayer.java
 *
 * A subclass of Player in which a human opponent decide where to moves his/her pieces
 *
 * @author Palash Surana
 * 
 */


public class HumanPlayer extends Player {
    
    // Declaration of variables
	// Constants are for positions in the coordinates array to get coordinates of inputted move
    private int[] coordinates = new int[4];
    private boolean pieceTaken = false;
    public boolean moveOK = false;
    private static final int boardSize = 8;
    private static final int coordinateLength = 5;
    private static final int letterStartPosition = 0;
    private static final int numberStartPosition = 1;
    private static final int letterEndPosition = 3;
    private static final int numberEndPosition = 4;
    
    // Constructor for the HumanPlayer class.
    public HumanPlayer(String name, Pieces p, Board b){
        super(name, p, b, null);
    }

    // Method that obtains user input and checks if it is valid
    public void getMove(String m){
        String moveInput = m;
        if (moveInput.length()==coordinateLength){
            coordinates[0] = ((int)(Character.toLowerCase(moveInput.charAt(letterStartPosition)))) - 97;
            coordinates[2] = ((int)(Character.toLowerCase((moveInput.charAt(letterEndPosition))))) - 97;
            if (coordinates[0]>=boardSize || coordinates[2]>=boardSize)
            	JOptionPane.showMessageDialog(null,"Your coordinates fall outside the board. Please re-enter.");
            else {
                coordinates[1] = moveInput.charAt(numberStartPosition) - '0' - 1;
                coordinates[3] = moveInput.charAt(numberEndPosition) - '0' - 1;
            }
            if (((getBoard().getPiece(coordinates[0], coordinates[1]) == null) || (makeMove() == false))) 
            		JOptionPane.showMessageDialog(null,"Invalid input. Please re-enter.");
            else {
                moveOK = true;
                if(moveOK) {
                		if(pieceTaken)
                			defeatPiece(this.getOpponent());
                    else
                    		pieceMove();
                }                    	
            }
        }           
        else 
        	JOptionPane.showMessageDialog(null,"Your move is not full!");
    }
    
    // Method that returns true if it is possible to make a move
    public boolean makeMove(){
        for (Iterator<Move> it = getBoard().getPiece(coordinates[0], coordinates[1]).availableMoves().iterator(); it.hasNext();) {
            Move item = it.next();
            if (item==null)
                System.out.print("");
            else if((item.getTargetX()==coordinates[2]&&item.getTargetY()==coordinates[3])&&((getBoard().getPiece(coordinates[0],coordinates[1]).getColourChar()==getPieces().getPiece(0).getColourChar()))){
                System.out.print("");
                if(item.isTaken())
                    pieceTaken = true;
                return true;
            }
            else
                System.out.print("");
        }
        return false;
    }
    
    // Method that returns true if player takes a piece
    public boolean isPieceTaken() {return pieceTaken;}
     
    // Method that moves the player's piece
    private void pieceMove() {
        this.getBoard().getPiece(this.coordinates[0], this.coordinates[1]).setPosition(this.coordinates[2], this.coordinates[3]);
        this.getBoard().setPosition(this.coordinates[2], this.coordinates[3], this.getBoard().getPiece(this.coordinates[0], this.coordinates[1]));                
        this.getBoard().remove(this.coordinates[0], this.coordinates[1]);
    }
    
    // Method that captures the opponent's piece
    private void defeatPiece(Player opponentPiece) {
        opponentPiece.deletePiece(opponentPiece.getBoard().getPiece(this.coordinates[2], this.coordinates[3]));
        pieceMove();
    }

}
