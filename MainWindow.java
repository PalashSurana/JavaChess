package assignment2018;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import assignment2018.codeprovided.*;

/**
 * MainWindow.java
 *
 * Implements the GUI version of the program and gives us an output. 
 * Regulates the entire functioning of the program
 *
 * @author Palash Surana
 * 
 */
public class MainWindow extends JFrame {
    
	// Instance variables of a class
    private final static int width = 880;
    private final static int height = 770;
    private boolean moveOK = false;
    private Board board = new Board();
    private Pieces blackSet = new Pieces (board,0);
    private Pieces whiteSet = new Pieces (board,1);
    private HumanPlayer player1 = new HumanPlayer("Player 1",null,board);
    private HumanPlayer opponent1 = new HumanPlayer("Human Opponent",null,null);
    private RandomPlayer opponent2 = new RandomPlayer("Random Opponent",null,null);
    private AggressivePlayer opponent3 = new AggressivePlayer("Aggressive Opponent",null,null);
    private GraphicalDisplay display;
    private boolean start = false;
    private boolean colour=false;
    private JTextField inputText;
    private JLabel playerTurn;
    private JButton startButton;
    private JRadioButton humPlayerRadio;
    private JRadioButton randPlayerRadio;
    private JRadioButton aggPlayerRadio;
    private ButtonGroup playerType;
    
