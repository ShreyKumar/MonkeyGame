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
		//print once 
		this.printGrid();
		System.out.println("Your next Move:");
		Scanner input = new Scanner(System.in);
		char text_input = input.next().charAt(0);
		game.move(text_input);
		input.close();
		this.printGrid();
	}

	@Override
	public void displayWinner() {
		// TODO Auto-generated method stub
		
	}
	

	
}
