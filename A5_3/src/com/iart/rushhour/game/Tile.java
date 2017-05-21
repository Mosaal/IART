package com.iart.rushhour.game;

public class Tile {

	// Instance variables
	private int x, y;
	private int width, height;

	/**
	 * Creates a Tile instance
	 * @param x the x coordinate of the tile
	 * @param y the y coordinate of the tile
	 * @param width the width of the tile
	 * @param height the height of the tile
	 */
	public Tile(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Instance method
	/**
	 * Checks whether the given position is contained by the Tile
	 * @param x the x coordinate of the position to be checked
	 * @param y the y coordinate of the position to be checked
	 */
	public boolean contains(int x, int y) {
		if (x > this.x && x < this.x + this.width)
			if (y > this.y && y < this.y + this.height)
				return true;
		return false;
	}
}
