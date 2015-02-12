package mazegame;

/**
 * The Unvisited Hallway object
 * @author Shreyansh Kumar
 *
 */

public class UnvisitedHallway extends Sprite {
	
	/**
	 * Creates a UnvisitedHallway object with a given symbol, row and column
	 * @param symbol This UnvisitedHallway's symbol
	 * @param row This UnvisitedHallway's row
	 * @param column This UnvisitedHallway's column
	 */
	public UnvisitedHallway(char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = MazeConstants.VACANT;
		this.row = row;
		this.column = column;
	}
}
