package GUI;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance variables
	private int[][] grid;
	
	public Canvas(int[][] grid) {
		this.grid = grid;
	}
	
	// Instance methods
	/** Returns the grid */
	public int[][] getGrid() { return grid; }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
