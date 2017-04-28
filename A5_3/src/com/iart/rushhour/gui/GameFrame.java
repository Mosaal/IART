package com.iart.rushhour.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.iart.rushhour.game.Board;
import com.iart.rushhour.logic.Utils;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 7856046888785438219L;

	// Instance variables
	private int alg;
	private int mode;
	private Canvas canvas;
	private JPanel mainPanel;
	private JLabel movesLabel;
	
	/**
	 * Creates a GameFrame instance
	 * @param board the board to be displayed on the screen
	 */
	public GameFrame(Board board, int mode, int alg, int lvl) {
		super("Rush Hour");
		
		this.alg = alg;
		this.mode = mode;
		
		canvas = new Canvas(board, this);
		
		movesLabel = new JLabel("Moves: 0");
		movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		movesLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movesLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		
		JButton backBtn = new JButton("Back");
		backBtn.setFocusPainted(false);
		backBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		backBtn.addActionListener(e -> {
			new MainMenuFrame().setVisible(true);
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setFocusPainted(false);
		resetBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		resetBtn.addActionListener(e -> {
			setMovesLabel(0);
			canvas.setValidMoves(0);
			
			Board b = null;
			try { b = Utils.loadLevel(lvl); }
			catch (IOException e1) { e1.printStackTrace(); }
			
			canvas.setBoard(b);
			canvas.repaint();
		});
		
		JPanel temp1 = new JPanel();
		temp1.setLayout(new BorderLayout());
		temp1.add(backBtn, BorderLayout.WEST);
		temp1.add(movesLabel, BorderLayout.CENTER);
		temp1.add(resetBtn, BorderLayout.EAST);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(temp1, BorderLayout.NORTH);
		mainPanel.add(canvas, BorderLayout.CENTER);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		
		pack();
		setContentPane(mainPanel);
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
	
	/** Returns the chosen algorithm */
	public int getAlg() { return alg; }
	
	/** Returns the chosen game mode */
	public int getMode() { return mode; }
}
