package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JPanel;

import Game.Block;
import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance variables
	private Board board;
	private GameFrame root;
	private Tile[][] selectGrid;
	private HashMap<Integer, Color> colors;
	
	private final int OFFSET = 4;
	private final int ARC_WIDTH = 25;
	
	/**
	 * Creates a Canvas instance
	 * @param board the board to be displayed on the screen
	 */
	public Canvas(Board board, GameFrame root) {
		this.root = root;
		this.board = board;
		colors = new HashMap<Integer, Color>();
		
		// Initialize selection grid
		selectGrid = new Tile[board.getHeight() + 2][board.getWidth() + 2];
		for (int i = 0; i < selectGrid.length; i++)
			for (int j = 0; j < selectGrid[i].length; j++)
				selectGrid[i][j] = new Tile(j, i, getWidth() / board.getWidth(), getHeight() / board.getHeight());
		
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
		
		// Handle user's input
		handleInput();
	}
	
	/** Handles the user's mouse input */
	private void handleInput() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO
				// Handle input
				
				// Update the whole screen
				root.revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
		});
	}
	
	// Instance methods
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Set the cell size
		int xSize = getWidth() / board.getWidth();
		int ySize = getHeight() / board.getHeight();
		
		// Paint the background gray
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Paint the border
		// TODO
		
		// Paint the board
		for (Entry<Integer, Block> temp: board.getBlocks().entrySet()) {
			Block block = temp.getValue();
			
			// Set color and paint block
			g.setColor(colors.get(block.getID()));
			if (block.getOrientation() == Block.HOR) {
				g.fillRoundRect((block.getCol() * xSize) + (OFFSET / 2),
								(block.getRow() * ySize) + (OFFSET / 2),
								(xSize * block.getLength()) - OFFSET, ySize - OFFSET,
								ARC_WIDTH, ARC_WIDTH);
			} else {
				g.fillRoundRect((block.getCol() * xSize) + (OFFSET / 2),
								(block.getRow() * ySize) + (OFFSET / 2),
								xSize - OFFSET, (ySize * block.getLength()) - OFFSET,
								ARC_WIDTH, ARC_WIDTH);
			}
		}
	}
}
