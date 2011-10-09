package chess.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import chess.business.BusinessController;
import chess.net.NetworkGame;

class NetFormGUI extends JPanel {
	private JTextField serverIP;
	private JTextField serverPort;
	private JTextField localPort;
	private JTextField playerName;
	
	public NetFormGUI() {
		super();
		init();
		
	}
		
	private void init() {
		serverIP = new JTextField("localhost");
		serverPort = new JTextField("24377");
		localPort = new JTextField("24377");
		playerName = new JTextField("Fofito");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 250));
		
		// host
		JButton host = new JButton("host me, oh oh oh (?)");
		host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker sw = new SwingWorker() {
					protected Object doInBackground() throws Exception {
						System.out.println("Server on localhost:" + localPort.getText() + " for " + playerName.getText());
						return NetworkGame.createServer(playerName.getText(), Integer.valueOf(localPort.getText()));

					};
					
					protected void done() {
						try {
							NetworkGame ng = (NetworkGame) get();
							if(ng != null) {
								GamePanelGUI gamepanel = new GamePanelGUI();
								gamepanel.setWhiteName(ng.getLocalName());
								gamepanel.setBlackName(ng.getRemoteName());
								gamepanel.setStartColor('w');
								ng.setLocalPlayer(gamepanel.getBlackPlayer());
								startMultiplayerGame(gamepanel, ng);
								
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
							
						} catch (ExecutionException e) {
							e.printStackTrace();
							
						}

					};
					
				};
				sw.execute();
				
			}
		});
		add(host);
		
		// join
		JButton join = new JButton("join me, oh oh oh (?)");
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker sw = new SwingWorker() {
					protected Object doInBackground() throws Exception {
						System.out.println("Connect to " + serverIP.getText() + ":" + serverPort.getText() + " for " + playerName.getText());
						return NetworkGame.createClient(playerName.getText(), serverIP.getText(), Integer.valueOf(serverPort.getText()));

					};
					
					protected void done() {
						try {
							NetworkGame ng = (NetworkGame) get();
							if(ng != null) {
								GamePanelGUI gamepanel = new GamePanelGUI();
								gamepanel.setWhiteName(ng.getRemoteName());
								gamepanel.setBlackName(ng.getLocalName());
								gamepanel.setStartColor('b');
								ng.setLocalPlayer(gamepanel.getWhitePlayer());
								startMultiplayerGame(gamepanel, ng);
								
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
							
						} catch (ExecutionException e) {
							e.printStackTrace();
							
						}

					};
					
				};
				sw.execute();
				
			}
		});
		add(join);
		
	}
	
	public void startMultiplayerGame(GamePanelGUI gp, final NetworkGame ng) {
		BusinessController controller = new BusinessController();
		ng.setController(controller);
		gp.setController(controller);
		gp.setGameType(GamePanelGUI.NET_GAME);
		JFrame frame = (JFrame) getTopLevelAncestor();
		frame.getContentPane().removeAll();
		frame.invalidate();
		frame.setContentPane(gp);
		frame.validate();
		ng.startGame();
		gp.startGame();
		
	}
	
	
}