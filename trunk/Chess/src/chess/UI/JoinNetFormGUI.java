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

public class JoinNetFormGUI extends JPanel {
	private JTextField host;
	private JTextField port;
	private JTextField blackName;
	    
	public JoinNetFormGUI() {
		super();
		init();
				
	}
	
	private void init() {
		GridBagConstraints gbcontraints;
		JLabel title = new JLabel("Jugadores");
		JButton start = new JButton("Comenzar");
		JLabel hostLabel = new JLabel("Host");
		JLabel portLabel = new JLabel("Puerto");
		JLabel blackimage = new JLabel(PieceRepositoryGUI.get().getPiece("Kbb").getImageIcon());
		host = new JTextField("127.0.0.1", 20);
		port = new JTextField("4500", 20);
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
	    add(blackimage, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 1;
	    add(blackName, gbcontraints);

            gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
		add(hostLabel, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 2;
		add(host, gbcontraints);
                
            gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 3;
		add(portLabel, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 3;
		add(port, gbcontraints);
                
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 6;
		
		gbcontraints.gridwidth = 2;
		gbcontraints.fill = GridBagConstraints.HORIZONTAL;
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNetGame();
				
			}
		});
		start.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			
			public void keyReleased(KeyEvent e) { }
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					startNetGame();
					
				}
				
			}
		});
	    add(start, gbcontraints);
                
	}
	
	private void startNetGame() {
		GamePanelGUI gamepanel = new GamePanelGUI();
		JFrame frame = (JFrame) getTopLevelAncestor();
		/* TODO: get remote name*/
                gamepanel.setWhiteName("remote name");
		gamepanel.setBlackName(blackName.getText());
		gamepanel.setGameType(GamePanelGUI.LOCAL_GAME);
		frame.getContentPane().removeAll();
		frame.invalidate();
		frame.setContentPane(gamepanel);
		frame.validate();
		gamepanel.startGame();
		
	}

}
