package mazegame;

public class MobileBanana extends Banana implements Moveable{
	public MobileBanana(int value, int column, int row, char symbol) {
		super(value, column, row, symbol);
		this.symbol = symbol;
		this.column = column;
		this.row = row;
		this.value = value;
	}
	public void move(int row, int col){
		this.row += row;
		this.column += column;
	}
}
