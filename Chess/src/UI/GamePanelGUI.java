package UI;

import chess.business.Controller;
import chess.dtos.PlayerDTO;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanelGUI extends JPanel {

    public static final int LOCAL_GAME = 1;
    private int gameType;
    private char currentColor;
    private String whiteName;
    private String blackName;
    private Controller controller;
    private SidePanelGUI leftPanel;
    private SidePanelGUI rightPanel;
    private BoardGUI boardPanel;
    private TopPanelGUI topPanel;

    public GamePanelGUI() {
        currentColor = 'w';
        gameType = GamePanelGUI.LOCAL_GAME;
        controller = new Controller();
        init();

    }

    private void init() {
        setLayout(new BorderLayout());
        leftPanel = new SidePanelGUI();
        rightPanel = new SidePanelGUI();
        boardPanel = new BoardGUI();
        topPanel = new TopPanelGUI();

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

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
                if (!start.isEmpty() && start.getPiece().getColor() == currentColor) {
                    start.setBorder(BorderFactory.createLoweredBevelBorder());

                } else {
                    start = null;

                }

            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
        add(boardPanel, BorderLayout.CENTER);

    }

    public void setGameType(int type) {
        gameType = type;

    }

    public void startGame() {
        PlayerDTO whitePlayerDTO = new PlayerDTO();
        PlayerDTO blackPlayerDTO = new PlayerDTO();
        whitePlayerDTO.setColor('w');
        whitePlayerDTO.setName(whiteName);

        blackPlayerDTO.setColor('b');
        blackPlayerDTO.setName(blackName);

        controller.newGame(whitePlayerDTO, blackPlayerDTO);


    }

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
        topPanel.setBlackName(blackName);

    }

    public String getWhiteName() {
        return whiteName;
    }

    public void setWhiteName(String whiteName) {
        this.whiteName = whiteName;
        topPanel.setWhiteName(whiteName);

    }

    public void boardAction(CellGUI start, CellGUI end) {
        System.out.println("Me han llamado :D");
        if (start.isEmpty()) {
            System.out.println("Celda start vacia");

        } else {
            System.out.println("Celda start llena con: " + start.getPiece().getKeyName());

        }

        if (end.isEmpty()) {
            System.out.println("Celda end vacia");

        } else {
            System.out.println("Celda end llena con: " + end.getPiece().getKeyName());

        }

    }
}
