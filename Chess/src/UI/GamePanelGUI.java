package UI;

import chess.business.Controller;
import chess.dtos.InactivePieceDTO;
import chess.dtos.PlayerDTO;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanelGUI extends JPanel {
	public static final int LOCAL_GAME = 1;
	private int gameType;
    private String whiteName;
    private String blackName;
    private Controller controller;
    
    
    private SidePanelGUI leftPanel;
    private SidePanelGUI rightPanel;
    private BoardGUI boardPanel;
    private TopPanelGUI topPanel;
   
    public GamePanelGUI() {
        controller = new Controller();
        init();
        
    }

    private void init() {
    	setLayout(new BorderLayout());
    	
    	leftPanel = new SidePanelGUI();
    	rightPanel = new SidePanelGUI();
    	boardPanel = new BoardGUI(this);
    	topPanel = new TopPanelGUI();
    	add(topPanel, BorderLayout.NORTH);
    	add(leftPanel, BorderLayout.WEST);
    	add(rightPanel, BorderLayout.EAST);
    	add(boardPanel, BorderLayout.CENTER);
    	controller = new Controller();
    	
    	
    	// test
    	boardPanel.addMouseListener(new MouseListener() {
			
    		public void mouseReleased(MouseEvent arg0) {
				System.out.println("mouse release");
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				System.out.println("mouse press");
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				System.out.println("mouse exitado");
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("mouse entro");
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("we got clicked");
				
			}
		});
    	
    	   	
    }
    
    
    /*
    public GamePanelGUI(String whiteName, String blackName) {
        this.whiteName = whiteName;
        this.blackName = blackName;
        controller = new Controller();
        this.setLayout(new BorderLayout());
        leftPanel = new SidePanelGUI();
        rightPanel = new SidePanelGUI();
        board = new BoardGUI();
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new JLabel("Blancas"), BorderLayout.WEST);
        topPanel.add(new JLabel("Negras"), BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(board, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }*/

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public void setWhiteName(String whiteName) {
        this.whiteName = whiteName;
    }
    
    public String[][] getBoard() {
        return controller.getBoard();
    }
    
    public InactivePieceDTO[] getInactivePieces(PlayerDTO player) {
        return controller.getPlayersInactivePieces(player);
    }

	public void setWhitePlayerName(String text) {
		whiteName = text;
		
	}

	public void setBlackPlayerName(String text) {
		blackName = text;
		
	}

	public void setGameType(int type) {
		gameType = type; 
		
	}

	public void startGame() {
		// TODO Auto-generated method stub
		
	} 
}
