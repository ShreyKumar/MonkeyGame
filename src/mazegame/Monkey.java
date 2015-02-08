package mazegame;


public class Monkey extends Sprite implements Moveable {
	protected int numMoves;
	protected int score;
	
	
	public Monkey(int numMoves, int score, char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = symbol;
		this.row = row;
		this.column = column;
		this.numMoves = numMoves;
		this.score = score;
	}
	/**
	 * Monkey eats Banana
	 */
	public void eatBanana(){
		this.score += 1;
	}
	
	/**
	 * Returns the number of moves the Monkey has made
	 * @return the number of moves the Monkey made in integer form
	 */
	public int getnumMoves(){
		return this.numMoves;
	}
	
	/**
	 * Returns the current score of Monkey
	 * @return the current score of the Monkey
	 */
	public int getScore(){
		return this.score;
	}
	
	public void move(int col, int row){
		this.row += row;
		this.column += col;
		this.numMoves += 1;
	}
	public int getNumMoves() {
		return this.numMoves;
	}
	
	
}
