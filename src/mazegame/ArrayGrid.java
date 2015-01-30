package mazegame;

public class ArrayGrid<T> implements Grid<T>{
	protected int numCols;
	protected int numRows;
	protected T[][] arraygrid;
	
	public ArrayGrid(int numCols, int numRows){
		this.numCols = numCols;
		this.numRows = numRows;
	}
	
	
	public int getNumCols(){
		return this.numCols;
	}
	
	public int getNumRows(){
		return this.numRows;
	}
	
	public void setCell(int row, int col, T item){
		Sprite.row = row;
		Sprite.column = col;
	}
	
	public T getCell(int row, int col){
		
	}
	
}
