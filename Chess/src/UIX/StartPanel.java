package UIX;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {

	public StartPanel() {
		super();
		init();
		
	}
	
	public void init() {
		JLabel title = new JLabel("Chesssss");
		JButton localgame = new JButton("Partida local");
		JButton netgame = new JButton("Partida en red");
		JButton exit = new JButton("Salir");
		GridBagConstraints gbcontraints;
		
		setLayout(new GridBagLayout());
		
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 0;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.weightx = 4.0;
		gbcontraints.weighty = 2.0;
		title.setIcon()
		add(title, gbcontraints);
		
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 1;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		gbcontraints.weighty = 3.0;
		add(localgame, gbcontraints);
		
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
		gbcontraints.anchor = GridBagConstraints.SOUTH;
		add(netgame, gbcontraints);
		
		gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 3;
		gbcontraints.anchor = GridBagConstraints.SOUTHEAST;
		gbcontraints.weightx = 3.0;
		gbcontraints.weighty = 4.0;
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		exit.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.exit(0);
					
				}
				
			}
		});
		add(exit, gbcontraints);
		
		
	}

}
