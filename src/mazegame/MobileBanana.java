package mazegame;

/**
 * The Mobile Banana object of the MazeGame
 * @author Shreyansh Kumar
 * 
 */
public class MobileBanana extends Banana implements Moveable{
	/**
	 * Creates a MobileBanana object and sets its value, row, column and symbol
	 * @param value This MobileBanana's value
	 * @param row This MobileBanana's row
	 * @param column This MobileBanana's column
	 * @param symbol This MobileBanana's symbol
	 */
	public MobileBanana(int value, int row, int column, char symbol) {
		super(value, column, row, symbol);
		this.symbol = symbol;
		this.column = column;
		this.row = row;
		this.value = value;
	}
	
	/**
	 * Changes the position of this Mobile Banana to its desired row and column
	 */
	public void move(int row, int col){
		this.row = row;
		this.column = col;
	}
}
