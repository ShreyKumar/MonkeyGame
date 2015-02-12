package mazegame;

/**
 * The Grid interface of the MazeGame
 * @author Shreyansh Kumar
 *
 * @param <T> The type of Object we wish to use
 */

public interface Grid<T> {
	
	/**
	 * Sets the cell in this Grid to a specific row, column and item of type T
	 * @param row This item's row position
	 * @param col This item's column position
	 * @param item This item we wish to store in Grid of type T
	 */
	public void setCell(int row, int col, T item);
	
	/**
	 * Returns an item of type T at a particular row and column
	 * @param row This item's row position
	 * @param col This item's column position
	 * @return an item of type T
	 */
	public T getCell(int row, int col);
	
	/**
	 * Returns the row position of T
	 * @return the row position of T
	 */
	public int getNumRows();
	
	/**
	 * Returns the column position of T
	 * @return the column position of T
	 */
	public int getNumCols();
	
	/**
	 * Returns true if two Grids contain the same contents of type T
	 * @param other The Grid we wish to compare to
	 * @return true if two Grids contain the same contents
	 */
	public boolean equals(Grid<T> other);
	
	@Override
	public String toString();
}
