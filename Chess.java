package assignment2018;

import assignment2018.codeprovided.*;

/**
 * Chess.java
 *
 * Runs the entire program, has the main and executes it all. 
 * 
 * @author Palash Surana
 * 
 */
public class Chess {
    
    public static void main(String[] args) {
    	//Initialization of new board.
        Board board = new Board();
        //Initialization of graphic display.
        
        TextDisplay tdisp = new TextDisplay();
        Pieces data = new Pieces(board,0);
        Pieces data1 = new Pieces(board,1);
        tdisp.displayBoard(data);
        GraphicalDisplay disp = new GraphicalDisplay();
        //Setting up a graphic representation of chess board.
        disp.setPieces(board.getData());
        //Initialization of main game window.
        new MainWindow(disp);       
    }
}
