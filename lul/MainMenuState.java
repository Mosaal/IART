import java.awt.Graphics;

public class MainMenuState extends GameState {

	private static final long serialVersionUID = 2464328187566161896L;

	protected MainMenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		
		initInput();
	}

	@Override
	public void initInput() {
		
	}
}
