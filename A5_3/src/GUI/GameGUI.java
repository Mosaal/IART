package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGUI extends JFrame {

	private static final long serialVersionUID = 3858229905442780638L;

	/** Creates the main window of the game */
	public GameGUI() {
		super("Rush Hour");
		
		JPanel mainPanel = new JPanel();

		setContentPane(mainPanel);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameGUI();
	}
}
