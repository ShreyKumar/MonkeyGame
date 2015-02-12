package ui;
import java.io.IOException;
import java.util.Scanner;
import mazegame.MazeGame;

/**
 * A TextUI object of the basic text representation of the Maze Game
 * @author Shreyansh Kumar
 */
public class TextUI implements UI {

	/** The MazeGame object we wish to use */
    private MazeGame game;

    /**
     * Creates a new TextUI object with a particular game as its instance 
     * variable
     * @param maze The game object to work with
     */
	public TextUI(MazeGame maze) {
		this.game = maze;
	}
	

	
	/**
	 * Launches the game of this object, asks for the user's move and updates 
	 * accordingly
	 */
	@Override
	public void launchGame() throws IOException {
		while(true){
			
			/* Print the grid */
			System.out.println(this.game.getMaze().toString());
			System.out.println("Your next move:");
			
			/** Ask for user input */
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			/* Moves accordingly from the given input */
			char input_char = input.nextLine().charAt(0);
			this.game.move(input_char);
			
			/* Checks the status of the game*/
			int won = this.game.hasWon();
			
			/* 
			 * If the game is over, break out of function, 
			 * otherwise reset input and ask again 
			 */
			if(won == 1 || won == 2 || won == 3){
				break;
			} else {
				input.reset();
			}
			
		}
	}

	/**
	 * Outputs a message to the screen when the game has ended
	 */
	@Override
	public void displayWinner() {
		 int won = game.hasWon();
	        String message;
	        
	        if (game.isBlocked()) { // no winners
	            message = "Game over! Both players are stuck.";
	        } else {
	            if (won == 0) { // game is still on
	                return;
	            } else if (won == 1) { 
	                message = "Congratulations Player 1! You won the maze "
	                		+ "in " + game.getPlayerOne().getNumMoves() 
	                		+ " moves.";
	            } else if (won == 2) { 
	                message = "Congratulations Player 2! You won the maze in " 
	                          + game.getPlayerTwo().getNumMoves() + " moves.";
	            } else { // it's a tie
	                message = "It's a tie!";
	            }
	        }
	        
	        System.out.println(message);
	}
	

	
}
