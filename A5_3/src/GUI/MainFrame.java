package GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 3858229905442780638L;

	/** Creates the main window of the game */
	public MainFrame() {
		super("Rush Hour");
		
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
