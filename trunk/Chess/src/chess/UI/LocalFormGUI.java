package chess.UI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LocalFormGUI extends JPanel {
	private JTextField whiteName;
	private JTextField blackName;
	    
	public LocalFormGUI() {
		super();
		init();
				
	}
	
	private void init() {
		GridBagConstraints gbcontraints;
		JLabel title = new JLabel("Jugadores");
		JButton start = new JButton("Comenzar");
		JLabel whiteimage = new JLabel(PieceRepositoryGUI.get().getPiece("Kwb").getImageIcon());
		JLabel blackimage = new JLabel(PieceRepositoryGUI.get().getPiece("Kbb").getImageIcon());
		whiteName = new JTextField("Blancas", 20);
		blackName = new JTextField("Negras", 20);
	    
	    setLayout(new GridBagLayout());
	    	    
	    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    title.setFont(new Font("Trebuchet MS", Font.BOLD, 42));
	    gbcontraints = new GridBagConstraints();
	    gbcontraints.gridx = 0;
	    gbcontraints.gridy = 0;
	    gbcontraints.gridwidth = 2;
	    add(title, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 1;
		add(whiteimage, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 1;
		add(whiteName, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
	    add(blackimage, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 2;
	    add(blackName, gbcontraints);

	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 6;
		
		gbcontraints.gridwidth = 2;
		gbcontraints.fill = GridBagConstraints.HORIZONTAL;
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startLocalGame();
				
			}
		});
		start.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			
			public void keyReleased(KeyEvent e) { }
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					startLocalGame();
					
				}
				
			}
		});
	    add(start, gbcontraints);
                
	}
	
	private void startLocalGame() {
		GamePanelGUI gamepanel = new GamePanelGUI();
		JFrame frame = (JFrame) getTopLevelAncestor();
		gamepanel.setWhiteName(whiteName.getText());
		gamepanel.setBlackName(blackName.getText());
		gamepanel.setGameType(GamePanelGUI.LOCAL_GAME);
		frame.getContentPane().removeAll();
		frame.invalidate();
		frame.setContentPane(gamepanel);
		frame.validate();
		gamepanel.startGame();
		
	}

}
