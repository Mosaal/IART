package com.iart.rushhour.gamestate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.iart.rushhour.logic.Utils;

public class MainMenuState extends GameState {

	private static final long serialVersionUID = 3078891038492049408L;

	// Instance variables
	private JButton startBtn;
	private ImageIcon bgImage;
	private JRadioButton aiBtn;
	private JRadioButton userBtn;
	private JComboBox<String> levelCbx;
	
	/**
	 * Creates a MainMenuState instance
	 * @param gsm the state's game state manager
	 */
	protected MainMenuState(GameStateManager gsm) {
		super(gsm);
		setLayout(new GridBagLayout());
		
		gsm.getRoot().setResizable(false);
		gsm.getRoot().setSize(new Dimension(400, 450));
		
		Font font = new Font("Monospaced", Font.PLAIN, 15);
		bgImage = new ImageIcon("res/background.png");
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel titleLbl = new JLabel("Rush Hour");
		titleLbl.setFont(new Font("Monospaced", Font.PLAIN, 40));
		titleLbl.setForeground(Color.WHITE);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 45, 0);
		add(titleLbl, gbc);
		
		startBtn = new JButton("Start");
		startBtn.setFocusable(false);
		startBtn.setFont(font);
		
		gbc.gridy = 1;
		add(startBtn, gbc);
		
		levelCbx = new JComboBox<String>(Utils.searchLevels());
		levelCbx.setSelectedIndex(gsm.level - 1);
		levelCbx.setFocusable(false);
		levelCbx.setFont(font);
		
		gbc.gridy = 2;
		add(levelCbx, gbc);
		
		JLabel modeLbl = new JLabel("Mode:");
		modeLbl.setForeground(Color.WHITE);
		modeLbl.setFont(font);
		
		aiBtn = new JRadioButton("AI");
		aiBtn.setForeground(Color.WHITE);
		aiBtn.setFont(font);
		
		userBtn = new JRadioButton("User");
		userBtn.setForeground(Color.WHITE);
		userBtn.setFont(font);
		
		if (gsm.mode == 0) aiBtn.setSelected(true);
		else userBtn.setSelected(true);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(aiBtn);
		btnGroup.add(userBtn);
		
		JPanel modePanel = new JPanel();
		modePanel.setOpaque(false);
		
		modePanel.add(modeLbl);
		modePanel.add(aiBtn);
		modePanel.add(userBtn);
		
		gbc.gridy = 3;
		add(modePanel, gbc);
		
		// Initialize input
		initInput();
	}

	@Override
	public void initInput() {
		startBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Start")) {
				gsm.mode = (aiBtn.isSelected()) ? 0 : 1;
				gsm.level = levelCbx.getSelectedIndex() + 1;
				gsm.setState(GameStateManager.PLAY_GAME_STATE);
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw background image
		bgImage.paintIcon(this, g, 0, 0);
	}
}
