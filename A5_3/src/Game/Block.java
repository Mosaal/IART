package Game;

import java.awt.Color;
import java.util.Random;

public class Block {

	// Instance variables
	private int row;
	private int col;
	private final int ID;
	private final int length;
	private final int orientation;
	private Color color;

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

		setColor();
	}

	/**
	 * Returns True if the block is occupying a certain spot on the grid
	 */
	public Boolean takesSpot(int y, int x) {
		switch(orientation) {
		case HOR:
			if(y == row) {
				if(col >= x && col <= x+length) return true;
			}
			break;
		case VER:
			if(x == col) {
				if(row >= y && row <= y+length) return true;
			}
			break;
		}

		return false;
	}

	// Instance methods
	/** Returns the ID of the block */
	public final int getID() { return ID; }

	/** Returns the length of the block */
	public final int getLength() { return length; }

	/** Returns the direction of the block */
	public final int getOrientation() { return orientation; }

	/** Returns the row of the top left corner of the block */
	public int getRow() { return row; }

	/** Returns the column of the top left corner of the block */
	public int getCol() { return col; }

	/** Returns the block's colour */
	public Color getColor() { return color; }

	/** Sets the colour of the block */
	public void setColor() {
		Random rand = new Random();
		float hue = rand.nextFloat();
		// Saturation between 0.1 and 0.3
		float saturation = (rand.nextInt(2000) + 2000) / 10000f;
		float luminance = 0.9f;
		color = Color.getHSBColor(hue, saturation, luminance);
	}

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
		str += "\nLength: " + length + "\nOrientation: ";
		str += (orientation == HOR) ? "Horizontal" : "Vertical";

		return str;
	}
}
