package com.iart.rushhour.gamestate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayGameState extends GameState {

	private static final long serialVersionUID = 1663106394646702912L;

	// Instance variables
	private Canvas canvas;
	private JLabel movesLbl;
	private JButton backBtn;
	private JButton resetBtn;

	/**
	 * Creates a PlayGameState instance
	 * @param gsm the state's game state manager
	 */
	protected PlayGameState(GameStateManager gsm) {
		super(gsm);
		gsm.getRoot().setResizable(true);

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 400));

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());

		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(backBtn, BorderLayout.WEST);

		movesLbl = new JLabel("Moves: 0");
		movesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		movesLbl.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(movesLbl, BorderLayout.CENTER);

		resetBtn = new JButton("Reset");
		resetBtn.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(resetBtn, BorderLayout.EAST);

		canvas = new Canvas(this);

		// Add components
		add(northPanel, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);

		initInput();
	}
	
	// Instance methods
	/**
	 * Updates the amount of moves displayed on the moves label
	 * @param moves the amount of moves to be displayed
	 */
	public void updateMovesLbl(int moves) { movesLbl.setText("Moves: " + moves); }

	@Override
	public void initInput() {
		backBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Back"))
				gsm.setState(GameStateManager.MAIN_MENU_STATE);
		});
		
		resetBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Reset"))
				System.out.println("AYYY LMAO");
		});
	}
}
