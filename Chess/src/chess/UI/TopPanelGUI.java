package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class TopPanelGUI extends JPanel {
	private JLabel whiteLabel;
	private JLabel blackLabel;
		
	public TopPanelGUI() {
		super();
		init();
		
	}
	
	private void init() {
		JLabel center = new JLabel("CHESS");
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		whiteLabel = new JLabel(PieceRepositoryGUI.get().getPiece("Kwb").getImageIcon());
		blackLabel = new JLabel(PieceRepositoryGUI.get().getPiece("Kbb").getImageIcon());
		
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
	
	public void raiseWhite() {
		blackLabel.setBorder(null);
		whiteLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
	}
	
	public void raiseBlack() {
		whiteLabel.setBorder(null);
		blackLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
	}
	
	public void toggleRaise() {
		if(whiteLabel.getBorder() != null) {
			raiseBlack();
			
		} else {
			raiseWhite();
			
		}
		
	}
		
}
