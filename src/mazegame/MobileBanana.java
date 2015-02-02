package mazegame;

public class MobileBanana extends Sprite implements Moveable{
	public MobileBanana(char symbol, int column, int row) {
		super(symbol, row, column);
		this.symbol = symbol;
		this.column = column;
		this.row = row;
	}
	public void move(int row, int col){
		this.row += row;
		this.column += col;
	}
}
