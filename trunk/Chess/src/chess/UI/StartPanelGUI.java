package chess.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class StartPanelGUI extends JPanel {

	public StartPanelGUI() {
		super();
		init();
		
	}
	
	public void init() {
		JLabel title = new JLabel("CHESS");
		JButton localgame = new JButton("Partida local");
		JButton netgame = new JButton("Partida en red");
		JButton exit = new JButton("Salir");
		GridBagConstraints gbcontraints;
		
		setLayout(new GridBagLayout());
		
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 64));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.weighty = 2.0;
		add(title, gbcontraints);
		
		localgame.setPreferredSize(new Dimension(220, 60));
		localgame.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 1;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.weighty = 2.0;
		gbcontraints.insets = new Insets(0, 0, 10, 0);
		localgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.getContentPane().removeAll();
				frame.invalidate();
				frame.setContentPane(new LocalFormGUI());
				frame.validate();
				
			}
		});
		add(localgame, gbcontraints);
		
		netgame.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		netgame.setPreferredSize(new Dimension(220, 60));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.insets = new Insets(10, 0, 0, 0);
		netgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.getContentPane().removeAll();
				frame.invalidate();
				frame.setContentPane(new NetFormGUI());
				frame.validate();
				
			}
		});
		add(netgame, gbcontraints);
		
		exit.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		exit.setPreferredSize(new Dimension(100, 60));
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.weightx = 3.0;
		gbcontraints.weighty = 4.0;
		gbcontraints.insets = new Insets(10, 10, 10, 10);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		exit.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			
			public void keyReleased(KeyEvent e) { }
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
					
				}
				
			}
		});
		add(exit, gbcontraints);
				
	}

}
