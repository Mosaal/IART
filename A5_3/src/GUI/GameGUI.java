package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Game.Board;
import Logic.Utils;

public class GameGUI extends JFrame {

	private static final long serialVersionUID = -8277252937409034080L;

	// Instance variables
	private Canvas canvas;
	private JButton startBtn;
	private JComboBox<String> comboBox;
	private JRadioButton aiMode, userMode;
	private JRadioButton astarBtn, bfsBtn, dfsBtn;
	
	/** Creates the main window of the game */
	public GameGUI() {
		super("Rush Hour");
		
		JPanel mainPanel = new JPanel();
		
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(300, 200));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel nameLabel = new JLabel("Rush Hour");
		nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		nameLabel.setBorder(new EmptyBorder(10, 10, 5, 10));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(nameLabel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		mainPanel.add(btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		startBtn = new JButton("Start Game");
		startBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		startBtn.setFocusPainted(false);
		startBtn.addActionListener(e -> {
			Board board = null;
			try { board = Utils.loadLevel(comboBox.getSelectedIndex() + 1); }
			catch (IOException e1) { e1.printStackTrace(); }
			
			if (board != null) {
				canvas = new Canvas(board);
				
				setContentPane(canvas);
				setResizable(true);
				setSize(448, 454);
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int centerX = (int) screenSize.getWidth() / 2;
				int centerY = (int) screenSize.getHeight() / 2;
				
				setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
				revalidate();
			} else {
				JOptionPane.showMessageDialog(this, "The level's file has invalid information!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnPanel.add(startBtn);
		
		comboBox = new JComboBox<String>(Utils.searchLevels());
		comboBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
		((JLabel) comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBox.setSelectedIndex(0);
		btnPanel.add(comboBox);
		
		JPanel radioPanel = new JPanel();
		mainPanel.add(radioPanel);
		radioPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel modePanel = new JPanel();
		modePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		radioPanel.add(modePanel);
		modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
		
		JLabel modeLabel = new JLabel("Game Mode:");
		modeLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		modeLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
		modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		modePanel.add(modeLabel);
		
		aiMode = new JRadioButton("AI Mode");
		aiMode.setBorder(new EmptyBorder(0, 0, 10, 0));
		aiMode.setFont(new Font("Monospaced", Font.PLAIN, 13));
		aiMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		aiMode.setSelected(true);
		modePanel.add(aiMode);
		
		userMode = new JRadioButton("User Mode");
		userMode.setFont(new Font("Monospaced", Font.PLAIN, 13));
		userMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		modePanel.add(userMode);
		
		ButtonGroup modeGrp = new ButtonGroup();
		modeGrp.add(aiMode);
		modeGrp.add(userMode);
		
		JPanel algPanel = new JPanel();
		algPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		radioPanel.add(algPanel);
		algPanel.setLayout(new BoxLayout(algPanel, BoxLayout.Y_AXIS));
		
		JLabel algLabel = new JLabel("Algorithm:");
		algLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		algLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
		algLabel.setHorizontalAlignment(SwingConstants.CENTER);
		algLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		algPanel.add(algLabel);
		
		astarBtn = new JRadioButton("A*");
		astarBtn.setBorder(new EmptyBorder(0, 0, 10, 0));
		astarBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		astarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		astarBtn.setSelected(true);
		algPanel.add(astarBtn);
		
		bfsBtn = new JRadioButton("BFS");
		bfsBtn.setBorder(new EmptyBorder(0, 0, 10, 0));
		bfsBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		bfsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		algPanel.add(bfsBtn);
		
		dfsBtn = new JRadioButton("DFS");
		dfsBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		dfsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		algPanel.add(dfsBtn);
		
		ButtonGroup algGrp = new ButtonGroup();
		algGrp.add(astarBtn);
		algGrp.add(bfsBtn);
		algGrp.add(dfsBtn);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		
		pack();
		setResizable(false);
		setMinimumSize(new Dimension(300, 300));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	}
	
	/** Main function */
	public static void main(String[] args) {
		new GameGUI();
	}
}
