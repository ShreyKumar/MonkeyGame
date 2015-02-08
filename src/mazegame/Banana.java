package mazegame;

public class Banana extends Sprite{
	
	/** The Banana's value of points */
	public int value;
	public Banana(int value, int column, int row, char symbol){
		super(symbol, row, column);
		this.value = value;
		this.row = row;
		this.column = column;
		this.symbol = symbol;
	}

	public int getValue(){
		return this.value;
	}
}
