/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author mori
 * @deprecated
 */
public class PlayerFormGUI extends javax.swing.JPanel {

    private JTextField blackName;
    private JTextField whiteName;
    private JButton startButton;

    public PlayerFormGUI() {
        blackName = new JTextField(25);
        whiteName = new JTextField(25);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        this.add(new JLabel("Nombre Jugador Blanco"));
        this.add(whiteName);
        this.add(new JLabel("Nombre Jugador Negro "));
        this.add(blackName);
        startButton = new JButton("Comenzar");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startGame(evt);
            }
        });
        this.add(startButton);
    }
    private void startGame(java.awt.event.MouseEvent evt) {
        GamePanelGUI gamePanel = new GamePanelGUI(this.whiteName.getText(),this.blackName.getText());
        JFrame topFrame = (JFrame) this.getTopLevelAncestor();
        topFrame.getContentPane().removeAll();
        topFrame.invalidate();
        topFrame.setContentPane(gamePanel);
        topFrame.validate();
    }
}
