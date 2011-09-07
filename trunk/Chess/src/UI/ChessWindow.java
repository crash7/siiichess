package UI;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingWorker;


public class ChessWindow extends JFrame {
	
	public ChessWindow() {
		super("Chess");
		init();
		
	}
	
	public void init() {
		setSize(new Dimension(700, 700));
		setLocationRelativeTo(null);
                setResizable(false);
		setContentPane(new StartPanelGUI());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Construimos el repositorio de piezas
		repoWorker.execute();
		
	}
	
	SwingWorker repoWorker = new SwingWorker() {

		protected Object doInBackground() throws Exception {
			return PieceRepositoryGUI.get();
			
		}
		
	};

}
