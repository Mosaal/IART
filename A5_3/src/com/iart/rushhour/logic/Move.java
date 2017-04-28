package com.iart.rushhour.logic;

public class Move {
	
	// Instance variables
	private final int blockID;
	private final int direction;

	/**
	 * Creates a Move instance
	 * @param blockID the unique identifier of the block that was moved
	 * @param direction the direction the block was moved towards
	 */
	public Move(final int blockID, final int direction) {
		this.blockID = blockID;
		this.direction = direction;
	}
	
	// Instance methods
	/** Returns the ID of the block that was moved */
	public final int getBlockID() { return blockID; }
	
	/** Returns the direction of the move */
	public final int getDirection() { return direction; }
	
	@Override
	public String toString() {
		String str = "";
		String[] dirs = new String[] { "Up", "Down", "Left", "Right" };
		
		str += "Block ID: " + blockID;
		str += "\nDirection: " + dirs[direction];
		
		return str;
	}
}
