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
	
	// Static variables
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
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
	
	/** Returns the array with the information about the exit */
	public final int[] getExit() { return exit; }
	
	/** Returns the list of blocks on the board */
	public HashMap<Integer, Block> getBlocks() { return blocks; }
	
	/**
	 * Checks if the block is out of bounds of the board
	 * @param newBlock new block to be added to the board
	 */
	public boolean isOutOfBounds(Block newBlock) {
		if (newBlock.getRow() >= 0 && newBlock.getCol() >= 0) {
			if (newBlock.getOrientation() == Block.HOR) {
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
			if (newBlock.getOrientation() == Block.HOR) {
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
					blocks.put(newBlock.getID(), newBlock);
					
					for (int i = 0; i < newBlock.getLength(); i++) {
						if (newBlock.getOrientation() == Block.HOR)
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
	
	/**
	 * Checks if the specified block can be moved in a given direction
	 * @param block block to be moved
	 * @param direction direction the block is going to be moved to
	 */
	public boolean canBlockBeMoved(Block block, final int direction) {
		// TODO
		if (block.getOrientation() == Block.HOR) {
			if (direction == LEFT && block.getCol() > 0) {
				if (grid[block.getRow()][block.getCol() - 1] == 0)
					return true;
			} else if (direction == RIGHT && block.getCol() < width - 1) {
				if (grid[block.getRow()][block.getCol() + 1] == 0)
					return true;
			}
		} else {
			if (direction == UP && block.getRow() > 0) {
				if (grid[block.getRow() - 1][block.getCol()] == 0)
					return true;
			} else if (direction == DOWN && block.getRow() < height - 1) {
				if (grid[block.getRow() + 1][block.getCol()] == 0)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Moves the specified block in a given direction
	 * @param block block to be moved
	 * @param direction direction the block is going to be moved to
	 */
	public void moveBlock(Block block, final int direction) {
		// TODO
		if (canBlockBeMoved(block, direction)) {
			System.out.println("YES");
			switch (direction) {
			case UP:
				grid[block.getRow() - 1][block.getCol()] = block.getID();
				grid[block.getRow() + block.getLength()][block.getCol()] = 0;
				blocks.get(block.getID()).setPosition(block.getRow() - 1, block.getCol());
				break;
			case DOWN:
				grid[block.getRow()][block.getCol()] = 0;
				grid[block.getRow() + block.getLength()][block.getCol()] = block.getID();
				blocks.get(block.getID()).setPosition(block.getRow() + 1, block.getCol());
				break;
			case LEFT:
				grid[block.getRow()][block.getCol() - 1] = block.getID();
				grid[block.getRow()][block.getCol() + block.getLength()] = 0;
				blocks.get(block.getID()).setPosition(block.getRow(), block.getCol() - 1);
				break;
			case RIGHT:
				grid[block.getRow()][block.getCol()] = 0;
				grid[block.getRow()][block.getCol() + block.getLength()] = block.getID();
				blocks.get(block.getID()).setPosition(block.getRow(), block.getCol() + 1);
				break;
			}
		}
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
