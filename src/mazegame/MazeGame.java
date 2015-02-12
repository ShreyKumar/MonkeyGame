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
        
        /* Get array dimensions */
        int[] dimensions = getDimensions(layoutFileName);
        
        /* The maze grid to store all Sprites*/
        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
        
        /* A scanner variable to read all the file data */
        Scanner sc = new Scanner(new File(layoutFileName));

        /* INITIALIZE THE GRID HERE */
        
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
        
    	/* A Scanner variable to read all file data */
        Scanner sc = new Scanner(new File(layoutFileName));

        /* Get the entire string line */
        String nextLine = sc.nextLine();
        
        /* Find total number of columns from line length */
        int numCols = nextLine.length();

        /* Find numRows by initializing the variable to 1 */
        int numRows = 1;

        /* find the number of rows */
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    /**
     * Returns true if sprite inside ArrayGrid is a valid spot to move
     * @param move the Sprite inside ArrayGrid
     * @return true if Sprite is not a wall, another player and not a 
     * visited sprite
     */
    
    private boolean valid_space(Sprite move){
    	/* Boolean to determine whether this is a wall sprite*/
    	boolean isWall = move.getSymbol() != MazeConstants.WALL;
    	
    	/* Boolean to determine whether this is a visited sprite */
    	boolean isVisited = move.getSymbol() != MazeConstants.VISITED;
    	
    	/* Boolean to determine whether this is player1 sprite */
    	boolean isP1 = move.getSymbol() != MazeConstants.P1;
    	
    	/* Boolean to determine whether this is player2 sprite */
    	boolean isP2 = move.getSymbol() != MazeConstants.P2;
    	
    	/* Return true when only all conditions above are satisfied */
    	return  isWall && isVisited && isP1 && isP2;
    }
    
    /**
     * Returns true if this MobileBanana's intended cell to move is valid
     * @param move the Sprite to check for
     * @return True if sprite is a valid space for a MobileBanana to move
     */
    private boolean mb_valid_space(Sprite move){
    	return this.valid_space(move) 
    			&& move.getSymbol() != MazeConstants.BANANA
    			&& move.getSymbol() != MazeConstants.MOBILE_BANANA;
    }
    
    /**
     * Adds a visited sprite in original position and checks if the move symbol
     * is a Banana and increments the players score
     * @param player the player to move	
     * @param col the player's current column position
     * @param row the player's current row position
     * @param move_symbol the symbol of the sprite in which the player will 
     * move into
     */
    private void move_sprite(Monkey player, int col, int row, Sprite move, 
    		String direction){
		if(this.valid_space(move)){
			/* Create a new visited sprite and store it in player's original 
			 * position 
			 */
			VisitedHallway visited = new VisitedHallway(MazeConstants.VISITED, 
					row, col);
			maze.setCell(row, col, visited);
			
			/* Increase score and remove banana from the list if the intended 
			 * sprite to be moved is a mobile banana
			 */
			if(move.getSymbol() == MazeConstants.MOBILE_BANANA){
				bananas.remove(move);
				player.score += MazeConstants.MOBILE_BANANA_SCORE;
			} else if(move.getSymbol() == MazeConstants.BANANA) {
				bananas.remove(move);
				player.eatBanana();
			}
			
			/* Check the direction and move in accordingly */
			
			/* At each case:
			 * 1. Change the player's instance variable by calling the move 
			 * method
			 * 2. Change the grid display to appropriate row and column
			 */
			switch(direction){
				case "up":
					player.move(player.getColumn(), player.getRow() + 
							MazeConstants.UP);
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "down":
					player.move(player.getColumn(), player.getRow() + 
							MazeConstants.DOWN);
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "right":
					player.move(player.getColumn() + 
							MazeConstants.RIGHT, player.getRow());
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
				case "left":
					player.move(player.getColumn() + 
							MazeConstants.LEFT, player.getRow());
					maze.setCell(player.getRow(), player.getColumn(), player);
					break;
			}
			
			
			
		}
    }
    
    /**
     * Returns true if given row and column of the array grid are in bound
     * @param row the row to check for
     * @param col the column to check for
     * @return True if the row and col are less than the ArrayGrid's dimension
     */
    private boolean inBound(int row, int col){
    	return row < maze.getNumRows() && col < maze.getNumCols();
    }
    
    /**
     * Moves the Mobile Banana to a random place
     */
    private void move_mobile(){
    	
    	for(int i = 0; i < bananas.size(); i++){
    		/* The Banana the for loop is getting from the list */
			Banana banana = bananas.get(i);
			
			/* Only change when its a Mobile Banana */
    		if(banana.getSymbol() == MazeConstants.MOBILE_BANANA){
    			/* Type cast to Mobile Banana so we are able to use its 
    			 * methods
    			 */
    			MobileBanana mobile = (MobileBanana) banana;
    			
    			/* pick a random number */
    			int randomno = random.nextInt(4);
    	
    			/*
    			 * At each case:
    			 * 1. Check if the row is in bound and the space to move in 
    			 * is valid
    			 * 2. Change the original Sprite object's array grid cell to 
    			 * an UnvisitedHallway
    			 * 3. Change the Mobile Banana's instance variable to the 
    			 * newer position by calling the move method
    			 * 4. Change the grid display to the newer position by calling 
    			 * setCell
    			 * 
    			 */
    			switch(randomno){
    			/* if number = 0, then move up */
    			case 0:
    				if(this.inBound(mobile.getRow() + MazeConstants.UP, 
    						mobile.getColumn()) && this.mb_valid_space(
    								maze.getCell(mobile.getRow() + 
    										MazeConstants.UP, 
    										mobile.getColumn()))){
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							new UnvisitedHallway(
    									MazeConstants.VACANT, mobile.getRow(), 
    										mobile.getColumn()));
    					
    					mobile.move(mobile.getRow() + MazeConstants.UP, 
    							mobile.getColumn());
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							mobile);
    				}
    			break;
    			/* if number = 1, then move down */
    			case 1:
    				if(this.inBound(mobile.getRow()+1, mobile.getColumn()) 
    						&& this.mb_valid_space(
    								maze.getCell(
    										mobile.getRow() + 
    										MazeConstants.DOWN, 
    										mobile.getColumn()))){
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							new UnvisitedHallway(
    									MazeConstants.VACANT, 
    									mobile.getRow(), mobile.getColumn()));
    					
    					mobile.move(mobile.getRow() + 
    							MazeConstants.DOWN, mobile.getColumn());
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							mobile);
    				}
    			break;
    			/* if number = 2, then move left */
    			case 2:
    				if(this.inBound(mobile.getRow(), mobile.getColumn() 
    						+ MazeConstants.LEFT) && this.mb_valid_space(
    								maze.getCell(
    										mobile.getRow(), 
    										mobile.getColumn() + 
    										MazeConstants.LEFT))){
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							new UnvisitedHallway(MazeConstants.VACANT, 
    									mobile.getRow(), mobile.getColumn()));
    					
    					mobile.move(mobile.getRow(), mobile.getColumn() + 
    							MazeConstants.LEFT);
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							mobile);
    				}
    			break;
    			case 3:
    			/* if number = 3, then move right */
    				if(this.inBound(mobile.getRow(), mobile.getColumn() + 
    						MazeConstants.RIGHT) && this.mb_valid_space(
    								maze.getCell(
    										mobile.getRow(), 
    										mobile.getColumn() + 
    										MazeConstants.RIGHT))){
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							new UnvisitedHallway(MazeConstants.VACANT, 
    									mobile.getRow(), mobile.getColumn()));
    					
    					mobile.move(mobile.getRow(), mobile.getColumn() + 
    							MazeConstants.RIGHT);
    					
    					maze.setCell(mobile.getRow(), mobile.getColumn(), 
    							mobile);
    				}
    			break;

				}
    		}
    	}
    	
    	
    }
    
    /**
     * Given a certain key from the keyboard input, moves the player in 
     * a certain direction
     * @param nextMove input key from the keyboard
     */
    
    public void move(char nextMove){
    	
    	/* At each player movement:
    	 * 1. Store the sprite of the cell that the player is going to move
    	 * 2. Call the move_sprite method to move the sprite if it is valid
    	 * 3. Call the move_mobile method to move the mobile banana accordingly
    	 * 
    	 */
    	
    	/* player1 movement */
    	
    	/* top cell */
    	if(nextMove == MazeConstants.P1_UP){
 
    		/* Get current position */
    		int column = player1.getColumn();
    		int row = player1.getRow();
    		
    		/* Move Sprite and MobileBanana */
        	Sprite tomove = maze.getCell(row + MazeConstants.UP, column);
    		this.move_sprite(player1, player1.getColumn(), 
    				player1.getRow(), tomove, "up");
    		this.move_mobile();

        /* left cell */
    	} else if(nextMove == MazeConstants.P1_LEFT){
    		
    		/* Get current position */
    		int column = player1.getColumn();
    		int row = player1.getRow();

    		/* Move Sprite and MobileBanana */
    		Sprite tomove = maze.getCell(row, column + MazeConstants.LEFT);
    		this.move_sprite(player1, column, row, tomove, "left");
    		this.move_mobile();
    	

        /* bottom cell */
    	} else if(nextMove == MazeConstants.P1_DOWN){
    		
    		/* Get current position */
    		int column = player1.getColumn();
    		int row = player1.getRow();

    		/* Move Sprite and MobileBanana */
    		Sprite tomove = maze.getCell(row + MazeConstants.DOWN, column);
    		this.move_sprite(player1, column, row, tomove, "down");
    		this.move_mobile();
    	

        /* right cell */
    	} else if(nextMove == MazeConstants.P1_RIGHT){
    		
    		/* Get current position */
    		int column = player1.getColumn();
    		int row = player1.getRow();

    		/* Move Sprite and MobileBanana */
    		Sprite tomove = maze.getCell(row, column + MazeConstants.RIGHT);
    		this.move_sprite(player1, column, row, tomove, "right");
    		this.move_mobile();
    	}
    	
    	/* player2 movement*/
    	
    	/* top cell */
    	if(nextMove == MazeConstants.P2_UP){
    		
    		/* Get current position */
    		int column = player2.getColumn();
    		int row = player2.getRow();

    		/* Move Sprite and MobileBanana */
        	Sprite tomove = maze.getCell(row + MazeConstants.UP, column);
    		this.move_sprite(player2, player2.getColumn(), 
    				player2.getRow(), tomove, "up");
    		this.move_mobile();
    	
        /* left cell */
    	} else if(nextMove == MazeConstants.P2_LEFT){
    		
    		/* Get current position */
    		int column = player2.getColumn();
    		int row = player2.getRow();

    		/* Move Sprite and MobileBanana */
    		Sprite tomove = maze.getCell(row, column + MazeConstants.LEFT);
    		this.move_sprite(player2, column, row, tomove, "left");
    		this.move_mobile();
    	

        /* bottom cell */
    	} else if(nextMove == MazeConstants.P2_DOWN){
    		
    		/* Get current position */
    		int column = player2.getColumn();
    		int row = player2.getRow();
    		
    		Sprite tomove = maze.getCell(row + MazeConstants.DOWN, column);
    		this.move_sprite(player2, column, row, tomove, "down");
    		this.move_mobile();
    	
        /* right cell */
    	} else if(nextMove == MazeConstants.P2_RIGHT){
    		
    		/* Get current position */
    		int column = player2.getColumn();
    		int row = player2.getRow();

    		/* Move Sprite and MobileBanana */
    		Sprite tomove = maze.getCell(row, column + MazeConstants.RIGHT);
    		this.move_sprite(player2, column, row, tomove, "right");
    		this.move_mobile();
    	}
    }
    
    /**
     * Returns the ID of the player with the greatest score
     */
    public int hasWon(){
    	/* Get player1's score */
    	int player1sc = player1.getScore();
    	
    	/* get player2's score */
    	int player2sc = player2.getScore();
    	
    	/* Tie condition: all bananas must be empty and both players have 
    	 * equal score or both players are blocked
    	 */
    	if((bananas.size() == 0 && player1sc == player2sc) || 
    			this.isBlocked()){
    		
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
     * Returns the ArrayGrid maze object
     * @return ArrayGrid maze object
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
	 * Changes the player 1 Monkey object
	 * @param player 1 object to be set 
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
	 * Returns true if the surrounding cells of both Monkeys are not valid 
	 * spaces to move into.
	 * 
	 * Conditions to check if blocked:
	 * 1. Surrounding cells are not Bananas
	 * 2. Surrounding cells are not MobileBananas
	 * 3. Surrounding cells are not Vacant cells
	 * 
	 * @return True if the surrounding cells of both Monkeys are not valid 
	 * spaces to move into.
	 */
	public boolean isBlocked(){
		
		/* Player 1's  row */
		int p1row = this.player1.getRow();
		
		/* Player 2's row */
		int p2row = this.player2.getRow();
		
		/* Player 1's column */
		int p1col = this.player1.getColumn();
		
		/* Player 2's column */
		int p2col = this.player2.getColumn();
		
		/* Player 1's top, bott, left and right Sprite cell */
		Sprite p1top = maze.getCell(p1row + MazeConstants.UP, p1col);
		Sprite p1bott = maze.getCell(p1row + MazeConstants.DOWN, p1col);
		Sprite p1left = maze.getCell(p1row, p1col + MazeConstants.LEFT);
		Sprite p1right = maze.getCell(p1row, p1col + MazeConstants.RIGHT);
		

		/* Player 2's top, bott, left and right Sprite cell*/
		Sprite p2top = maze.getCell(p2row + MazeConstants.UP, p2col);
		Sprite p2bott = maze.getCell(p2row + MazeConstants.DOWN, p2col);
		Sprite p2left = maze.getCell(p2row, p2col + MazeConstants.LEFT);
		Sprite p2right = maze.getCell(p2row, p2col + MazeConstants.RIGHT);
		
		/* Determine if Player 1 is blocked */
		boolean p1;
		
		/* Determine if Player 2 is blocked */
		boolean p2;
		
		/* Checks if both player 1's and player 2's surrounding cells 
		 * are not vacant 
		 */
		boolean p1_vacant_top = p1top.getSymbol() != MazeConstants.VACANT;
		boolean p1_vacant_bott = p1bott.getSymbol() != MazeConstants.VACANT;
		boolean p1_vacant_left = p1left.getSymbol() != MazeConstants.VACANT;
		boolean p1_vacant_right = p1right.getSymbol() != MazeConstants.VACANT;
		
		
		/* If top, bottom, left and right are not vacant, then surrounding 
		 * cells are not vacant
		 */
		boolean p1_vacant = p1_vacant_top && p1_vacant_bott && 
				p1_vacant_left && p1_vacant_right;
		
		/* Follow same procedure with Player 2*/
		boolean p2_vacant_top = p2top.getSymbol() != MazeConstants.VACANT;
		boolean p2_vacant_bott = p2bott.getSymbol() != MazeConstants.VACANT;
		boolean p2_vacant_left = p2left.getSymbol() != MazeConstants.VACANT;
		boolean p2_vacant_right = p2right.getSymbol() != MazeConstants.VACANT;
		
		boolean p2_vacant = p2_vacant_top && p2_vacant_bott && 
				p2_vacant_left && p2_vacant_right;
		
		/* Check if surrounding cells are not MobileBanana 
		 * Same format as above
		 */
		boolean p1_mobile_top = p1top.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p1_mobile_bott = p1bott.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p1_mobile_left = p1left.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p1_mobile_right = p1right.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		
		
		boolean p1_mobile = p1_mobile_top && p1_mobile_bott && 
				p1_mobile_left && p1_mobile_right;
		
		/* Player 2 */
		boolean p2_mobile_top = p2top.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p2_mobile_bott = p2bott.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p2_mobile_left = p2left.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		boolean p2_mobile_right = p2right.getSymbol() 
				!= MazeConstants.MOBILE_BANANA;
		
		boolean p2_mobile = p2_mobile_top && p2_mobile_bott 
				&& p2_mobile_left && p2_mobile_right;
		
		/* Checks if surrounding cells are not Banana */
		/* Player 1 */
		boolean p1_banana_top = p1top.getSymbol() != MazeConstants.BANANA;
		boolean p1_banana_bott = p1bott.getSymbol() != MazeConstants.BANANA;
		boolean p1_banana_left = p1left.getSymbol() != MazeConstants.BANANA;
		boolean p1_banana_right = p1right.getSymbol() != MazeConstants.BANANA;
		
		boolean p1_banana = p1_banana_top && p1_banana_bott &&
				p1_banana_left && p1_banana_right;
		
		/* Player 2 */
		boolean p2_banana_top = p2top.getSymbol() != MazeConstants.BANANA;
		boolean p2_banana_bott = p2bott.getSymbol() != MazeConstants.BANANA;
		boolean p2_banana_left = p2left.getSymbol() != MazeConstants.BANANA;
		boolean p2_banana_right = p2right.getSymbol() != MazeConstants.BANANA;
		
		boolean p2_banana = p2_banana_top && p2_banana_bott && 
				p2_banana_left && p2_banana_right;
		
		
		/* If the conditions hold, player 1's Sprite is blocked */
		p1 = p1_vacant && p1_mobile && p1_banana;
		
		/* If all conditions hold, player 2's Sprite is blocked */
		p2 = p2_vacant && p2_mobile && p2_banana;
		
		
		return p1 && p2;
		
	}
	
    /**
     * Returns a Sprite from this ArrayGrid maze at given a given row and 
     * column
     * @param i This Sprite's row position to look for
     * @param j This Sprite's column position to look for
     * @return This Sprite object at a given row and column
     */
    public Sprite get(int i, int j){
		return this.getMaze().getCell(i, j);
	}
	
}
