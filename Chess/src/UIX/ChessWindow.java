package UIX;

import java.awt.Dimension;

import javax.swing.JFrame;

import UIX.pieces.GraphicPiece;

public class ChessWindow extends JFrame {
	
	public ChessWindow() {
		super("Chess");
		init();
		
	}
	
	public void init() {
		setSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
		setContentPane(new StartPanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

}
