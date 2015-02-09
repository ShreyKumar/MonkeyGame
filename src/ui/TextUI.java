package ui;
import java.io.IOException;
import java.util.Scanner;

import mazegame.MazeGame;



public class TextUI implements UI {

    private MazeGame game;

	public TextUI(MazeGame maze) {
		this.game = maze;
	}
	
	private void printGrid() throws IOException{
		int rows = game.getNumRows();
		int cols = game.getNumCols();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				//check if last element
				System.out.print(game.get(i, j));
			}
			System.out.print("\n");
		}
	}
	
	@Override
	public void launchGame() throws IOException {
		while(true){
			this.printGrid();
			System.out.println("Your next move:");
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			char input_char = input.nextLine().charAt(0);
			this.game.move(input_char);
			
			int won = this.game.hasWon();
			if(won == 1 || won == 2 || won == 3){
				break;
			} else {
				input.reset();
			}
			
		}
	}

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
	                message = "Congratulations Player 1! You won the maze in " + 
	                          game.getPlayerOne().getNumMoves() + " moves.";
	            } else if (won == 2) { 
	                message = "Congratulations Player 2! You won the maze in " + 
	                          game.getPlayerTwo().getNumMoves() + " moves.";
	            } else { // it's a tie
	                message = "It's a tie!";
	            }
	        }
	        
	        System.out.println(message);
	}
	

	
}
