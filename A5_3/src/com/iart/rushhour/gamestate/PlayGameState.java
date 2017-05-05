package com.iart.rushhour.gamestate;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.iart.rushhour.game.Board;
import com.iart.rushhour.logic.Algorithms;
import com.iart.rushhour.logic.Move;
import com.iart.rushhour.logic.Utils;

public class PlayGameState extends GameState {

	private static final long serialVersionUID = 1663106394646702912L;

	// Instance variables
	private Canvas canvas;
	private JLabel movesLbl;
	private JButton backBtn;
	private JButton resetBtn;
	private JButton startBtn;

	private JTable statsTable;
	private DefaultTableModel tableModel;
	private String[] columnNames = { "Algorithm", "Number of Explored States", "Execution Time" };

	private ArrayList<Move> movesList;
	private final double BILLION = 1000000000.0;

	/**
	 * Creates a PlayGameState instance
	 * @param gsm the state's game state manager
	 */
	protected PlayGameState(GameStateManager gsm) {
		super(gsm);
		gsm.getRoot().setResizable(true);

		setLayout(new BorderLayout());
		Font font = new Font("Monospaced", Font.PLAIN, 15);

		JTabbedPane tabbedPanel = new JTabbedPane();
		tabbedPanel.setFont(font);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());

		backBtn = new JButton("Back");
		backBtn.setFocusable(false);
		backBtn.setFont(font);
		northPanel.add(backBtn, BorderLayout.WEST);

		movesLbl = new JLabel("Moves: 0");
		movesLbl.setBorder(new EmptyBorder(10, 0, 10, 0));
		movesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		movesLbl.setFont(font);
		northPanel.add(movesLbl, BorderLayout.CENTER);

		resetBtn = new JButton("Reset");
		resetBtn.setFocusable(false);
		resetBtn.setFont(font);
		northPanel.add(resetBtn, BorderLayout.EAST);

		canvas = new Canvas(gsm);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(canvas, BorderLayout.CENTER);

		statsTable = new JTable();
		statsTable.setFocusable(false);
		statsTable.setFont(font);
		statsTable.getTableHeader().setFont(font);

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		statsTable.setModel(tableModel);

		tableModel.addRow(new String[] { "BFS", "0", "0.000 s" });
		tableModel.addRow(new String[] { "DFS", "0", "0.000 s" });
		tableModel.addRow(new String[] { "A* Distance", "0", "0.000 s" });
		tableModel.addRow(new String[] { "A* Blocking", "0", "0.000 s" });
		tableModel.addRow(new String[] { "A* Distance+Blocking", "0", "0.000 s" });

		tabbedPanel.add("Board", mainPanel);
		tabbedPanel.add("Stats", new JScrollPane(statsTable));

		startBtn = new JButton("Start");
		startBtn.setFocusable(false);
		startBtn.setFont(font);

		// Handle the canvas' input
		if (gsm.mode == 0)
			runAlgorithms();
		else if (gsm.mode == 1)
			canvas.initInput();

		// Handle the frame's input
		initInput();

		// Add components
		add(tabbedPanel, BorderLayout.CENTER);
		if (gsm.mode == 0)
			add(startBtn, BorderLayout.SOUTH);
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
			if (e.getActionCommand().equals("Reset")) {
				gsm.board = Utils.loadLevel(gsm.level);
				movesLbl.setText("Moves: 0");
				startBtn.setEnabled(true);
				canvas.stopTimer();

				canvas.ignoreAllInput = false;
				canvas.validMoves = 0;
				canvas.repaint();
			}
		});

		startBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Start")) {
				startBtn.setEnabled(false);
				canvas.displayMoves(movesList);
			}
		});
	}

	/** Runs the search algorithms */
	private void runAlgorithms() {
		// Run BFS
		long start = System.nanoTime();
		Board lastNode = Algorithms.BFS(gsm.board);
		long end = System.nanoTime() - start;

		// Get elapsed time
		double elapsedTime = (double) end / BILLION;

		// Set data on the table
		tableModel.setValueAt(Integer.toString(lastNode.getVisitedNodes()), 0, 1);
		tableModel.setValueAt(String.format("%.3f", elapsedTime) + " s", 0, 2);

		// Get moves from BFS
		movesList = new ArrayList<Move>();
		while (lastNode.getParent() != null) {
			movesList.add(lastNode.getMove());
			lastNode = lastNode.getParent();
		}

		// Run DFS
		start = System.nanoTime();
		lastNode = Algorithms.DFS(gsm.board);
		end = System.nanoTime() - start;

		// Get elapsed time
		elapsedTime = (double) end / BILLION;

		// Set data on the table
		tableModel.setValueAt(Integer.toString(lastNode.getVisitedNodes()), 1, 1);
		tableModel.setValueAt(String.format("%.3f", elapsedTime) + " s", 1, 2);

		// Run A* Distance
		start = System.nanoTime();
		lastNode = Algorithms.AStar(gsm.board, Algorithms.DISTANCE_HEURISTIC);
		end = System.nanoTime() - start;

		// Get elapsed time
		elapsedTime = (double) end / BILLION;

		// Set data on the table
		tableModel.setValueAt(Integer.toString(lastNode.getVisitedNodes()), 2, 1);
		tableModel.setValueAt(String.format("%.3f", elapsedTime) + " s", 2, 2);

		// Run A* Blocking
		start = System.nanoTime();
		lastNode = Algorithms.AStar(gsm.board, Algorithms.NUM_BLOCKING_HEURISTIC);
		end = System.nanoTime() - start;

		// Get elapsed time
		elapsedTime = (double) end / BILLION;

		// Set data on the table
		tableModel.setValueAt(Integer.toString(lastNode.getVisitedNodes()), 3, 1);
		tableModel.setValueAt(String.format("%.3f", elapsedTime) + " s", 3, 2);

		// Run A* Distance+Blocking
		start = System.nanoTime();
		lastNode = Algorithms.AStar(gsm.board, Algorithms.DISTANCE_NUM_BLOCKING_HEURISTIC);
		end = System.nanoTime() - start;

		// Get elapsed time
		elapsedTime = (double) end / BILLION;

		// Set data on the table
		tableModel.setValueAt(Integer.toString(lastNode.getVisitedNodes()), 4, 1);
		tableModel.setValueAt(String.format("%.3f", elapsedTime) + " s", 4, 2);
	}
}
