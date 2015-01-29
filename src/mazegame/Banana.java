package mazegame;

public class Banana extends Sprite{
	
	/** The Banana's value of points */
	protected int value;
	public Banana(int value){
		super(symbol, row, column);
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}
