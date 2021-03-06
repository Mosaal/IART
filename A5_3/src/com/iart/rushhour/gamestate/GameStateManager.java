package com.iart.rushhour.gamestate;

import com.iart.rushhour.app.Application;
import com.iart.rushhour.game.Board;

public class GameStateManager {

	// Instance variables
	public int mode;
	public int level;
	public Board board;
	
	private Application root;
	private GameState gameState;
	
	// Static variables
	public static final int MAIN_MENU_STATE = 0;
	public static final int PLAY_GAME_STATE = 1;
	
	/**
	 * Creates a GameStateManager instance
	 * @param root the application's root frame
	 */
	public GameStateManager(Application root) {
		mode = 0;
		level = 1;
		this.root = root;
	}
	
	// Instance methods
	/** Returns the state manager's root frame */
	public Application getRoot() { return root; }
	
	/**
	 * Updates the amount of moves displayed on the moves label
	 * @param moves the amount of moves to be displayed
	 */
	public void updateMovesLbl(int moves) {
		if (gameState instanceof PlayGameState)
			((PlayGameState) gameState).updateMovesLbl(moves);
	}
	
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
