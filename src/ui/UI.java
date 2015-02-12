package ui;

import java.io.IOException;

/**
 * The UI interface for handling front-end 
 * @author Shreyansh Kumar 
 *
 */
public interface UI {
	
	/**
	 * Launches the game of the selected UI
	 * @throws IOException
	 */
	public void launchGame() throws IOException;
	
	/**
	 * Displays the end message once game ends
	 */
	public void displayWinner();
}
