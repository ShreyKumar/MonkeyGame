package mazegame;
/**
 * 
 * The Wall object of this MazeGame, also a subclass of Sprite
 * @author Shreyansh Kumar
 *
 */

public class Wall extends Sprite {
	/**
	 * Creates a Wall object with a given Symbol, row and column
	 * @param symbol This Wall's symbol
	 * @param row This Wall's row
	 * @param column This Wall's column 
	 * 
	 */
	public Wall(char symbol, int row, int column) {
		super(symbol, row, column);
		this.symbol = MazeConstants.WALL;
		this.row = row;
		this.column = column;
	}
}
