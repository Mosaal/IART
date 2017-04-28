
public class GameStateManager {

	private Application root;
	private GameState gameState;
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int PLAY_GAME_STATE = 1;
	
	public GameStateManager(Application root) { this.root = root; }
	
	public void setState(int state) {
		if (state == MAIN_MENU_STATE)
			gameState = new MainMenuState(this);
		else if (state == PLAY_GAME_STATE)
			gameState = new PlayGameState(this);
		
		root.setContentPane(gameState);
		root.revalidate();
	}
}
