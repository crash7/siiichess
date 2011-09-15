package chess.UI;

import java.awt.Color;
import javax.swing.JLabel;

public class CellGUI extends JLabel {
	private boolean darkCell;
	private PieceGUI piece;
	private int xPosition;
	private int yPosition;
	
	
	public CellGUI(int x, int y, boolean dark) {
		super();
		this.xPosition = x;
		this.yPosition = y;
		darkCell = dark;
		setOpaque(true);
		setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		if(darkCell) {
			setBackground(new Color(170, 125, 30));
			
		} else {
			setBackground(new Color(230, 200, 140));
			
		}
				
	}
	
	public void setPiece(PieceGUI p) {
		piece = p;
		setIcon(p.getImage());
		
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

	public int getXPosition() {
		return xPosition;
		
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
		
	}

	public int getYPosition() {
		return yPosition;
		
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
		
	}
	
}
