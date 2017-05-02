package com.iart.rushhour.gamestate;

import com.iart.rushhour.app.Application;

public class GameStateManager {

	// Instance variables
	private Application root;
	private GameState gameState;
	
	// Static variables
	public static final int MAIN_MENU_STATE = 0;
	public static final int PLAY_GAME_STATE = 1;
	
	/**
	 * Creates a GameStateManager instance
	 * @param root the application's root frame
	 */
	public GameStateManager(Application root) { this.root = root; }
	
	// Instance methods
	/** Returns the state manager's root frame */
	public Application getRoot() { return root; }
	
	/**
	 * Sets the current game state to a given state
	 * @param state the state to be set
	 */
	public void setState(int state) {
		if (state == MAIN_MENU_STATE)
			gameState = new MainMenuState(this);
		else if (state == PLAY_GAME_STATE)
			gameState = new PlayGameState(this);
		
		root.setContentPane(gameState);
		root.revalidate();
	}
}
