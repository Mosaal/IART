package com.iart.rushhour.gamestate;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 2924971106133287334L;

	// Instance variables
	private PlayGameState pgs;
	
	/**
	 * Creates a Canvas instance
	 * @param pgs the canvas' parent state
	 */
	public Canvas(PlayGameState pgs) {
		this.pgs = pgs;
	}
	
	// Instance methods
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
