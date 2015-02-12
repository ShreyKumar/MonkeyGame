package mazegame;


/**
 * The Sprite object of the Maze Game
 * @author Shreyansh Kumar
 *
 */
public abstract class Sprite {
	
	/** This Sprite's symbol */
	protected char symbol;
	
	/** This Sprite's current row */
	protected int row;

	/** This Sprite's current column */
	protected int column;
	
	/**
	 * Creates a Sprite object with a symbol, current position of row and 
	 * column
	 * @param symbol The Sprite's symbol
	 * @param row The Sprite's row position
	 * @param column The Sprite's column position
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the symbol of this Sprite
	 * @return the symbol of this Sprite
	 */
	public char getSymbol() {
		return this.symbol;
	}
	/**
	 * Returns the current row of this Sprite
	 * @return the current row of this Sprite in integer form
	 */
	public int getRow(){
		return this.row;
	}
	/**
	 * Returns the current column of this Sprite
	 * @return the current column of this Sprite in integer form
	 */
	public int getColumn(){
		return this.column;
	}
	/**
	 * Returns the String representation of this Sprite
	 */
	@Override
	public String toString(){
		return Character.toString(this.getSymbol());
	}

}
