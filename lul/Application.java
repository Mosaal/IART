import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application extends JFrame {

	private static final long serialVersionUID = 1035207996697568561L;

	private GameStateManager gsm;
	
	public Application() {
		// Set the frame's title
		super("Rush Hour");

		gsm = new GameStateManager(this);
		gsm.setState(GameStateManager.MAIN_MENU_STATE);

		// Set the frame's settings
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setMinimumSize(new Dimension(getWidth(), getHeight()));

		// Set the frame's location
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}

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
