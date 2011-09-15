package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopPanelGUI extends JPanel {
	private JLabel whiteLabel;
	private JLabel blackLabel;
		
	public TopPanelGUI() {
		super();
		init();
		
	}
	
	private void init() {
		JLabel center = new JLabel("CHESS");
		setLayout(new BorderLayout());
		
		whiteLabel = new JLabel(PieceRepositoryGUI.get().getPiece("Kwb").getImage());
		blackLabel = new JLabel(PieceRepositoryGUI.get().getPiece("Kbb").getImage());
		
		center.setHorizontalAlignment(SwingConstants.CENTER);
		center.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		
		whiteLabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 22));
		whiteLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		blackLabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 22));
		blackLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		
		add(whiteLabel, BorderLayout.WEST);
		add(blackLabel, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
				
	}
	
	public void setWhiteName(String name) {
		whiteLabel.setText(name);
		
	}
	
	public void setBlackName(String name) {
		blackLabel.setText(name);
		
	}
		
}
