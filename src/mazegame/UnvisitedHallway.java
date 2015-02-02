package mazegame;

public class UnvisitedHallway extends Sprite {
	public UnvisitedHallway(char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = ' ';
		this.row = row;
		this.column = column;
		
	}
}
