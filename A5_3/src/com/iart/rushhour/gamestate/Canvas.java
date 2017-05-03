package com.iart.rushhour.gamestate;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 2924971106133287334L;

	// Instance variables
	private GameStateManager gsm;
	
	/**
	 * Creates a Canvas instance
	 * @param pgs the canvas' parent state
	 */
	public Canvas(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	// Instance methods
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
