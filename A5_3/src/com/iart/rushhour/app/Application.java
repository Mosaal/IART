package com.iart.rushhour.app;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.iart.rushhour.gamestate.GameStateManager;

public class Application extends JFrame {

	private static final long serialVersionUID = 4244392030198194982L;

	// Instance variable
	private GameStateManager gsm;

	/** Creates an Application instance */
	public Application() {
		// Initialize the game state manager
		gsm = new GameStateManager(this);
		gsm.setState(GameStateManager.MAIN_MENU_STATE);

		// Set the frame's settings
		pack();
		setSize(400, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(getWidth(), getHeight()));

		// Set the frame's location
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}

	/**
	 * Where it all starts
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Application app = new Application();
				app.setVisible(true);
			}
		});
	}
}
