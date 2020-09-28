package assignment2018;

import java.awt.*;
import javax.swing.*;

import assignment2018.codeprovided.*;
/**
 * GraphicDisplay.java
 *
 * Implements the GUI version of the program and passes is to the MainWindow
 *
 * @author Palash Surana
 * 
 */
public class GraphicalDisplay extends JPanel implements Display {
	
	//Variables of class.
	private Graphics2D cell ;
	private Piece[][] piecesOnBoard;
	private int colourSwitch = 1;
	private int cellSize = 50;
	private int xDefault=Toolkit.getDefaultToolkit().getScreenSize().height/10;
	private int yDefault=Toolkit.getDefaultToolkit().getScreenSize().width/20;
	
	
	//Constructor of a board
	public GraphicalDisplay() {}	
	
	public void displayBoard(Pieces data) {}
	
	//Method changes cell's colour from step to step.
	private void switchColourCell(int i) {
		if (colourSwitch == 1) {
			cell.setColor(new Color(244,164,96));
			colourSwitch = -colourSwitch;
		}
		else {
			cell.setColor(new Color(139,69,19));
			colourSwitch = -colourSwitch;
		}
		if (i==7) 
			colourSwitch = -colourSwitch;
	}
	
	//Method adds graphic representation of pieces on board
	public void paintComponent(Graphics g) {
		cell = (Graphics2D)g;
		super.paintComponents(cell);	
		setBackground(new Color(135,206,235));
		int i=-1;
		int j=0;
		cell.setFont(new Font("Helvetica",Font.PLAIN,20));
		for (i=-1;i<=8;++i) {
			cell.setFont(new Font("Helvetica",Font.PLAIN,20));
            if(i==-1|i==8) {  
            }
            else {
            	//Left side letters
	            String str = ""+ (char)((i+1)+64);
	            cell.setColor(new Color(139,69,19));
				cell.drawString(str, xDefault-cellSize/2-5, yDefault+cellSize*i+cellSize/2 + 10);   
            }
            for (j=-1+1;j<8;++j) {
            	//Top side integers
                if (i==-1) {
                    String str = ""+(j+1);
                    cell.setColor(new Color(139,69,19));
    				cell.drawString(str, xDefault+cellSize*j+cellSize/2-5, yDefault+cellSize*i/2+15);
                }
                //Bottom side integers
                else if(i==8) {
                	String str = ""+(j+1);
	                cell.setColor(new Color(139,69,19));
					cell.drawString(str, xDefault+cellSize*j+cellSize/2-5, yDefault+cellSize*i+cellSize/2+2);
                }
                else {
                	switchColourCell(j);
                	Rectangle rect = new Rectangle(xDefault+cellSize*j, yDefault+cellSize*i, cellSize, cellSize);
    				cell.fill(rect);
    				cell.draw(rect);  
    				if (!(piecesOnBoard[i][j]==null)) {
    					cell.setFont(new Font("Helvetica",Font.PLAIN,30));
    					String str = ""+charToPiece(piecesOnBoard[i][j].getChar());
    					if(piecesOnBoard[i][j].getColour()==0) {
    						cell.setColor(Color.black);
    						cell.drawString(str, xDefault+cellSize*j+cellSize/4-2, yDefault+cellSize*i+cellSize/2+5);
    					}
    					if(piecesOnBoard[i][j].getColour()==1) {
    						cell.setColor(Color.white);
    						cell.drawString(str, xDefault+cellSize*j+cellSize/4-2, yDefault+cellSize*i+cellSize/2+5);
    					}
    				}     				
    				xDefault=Toolkit.getDefaultToolkit().getScreenSize().height/10;
    				yDefault=Toolkit.getDefaultToolkit().getScreenSize().width/20;
                }
            }
            if(i==-1|i==8){  
            }
            else {
            	//Right side letters
            	    cell.setFont(new Font("Helvetica",Font.PLAIN,20));
                String str = ""+ (char)((i+1)+64);
                cell.setColor(new Color(139,69,19));
				cell.drawString(str, xDefault+cellSize*j+cellSize/2-10, yDefault+cellSize*i+cellSize/2+10);
            }
        }
	}
	//Method sets pieces for a graphic representation and repaint board.
	public void setPieces(Piece[][] p) {
		piecesOnBoard = p;
		this.repaint();
	}
	
	//Method converts piece's name to a Unicode representation.
	private char charToPiece(char ch) {
		switch(ch) {
		case 'p':
			return '\u2659';
		case 'r':
			return '\u2656';
		case 'n':
			return '\u2658';
		case 'b':
			return '\u2657';
		case 'q':
			return '\u2655';
		case 'k':
			return '\u2654';
		case 'P':
			return '\u265F';
		case 'R':
			return '\u265C';
		case 'N':
			return '\u265E';
		case 'B':
			return '\u265D';
		case 'Q':
			return '\u265B';
		case 'K':
			return '\u265A';
		default:
			return '.';	
		}
	}
}