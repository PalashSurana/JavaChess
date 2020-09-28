package assignment2018;

import assignment2018.codeprovided.*;

/**
 * TextDisplay.java
 *
 * A class that implements Display and displays the text form of the chess board in console
 *
 * @author Palash Surana
 * 
 */

public class TextDisplay implements Display {

	// This method displays all the pieces on the board using ASCII and CodePiece
    public void displayBoard(Pieces data) {
        for (int i=0;i<10;++i) {
            if(i==0||i==9){
                System.out.print("  |");   
            }
            else
                System.out.print((char)(i+64)+"| ");               
            for (int j=0;j<8;++j){
                if (i==0)
                    System.out.print((j+1)+"|");
                else if(i==9)
                    System.out.print((j+1)+"|");
                else if(data.getPiece(i).getBoard().getPiece(i-1, j)==null)
                    System.out.print(". ");
                else
                    System.out.print(data.getPiece(i).getBoard().getPiece(i-1,j)+" "); 
            }
            if(i==0|i==9){
                System.out.print("");   
            }
            else
                System.out.print("|"+((char)(i+64)));
            System.out.println("");
        }
    }
}
