package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.Block;
import Game.Board;

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
		// new GameGUI();
		
		Board b = new Board(6, 6, Board.RIGHT, 2);
		Block bl = new Block(2, 0, 0, 3, Block.HOR);
		
		b.addBlock(new Block(1, 2, 0, 2, Block.HOR));
		b.addBlock(bl);
		b.addBlock(new Block(3, 5, 0, 3, Block.HOR));
		b.addBlock(new Block(4, 3, 4, 2, Block.HOR));
		
		b.addBlock(new Block(5, 3, 0, 2, Block.VER));
		b.addBlock(new Block(6, 1, 2, 3, Block.VER));
		b.addBlock(new Block(7, 0, 5, 3, Block.VER));
		b.addBlock(new Block(8, 4, 4, 2, Block.VER));
		
		System.out.println(b.toString(false));
		
		b.moveBlock(bl, Board.RIGHT);
		
		System.out.println(b.toString(false));
	}
}
