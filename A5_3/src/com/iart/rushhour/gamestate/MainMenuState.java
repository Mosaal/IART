package com.iart.rushhour.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.iart.rushhour.logic.Utils;

public class MainMenuState extends GameState {

	private static final long serialVersionUID = 3078891038492049408L;

	// Instance variables
	private JButton startBtn;
	private ImageIcon bgImage;
	private JComboBox<String> levelCbx;
	
	/**
	 * Creates a MainMenuState instance
	 * @param gsm the state's game state manager
	 */
	protected MainMenuState(GameStateManager gsm) {
		super(gsm);
		gsm.getRoot().setResizable(false);
		
		bgImage = new ImageIcon("res/background.png");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLbl = new JLabel("Rush Hour");
		titleLbl.setForeground(new Color(255, 255, 255));
		titleLbl.setFont(new Font("Monospaced", Font.PLAIN, 50));
		
		GridBagConstraints gbc_titleLbl = new GridBagConstraints();
		gbc_titleLbl.weighty = 0.6;
		gbc_titleLbl.insets = new Insets(0, 0, 5, 0);
		gbc_titleLbl.weightx = 1.0;
		gbc_titleLbl.gridx = 0;
		gbc_titleLbl.gridy = 0;
		add(titleLbl, gbc_titleLbl);
		
		startBtn = new JButton("Start");
		startBtn.setFocusPainted(false);
		startBtn.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.weighty = 0.2;
		gbc_startBtn.insets = new Insets(0, 0, 5, 0);
		gbc_startBtn.weightx = 1.0;
		gbc_startBtn.gridx = 0;
		gbc_startBtn.gridy = 1;
		add(startBtn, gbc_startBtn);
		
		levelCbx = new JComboBox<String>(Utils.searchLevels());
		levelCbx.setSelectedIndex(0);
		levelCbx.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		GridBagConstraints gbc_lvlComboBox = new GridBagConstraints();
		gbc_lvlComboBox.weighty = 0.2;
		gbc_lvlComboBox.weightx = 1.0;
		gbc_lvlComboBox.gridx = 0;
		gbc_lvlComboBox.gridy = 2;
		add(levelCbx, gbc_lvlComboBox);
		
		// Initialize input
		initInput();
	}

	@Override
	public void initInput() {
		startBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Start"))
				gsm.setState(GameStateManager.PLAY_GAME_STATE);
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw background image
		bgImage.paintIcon(this, g, 0, 0);
	}
}
