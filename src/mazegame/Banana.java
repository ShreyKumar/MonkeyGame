package mazegame;

/**
 * The Banana object
 * @author Shreyansh Kumar
 *
 */
public class Banana extends Sprite{
	
	/** The Banana's value of points */
	protected int value;
	
	/**
	 * Creates Banana object with a particular value, column, row and symbol
	 * @param value The value of the Banana
	 * @param column The Banana's current column
	 * @param row The Banana's current row
	 * @param symbol The Banana's symbol 
	 */
	public Banana(int value, int column, int row, char symbol){
		super(symbol, row, column);
		this.value = value;
		this.row = row;
		this.column = column;
		this.symbol = symbol;
	}

	/**
	 * Returns the value of the Banana
	 * @return the value of the Banana
	 */
	public int getValue(){
		return this.value;
	}
}
