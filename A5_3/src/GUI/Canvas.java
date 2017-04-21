package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.Block;
import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance variables
	private int xSize;
	private int ySize;
	private int lastClick;
	private int validMoves;
	private boolean waitingForClick;

	private Board board;
	private GameFrame root;
	private Tile[][] selectGrid;
	private HashMap<Integer, Color> colors;

	private final int BORDER = 2;
	private final int OFFSET = 4;
	private final int ARC_WIDTH = 25;
	private final String[] options = new String[] { "Quit", "Main Menu" };

	/**
	 * Creates a Canvas instance
	 * @param board the board to be displayed on the screen
	 * @param root the parent frame of this panel
	 */
	public Canvas(Board board, GameFrame root) {
		this.root = root;
		this.board = board;

		validMoves = 0;
		waitingForClick = false;
		colors = new HashMap<Integer, Color>();

		// Set the initial square size
		xSize = getWidth() / (board.getWidth() + 2);
		ySize = getHeight() / (board.getHeight() + 2);

		// Ready the canvas
		updateSelectionGrid();
		generateColors();

		// Ignore input if the chosen mode is AI
		if (root.getMode() == 1) {
			handleInput();
		} else {
			// TODO
			// Run algorithm for the board
		}
	}
	
	/** Sets the current board on the screen */
	public void setBoard(Board board) { this.board = board; }
	
	/** Sets the number of valid moves made */
	public void setValidMoves(int validMoves) { this.validMoves = validMoves; }

	/** Updates the grid used to detect user input */
	private void updateSelectionGrid() {
		// Initialize selection grid
		selectGrid = new Tile[board.getHeight() + BORDER][board.getWidth() + BORDER];
		for (int i = 0; i < selectGrid.length; i++)
			for (int j = 0; j < selectGrid[i].length; j++)
				selectGrid[i][j] = new Tile(j * xSize, i * ySize, xSize, ySize);
	}

	/** Generates the block's random colors */
	private void generateColors() {
		// Set the predefined colors
		colors.put(0, Color.GRAY);
		colors.put(1, Color.RED);

		// Initialize the colors
		for (int i = 2; i < board.getNumOfBlocks() + 1; i++) {
			// Generate a random color
			int r = (int) (new Random().nextDouble() * 255.0);
			int g = (int) (new Random().nextDouble() * 255.0);
			int b = (int) (new Random().nextDouble() * 255.0);

			// Store it if it is new
			Color color = new Color(r, g, b);
			if (!colors.containsValue(color) && !color.equals(Color.BLACK)) colors.put(i, color);
			else i--;
		}
	}

	/**
	 * Calculates the direction of the click
	 * @param row the row that got clicked
	 * @param col the column that got clicked
	 */
	private int calculateDirection(int row, int col) {
		int dir = -1;
		int blockRow = board.getBlockByID(lastClick).getRow();
		int blockCol = board.getBlockByID(lastClick).getCol();

		if (blockRow == row)
			dir = (col < blockCol) ? Board.LEFT : Board.RIGHT;
		else if (blockCol == col)
			dir = (row < blockRow) ? Board.UP : Board.DOWN;

		return dir;
	}

	/** Handles the user's mouse input */
	private void handleInput() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Check what Tile got clicked
				for (int i = 0; i < selectGrid.length; i++) {
					for (int j = 0; j < selectGrid[i].length; j++) {
						if (selectGrid[i][j].contains(e.getX(), e.getY())) {
							if (i > 0 && i <= board.getHeight() && j > 0 && j <= board.getWidth()) {
								int blockID = board.getGrid()[i - 1][j - 1];

								// Check what was clicked
								if (blockID == 0) {
									if (waitingForClick) {
										int dir = calculateDirection(i - 1, j - 1);

										if (dir != -1 && board.canBlockBeMoved(lastClick, dir)) {
											// Update data
											root.setMovesLabel(++validMoves);
											board.moveBlock(lastClick, dir);

											// Update whole screen
											root.repaint();
											repaint();
										}

										// Set ready for next click
										waitingForClick = false;

										// Check if the game is over
										if (board.isGameOver()) {
											int n = JOptionPane.showOptionDialog(root, "Total Moves: " + validMoves, "Game Over!",
													JOptionPane.YES_NO_CANCEL_OPTION,
													JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

											if (n == 0) {
												System.exit(0);
											} else {
												new MainMenuFrame();
												root.dispatchEvent(new WindowEvent(root, WindowEvent.WINDOW_CLOSING));
											}
										}
									}
								} else {
									if (!waitingForClick) {
										// Save the last clicked block
										lastClick = blockID;
										waitingForClick = true;
									}
								}

								return;
							}
						}
					}
				}
			}
		});
	}

	// Instance methods
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Update the square size
		xSize = getWidth() / (board.getWidth() + BORDER);
		ySize = getHeight() / (board.getHeight() + BORDER);

		// Update the selection grid
		updateSelectionGrid();

		// Paint the background gray
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Paint the ground and the exit gray
		int exitCol = board.getWidth() + (BORDER / 2);
		int exitRow = board.getExitRow() + (BORDER / 2);

		g.setColor(Color.GRAY);
		g.fillRect(exitCol * xSize, exitRow * ySize, xSize, ySize);
		g.fillRect(xSize, ySize, board.getWidth() * xSize, board.getHeight() * ySize);

		// Paint the board
		for (Entry<Integer, Block> temp: board.getBlocks().entrySet()) {
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
	}
}
