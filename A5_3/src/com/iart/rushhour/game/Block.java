package com.iart.rushhour.game;

public class Block {

	// Instance variables
	private int row;
	private int col;
	private final int ID;
	private final int length;
	private final int orientation;

	// Static variables
	public static final int HOR = 0;
	public static final int VER = 1;

	/**
	 * Creates a Block instance
	 * @param ID unique identifier of the block, ID > 0
	 * @param row row of the block, row > 0 && row < length of the board
	 * @param col column of the block, col > 0 && row < length of the board
	 * @param length length of the block, length > 1
	 * @param direction specifies whether the block is horizontal (0) or vertical (1)
	 */
	public Block(final int ID, final int row, final int col, final int length, final int orientation) {
		this.ID = ID;
		this.row = row;
		this.col = col;
		this.length = length;
		this.orientation = orientation;
	}

	// Instance methods
	/** Returns the ID of the block */
	public final int getID() { return ID; }

	/** Returns the length of the block */
	public final int getLength() { return length; }

	/** Returns the direction of the block */
	public final int getOrientation() { return orientation; }

	/** Returns the row of the top left corner of the block */
	public final int getRow() { return row; }

	/** Returns the column of the top left corner of the block */
	public final int getCol() { return col; }

	/**
	 * Sets the new position of the block based on the top left corner
	 * @param row row of the new position, row >= 0
	 * @param col col of the new position, col >= 0
	 */
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;

		result = prime * result + ID;
		result = prime * result + row;
		result = prime * result + col;
		result = prime * result + length;
		result = prime * result + orientation;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Block b = (Block) obj;
		if (ID != b.getID())
			return false;

		return true;
	}

	@Override
	public String toString() {
		String str = "Block #" + ID;
		String[] oris = new String[] { "Horizontal", "Vertical" };

		str += "\nRow: " + row + ", Col: " + col;
		str += "\nLength: " + length;
		str += "\nOrientation: " + oris[orientation];

		return str;
	}
}
