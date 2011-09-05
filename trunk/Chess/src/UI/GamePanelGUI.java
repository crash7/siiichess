package UI;

import chess.business.Controller;
import chess.dtos.PlayerDTO;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class GamePanelGUI extends JPanel implements Observer {
    public static final int LOCAL_GAME = 1;
    private int gameType;
    private PlayerDTO whitePlayer;
    private PlayerDTO blackPlayer;
    private PlayerDTO currentPlayer;
    private Controller controller;
    private CellGUI currentIteraction[];
    private SwingWorker actionWorker;
    private SidePanelGUI leftPanel;
    private SidePanelGUI rightPanel;
    private BoardGUI boardPanel;
    private TopPanelGUI topPanel;
    private MessagesGUI bottomPanel;

    public GamePanelGUI() {
    	gameType = GamePanelGUI.LOCAL_GAME;
    	controller = new Controller();
    	controller.addObserver(this);
    	currentIteraction = null;
    	actionWorker = null;
    	whitePlayer = new PlayerDTO();
    	whitePlayer.setColor('w');
    	whitePlayer.setName("Blancas");
    	blackPlayer = new PlayerDTO();
    	blackPlayer.setColor('b');
    	blackPlayer.setName("Negras");
    	init();

    }

    private void init() {
        setLayout(new BorderLayout());
        leftPanel = new SidePanelGUI();
        rightPanel = new SidePanelGUI();
        boardPanel = new BoardGUI();
        topPanel = new TopPanelGUI();
        bottomPanel = new MessagesGUI();

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        boardPanel.addMouseListener(new MouseListener() {
            private CellGUI start;
            private CellGUI end;

            public void mouseReleased(MouseEvent e) {
                if (start != null) {
                    start.setBorder(null);
                    end = (CellGUI) e.getComponent().getComponentAt(e.getX(), e.getY());
                    if (end != null && !start.equals(end)) {
                        boardAction(start, end);

                    }

                }

            }

            public void mousePressed(MouseEvent e) {
                start = (CellGUI) e.getComponent().getComponentAt(e.getX(), e.getY());
                if (!start.isEmpty() && start.getPiece().getColor() == currentPlayer.getColor()) {
                    start.setBorder(BorderFactory.createLoweredBevelBorder());

                } else {
                    start = null;

                }

            }

            public void mouseExited(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseClicked(MouseEvent e) { }
        });
        add(boardPanel, BorderLayout.CENTER);

    }

    public void setGameType(int type) {
        gameType = type;

    }

    public void startGame() {
    	currentPlayer = whitePlayer;
    	controller.newGame(whitePlayer, blackPlayer);
    	
    	
    }

    public void setBlackName(String blackName) {
        blackPlayer.setName(blackName);
    	topPanel.setBlackName(blackName);

    }

    public void setWhiteName(String whiteName) {
        whitePlayer.setName(whiteName);
        topPanel.setWhiteName(whiteName);

    }

    public void boardAction(CellGUI start, CellGUI end) {
    	if(actionWorker == null || actionWorker.getState() != SwingWorker.StateValue.STARTED) {
    		currentIteraction = new CellGUI[] {start, end};
    		actionWorker = new SwingWorker() {
        		protected Object doInBackground() throws Exception {
        			if(currentIteraction != null && currentIteraction.length == 2) {
        				// It's showtime!
        				//controller.move(player, xs, ys, xd, yd);
        				System.out.println("Me han llamado :D (esta es la llamada al controller!");
        		    	if(currentIteraction[0].isEmpty()) {
        		    		System.out.println("Celda start vacia");
        		    		
        		    	} else {
        		    		System.out.println("Celda start llena con: " + currentIteraction[0].getPiece().getKeyName());
        		    		
        		    	}
        		    	if(currentIteraction[1].isEmpty()) {
        		    		System.out.println("Celda end vacia");
        		    		
        		    	} else {
        		    		System.out.println("Celda end llena con: " + currentIteraction[1].getPiece().getKeyName());
        		    		    		
        		    	}
        				
        			}
        			return null;
        			
        		}
        		
        	};
    		actionWorker.execute();
    		
    	}
    	
    }
    
    private void swapPlayer() {
    	if(gameType == GamePanelGUI.LOCAL_GAME) {
    		if(currentPlayer == whitePlayer) {
    			currentPlayer = blackPlayer;
    			
    		} else {
    			currentPlayer = whitePlayer;
    			
    		}
    		
    	}
    	
    }

    public void update(Observable o, Object arg) {
        if (o != null && o instanceof Controller){
        	// Tenemos el tick de la vida!
        	if (arg!=null){
                    Integer i = (Integer) arg;
                    switch (i) {
                        case 0:
                            bottomPanel.Update("Jugando...");
                            break;
                        case 1:
                            bottomPanel.Update("Jaque blanco.");
                            break;
                        case 2:
                            bottomPanel.Update("Jaque negro.");
                            break;
                        case 3:
                            bottomPanel.Update("Jaque Mate blanco.");
                            break;
                        case 4:
                            bottomPanel.Update("Jaque Mate negro.");
                            break;
                        case 5:
                            bottomPanel.Update("Jaque.");
                            break;
                        case 6:
                            bottomPanel.Update("Movimiento Invalido. Por favor, vuelve a intentar.");
                            break;
                    }
                    boardPanel.paintBoard(controller.getBoard());
                }
        	
        }
        
    }

}
