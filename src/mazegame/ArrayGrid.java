package mazegame;

public class ArrayGrid<T> implements Grid<T>{
	protected int numCols;
	protected int numRows;
	T[][] arraygrid;
	
	
	@SuppressWarnings("unchecked")
	public ArrayGrid( int numRows, int numCols){
		this.numCols = numCols;
		this.numRows = numRows;
		this.arraygrid =  (T[][]) new Object[this.numRows][this.numCols];
		//this.arraygrid = T[this.numRows][this.numCols];
	}
	
	/**
	 * Returns the number of columns
	 */
	public int getNumCols(){
		return this.numCols;
	}
	
	/**
	 * Returns the number of rows
	 */
	public int getNumRows(){
		return this.numRows;
	}
	
	/**
	 * Sets a particular cell in arraygrid to an item T
	 */
	public void setCell(int row, int col, T item){
		this.arraygrid[row][col] = item;
	}
	
	/**
	 * Returns an element T at a given row and col
	 */
	public T getCell(int row, int col){
		return this.arraygrid[row][col];
	}

	@Override
	/**
	 * Returns if two array grids are identical
	 */
	public boolean equals(Grid<T> other) {
		for(int a = 0; a < this.numRows; a++){
			for(int b = 0; b < this.numCols; b++){
				for(int c = 0; c < other.getNumRows(); c++){
					for(int d = 0; d < other.getNumCols(); d++){
						if(this.getCell(a, b) != other.getCell(c, d)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
}
