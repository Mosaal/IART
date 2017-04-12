package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;

import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance vaiables
	private Board board;
	private final int BORDER_OFFSET = 2;
	private HashMap<Integer, Color> colors;
	
	/**
	 * Creates a Canvas instance
	 * @param board the board to be set on the canvas
	 */
	public Canvas(Board board) {
		this.board = board;
		colors = new HashMap<Integer, Color>();
		
		// Set the predefined colors
		colors.put(-1, new Color(0, 0, 0));
		colors.put(0, new Color(128, 128, 128));
		colors.put(1, new Color(255, 0, 0));
		
		// Create as many colors as necessary
		for (int i = 2; i < board.getNumOfBlocks(); i++) {
			// Randomize a color
			int r = (int) (new Random().nextDouble() * 255.0);
			int g = (int) (new Random().nextDouble() * 255.0);
			int b = (int) (new Random().nextDouble() * 255.0);
			
			// Put it in the map if it is new
			if (!colors.containsValue(new Color(r, g, b)))
				colors.put(i, new Color(r, g, b));
			else
				i--;
		}
		
		System.out.println(colors.toString());
	}
	
	// Instance methods
	/** Returns this instance's board */
	public Board getBoard() { return board; }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Get the grid
		int[][] grid = board.getGrid();
		
		// Set the square size
		int ySize = getHeight() / (board.getHeight() + BORDER_OFFSET);
		int xSize = getWidth() / (board.getWidth() + BORDER_OFFSET);
		
		// Draw the border
		for (int y = 0; y < board.getHeight() + BORDER_OFFSET; y++) {
			for (int x = 0; x < board.getWidth() + BORDER_OFFSET; x++) {
				// Set the color
				if (y == board.getExitRow() + 1 && x == (grid.length + BORDER_OFFSET) - 1)
					g.setColor(colors.get(0));
				else
					g.setColor(new Color(0, 0, 0));
				
				// Check if it belongs to the border
				if (y == 0 || y == (grid.length + BORDER_OFFSET) - 1)
					g.fillRect(x * xSize, y * ySize, xSize, ySize);
				if (x == 0 || x == (grid.length + BORDER_OFFSET) - 1)
					g.fillRect(x * xSize, y * ySize, xSize, ySize);
			}
		}
		
		// Draw the grid
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				// Set the color
				g.setColor(colors.get(grid[y][x]));
				
				// Draw the square
				g.fillRect((x * xSize) + xSize, (y * ySize) + ySize, xSize, ySize);
			}
		}
	}
}
