package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Board;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1407057593083296463L;

	// Instance vaiables
	private Board board;
	private JButton[][] buttons;
	private boolean waitingForClick;
	private HashMap<Integer, Color> colors;

	/**
	 * Creates a Canvas instance
	 * @param board the board to be set on the canvas
	 */
	public Canvas(Board board) {
		this.board = board;
		waitingForClick = false;
		colors = new HashMap<Integer, Color>();
		buttons = new JButton[board.getHeight() + 2][board.getWidth() + 2];
		
		// TODO
		// Add labels to keep track of the status of the game
		
		// Prepare the canvas
		generateColors();
		setCanvasSettings();
		handleInput();
	}

	/** Generates random colors for each of the blocks on the board */
	private void generateColors() {
		// Set the predefined colors
		colors.put(-1, new Color(0, 0, 0));
		colors.put(0, new Color(128, 128, 128));
		colors.put(1, new Color(255, 0, 0));

		// Create as many colors as necessary
		for (int i = 2; i < board.getNumOfBlocks() + 2; i++) {
			// Randomize a color
			int r = (int) (new Random().nextDouble() * 255.0);
			int g = (int) (new Random().nextDouble() * 255.0);
			int b = (int) (new Random().nextDouble() * 255.0);

			// Put it in the map if it is new
			if (!colors.containsValue(new Color(r, g, b)))
				colors.put(i, new Color(r, g, b));
			else
				i--;
		}
	}

	/** Sets the panel's settings */
	private void setCanvasSettings() {
		// Set the layout
		setLayout(new GridLayout(board.getHeight() + 2, board.getWidth() + 2));
		
		// Set the buttons
		for (int i = 0; i < board.getHeight() + 2; i++) {
			for (int j = 0; j < board.getWidth() + 2; j++) {
				JButton button = new JButton();
				button.setActionCommand(i + ":" + j);
				
				// Paint the whole button
				button.setOpaque(true);
				button.setBorderPainted(false);
				
				// Check if it is the border
				if (i == board.getExitRow() + 1 && j == board.getWidth() + 1)
					button.setBackground(colors.get(0));
				else if (i == 0 || i == board.getHeight() + 1 || j == 0 || j == board.getWidth() + 1)
					button.setBackground(colors.get(-1));
				else if (i > 0 && i <= board.getHeight() && j > 0 && j <= board.getWidth())
					button.setBackground(colors.get(board.getGrid()[i - 1][j - 1]));
				
				add(button);
				buttons[i][j] = button;
			}
		}
	}
	
	/** Handles the input from the user */
	private void handleInput() {
		int[][] grid = board.getGrid();
		
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int row = Integer.parseInt(e.getActionCommand().split(":")[0]);
						int col = Integer.parseInt(e.getActionCommand().split(":")[1]);
						
						// Check if it is not the border
						if (row > 0 && row <= board.getHeight() && col > 0 && col <= board.getWidth()) {
							int blockID = grid[row - 1][col - 1];
							
							// TODO
							// Figure out which block got clicked
							// Check if it is a new click or it is waiting to be clicked
							// Depending on the situation see if the block can be moved
							
							// Check what has been clicked
							if (blockID == 0)
								System.out.println("Ground");
							else
								System.out.println("BlockID: " + blockID);
						}
					}
				});
			}
		}
	}
}
