package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Game.Block;
import Game.Board;

public class Utils {
	
	private static int ID;
	private static int row;
	private static int col;
	private static int orientation;
	private static int length;
	
	/**
	 * Checks whether the arguments of the text file are valid or not
	 * @param width width of the board
	 * @param height height of the board
	 * @param block string array with information about a block
	 */
	private static boolean validArgs(final int width, final int height, String[] block) {
		ID = Integer.parseInt(block[0]);
		row = Integer.parseInt(block[1]);
		col = Integer.parseInt(block[2]);
		orientation = Integer.parseInt(block[3]);
		length = Integer.parseInt(block[4]);
		
		if (ID > 0 && length > 0) {
			if (row >= 0 && col >= 0) {
				if (orientation == Block.HOR) {
					if (col + length <= width)
						return true;
				} else if (orientation == Block.VER) {
					if (row + length <= height)
						return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Loads the specified level
	 * @param level level to be loaded
	 * @throws IOException
	 */
	public static Board loadLevel(final int level) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("res/levels/Level" + level + ".txt")));
		
		// Read first line
		String line = br.readLine();
		int numBlocks = Integer.parseInt(line);
		
		// Read second line
		line = br.readLine();
		String[] dimension = line.split(":");
		
		// Parse and validate size of the board
		int width = Integer.parseInt(dimension[0]);
		int height = Integer.parseInt(dimension[1]);
		
		if (width <= 0 || height <= 0) {
			br.close();
			return null;
		}
		
		// Parse and validate information about the blocks
		int i = 0;
		Block[] blocks = new Block[numBlocks];
		while ((line = br.readLine()) != null) {
			if (validArgs(width, height, line.split(":"))) {
				blocks[i++] = new Block(ID, row, col, length, orientation);
			} else {
				br.close();
				return null;
			}
		}
		
		// Create board and blocks
		Board board = new Board(width, height);
		for (int b = 0; b < numBlocks; b++) {
			if (!board.addBlock(blocks[b])) {
				br.close();
				return board;
			}
		}
		
		br.close();
		return board;
	}
}
