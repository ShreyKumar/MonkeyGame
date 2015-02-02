package mazegame;

public class ArrayGrid<T> implements Grid<T>{
	protected int numCols;
	protected int numRows;
	T[][] arraygrid;
	
	
	@SuppressWarnings("unchecked")
	public ArrayGrid(int numCols, int numRows){
		this.numCols = numCols;
		this.numRows = numRows;
		this.arraygrid =  (T[][]) new ArrayGrid[this.numRows][this.numCols];
	}
	
	/**
	 * 
	 */
	public int getNumCols(){
		return this.numCols;
	}
	
	/**
	 * 
	 */
	public int getNumRows(){
		return this.numRows;
	}
	
	public void setCell(int row, int col, T item){
		this.arraygrid[row][col] = item;
	}
	
	public T getCell(int row, int col){
		return this.arraygrid[row][col];
	}

	@Override
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
