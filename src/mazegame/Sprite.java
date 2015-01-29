package mazegame;

public abstract class Sprite {
	/** The Sprite's symbol */
	protected static char symbol;
	
	/** The Sprite's current row */
	protected static int row;

	/** The Sprite's current column */
	protected static int column;
	
	public Sprite(char symbol, int row, int column) {
		Sprite.symbol = symbol;
		Sprite.row = row;
		Sprite.column = column;
	}
	
	/**
	 * Returns the symbol of the sprite
	 * @return the symbol of the sprite (X, , B or M)
	 */
	public char getSymbol() {
		return Sprite.symbol;
	}
	/**
	 * Returns the current row of the sprite
	 * @return the current row of the sprite in integer form
	 */
	public int getRow(){
		return Sprite.row;
	}
	/**
	 * Returns the current column of the sprite
	 * @return the current column of the sprite in integer form
	 */
	public int getColumn(){
		return Sprite.column;
	}
	/**
	 * Returns the String representation of this Sprite
	 * NOTE: to do for later
	 */
	@Override
	public String toString(){
		return "test";
	}

}
