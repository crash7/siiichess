package chess.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import chess.business.BusinessController;
import chess.net.NetworkGame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

class NetFormGUI extends JPanel {
	private JTextFieldFL ip;
	private JTextFieldFL puerto;
	private JTextFieldFL puertolocal;
	private JTextFieldFL nombre;
	private JLabel lobbyStatus;
	
	public NetFormGUI() {
		super();
		init();
		
	}
		
	private void init() {
	    // ******** Layout General ********
		setLayout(new BorderLayout(0, 10));
	    JLabel titulo = new JLabel ("Partida en red");
	    titulo.setFont(new Font("Trebuchet MS", Font.BOLD, 42));
	    titulo.setHorizontalAlignment(SwingConstants.CENTER);
	    add(titulo, BorderLayout.NORTH);
	    JPanel centro = new JPanel(new BorderLayout());
	    add(centro,BorderLayout.CENTER);
	    nombre = new JTextFieldFL("Ingrese su nombre");
	    nombre.setFont(new Font("Tahoma", 0, 18));
	    nombre.setHorizontalAlignment(SwingConstants.CENTER);
	    centro.add(nombre,BorderLayout.NORTH);
	    JSplitPane splitpane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    splitpane.setDividerLocation(395);
	    JPanel izquierda = new JPanel(new GridBagLayout());
	    JPanel derecha = new JPanel(new GridBagLayout());
	    splitpane.setLeftComponent(izquierda);
	    splitpane.setRightComponent(derecha);
	    centro.add(splitpane,BorderLayout.CENTER);
	    GridBagConstraints gridbag = new GridBagConstraints();
	    lobbyStatus = new JLabel("All your base belong to us") ;
	    lobbyStatus.setHorizontalAlignment(JLabel.CENTER);
	    add(lobbyStatus,BorderLayout.SOUTH);
	     
	    // ***** Left Panel ****
	    ip = new JTextFieldFL("IP");
	    ip.setHorizontalAlignment(javax.swing.JTextField.CENTER);        
	    ip.setPreferredSize(new java.awt.Dimension(80, 20));
	    gridbag.gridx = 0;
	    gridbag.gridy = 0;
	    gridbag.gridwidth = 2;
	    gridbag.insets = new java.awt.Insets(0, 0, 0, 20);
	    izquierda.add(ip,gridbag);
	     
	    puerto = new JTextFieldFL("Puerto");
	    puerto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	    puerto.setPreferredSize(new java.awt.Dimension(50, 20));
	    gridbag = new java.awt.GridBagConstraints();
	    gridbag.gridx = 2;
	    gridbag.gridy = 0;
	    izquierda.add(puerto,gridbag);
	     
	    JButton unirse = new JButton("Conectar");
	    gridbag = new java.awt.GridBagConstraints();
	    gridbag.gridx = 1;
	    gridbag.gridy = 1;
	    gridbag.gridwidth = 2;
	    gridbag.insets = new java.awt.Insets(20, 0, 0, 0);
	    izquierda.add(unirse,gridbag);
	    unirse.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SwingWorker sw = new SwingWorker() {
	    			protected Object doInBackground() throws Exception {
	    				System.out.println("Connect to " + ip.getText() + ":" + puerto.getText() + " for " + nombre.getText());
	    				lobbyStatus.setText("Trying to connect...");
	    				return NetworkGame.createClient(nombre.getText(), ip.getText(), Integer.valueOf(puerto.getText()));

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
								
							} else {
								lobbyStatus.setText("Connect failed..server offline?");
								
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
							
						} catch (ExecutionException e) {
							lobbyStatus.setText("Invalid IP/PORT. Check format");
							
						}
	
					};
					
				};
				sw.execute();
				
			}
		});
             
        //***** Right Panel *****
        puertolocal = new JTextFieldFL("Puerto");
        puertolocal.setHorizontalAlignment(javax.swing.JTextField.CENTER);        
        puertolocal.setPreferredSize(new java.awt.Dimension(50, 20));
        gridbag = new java.awt.GridBagConstraints();
        gridbag.insets = new java.awt.Insets(0, 0, 20, 0);
        derecha.add(puertolocal, gridbag);

        JButton crear = new JButton("Crear");
        crear.setPreferredSize(new java.awt.Dimension(77, 23));
        gridbag = new java.awt.GridBagConstraints();
        gridbag.gridx = 0;
        gridbag.gridy = 1;
        gridbag.insets = new java.awt.Insets(1, 0, 0, 0);
        derecha.add(crear, gridbag);
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker sw = new SwingWorker() {
					protected Object doInBackground() throws Exception {
						System.out.println("Server on localhost:" + puertolocal.getText() + " for " + nombre.getText());
						lobbyStatus.setText("Waiting for connection on port " + puertolocal.getText());
						return NetworkGame.createServer(nombre.getText(), Integer.valueOf(puertolocal.getText()));

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
							lobbyStatus.setText("Invalid PORT. Check format");
							
						}

					};
					
				};
				sw.execute();
				
			}
		});
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