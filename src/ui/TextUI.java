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

	private void updateGame() throws IOException{
		for(int i = 0; i < this.game.getNumRows(); i++){
			for(int j = 0; j < this.game.getNumCols(); j++){
				System.out.println(this.game.get(i, j).toString());
			}
		}
	}
	
	@Override
	public void launchGame() throws IOException {
		while(true){
			this.printGrid();
			System.out.println("Your next move:");
			Scanner input_str = new Scanner(System.in);
			this.game.move(input_str.nextLine().charAt(0));
			this.updateGame();
			input_str.close();
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
