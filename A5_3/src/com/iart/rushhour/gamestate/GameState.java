package com.iart.rushhour.gamestate;

import javax.swing.JPanel;

public abstract class GameState extends JPanel {

	private static final long serialVersionUID = -8197544069664799663L;
	
	// Inherited variable
	protected GameStateManager gsm;
	
	/** Super constructor */
	protected GameState(GameStateManager gsm) { this.gsm = gsm; }
	
	// Inherited method
	public abstract void initInput();
}
