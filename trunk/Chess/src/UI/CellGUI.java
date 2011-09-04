package UI;

import java.awt.Color;

import javax.swing.JLabel;

public class CellGUI extends JLabel {
	private boolean darkCell;
	private PieceGUI piece;
	
	public CellGUI(boolean dark) {
		darkCell = dark;
		if(darkCell) {
			setBackground(Color.BLUE);
			
		} else {
			setBackground(Color.WHITE);
			
		}
		
	}
	
	public CellGUI() {
		this(false);
		piece = null;
		
	}
	
	public void setPiece(PieceGUI p) {
		piece = p;
		
	}
	
	public PieceGUI getPiece() {
		return piece;
		
	}
	
	public boolean isEmpty() {
		return piece == null;
		
	}
	
	public boolean isDark() {
		return darkCell;
		
	}
	
}
