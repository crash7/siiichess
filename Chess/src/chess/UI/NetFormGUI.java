package chess.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class NetFormGUI extends JPanel {
	private JTextField serverip;
	private JTextField serverport;
	private JTextField playerName;
	
	public NetFormGUI() {
		super();
		init();
		
	}
		
	private void init() {
		serverip = new JTextField("localhost");
		serverport = new JTextField("23477");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 250));
		
		// host
		JButton host = new JButton("host me, oh oh oh (?)");
		host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hostCallback();
				
			}
		});
		add(host);
		
		// join
		JButton join = new JButton("join me, oh oh oh (?)");
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joinCallback();
				
			}
		});
		add(join);
		
	}
	
	private void hostCallback() {
		System.out.println("Server on localhost:23477 for " + playerName.getText());
		
		
		
	}
	
	private void joinCallback() {
		System.out.println("Connect to " + serverip.getText() + ":" + serverport.getText() + " for " + playerName.getText());
		
		
	}
	
}