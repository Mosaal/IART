package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Board;
import java.awt.Component;
import javax.swing.SwingConstants;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 7856046888785438219L;

	// Instance variables
	private Canvas canvas;
	private JLabel movesLabel;
	
	/**
	 * Creates a GameFrame instance
	 * @param board the board to be displayed on the screen
	 */
	public GameFrame(Board board) {
		super("Rush Hour");
		
		canvas = new Canvas(board, this);
		
		movesLabel = new JLabel("Moves: 0");
		movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movesLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movesLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		JPanel temp = new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
		temp.add(movesLabel);
		temp.add(canvas);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		
		pack();
		setContentPane(temp);
		setMinimumSize(new Dimension(450, 450));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	}
	
	/**
	 * Updates the amount of moves on the label
	 * @param moves amount of moves to be set on the label
	 */
	public void setMovesLabel(int moves) { movesLabel.setText("Moves: " + moves); }
}
