package Game;

public class Block {
	
	// Instance variables
	private int row;
	private int col;
	private final int length;
	private final int direction;
	private final int ID;
	
	// Static variables
	public static final int HOR = 0;
	public static final int VER = 1;
	
	/**
	 * Creates a Block instance
	 * @param length length of the block, length > 1
	 * @param direction specifies whether the block is horizontal (0) or vertical (1)
	 * @param ID unique identifier of the block
	 */
	public Block(final int length, final int direction, final int ID) {
		this.length = length;
		this.direction = direction;
		this.ID = ID;
	}

	// Instance methods
	/** Returns the row of the top left corner of the block */
	public int getRow() { return row; }
	
	/** Returns the column of the top left corner of the block */
	public int getCol() { return col; }
	
	/** Returns the length of the block */
	public final int getLength() { return length; }
	
	/** Returns the direction of the block */
	public final int getDirection() { return direction; }
	
	/** Returns the ID of the block */
	public final int getID() { return ID; }

	/**
	 * Sets the new position of the block based on the top left corner
	 * @param row row of the new position, row >= 0
	 * @param col col of the new position, col >= 0
	 */
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/** Returns information of the block in a string */
	public String toString() {
		String str = "Block #" + ID;
		
		str += "\nRow: " + row + ", Col: " + col;
		str += "\nLength: " + length + "\nDirection: ";
		str += (direction == HOR) ? "horizontal" : "vertical";
		
		return str;
	}
}
