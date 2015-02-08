package mazegame;

public class VisitedHallway extends Sprite {
	public VisitedHallway(char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = MazeConstants.VISITED;
		this.row = row;
		this.column = column;
	}
}
