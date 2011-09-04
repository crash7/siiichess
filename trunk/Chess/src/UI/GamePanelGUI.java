package UI;

import chess.business.Controller;
import chess.dtos.InactivePieceDTO;
import chess.dtos.PlayerDTO;
import java.awt.BorderLayout;
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
    private JPanel topPanel;
    private BoardGUI board;
    public GamePanelGUI() {
        controller = new Controller();
        init();
        
    }

    private void init() {
    	
    	
    	
    	controller = new Controller();
    	   	
    }
    
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
    }

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
