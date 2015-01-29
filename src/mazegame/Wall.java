package mazegame;

public class Wall extends Sprite {
	public Wall() {
		super(symbol, row, column);
		Sprite.symbol = 'X';
	}
}
