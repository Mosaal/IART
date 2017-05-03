package com.iart.rushhour.gamestate;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PlayGameState extends GameState {

	private static final long serialVersionUID = 1663106394646702912L;

	// Instance variables
	private Canvas canvas;
	private JLabel movesLbl;
	private JButton backBtn;
	private JButton resetBtn;
	
	private JTable statsTable;
	private DefaultTableModel tableModel;
	private String[] columnNames = { "Algorithm", "Number of Moves", "Time in seconds" };
	
	/**
	 * Creates a PlayGameState instance
	 * @param gsm the state's game state manager
	 */
	protected PlayGameState(GameStateManager gsm) {
		super(gsm);
		gsm.getRoot().setResizable(true);
		setLayout(new BorderLayout());
		
		JTabbedPane tabbedPanel = new JTabbedPane();
		tabbedPanel.setFont(new Font("Monospaced", Font.PLAIN, 15));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());

		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(backBtn, BorderLayout.WEST);

		movesLbl = new JLabel("Moves: 0");
		movesLbl.setBorder(new EmptyBorder(10, 0, 10, 0));
		movesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		movesLbl.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(movesLbl, BorderLayout.CENTER);

		resetBtn = new JButton("Reset");
		resetBtn.setFont(new Font("Monospaced", Font.PLAIN, 15));
		northPanel.add(resetBtn, BorderLayout.EAST);

		canvas = new Canvas(gsm);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(canvas, BorderLayout.CENTER);
		
		statsTable = new JTable();
		statsTable.setFocusable(false);
		statsTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		statsTable.getTableHeader().setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		statsTable.setModel(tableModel);
		
		tableModel.addRow(new String[] { "A* Distance", "0", "0.000" });
		tableModel.addRow(new String[] { "A* Blocking", "0", "0.000" });
		tableModel.addRow(new String[] { "A* Distance+Blocking", "0", "0.000" });
		tableModel.addRow(new String[] { "BFS", "0", "0.000" });
		tableModel.addRow(new String[] { "DFS", "0", "0.000" });
		
		tabbedPanel.add("Board", mainPanel);
		tabbedPanel.add("Stats", new JScrollPane(statsTable));
		
		add(tabbedPanel, BorderLayout.CENTER);
		
		initInput();
	}
	
	// Instance methods
	/**
	 * Updates the amount of moves displayed on the moves label
	 * @param moves the amount of moves to be displayed
	 */
	public void updateMovesLbl(int moves) { movesLbl.setText("Moves: " + moves); }

	/**
	 * Updates a specified value on the statistics table given by a row and column
	 * @param value the new value to be set
	 * @param row the row of the value to be set
	 * @param col the column of the value to be set
	 */
	public void updateStatsTable(String value, int row, int col) { tableModel.setValueAt(value, row, col); }
	
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
