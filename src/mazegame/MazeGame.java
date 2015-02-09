package mazegame;
import java.io.File;
import java.util.ArrayList;
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
    private ArrayGrid<Sprite> maze;
    
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
        
        //use for loop to output every symbol
        int i = 0;
        bananas = new ArrayList<Banana>();
        
        //i = row
        //j = column 
        while(sc.hasNextLine()){
	        String line = sc.nextLine();
	        for(int j = 0; j < line.length(); j++){
	        	char symb = line.charAt(j);

	        if(symb == MazeConstants.WALL){
	        	//create a wall
	        	Wall sprite = new Wall(symb, i, j);
				maze.setCell(i, j, sprite);
	        } else if(symb == MazeConstants.VACANT){
	        	//create an unvisited hallway
	        	UnvisitedHallway sprite = new UnvisitedHallway(symb, i, j);
				maze.setCell(i, j, sprite);
	        } else if(symb == MazeConstants.MOBILE_BANANA){
	        	//create a mobile banana
	        	MobileBanana sprite = new MobileBanana(3, i, j, symb);
	       		bananas.add(sprite);
				maze.setCell(i, j, sprite);
	       	} else if(symb == MazeConstants.P1){
	       		//create a player 1 monkey
	       		player1 = new Monkey(0, 0, symb, i, j);
				maze.setCell(i, j, player1);
			} else if(symb == MazeConstants.P2) {
	        	//create a player 2 monkey
	        	player2 = new Monkey(0, 0, symb, i, j);
				maze.setCell(i, j, player2);
	        } else {
	        	//create a banana
	        	Banana sprite = new Banana(1, i, j, MazeConstants.BANANA);
	        	bananas.add(sprite);
	        	maze.setCell(i, j, sprite);
	        }
	        
	        }
	    i++;
	        	
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
    
    /**
     * Returns true if cell is a valid spot to move
     * @param move the Sprite inside ArrayGrid
     * @return true if Sprite is not a wall, another player and not a visited sprite
     */
    
    private boolean valid_space(Sprite move){
    	boolean isWall = move.getSymbol() != MazeConstants.WALL;
    	boolean isVisited = move.getSymbol() != MazeConstants.VISITED;
    	boolean isP1 = move.getSymbol() != MazeConstants.P1;
    	boolean isP2 = move.getSymbol() != MazeConstants.P2;
    	
    	return  isWall && isVisited && isP1 && isP2;
    }
    
    private boolean mb_valid_space(Sprite move){
    	return this.valid_space(move) && move.getSymbol() != MazeConstants.BANANA && move.getSymbol() != MazeConstants.MOBILE_BANANA;
    }
    
    /**
     * Adds a visited sprite in original position and checks if the move symbol is a Banana and increments the players score
     * @param player the player to move	
     * @param col the player's current column position
     * @param row the player's current row position
     * @param move_symbol the symbol of the sprite in which the player will move into
     */
    
    private void move_sprite(Monkey player, int col, int row, Sprite move, String direction){
		if(this.valid_space(move)){
			/* create a new visited sprite and store it in player's original position */
			VisitedHallway visited = new VisitedHallway(MazeConstants.VISITED, row, col);
			maze.setCell(row, col, visited);
			if(move.getSymbol() == MazeConstants.MOBILE_BANANA){
				bananas.remove(move);
				player.score += MazeConstants.MOBILE_BANANA_SCORE;
			} else if(move.getSymbol() == MazeConstants.BANANA) {
				bananas.remove(move);
				player.score += MazeConstants.BANANA_SCORE;
			}
			
			switch(direction){
				case "up":
					player.move(player.getColumn(), player.getRow()-1);
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "down":
					player.move(player.getColumn(), player.getRow()+1);
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "right":
					player.move(player.getColumn()+1, player.getRow());
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "left":
					player.move(player.getColumn()-1, player.getRow());
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
			}
			
			
			
		}
    }
    
    private boolean inBound(int row, int col){
    	return row < maze.getNumRows() && col < maze.getNumCols();
    }
    
    private void move_mobile(){

    	for(int i = 0; i < bananas.size(); i++){
			Banana banana = bananas.get(i);
			
    		if(banana.getSymbol() == MazeConstants.MOBILE_BANANA){
    			MobileBanana mobile = (MobileBanana) banana;
    			//pick a random number
    			int randomno = random.nextInt(5);
    	
    			switch(randomno){
    			//if number = 0, then move up
    			case 1:
    				if(this.inBound(mobile.getRow()-1, mobile.getColumn()) && this.mb_valid_space(maze.getCell(mobile.getRow()-1, mobile.getColumn()))){
    					maze.setCell(mobile.getRow(), mobile.getColumn(), new UnvisitedHallway(MazeConstants.VACANT, mobile.getRow(), mobile.getColumn()));
    					mobile.move(mobile.getRow()-1, mobile.getColumn());
    					maze.setCell(mobile.getRow(), mobile.getColumn(), mobile);
    				}
    			break;
    			//if number = 1, then move down
    			case 2:
    				if(this.inBound(mobile.getRow()+1, mobile.getColumn()) && this.mb_valid_space(maze.getCell(mobile.getRow()+1, mobile.getColumn()))){
    					maze.setCell(mobile.getRow(), mobile.getColumn(), new UnvisitedHallway(MazeConstants.VACANT, mobile.getRow(), mobile.getColumn()));
    					mobile.move(mobile.getRow()+1, mobile.getColumn());
    					maze.setCell(mobile.getRow(), mobile.getColumn(), mobile);
    				}
    			break;
    			//if number = 2, then move left
    			case 3:
    				if(this.inBound(mobile.getRow(), mobile.getColumn()-1) && this.mb_valid_space(maze.getCell(mobile.getRow(), mobile.getColumn()-1))){
    					maze.setCell(mobile.getRow(), mobile.getColumn(), new UnvisitedHallway(MazeConstants.VACANT, mobile.getRow(), mobile.getColumn()));
    					mobile.move(mobile.getRow(), mobile.getColumn()-1);
    					maze.setCell(mobile.getRow(), mobile.getColumn(), mobile);
    				}
    			break;
    			case 4:
    			//if number = 3, then move right
    				if(this.inBound(mobile.getRow(), mobile.getColumn()+1) && this.mb_valid_space(maze.getCell(mobile.getRow(), mobile.getColumn()+1))){
    					maze.setCell(mobile.getRow(), mobile.getColumn(), new UnvisitedHallway(MazeConstants.VACANT, mobile.getRow(), mobile.getColumn()));
    					mobile.move(mobile.getRow(), mobile.getColumn()+1);
    					maze.setCell(mobile.getRow(), mobile.getColumn(), mobile);
    				}
    			break;

				}
    		}
    	}
    	
    	
    }
    
    /**
     * Given a certain key from the keyboard input, moves the player in a certain direction
     * @param nextMove input key from the keyboard
     */
    
    public void move(char nextMove){
    	/* Mobile Banana movement */
    	
    	//player1
    	if(nextMove == MazeConstants.P1_UP){
        	Sprite tomove = maze.getCell(player1.getRow()-1, player1.getColumn());
    		this.move_sprite(player1, player1.getColumn(), player1.getRow(), tomove, "up");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P1_LEFT){
    		this.move_sprite(player1, player1.getColumn(), player1.getRow(), maze.getCell(player1.getRow(), player1.getColumn()-1), "left");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P1_DOWN){
    		this.move_sprite(player1, player1.getColumn(), player1.getRow(), maze.getCell(player1.getRow()+1, player1.getColumn()), "down");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P1_RIGHT){
    		this.move_sprite(player1, player1.getColumn(), player1.getRow(), maze.getCell(player1.getRow(), player1.getColumn()+1), "right");
    		this.move_mobile();
    	}
    	
    	//player2
    	if(nextMove == MazeConstants.P2_UP){
    		this.move_sprite(player2, player2.getColumn(), player2.getRow(), maze.getCell(player2.getRow()-1, player2.getColumn()), "up");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P2_LEFT){
    		this.move_sprite(player2, player2.getColumn(), player2.getRow(), maze.getCell(player2.getRow(), player2.getColumn()-1), "left");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P2_DOWN){
    		this.move_sprite(player2, player2.getColumn(), player2.getRow(), maze.getCell(player2.getRow()+1, player2.getColumn()), "down");
    		this.move_mobile();
    	} else if(nextMove == MazeConstants.P2_RIGHT){
    		this.move_sprite(player2, player2.getColumn(), player2.getRow(), maze.getCell(player2.getRow(), player2.getColumn()+1), "right");
    		this.move_mobile();
    	}
    }
    
    /**
     * Returns the ID of the player with the greatest score
     */
    public int hasWon(){
    	int player1sc = player1.getScore();
    	int player2sc = player2.getScore();
    	
    	if((bananas.size() == 0 && player1sc == player2sc) || this.isBlocked()){
    		return 3;
    	} else if(player1sc > player2sc && bananas.size() == 0){
    		return 1;
    	} else if(player1sc < player2sc && bananas.size() == 0){
    		return 2;
    	} else {
    		return 0;
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
	public void setMaze(ArrayGrid<Sprite> maze) {
		this.maze = maze;
	}


    /**
     * Returns player 1's Monkey object
     * @return the player 1 monkey object
     */
	public Monkey getPlayerOne() {
		return player1;
	}

	/**
	 * Changes the player1 Monkey object 
	 * @param player1 object to be set 
	 */
	public void setPlayerOne(Monkey player1) {
		this.player1 = player1;
	}

	/**
	 * Returns player 2's Monkey object
	 * @return the player 2 monkey object
	 */
	public Monkey getPlayerTwo() {
		return player2;
	}

	/**
	 * Sets the player 2 Monkey object
	 * @param player2 monkey object
	 */
	public void setPlayerTwo(Monkey player2) {
		this.player2 = player2;
	}
	
	/**
	 * Returns the number of rows in the maze file 
	 * @return the number of rows in maze file
	 * @throws IOException
	 */
	public int getNumRows() throws IOException{
		int[] dimensions = this.getDimensions(MazeConstants.FILENAME);
		return dimensions[0];
	}
	
	/**
	 * Returns the number of columns in the maze file
	 * @return the number of columns in the maze file
	 * @throws IOException
	 */
	public int getNumCols() throws IOException{
		int[] dimensions = this.getDimensions(MazeConstants.FILENAME);
		return dimensions[1];
	}
    
	/**
	 * Checks and returns a boolean if symbols of top, bottom, left, right of both players are not space
	 * @return True if all symbols of top, left, right and bottom are not spaces for both players. 
	 */
	public boolean isBlocked(){
		//check on all sides if symbol is ' '
		int player1row = this.player1.getRow();
		int player2row = this.player2.getRow();
		int player1col = this.player1.getColumn();
		int player2col = this.player2.getColumn();
		
		//check player1
		Sprite player1topcell = maze.getCell(player1row-1, player1col);
		Sprite player1bottomcell = maze.getCell(player1row+1, player1col);
		Sprite player1leftcell = maze.getCell(player1row, player1col-1);
		Sprite player1rightcell = maze.getCell(player1row, player1col+1);
		

		//check player2
		Sprite player2topcell = maze.getCell(player2row-1, player2col);
		Sprite player2bottomcell = maze.getCell(player2row+1, player2col);
		Sprite player2leftcell = maze.getCell(player2row, player2col-1);
		Sprite player2rightcell = maze.getCell(player2row, player2col+1);
		
		//player blocked bools
		boolean player1;
		boolean player2;
		
		boolean player1_vacant = player1topcell.getSymbol() != MazeConstants.VACANT && player1bottomcell.getSymbol() != MazeConstants.VACANT && player1leftcell.getSymbol() != MazeConstants.VACANT && player1rightcell.getSymbol() != MazeConstants.VACANT;
		boolean player2_vacant = player2topcell.getSymbol() != MazeConstants.VACANT && player2bottomcell.getSymbol() != MazeConstants.VACANT && player2leftcell.getSymbol() != MazeConstants.VACANT && player2rightcell.getSymbol() != MazeConstants.VACANT;
		
		boolean player1_mobile = player1topcell.getSymbol() != MazeConstants.MOBILE_BANANA && player1bottomcell.getSymbol() != MazeConstants.MOBILE_BANANA && player1leftcell.getSymbol() != MazeConstants.MOBILE_BANANA && player1rightcell.getSymbol() != MazeConstants.MOBILE_BANANA;
		boolean player2_mobile = player2topcell.getSymbol() != MazeConstants.MOBILE_BANANA && player2bottomcell.getSymbol() != MazeConstants.MOBILE_BANANA && player2leftcell.getSymbol() != MazeConstants.MOBILE_BANANA && player2rightcell.getSymbol() != MazeConstants.MOBILE_BANANA;
		
		boolean player1_banana = player1topcell.getSymbol() != MazeConstants.BANANA && player1bottomcell.getSymbol() != MazeConstants.BANANA && player1leftcell.getSymbol() != MazeConstants.BANANA && player1rightcell.getSymbol() != MazeConstants.BANANA;
		boolean player2_banana = player2topcell.getSymbol() != MazeConstants.BANANA && player2bottomcell.getSymbol() != MazeConstants.BANANA && player2leftcell.getSymbol() != MazeConstants.BANANA && player2rightcell.getSymbol() != MazeConstants.BANANA;
		
		
		
		if(player1_vacant && player1_mobile && player1_banana){
			player1 = true;
		} else {
			player1 = false;
		}
		
		
		if(player2_vacant && player2_mobile && player2_banana){
			player2 = true;
		} else {
			player2 = false;
		}
		
		return player1 && player2;
		
	}
	
    
    public Sprite get(int i, int j){
		return this.getMaze().getCell(i, j);
	}
	
}