    // Constructor for the main game window
    public MainWindow(GraphicalDisplay disp)   {
        
      	// Creation of a container instance
        Container contr = new Container();
        contr = getContentPane();
        getContentPane().setBackground(new Color(135,206,235));
        
        // Sets the layout for container
        contr.setLayout(new BorderLayout());
        
        // Creation of a group of buttons
        JLabel playerLabel = new JLabel("Who would you like to play against? ");
        playerType = new ButtonGroup();  
        
        // Radio buttons that represent opponents
        humPlayerRadio = new JRadioButton("Human player");
        randPlayerRadio = new JRadioButton("Random player");
        aggPlayerRadio = new JRadioButton("Aggressive player");
        randPlayerRadio.setSelected(true);
        
        // Collecting the different kinds of player types and putting them in a group
        playerType.add(humPlayerRadio);
        playerType.add(randPlayerRadio);
        playerType.add(aggPlayerRadio);
        
        // Creation of a panel, which all player type related buttons are added to
        JPanel playerButtons = new JPanel();
        playerButtons.setBorder(new EmptyBorder(100, 0, 0, 100));
        playerButtons.setBackground(new Color(135,206,235));
        playerButtons.setLayout(new GridLayout(8,1));
        playerButtons.add(playerLabel);
        playerButtons.add(humPlayerRadio);
        playerButtons.add(randPlayerRadio);
        playerButtons.add(aggPlayerRadio);
        
        // Creates a text box for reading move from the user
        JLabel inputLabel = new JLabel("Enter the from and to coordinates:");
        inputText = new JTextField(5);
        
        // Button which puts the move into action
        JButton inputMoveButton = new JButton("Play");
        inputMoveButton.addActionListener(new MakeMove());
        
        // Creation of a panel, which then everything is added to
        JPanel inputPanel = new JPanel(new GridLayout(3,1));
        inputPanel.setBorder(new EmptyBorder(0, 250, 80, 250));
        inputPanel.setBackground(new Color(135,206,235));
        inputPanel.add(inputLabel);
        inputPanel.add(inputText);
        inputPanel.add(inputMoveButton);
        
        // The start game button
        startButton = new JButton("Start!");
        startButton.addActionListener(new StartGame());
        
        // Adding the start button to the panel
        playerButtons.add(startButton);
        
        // The exit game button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new Exit());
        playerButtons.add(exitButton);
        
        // Setting up of a window using border layout
        display = disp;
        playerTurn = new JLabel("Please set up game first.");
        playerTurn.setBorder(new EmptyBorder(50, 200, 0, 150));
        contr.add(playerTurn, BorderLayout.NORTH);
        contr.add(playerButtons, BorderLayout.LINE_END); 
        contr.add(display,BorderLayout.CENTER);
        contr.add(inputPanel, BorderLayout.SOUTH);
        setTitle("Chess GUI");
        setSize(width,height);       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);              
    }
    
    // Method that returns the selected button from a group of radio buttons
    private JRadioButton isSelected(ButtonGroup bg) {
    	Enumeration<AbstractButton> buttons = bg.getElements();
	    JRadioButton selectedButton = null;
	    while (buttons.hasMoreElements()) {
	        selectedButton = (JRadioButton) buttons.nextElement();
	        if (selectedButton.isSelected()) 
	            break;
	    }
	    return selectedButton;
    }
    
    // Method that assigns colours to players
    private void setColour(Player player) {
		player.pieces = blackSet;
		player1.pieces = whiteSet;
		playerTurn.setText(player1.toString()+", it is your turn.");
		colour = true;		
		player1.setOpponent(player);
		player.setOpponent(player1);
    }
    
    // Method that runs against random computer
    private void runGameAgainstRandComp(RandomPlayer opponent) {

    	display.setPieces(player1.getBoard().getData());
		if (start == true) {
			String move = inputText.getText();
			player1.getMove(move);
			inputText.setText("");
			if(player1.moveOK == true) {
				player1.moveOK = false;
				display.setPieces(player1.getBoard().getData());
				opponent.getMove("");
				display.setPieces(player1.getBoard().getData());
				opponent.moveOK = false;
			}
	    		int player1Pieces = player1.getPieces().getNumPieces()-1;
	    		int opponentPieces = opponent.getPieces().getNumPieces()-1;
	        char player1Colour = player1.getPieces().getPiece(0).getColourChar();
	        char opponentColour = opponent.getPieces().getPiece(0).getColourChar();
	        
	        // Calls victory function to check who has won and display victory message
	        victory(player1Colour, player1, opponent, player1Pieces);
	        victory(opponentColour, opponent, player1, opponentPieces);
		}
		else if (start != true)
			JOptionPane.showMessageDialog(null,"Error. Game has not been set up.");
    }
    
    // Method that runs game against another human player.
    private boolean p1 = true;
    private boolean p2 = false;
    private void runGameAgainstHum(HumanPlayer pl1, HumanPlayer pl2) {
    	// Initialization of variables to stop game once a player is defeated
    		int player1Pieces = pl1.getPieces().getNumPieces()-1;
    		int opponentPieces = pl2.getPieces().getNumPieces()-1;
        char player1Colour = pl1.getPieces().getPiece(0).getColourChar();
        char opponentColour = pl2.getPieces().getPiece(0).getColourChar();
	    if (start == true) {
		    	String move = inputText.getText();
		    	if(p1 == true) {
		    		pl1.getMove(move);
		    		if(pl1.moveOK == true) {
		    			display.setPieces(pl1.getBoard().getData());
		    			pl1.moveOK = false;
		    			p1 = false;
		    			p2 = true;
		    			victory(opponentColour, pl2, pl1, opponentPieces);
		    			playerTurn.setText(pl2.toString()+", it is your turn.");	    			
		    		}   			
		    }
		    	else if(p2 == true) {
		    		pl2.getMove(move);
		    		if(pl2.moveOK == true) {
		    			display.setPieces(pl1.getBoard().getData());
		    			pl2.moveOK = false;
		    			p1 = true;
		    			p2 = false;
		    			victory(player1Colour, pl1, pl2, player1Pieces);
		    			playerTurn.setText(pl1.toString()+", it is your turn.");
		    		}
		    	}
		    	inputText.setText("");
	    }
    	else if (start != true)
			JOptionPane.showMessageDialog(null,"Error. Game has not been set up.");
    	
    }
    // Method that runs game against aggressive computer.
    private void runGameAgainstAggComp(AggressivePlayer opponent) {
	    	//escape values
	    	display.setPieces(player1.getBoard().getData());
			if (start == true) {
				String move = inputText.getText();
				player1.getMove(move);
				inputText.setText("");
				if(player1.moveOK) {
					player1.moveOK = false;
					display.setPieces(player1.getBoard().getData());
					opponent.getMove("");
					display.setPieces(player1.getBoard().getData());
					opponent.moveOK = false;
				}
			    	int player1Pieces = player1.getPieces().getNumPieces()-1;
			    	int opponentPieces = opponent.getPieces().getNumPieces()-1;
			    	char player1Colour = player1.getPieces().getPiece(0).getColourChar();
			    	char opponentColour = opponent.getPieces().getPiece(0).getColourChar();
			    
			    victory(player1Colour, player1, opponent, player1Pieces);
			    victory(opponentColour, opponent, player1, opponentPieces);   
		}
		else
			JOptionPane.showMessageDialog(null,"Error. Game has not been set up.");
    }
    
    // Creates the opponent for the player and makes use of setColour method to set colours for players.
    private class StartGame implements ActionListener {
    	public void actionPerformed(ActionEvent evt) {
    		boolean player = false;
    		colour = false;
    		if(start != true) {
    			// Creation of the player
    			switch(isSelected(playerType).getActionCommand()) {
    				case "Random player":
    					opponent2.board = board;
    					player = true;
    					break;
    				case "Human player":
    					opponent1.board = board;
    					player = true;
    					break;
    				case "Aggressive player":
    					opponent3.board = board;
    					player = true;
    					break; 
    				default:
    					JOptionPane.showMessageDialog(null,"You did not choose an opponent!");
    					break;
    			} 
    			// Assignment of colours.
    			if (isNotNull(opponent1))
    				setColour(opponent1);
    			else if (isNotNull(opponent2))
    				setColour(opponent2);
    			else if (isNotNull(opponent3))
    				setColour(opponent3);
    			if (player&&colour) {
    				display.setPieces(player1.getBoard().getData());
        			JOptionPane.showMessageDialog(null,"The game has begun.");
        			getContentPane().setBackground(new Color(135,206,235));
        			start = true;        			
        		}
    		}
    		else 
    			JOptionPane.showMessageDialog(null,"Game is already in progress.");
    	}
    } 
    // Class that connects button with runGame() method depending on player's choice.
    private class MakeMove implements ActionListener {
	    	public void actionPerformed(ActionEvent evt) {
	            if(isNotNull(opponent1)) {
	            		runGameAgainstHum(player1,opponent1);
	            }
	            else if(isNotNull(opponent2)) {
	            		runGameAgainstRandComp(opponent2);
	            }
	            else if(isNotNull(opponent3)) {
	            		runGameAgainstAggComp(opponent3);
	            }
	            else
	            	JOptionPane.showMessageDialog(null,"Game is not running properly. Please restart.");
	            	
	    	}
    }   
    
    // Method that returns true if player object has board connected.
    private boolean isNotNull(Player p) {
    		return (p.getBoard()!=null);
    }  
    
    // Method that calculates who has won and displays victory message.
    public void victory(char colr, Player hum, Player hum2, int coll) {
    		coll = hum.getPieces().getNumPieces()-1;
	    if(colr=='b')	{
			if(hum.getPieces().getPiece(coll).getChar()!='K') {
				JOptionPane.showMessageDialog(null,hum2.toString()+" is victorious!");
				System.exit(0);
			}	
		}	
		else {
			if(hum.getPieces().getPiece(coll).getChar()!='k') {
				JOptionPane.showMessageDialog(null,hum2.toString()+" is victorious!");
				System.exit(0);
			}		    				
		}
    }
    
    //Exit game.
    private class Exit implements ActionListener    {
        public void actionPerformed(ActionEvent evt)    {
            System.exit(0);
        }
    }
}
