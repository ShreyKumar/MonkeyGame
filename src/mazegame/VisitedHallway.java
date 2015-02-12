package mazegame;

/**
 * The VisitedHallway object of the MazeGame
 * @author Shreyansh Kumar
 * 
 */
public class VisitedHallway extends Sprite {
	
	/**
	 * Creates a VisitedHallway object with a given symbol, row and column
	 * @param symbol This VisitedHallway's symbol
	 * @param row This VisitedHallway's current row
	 * @param column This VisitedHallway's current column
	 */
	public VisitedHallway(char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = MazeConstants.VISITED;
		this.row = row;
		this.column = column;
	}
}
