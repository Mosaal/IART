package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance vaiables
	private Board board;
	
	/**
	 * Creates a Canvas instance
	 * @param board the board to be set on the canvas
	 */
	public Canvas(Board board) { this.board = board; }
	
	// Instance methods
	/** Returns this instance's board */
	public Board getBoard() { return board; }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int[][] grid = board.getGrid();
		int ySize = this.getHeight() / board.getHeight();
		int xSize = this.getWidth() / board.getWidth();
		
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				if (grid[y][x] == 0) {
					g.setColor(new Color(128, 128, 128));
					g.fillRect(x * xSize, y * ySize, xSize, ySize);
				} else if (grid[y][x] == 1) {
					g.setColor(new Color(255, 0, 0));
					g.fillRect(x * xSize, y * ySize, xSize, ySize);
				}
			}
		}
	}
}
