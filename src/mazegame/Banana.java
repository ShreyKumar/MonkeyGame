package mazegame;

public class Banana extends Sprite{
	
	/** The Banana's value of points */
	protected int value;
	public Banana(int value, char symbol, int column, int row){
		super(symbol, row, column);
		this.value = value;
		this.row = row;
		this.column = column;
	}
	
	public int getValue(){
		return this.value;
	}
}
