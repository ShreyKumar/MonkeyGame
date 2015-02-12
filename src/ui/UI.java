package ui;

import java.io.IOException;

/**
 * The UI interface for handling front-end 
 * @author Shreyansh Kumar 
 *
 */
public interface UI {
	
	/**
	 * 
	 * @throws IOException
	 */
	public void launchGame() throws IOException;
	public void displayWinner();
}
