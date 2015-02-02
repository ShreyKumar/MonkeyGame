package mazegame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

/**
 * A class that represents the basic functionality of the maze game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class MazeGame {

    /** A random number generator to move the MobileBananas. */
    private Random random;
    
    /** The maze grid. */
    private Grid<Sprite> maze;
    
    /** The first player. */
    private Monkey player1;
    
    /** The second player. */
    private Monkey player2;

    /** The bananas to eat. */
    private List<Banana> bananas;
    
    /**
     * Creates a new MazeGame that corresponds to the maze in the file
     * named layoutFileName.
     * @param layoutFileName the path to the input maze file
     */
    public MazeGame(String layoutFileName) throws IOException {
        random = new Random();
        
        int[] dimensions = getDimensions(layoutFileName);
        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
               
        Scanner sc = new Scanner(new File(layoutFileName));

        /* INITIALIZE THE GRID HERE */
        //first get rows and columns
        int rows = dimensions[0];
        int columns = dimensions[1];
        
        //use for loop to output every symbol
        for(int i = 0; i < rows; i++){
	        for(int j = 0; j < columns; j++){
	        	//find what type of symbol it is 
	        	char symb = sc.next().charAt(j);
	        	if(symb == 'X'){
	        		//create a wall
	        		Wall sprite = new Wall('X', i, j);
					maze.setCell(i, j, sprite);
	        	} else if(symb == ' '){
	        		//create an unvisited hallway
	        		UnvisitedHallway sprite = new UnvisitedHallway(symb, i, j);
					maze.setCell(i, j, sprite);
	        	} else if(symb == 'M'){
	        		//create a mobile banana
	        		MobileBanana sprite = new MobileBanana(symb, i, j);
					maze.setCell(i, j, sprite);
	        	} else if(symb == '1'){
	        		//create a player 1 monkey
	        		Monkey sprite = new Monkey(0, 0, symb, i, j);
					maze.setCell(i, j, sprite);
	        	} else {
	        		//create a player 2 monkey
	        		Monkey sprite = new Monkey(0, 0, symb, i, j);
					maze.setCell(i, j, sprite);
	        	}
	        }
        }
    
        sc.close();
    }
    
    /**
     * Returns the dimensions of the maze in the file named layoutFileName.
     * @param layoutFileName the path of the input maze file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the maze that
     * corresponds to the given input maze file
     * @throws IOException
     */    
    private int[] getDimensions(String layoutFileName) throws IOException {       
        
        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    public void move(char nextMove){
    	int randomrow = random.nextInt(maze.getNumRows());
    	int randomcol = random.nextInt(maze.getNumCols());
    	for(int i = 0; i < randomrow; i++){
    		for(int j = 0; j < randomcol; j++){
    			
    		}
    	}
    	
    	//player1
    	if(nextMove == 'W'){
    		int col = player1.getColumn();
    		int row = player1.getRow();
    		if(maze.getCell(row-1, col).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player1.row = row-1;
    		}
    	} else if(nextMove == 'A'){
    		int col = player1.getColumn();
    		int row = player1.getRow();
    		if(maze.getCell(row, col-1).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player1.column = col-1;
    		}
    	} else if(nextMove == 'S'){
    		int col = player1.getColumn();
    		int row = player1.getRow();
    		if(maze.getCell(row+1, col).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player1.row = row+1;
    		}
    	} else if(nextMove == 'D'){
    		int col = player1.getColumn();
    		int row = player1.getRow();
    		if(maze.getCell(row, col+1).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player1.column = col+1;
    		}
    	}
    	
    	//player2
    	if(nextMove == 'I'){
    		int col = player2.getColumn();
    		int row = player2.getRow();
    		if(maze.getCell(row-1, col).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player2.row = row-1;
    		}
    	} else if(nextMove == 'J'){
    		int col = player2.getColumn();
    		int row = player2.getRow();
    		if(maze.getCell(row, col-1).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player2.column = col-1;
    		}
    	} else if(nextMove == 'K'){
    		int col = player2.getColumn();
    		int row = player2.getRow();
    		if(maze.getCell(row+1, col).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player2.row = row+1;
    		}
    	} else if(nextMove == 'L'){
    		int col = player2.getColumn();
    		int row = player2.getRow();
    		if(maze.getCell(row, col+1).getSymbol() == ' '){
    			VisitedHallway visited = new VisitedHallway('.', row, col);
    			maze.setCell(row, col, visited);
    			player2.column = col+1;
    		}
    	}
    }
    
    /**
     * Returns the ID of the player with the greatest score
     */
    public int hasWon(){
		if(player1.getScore() > player2.getScore()){
			return 1;
		} else if(player1.getScore() < player2.getScore()){
			return 2;
		} else {
			return 3;
		}
    	
    }
    
    /**
     * Returns the arraygrid maze object
     * @return arraygrid maze object
     */
    public Grid<Sprite> getMaze() {
		return maze;
	}

    /**
     * Sets the maze to another given maze
     * @param maze to be set 
     */
	public void setMaze(Grid<Sprite> maze) {
		this.maze = maze;
	}

	/**
     * Returns whether the sprite has moved to a valid position in the grid
     * @param the Sprite that is meant to move at the position 
     * @param the number of rows the sprite is meant to move
     * @param the number of columns the sprite is meant to move
     * @return True if the position is a valid move
     */
    private boolean isvalidMove(Sprite sprite, int row, int col){
    	Sprite cell = maze.getCell(row, col);
		return cell.symbol == ' ';
    }

    /**
     * Returns player 1's Monkey object
     * @return the player 1 monkey object
     */
	public Monkey getPlayer1() {
		return player1;
	}

	/**
	 * Changes the player1 Monkey object 
	 * @param player1 object to be set 
	 */
	public void setPlayer1(Monkey player1) {
		this.player1 = player1;
	}

	public Monkey getPlayer2() {
		return player2;
	}

	public void setPlayer2(Monkey player2) {
		this.player2 = player2;
	}
	
	public int getNumRows() throws IOException{
		int[] dimensions = this.getDimensions("maze1.txt");
		return dimensions[0];
	}
	
	public int getNumCols() throws IOException{
		int[] dimensions = this.getDimensions("maze1.txt");
		return dimensions[1];
	}
    
    
}
