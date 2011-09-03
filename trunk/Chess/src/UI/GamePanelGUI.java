
package UI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanelGUI extends JPanel {
    private String whiteName;
    private String blackName;
    private SidePanelGUI leftPanel;
    private SidePanelGUI rightPanel;
    private BoardGUI board;
    public GamePanelGUI()
    {
        this.setLayout(new BorderLayout());
        leftPanel = new SidePanelGUI();
        rightPanel = new SidePanelGUI();
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(board, BorderLayout.CENTER);
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
    
}
