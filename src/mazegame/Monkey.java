package mazegame;

public class Monkey extends Sprite implements Moveable {
	protected int numMoves;
	protected int score;
	
	
	public Monkey(int numMoves, int score){
		super(symbol, row, column);
		
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
		
	}
}