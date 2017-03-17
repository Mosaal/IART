package Game;

import java.util.Map.Entry;
import java.util.HashMap;

public class Board {

	// Instance variables
	private int[][] grid;
	private final int width;
	private final int height;
	private HashMap<Integer, Block> blocks;
	
	/**
	 * Creates a Board instance
	 * @param width width of the board, width >= 3
	 * @param height height of the board, height >= 3
	 */
	public Board(final int width, final int height) {
		this.width = width;
		this.height = height;
		
		grid = new int[width][height];
		blocks = new HashMap<Integer, Block>();
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				grid[i][j] = 0;
	}
	
	// Instance methods
	/** Returns the width of the board */
	public final int getWidth() { return width; }
	
	/** Returns the height of the board */
	public final int getHeight() { return height; }
	
	/** Returns the grid of this instance */
	public int[][] getGrid() { return grid; }
	
	/** Returns the list of blocks on the board */
	public HashMap<Integer, Block> getBlocks() { return blocks; }
	
	/**
	 * Adds a new block to the board
	 * @param block new block to be added to the board
	 */
	public void addBlock(Block block) {
		/**
		 * TODO
		 * Must check if ID is unique
		 * Must check if position isn't taken
		 * Then add it to the grid and the map
		 */
	}
	
	/** Returns the current state of the board */
	public String toString() {
		String str = "";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
				str += grid[i][j] + " ";
			str += "\n";
		}
		
		str += "\n";
		for (Entry<Integer, Block> block: blocks.entrySet())
			str += block.toString() + "\n\n";
		
		return str;
	}
}
