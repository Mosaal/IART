package GUI;

/*import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;*/

public class MainFrame /*extends JFrame*/ {

	// private static final long serialVersionUID = 3858229905442780638L;

	/** Creates the main window of the game */
	/*public MainFrame() {
		super("Rush Hour");

		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(e -> {
			if (e.getActionCommand().equals("Start")) {
				System.out.println("Button pressed!");
				JPanel temp = new JPanel();
				temp.add(new JLabel("Ayyy lmao"));
				setContentPane(temp);
				revalidate();
			}
		});

		// Radio buttons placement
		ButtonGroup efiGrp = new ButtonGroup();
		JRadioButton timeBtn = new JRadioButton("by time");
		JRadioButton movesBtn = new JRadioButton("by moves");
		efiGrp.add(timeBtn);
		efiGrp.add(movesBtn);
		
		JPanel efiBtns = new JPanel();
		efiBtns.setLayout(new GridLayout(2, 1));
		efiBtns.add(timeBtn);
		efiBtns.add(movesBtn);
		
		ButtonGroup algGrp = new ButtonGroup();
		JRadioButton depthBtn = new JRadioButton("profundidade");
		JRadioButton widthBtn = new JRadioButton("largura");
		JRadioButton astarBtn = new JRadioButton("A*");
		algGrp.add(depthBtn);
		algGrp.add(widthBtn);
		algGrp.add(astarBtn);
		
		JPanel algBtns = new JPanel();
		algBtns.setLayout(new GridLayout(3, 1));
		algBtns.add(depthBtn);
		algBtns.add(widthBtn);
		algBtns.add(astarBtn);
		
		JPanel radioBtns = new JPanel();
		radioBtns.setLayout(new GridLayout(1, 2));
		radioBtns.add(efiBtns);
		radioBtns.add(algBtns);

		// Button placement
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.setPreferredSize(new Dimension(300, 300));
		mainPanel.add(startBtn);
		mainPanel.add(radioBtns);

		setContentPane(mainPanel);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}*/

	public static void main(String[] args) {
		// TODO
	}
}
