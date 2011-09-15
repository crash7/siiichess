package chess.UI;

import chess.business.BusinessController;
import chess.dtos.PlayerDTO;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class GamePanelGUI extends JPanel implements Observer {
    public static final int LOCAL_GAME = 1;
    private int gameType;
    private PlayerDTO whitePlayer;
    private PlayerDTO blackPlayer;
    private PlayerDTO currentPlayer;
    private BusinessController controller;
    private CellGUI currentIteraction[];
    private SwingWorker actionWorker;
    private SidePanelGUI leftPanel;
    private SidePanelGUI rightPanel;
    private BoardGUI boardPanel;
    private TopPanelGUI topPanel;
    private JLabel bottomLabel;
    
    public GamePanelGUI() {
    	gameType = GamePanelGUI.LOCAL_GAME;
    	controller = new BusinessController();
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
        bottomLabel = new JLabel();
        bottomLabel.setHorizontalAlignment(JLabel.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(bottomLabel, BorderLayout.SOUTH);
        
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
    	currentPlayer = blackPlayer;
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
    	if((actionWorker == null || actionWorker.isDone())) {
    		currentIteraction = new CellGUI[] {start, end};
    		actionWorker = new SwingWorker() {
        		protected Object doInBackground() throws Exception {
        			if(currentIteraction != null && currentIteraction.length == 2) {
        				// It's showtime!
        				controller.playerMove(currentPlayer, 
        						currentIteraction[0].getXPosition(), currentIteraction[0].getYPosition(), 
        						currentIteraction[1].getXPosition(), currentIteraction[1].getYPosition());
        				
        			}
        			return null;
        			
        		}
        		
        		protected void done() {
        			try {
						get();
					} catch (InterruptedException e) {
						e.printStackTrace();
						
					} catch (ExecutionException e) {
						e.printStackTrace();
						
					} catch (Exception e) {
						e.printStackTrace();
						
					}
        			
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
        if(o != null && o instanceof BusinessController) {
        		final Integer i = (Integer) arg;
                        SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    	System.out.println("Update: " + i);
        		if(i.intValue() != BusinessController.ILEGALMOVE) {
        			boardPanel.paintBoard(controller.getBoard());
        			rightPanel.updatePieces(controller.getInactivePiecesOf(blackPlayer));
        			leftPanel.updatePieces(controller.getInactivePiecesOf(whitePlayer));
        			swapPlayer();

        		}
        		switch (i.intValue()) {
                case BusinessController.LEGALMOVE:
                    bottomLabel.setText("Jugando..." + currentPlayer.getName());
                    break;
                case BusinessController.WHITECHECK:
                    bottomLabel.setText("Jaque al blanco.");
                    break;
                case BusinessController.BLACKCHECK:
                    bottomLabel.setText("Jaque al negro.");
                    break;
                case BusinessController.WHITECHECKMATE:
                    bottomLabel.setText("Jaque mate al blanco.");
                    boardPanel.removeMouseListener(boardPanel.getMouseListeners()[0]);
                    break;
                case BusinessController.BLACKCHECKMATE:
                    bottomLabel.setText("Jaque mate al negro.");
                    boardPanel.removeMouseListener(boardPanel.getMouseListeners()[0]);
                    break;
                case BusinessController.ILEGALMOVE:
                    bottomLabel.setText("Movimiento Invalido. Por favor, intenta nuevamente.");
                    break;

        		}


                }

                });
            } {
        	
        	
        }
        
    }
    
}