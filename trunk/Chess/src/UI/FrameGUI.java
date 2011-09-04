package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FrameGUI extends javax.swing.JFrame {

    JPanel mainPanel;
    public FrameGUI(String title) {
        super(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        JPanel mainPanel = new JPanel(new BorderLayout(100,100));
        Label mainTitle = new Label("Chess Game");
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 100, 100));
        JButton localGame = new JButton("Jugar Localmente");
        JButton lanGame = new JButton("Jugar en Red");
        centerPanel.add(localGame);
        centerPanel.add(lanGame);
        mainPanel.add(mainTitle, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        this.setContentPane(new StartPanelGUI_M());
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new FrameGUI("Chess").setVisible(true);
            }
        });
    }
}