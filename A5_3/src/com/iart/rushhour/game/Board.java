package com.iart.rushhour.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import com.iart.rushhour.logic.Move;

public class Board {

	// Instance variables
	private int f, g;
	private int visitedNodes;
	
	private int[][] grid;
	private final int width;
	private final int height;
	private final int exitRow;
	
	private Move move;
	private Board parent;
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
	public Board(final int width, final int height, final int exitRow) {
		move = null;
		parent = null;
		visitedNodes = 0;
		
		this.width = width;
		this.height = height;
		this.exitRow = exitRow;

		grid = new int[width][height];
		blocks = new HashMap<Integer, Block>();

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				grid[i][j] = 0;
	}

	/**
	 * Creates a cloned Board instance
	 * @param b the board to be cloned
	 */
	public Board(Board b) {
		width = b.getWidth();
		height = b.getHeight();
		exitRow = b.getExitRow();

		grid = new int[width][height];
		blocks = new HashMap<Integer, Block>();

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				grid[i][j] = b.getGrid()[i][j];

		for (Entry<Integer, Block> block: b.getBlocks().entrySet()) {
			Block bl = block.getValue();
			blocks.put(bl.getID(), new Block(bl.getID(), bl.getRow(), bl.getCol(), bl.getLength(), bl.getOrientation()));
		}
	}

	// Instance methods
	/** Returns the f value of this node */
	public int getF() { return f; }
	
	/** Returns the g value of this node */
	public int getG() { return g; }
	
	/** Returns the number of visited nodes up to this node */
	public int getVisitedNodes() { return visitedNodes; }
	
	/** Returns the move that reaches this node */
	public Move getMove() { return move; }
	
	/** Returns the parent of this node */
	public Board getParent() { return parent; }
	
	/** Returns the grid of this board */
	public int[][] getGrid() { return grid; }

	/** Returns the width of the board */
	public final int getWidth() { return width; }

	/** Returns the height of the board */
	public final int getHeight() { return height; }

	/** Returns the row of the Exit */
	public final int getExitRow() { return exitRow; }

	/** Returns the list of blocks on the board */
	public HashMap<Integer, Block> getBlocks() { return blocks; }

	/** Returns the number of blocks on the board */
	public int getNumOfBlocks() { return blocks.size(); }

	/**
	 * Returns the block with the specified ID
	 * @param ID ID of the block to be returned
	 */
	public Block getBlockByID(final int ID) { return blocks.containsKey(ID) ? blocks.get(ID) : null; }

	/**
	 * Sets the f value of this node
	 * @param f the f value to be set
	 */
	public void setF(int f) { this.f = f; }
	
	/**
	 * Sets the g value of this node
	 * @param g the g value to be set
	 */
	public void setG(int g) { this.g = g; }
	
	/**
	 * Sets the number of visited nodes up to this node
	 * @param visitedNodes the number of visited nodes to be set
	 */
	public void setVisitedNodes(int visitedNodes) { this.visitedNodes = visitedNodes; }
	
	/**
	 * Sets the move that reaches this node
	 * @param parent the move to be set
	 */
	public void setMove(Move move) { this.move = move; }
	
	/**
	 * Sets the parent of this node
	 * @param parent the parent to be set
	 */
	public void setParent(Board parent) { this.parent = parent; }
	
	/**
	 * Checks if the block is out of bounds of the board
	 * @param newBlock new block to be added to the board
	 */
	private boolean isOutOfBounds(Block newBlock) {
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
	private boolean isPositionTaken(Block newBlock) {
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
	public boolean canBlockBeMoved(final int blockID, final int direction) {
		Block block = blocks.get(blockID);

		if (block.getOrientation() == Block.HOR) {
			if (direction == LEFT && block.getCol() > 0) {
				if (grid[block.getRow()][block.getCol() - 1] == 0)
					return true;
			} else if (direction == RIGHT && block.getCol() + block.getLength() < width) {
				if (grid[block.getRow()][block.getCol() + block.getLength()] == 0)
					return true;
			}
		} else {
			if (direction == UP && block.getRow() > 0) {
				if (grid[block.getRow() - 1][block.getCol()] == 0)
					return true;
			} else if (direction == DOWN && block.getRow() + block.getLength() < height) {
				if (grid[block.getRow() + block.getLength()][block.getCol()] == 0)
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
	public void moveBlock(final int blockID, final int direction) {
		Block block = blocks.get(blockID);

		switch (direction) {
		case UP:
			grid[block.getRow() - 1][block.getCol()] = block.getID();
			grid[block.getRow() + block.getLength() - 1][block.getCol()] = 0;
			blocks.get(block.getID()).setPosition(block.getRow() - 1, block.getCol());
			break;
		case DOWN:
			grid[block.getRow()][block.getCol()] = 0;
			grid[block.getRow() + block.getLength()][block.getCol()] = block.getID();
			blocks.get(block.getID()).setPosition(block.getRow() + 1, block.getCol());
			break;
		case LEFT:
			grid[block.getRow()][block.getCol() - 1] = block.getID();
			grid[block.getRow()][block.getCol() + block.getLength() - 1] = 0;
			blocks.get(block.getID()).setPosition(block.getRow(), block.getCol() - 1);
			break;
		case RIGHT:
			grid[block.getRow()][block.getCol()] = 0;
			grid[block.getRow()][block.getCol() + block.getLength()] = block.getID();
			blocks.get(block.getID()).setPosition(block.getRow(), block.getCol() + 1);
			break;
		}
	}

	/** Checks if the game is over */
	public boolean isGameOver() {
		Block mainBlock = blocks.get(Block.MAIN_BLOCK_ID);

		if (mainBlock.getRow() == exitRow && mainBlock.getCol() + mainBlock.getLength() == width)
			return true;

		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;

		result = prime * result + width;
		result = prime * result + height;
		result = prime * result + exitRow;
		result = prime * result + Arrays.deepHashCode(grid);
		result = prime * result + ((blocks == null) ? 0 : blocks.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Board b = (Board) obj;
		if (width == b.getWidth() && height == b.getHeight() && exitRow == b.getExitRow()) {
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					if (grid[i][j] != b.getGrid()[i][j])
						return false;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		String str = "";

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j] == 0)
					str += "__" + " ";
				else if (grid[i][j] < 10)
					str += "0" + grid[i][j] + " ";
				else
					str += grid[i][j] + " ";
			}
			str += "\n";
		}

		return str;
	}
}
