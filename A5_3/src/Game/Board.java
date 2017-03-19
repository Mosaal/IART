package Game;

import java.util.Map.Entry;
import java.util.HashMap;

public class Board {

	// Instance variables
	private int[][] grid;
	private final int width;
	private final int height;
	private final int[] exit;
	private HashMap<Integer, Block> blocks;
	
	/**
	 * Creates a Board instance
	 * @param width width of the board, width >= 3
	 * @param height height of the board, height >= 3
	 * @param exitDir specifies the direction of the exit in regards to the board
	 * @param exitRC specifies the row or column of the exit
	 */
	public Board(final int width, final int height, final int exitDir, final int exitRC) {
		this.width = width;
		this.height = height;
		this.exit = new int[] { exitDir, exitRC };
		
		grid = new int[width][height];
		blocks = new HashMap<Integer, Block>();
		
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				grid[i][j] = 0;
	}
	
	// Instance methods
	/** Returns the grid of this board */
	public int[][] getGrid() { return grid; }
	
	/** Returns the width of the board */
	public final int getWidth() { return width; }
	
	/** Returns the height of the board */
	public final int getHeight() { return height; }
	
	/** Returns the list of blocks on the board */
	public HashMap<Integer, Block> getBlocks() { return blocks; }
	
	/**
	 * Checks if the block is out of bounds of the board
	 * @param newBlock new block to be added to the board
	 */
	public boolean isOutOfBounds(Block newBlock) {
		if (newBlock.getRow() >= 0 && newBlock.getCol() >= 0) {
			if (newBlock.getDirection() == Block.HOR) {
				if (newBlock.getCol() + newBlock.getLength() > width)
					return true;
			} else {
				if (newBlock.getRow() + newBlock.getLength() > height)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the position of the new block is already taken on the board
	 * @param newBlock new block to be added to the board
	 */
	public boolean isPositionTaken(Block newBlock) {
		for (int i = 0; i < newBlock.getLength(); i++) {
			if (newBlock.getDirection() == Block.HOR) {
				if (grid[newBlock.getRow()][newBlock.getCol() + i] != 0)
					return true;
			} else {
				if (grid[newBlock.getRow() + i][newBlock.getCol()] != 0)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Adds a new block to the board
	 * @param newBlock new block to be added to the board
	 */
	public boolean addBlock(Block newBlock) {
		if (!blocks.containsKey(newBlock.getID())) {
			if (!isOutOfBounds(newBlock)) {
				if (!isPositionTaken(newBlock)) {
					Block.lastID = newBlock.getID();
					blocks.put(newBlock.getID(), newBlock);
					
					for (int i = 0; i < newBlock.getLength(); i++) {
						if (newBlock.getDirection() == Block.HOR)
							grid[newBlock.getRow()][newBlock.getCol() + i] = newBlock.getID();
						else
							grid[newBlock.getRow() + i][newBlock.getCol()] = newBlock.getID();
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/** Returns the current state of the board in a string */
	public String toString(boolean bls) {
		String str = "";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++)
				str += grid[i][j] + " ";
			str += "\n";
		}
		
		if (bls) {
			str += "\n";
			for (Entry<Integer, Block> block: blocks.entrySet())
				str += block.toString() + "\n\n";
		}
		
		return str;
	}
}
