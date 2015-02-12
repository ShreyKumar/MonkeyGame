package mazegame;

/**
 * The Monkey object in the Maze Game.
 * @author Shreyansh Kumar
 */

public class Monkey extends Sprite implements Moveable {
	/** The number of moves of this Monkey */
	protected int numMoves;
	
	/** The score of Player */
	protected int score;
	
	/**
	 * Creates a Monkey object, with the number of moves, score, symbol,
	 * row and column of this Monkey
	 * @param numMoves The number of moves of this Monkey
	 * @param score The score of this Monkey
	 * @param symbol The symbol representation of the Monkey
	 * @param row The row of the Monkey 
	 * @param column The column of the Monkey 
	 */
	public Monkey(int numMoves, int score, char symbol, int row, int column){
		super(symbol, row, column);
		this.symbol = symbol;
		this.row = row;
		this.column = column;
		this.numMoves = numMoves;
		this.score = score;
	}
	/**
	 * Increases the score of this Monkey and removes Banana
	 */
	public void eatBanana(){
		this.score += MazeConstants.BANANA_SCORE;
	}
	
	/**
	 * Returns the current score of this Monkey
	 * @return the current score of this Monkey
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Changes the position of this Monkey to its desired row and column
	 * and increments number of moves by 1
	 */
	public void move(int col, int row){
		this.row = row;
		this.column = col;
		this.numMoves = this.getNumMoves() + 1;
	}
	
	/**
	 * Returns the number of moves this Monkey has made
	 * @return the number of moves this Monkey made in integer form
	 */
	
	public int getNumMoves() {
		return this.numMoves;
	}
	
}
