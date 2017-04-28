import javax.swing.JPanel;

public abstract class GameState extends JPanel {

	private static final long serialVersionUID = -5955379612186703380L;
	
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) { this.gsm = gsm; }
	
	public abstract void initInput();
}
