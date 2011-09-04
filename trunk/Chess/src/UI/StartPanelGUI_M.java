/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author mori
 * @deprecated
 */
public class StartPanelGUI_M extends javax.swing.JPanel {

    private JButton localGame;
    private JButton netGame;
    private Label mainTitle;

    public StartPanelGUI_M() {
        localGame = new JButton("Jugar localmente");
        netGame = new JButton("Jugar en red");
        mainTitle = new Label("Chess Game");
        mainTitle.setPreferredSize(new java.awt.Dimension(400, 50));
        mainTitle.setFont(new java.awt.Font("Times", 0, 24));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        localGame.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                localGameMouseClicked();
            }
        });
        this.add(mainTitle);
        this.add(localGame);
        this.add(netGame);
    }

    private void localGameMouseClicked() {
        JFrame topFrame = (JFrame) this.getTopLevelAncestor();
        topFrame.getContentPane().removeAll();
        topFrame.invalidate();
        topFrame.setContentPane(new PlayerFormGUI() );
        topFrame.validate();
    }
}
