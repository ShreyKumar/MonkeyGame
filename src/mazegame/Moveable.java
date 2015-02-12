package mazegame;

/**
 * A Moveable interface for all moveable Sprites
 * @author Shreyansh Kumar
 *
 */
public interface Moveable {
	
	/**
	 * Moves this Sprite to its desired row and column
	 * @param col The column to change the Sprite's column instance
	 * @param row The row to change the Sprite's row instance
	 */
	public void move(int col, int row);
	
}
