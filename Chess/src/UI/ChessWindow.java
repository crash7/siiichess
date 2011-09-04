package UI;

import java.awt.Dimension;
import javax.swing.JFrame;


public class ChessWindow extends JFrame {
	
	public ChessWindow() {
		super("Chess");
		init();
		
	}
	
	public void init() {
		setSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
		setContentPane(new StartPanelGUI());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

}
