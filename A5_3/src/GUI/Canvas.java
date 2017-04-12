package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JPanel;

import Game.Block;
import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance vaiables
	private Board board;
	private HashMap<Integer, Color> colors;
	
	private final int OFFSET = 4;
	private final int ARC_WIDTH = 25;
	
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
		for (int i = 2; i < board.getNumOfBlocks() + 2; i++) {
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
		int xSize = getWidth() / (board.getWidth() + 2);
		int ySize = getHeight() / (board.getHeight() + 2);
		
		// Paint the background black
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Paint the ground and exit grey
		int exitCol = grid.length + 1;
		int exitRow = board.getExitRow() + 1;
		int groundWidth = board.getWidth() * xSize;
		int groundHeight = board.getHeight() * ySize;
		
		g.setColor(new Color(128, 128, 128));
		g.fillRect(xSize, ySize, groundWidth, groundHeight);
		g.fillRect(exitCol * xSize, exitRow * ySize, xSize, ySize);
		
		// Paint the blocks
		for (Entry<Integer, Block> block: board.getBlocks().entrySet()) {
			int blockID = block.getValue().getID();
			int blockCol = block.getValue().getCol();
			int blockRow = block.getValue().getRow();
			int blockLength = block.getValue().getLength();
			
			// Set color and paint block according to orientation
			g.setColor(colors.get(blockID));
			if (block.getValue().getOrientation() == Block.HOR) {
				g.fillRoundRect(((blockCol * xSize) + xSize) + (OFFSET / 2),
								((blockRow * ySize) + ySize) + (OFFSET / 2),
								(xSize * blockLength) - OFFSET, ySize - OFFSET,
								ARC_WIDTH, ARC_WIDTH);
			} else {
				g.fillRoundRect(((blockCol * xSize) + xSize) + (OFFSET / 2),
								((blockRow * ySize) + ySize) + (OFFSET / 2),
								xSize - OFFSET, (ySize * blockLength) - OFFSET,
								ARC_WIDTH, ARC_WIDTH);
			}
		}
	}
}
