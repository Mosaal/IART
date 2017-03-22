package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Block;
import Game.Board;

public class TestGame {

	@Test
	public void testBlockCreation() {
		Block block = new Block(1, 0, 0, 3, Block.HOR);
		
		assertEquals(1, block.getID());
		assertEquals(0, block.getRow());
		assertEquals(0, block.getCol());
		assertEquals(3, block.getLength());
		assertEquals(Block.HOR, block.getOrientation());
	}
	
	@Test
	public void testBoardCreation() {
		Board board = new Board(10, 10, 3, Board.RIGHT);
		
		assertEquals(10, board.getWidth());
		assertEquals(10, board.getHeight());
		assertEquals(3, board.getExit()[0]);
		assertEquals(Board.RIGHT, board.getExit()[1]);
	}
	
	@Test
	public void testBlockPlacement() {
		Board board = new Board(6, 6, 3, Board.RIGHT);
		
		assertEquals(true, board.addBlock(new Block(1, 0, 0, 3, Block.HOR)));
		assertEquals(false, board.addBlock(new Block(1, 0, 0, 3, Block.HOR)));
		assertEquals(true, board.addBlock(new Block(2, 1, 0, 3, Block.VER)));
		assertEquals(false, board.addBlock(new Block(3, 2, 0, 3, Block.VER)));
	}
	
	@Test
	public void testBlockMovement() {
		Board board = new Board(6, 6, Board.RIGHT, 2);
		
		board.addBlock(new Block(1, 0, 0, 3, Block.HOR));
		board.addBlock(new Block(2, 1, 0, 2, Block.VER));
		
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.RIGHT));
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.RIGHT));
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.RIGHT));
		assertEquals(false, board.moveBlock(board.getBlockByID(1), Board.RIGHT));
		
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.LEFT));
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.LEFT));
		assertEquals(true, board.moveBlock(board.getBlockByID(1), Board.LEFT));
		assertEquals(false, board.moveBlock(board.getBlockByID(1), Board.LEFT));
		
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.DOWN));
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.DOWN));
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.DOWN));
		assertEquals(false, board.moveBlock(board.getBlockByID(2), Board.DOWN));
		
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.UP));
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.UP));
		assertEquals(true, board.moveBlock(board.getBlockByID(2), Board.UP));
		assertEquals(false, board.moveBlock(board.getBlockByID(2), Board.UP));
	}
}
