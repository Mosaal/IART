package com.iart.rushhour.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.iart.rushhour.game.Block;
import com.iart.rushhour.game.Board;
import com.iart.rushhour.game.Tile;
import com.iart.rushhour.logic.Move;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 2924971106133287334L;

	// Instance variables
	public int validMoves;
	public boolean ignoreAllInput;

	private int xSize;
	private int ySize;
	private int lastClick;
	private boolean waitingForClick;

	private Timer timer;
	private GameStateManager gsm;

	private Tile[][] selectGrid;
	private HashMap<Integer, Color> colors;

	private final int BORDER = 2;
	private final int OFFSET = 6;
	private final int ARC_WIDTH = 25;

	private final int BLOCK_ID = 0;
	private final int ROW = 1;
	private final int COL = 2;

	/**
	 * Creates a Canvas instance
	 * @param pgs the canvas' parent state
	 */
	public Canvas(GameStateManager gsm) {
		this.gsm = gsm;

		// Set the canvas
		validMoves = 0;
		ignoreAllInput = false;
		waitingForClick = false;
		colors = new HashMap<Integer, Color>();

		// Ready the canvas
		updateSelectionGrid();
		generateColors();

		// Set the initial square size
		xSize = getWidth() / (gsm.board.getWidth() + BORDER);
		ySize = getHeight() / (gsm.board.getHeight() + BORDER);
	}

	// Instance methods
	/** Updates the grid used to detect user input */
	private void updateSelectionGrid() {
		// Initialize selection grid
		selectGrid = new Tile[gsm.board.getHeight() + BORDER][gsm.board.getWidth() + BORDER];
		for (int i = 0; i < selectGrid.length; i++)
			for (int j = 0; j < selectGrid[i].length; j++)
				selectGrid[i][j] = new Tile(j * xSize, i * ySize, xSize, ySize);
	}

	/** Generates the blocks' random colors */
	private void generateColors() {
		// Set the predefined colors
		colors.put(0, Color.GRAY);
		colors.put(1, Color.RED);

		// Initialize the colors
		for (int i = 2; i < gsm.board.getNumOfBlocks() + 1; i++) {
			// Generate a random color
			int r = (int) (new Random().nextDouble() * 255.0);
			int g = (int) (new Random().nextDouble() * 255.0);
			int b = (int) (new Random().nextDouble() * 255.0);

			// Store it if it is new
			Color color = new Color(r, g, b);
			if (!colors.containsValue(color) && !color.equals(Color.BLACK))
				colors.put(i, color);
			else
				i--;
		}
	}

	/**
	 * Calculates the direction of the click
	 * @param row the row that got clicked
	 * @param col the column that got clicked
	 */
	private int calculateDirection(int row, int col) {
		int dir = -1;
		int blockRow = gsm.board.getBlockByID(lastClick).getRow();
		int blockCol = gsm.board.getBlockByID(lastClick).getCol();
		int length = gsm.board.getBlockByID(lastClick).getLength();
		int orient = gsm.board.getBlockByID(lastClick).getOrientation();

		if (blockRow == row && orient == 0) {
			if(col == blockCol) dir = Board.LEFT;
			else if(col > blockCol - 2 + length) dir = Board.RIGHT;
		}
		else if (blockCol == col && orient == 1) {
			if(row == blockRow) dir = Board.UP;
			else if(row > blockRow - 2 + length) dir = Board.DOWN;
		}

		return dir;
	}

	/**
	 * Checks if a clicked tile is valid
	 * @param e the mouse event
	 */
	private int[] validTile(MouseEvent e) {
		// Check what Tile got clicked
		for (int i = 0; i < selectGrid.length; i++)
			for (int j = 0; j < selectGrid[i].length; j++)
				if (selectGrid[i][j].contains(e.getX(), e.getY()))
					if (i > 0 && i <= gsm.board.getHeight() && j > 0 && j <= gsm.board.getWidth())
						return new int[] { gsm.board.getGrid()[i - 1][j - 1], i - 1, j - 1 };
		return null;
	}

	/** Handles the canvas' input */
	public void initInput() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!ignoreAllInput) {
					int[] ret = validTile(e);

					if (ret == null)
						return;

					if (ret[BLOCK_ID] != -1) {
						if (!waitingForClick) {
							// Save the last clicked block
							lastClick = ret[BLOCK_ID];
							if(ret[BLOCK_ID] != 0) { waitingForClick = true; }
						}
					}

					if (waitingForClick) {
						// Get direction of the move
						int dir = calculateDirection(ret[ROW], ret[COL]);

						// Check if it can be moved
						if (dir != -1 && gsm.board.canBlockBeMoved(lastClick, dir)) {
							// Update data
							gsm.updateMovesLbl(++validMoves);
							gsm.board.moveBlock(lastClick, dir);
						}

						// Set ready for next click
						waitingForClick = false;

						// Check if the game is over
						if (gsm.board.isGameOver())
							ignoreAllInput = true;
					}

					// Update screen
					repaint();
				}
			}
		});
	}

	/** Stops the animation's timer if it was started */
	public void stopTimer() {
		if (timer != null) {
			timer.purge();
			timer.cancel();
			timer = null;
		}
	}

	/**
	 * Display the moves by the reverse order given by the list
	 * @param moves the list of moves to be displayed
	 */
	public void displayMoves(ArrayList<Move> moves) {
		timer = new Timer();

		for (int i = moves.size() - 1; i >= 0; i--) {
			final int I = i;

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// Get move
					Move move = moves.get(I);

					// Update board and move count
					gsm.updateMovesLbl(++validMoves);
					gsm.board.moveBlock(move.getBlockID(), move.getDirection());

					// Update screen
					repaint();
				}
			}, (moves.size() - 1 - i) * 1000);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Update the square size
		xSize = getWidth() / (gsm.board.getWidth() + BORDER);
		ySize = getHeight() / (gsm.board.getHeight() + BORDER);

		// Update the selection grid
		updateSelectionGrid();

		// Paint the background gray
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Paint the ground and the exit gray
		int exitCol = gsm.board.getWidth() + (BORDER / 2);
		int exitRow = gsm.board.getExitRow() + (BORDER / 2);

		g.setColor(Color.GRAY);
		g.fillRect(exitCol * xSize, exitRow * ySize, xSize, ySize);
		g.fillRect(xSize, ySize, gsm.board.getWidth() * xSize, gsm.board.getHeight() * ySize);

		// Paint the blocks
		for (Entry<Integer, Block> temp: gsm.board.getBlocks().entrySet()) {
			Block block = temp.getValue();

			// Set color and paint block
			g.setColor(colors.get(block.getID()));
			if (block.getOrientation() == Block.HOR) {
				g.fillRoundRect((block.getCol() * xSize) + (OFFSET / 2) + xSize,
						(block.getRow() * ySize) + (OFFSET / 2) + ySize,
						(xSize * block.getLength()) - OFFSET, ySize - OFFSET,
						ARC_WIDTH, ARC_WIDTH);
			} else {
				g.fillRoundRect((block.getCol() * xSize) + (OFFSET / 2) + xSize,
						(block.getRow() * ySize) + (OFFSET / 2) + ySize,
						xSize - OFFSET, (ySize * block.getLength()) - OFFSET,
						ARC_WIDTH, ARC_WIDTH);
			}
		}

		// Paint the currently selected block
		if (waitingForClick) {
			Block block = gsm.board.getBlockByID(lastClick);

			g.setColor(new Color(255, 255, 0));
			if (block.getOrientation() == Block.HOR) {
				g.drawRect((block.getCol() * xSize) + xSize,
						(block.getRow() * ySize) + ySize,
						xSize * block.getLength(), ySize);
			} else {
				g.drawRect((block.getCol() * xSize) + xSize,
						(block.getRow() * ySize) + ySize,
						xSize, ySize * block.getLength());
			}
		}
	}
}
