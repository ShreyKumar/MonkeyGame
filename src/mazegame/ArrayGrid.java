package mazegame;

/**
 * An ArrayGrid object of the MazeGame
 * @author Shreyansh Kumar
 *
 * @param <T> The Sprite object type
 */

public class ArrayGrid<T> implements Grid<T>{
	/** The number of columns in the array grid */
	protected int numCols;
	
	/** The number of rows in the array grid */
	protected int numRows;
	
	/** The Multi-dimensional ArrayGrid array for storing contents */
	T[][] arraygrid;
	
	/**
	 * Creates a ArrayGrid object with a given total number of rows and columns
	 * @param numRows The total number of rows of this ArrayGrid
	 * @param numCols The total number of columns of this ArrayGrid
	 */
	@SuppressWarnings("unchecked")
	public ArrayGrid( int numRows, int numCols){
		this.numCols = numCols;
		this.numRows = numRows;
		this.arraygrid =  (T[][]) new Object[this.numRows][this.numCols];
	}
	
	/**
	 * Returns the number of columns of this ArrayGrid
	 */
	public int getNumCols(){
		return this.numCols;
	}
	
	/**
	 * Returns the number of rows of this ArrayGrid 
	 */
	public int getNumRows(){
		return this.numRows;
	}
	
	/**
	 * Sets a particular row and column in this ArrayGrid to an item T
	 */
	public void setCell(int row, int col, T item){
		this.arraygrid[row][col] = item;
	}
	
	/**
	 * Returns an element T at a given row and column of this ArrayGrid
	 */
	public T getCell(int row, int col){
		return this.arraygrid[row][col];
	}

	
	@Override
	/**
	 * Returns true if two ArrayGrids contain the same contents in their 
	 * corresponding cells
	 * 
	 * @param The other ArrayGrid object we wish to compare
	 */
	public boolean equals(Grid<T> other) {
		/* Row counter variable*/
		int i = 0;
		
		/* Column counter variable */
		int j = 0;
		
		/* If maze dimensions are not the same, not equal */
		if(this.getNumRows() != other.getNumRows() || 
				other.getNumCols() != this.getNumCols()){
			return false;
		} else {
			
			/* Loop through both ArrayGrid's cells */
			while(i < this.getNumRows()){
				while(j < this.getNumCols()){
					
					/* If different cell found, then not equal */
					if(this.getCell(i, j) != other.getCell(i, j)){
						return false;
					}
					j++;
				}
				
				/* Must reset to 0 (Column first index) before going to 
				 * next row 
				 */
				j = 0;
				i++;
			}
			return true;
		}
		
		
	}
	
	@Override
	public String toString(){
		String output = "";
		for(int i = 0; i < this.getNumRows(); i++){
			for(int j = 0; j < this.getNumCols(); j++){
				output += this.getCell(i, j).toString();
			}
			output += "\n";
		}
		return output;
	}
	
}
