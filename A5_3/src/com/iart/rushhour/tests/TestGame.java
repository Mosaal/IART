package com.iart.rushhour.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;
import com.iart.rushhour.logic.Algorithms;
import com.iart.rushhour.logic.Utils;

public class TestGame {

	private final int HEURISTIC = 0;
	private final int TEST_LEVEL = 1;
	
	/** Tests the creation of the board and its components */
	@Test
	public void testBlockCreation() {
		Block block = new Block(1, 0, 0, 3, Block.HOR);

		assertEquals(1, block.getID());
		assertEquals(0, block.getRow());
		assertEquals(0, block.getCol());
		assertEquals(3, block.getLength());
		assertEquals(Block.HOR, block.getOrientation());
	}

	/** Tests the creation of the block and its components */
	@Test
	public void testBoardCreation() {
		Board board = new Board(10, 10, 2);

		assertEquals(10, board.getWidth());
		assertEquals(10, board.getHeight());
		assertEquals(2, board.getExitRow());
		assertEquals(true, board.getBlocks().isEmpty());

		for (int i = 0; i < board.getHeight(); i++)
			for (int j = 0; j < board.getWidth(); j++)
				assertEquals(0, board.getGrid()[i][j]);
	}

	/** Tests the placement of the blocks on the board */
	@Test
	public void testBlockPlacement() {
		Board board = new Board(6, 6, 2);

		assertEquals(true, board.addBlock(new Block(1, 0, 0, 3, Block.HOR)));
		assertEquals(false, board.addBlock(new Block(1, 0, 0, 3, Block.HOR)));
		assertEquals(true, board.addBlock(new Block(2, 1, 0, 3, Block.VER)));
		assertEquals(false, board.addBlock(new Block(3, 2, 0, 3, Block.VER)));
	}

	/** Tests the movement of the blocks on the board */
	@Test
	public void testBlockMovement() {
		Board board = new Board(6, 6, 2);

		board.addBlock(new Block(1, 0, 0, 3, Block.HOR));
		board.addBlock(new Block(2, 1, 0, 2, Block.VER));
		board.addBlock(new Block(3, 5, 3, 3, Block.HOR));
		board.addBlock(new Block(4, 3, 5, 2, Block.VER));

		assertEquals(false, board.canBlockBeMoved(1, Board.UP));
		assertEquals(false, board.canBlockBeMoved(1, Board.DOWN));
		assertEquals(false, board.canBlockBeMoved(1, Board.LEFT));
		assertEquals(true, board.canBlockBeMoved(1, Board.RIGHT));

		assertEquals(false, board.canBlockBeMoved(2, Board.UP));
		assertEquals(true, board.canBlockBeMoved(2, Board.DOWN));
		assertEquals(false, board.canBlockBeMoved(2, Board.LEFT));
		assertEquals(false, board.canBlockBeMoved(2, Board.RIGHT));

		assertEquals(false, board.canBlockBeMoved(3, Board.UP));
		assertEquals(false, board.canBlockBeMoved(3, Board.DOWN));
		assertEquals(true, board.canBlockBeMoved(3, Board.LEFT));
		assertEquals(false, board.canBlockBeMoved(3, Board.RIGHT));

		assertEquals(true, board.canBlockBeMoved(4, Board.UP));
		assertEquals(false, board.canBlockBeMoved(4, Board.DOWN));
		assertEquals(false, board.canBlockBeMoved(4, Board.LEFT));
		assertEquals(false, board.canBlockBeMoved(4, Board.RIGHT));
	}

	/** Tests the loading of a level */
	@Test
	public void testLevelLoading() {
		try { assertNotEquals(null, Utils.loadLevel(TEST_LEVEL)); }
		catch (IOException e) { e.printStackTrace(); }
	}

	/** Tests the BFS algorithm */
	@Test
	public void testBFS() {
		Board board = null;
		try { board = Utils.loadLevel(TEST_LEVEL); }
		catch (IOException e) { e.printStackTrace(); }

		Board lastNode = Algorithms.BFS(board);
		assertNotEquals(null, lastNode);
	}

	/** Tests the DFS algorithm */
	@Test
	public void testDFS() {
		Board board = null;
		try { board = Utils.loadLevel(TEST_LEVEL); }
		catch (IOException e) { e.printStackTrace(); }

		Board lastNode = Algorithms.DFS(board);
		assertNotEquals(null, lastNode);
	}
	
	/** Tests the A* açgorithm */
	@Test
	public void testAStar() {
		Board board = null;
		try { board = Utils.loadLevel(TEST_LEVEL); }
		catch (IOException e) { e.printStackTrace(); }
		
		if (HEURISTIC > -1 && HEURISTIC < 3) {
			Board lastNode = Algorithms.AStar(board, HEURISTIC);
			assertNotEquals(null, lastNode);
		}
	}
}
