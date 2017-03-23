package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class GameGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8277252937409034080L;

	// Instance variables
	private static JPanel gamePanel;
	private static JButton startBtn;
	private static JRadioButton aiMode, userMode;
	private static JRadioButton astarBtn, bfsBtn, dfsBtn;
	
	/** Creates the main window of the game */
	public GameGUI() {
		super("Rush Hour");
		
		JPanel mainPanel = new JPanel();
		
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(280, 200));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel nameLabel = new JLabel("Rush Hour");
		nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		nameLabel.setBorder(new EmptyBorder(10, 10, 5, 10));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(nameLabel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
		mainPanel.add(btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		startBtn = new JButton("Start Game");
		btnPanel.add(startBtn);
		startBtn.addActionListener(this);
		startBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		aiMode.setSelected(true);
		aiMode.setFont(new Font("Monospaced", Font.PLAIN, 13));
		aiMode.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		astarBtn.setSelected(true);
		astarBtn.setFont(new Font("Monospaced", Font.PLAIN, 13));
		astarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		algPanel.add(astarBtn);
		
		bfsBtn = new JRadioButton("BFS");
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
		
		pack();
		setResizable(false);
		setMinimumSize(new Dimension(280, 280));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game")) {
			gamePanel = new JPanel();
			gamePanel.add(new JLabel("The game started..."));
			
			setContentPane(gamePanel);
			setResizable(true);
			revalidate();
		}
	}
	
	public static void main(String[] args) {
		new GameGUI();
	}
}
