package mazegame;

public class Wall extends Sprite {
	public Wall(char symbol, int row, int column) {
		super(symbol, row, column);
		this.symbol = 'X';
		this.row = row;
		this.column = column;
	}
}
