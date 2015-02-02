package mazegame;

public abstract class Sprite {
	/** The Sprite's symbol */
	protected char symbol;
	
	/** The Sprite's current row */
	protected int row;

	/** The Sprite's current column */
	protected int column;
	
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the symbol of the sprite
	 * @return the symbol of the sprite (X, , B or M)
	 */
	public char getSymbol() {
		return this.symbol;
	}
	/**
	 * Returns the current row of the sprite
	 * @return the current row of the sprite in integer form
	 */
	public int getRow(){
		return this.row;
	}
	/**
	 * Returns the current column of the sprite
	 * @return the current column of the sprite in integer form
	 */
	public int getColumn(){
		return this.column;
	}
	/**
	 * Returns the String representation of this Sprite
	 * NOTE: to do for later
	 */
	@Override
	public String toString(){
		return Character.toString(this.getSymbol());
	}

}
