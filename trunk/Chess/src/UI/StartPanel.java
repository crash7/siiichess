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
 */
public class StartPanel extends javax.swing.JPanel {

    private JButton localGame;
    private JButton netGame;
    private Label mainTitle;
    private JFrame frame;

    public StartPanel(JFrame frame) {
        localGame = new JButton("Jugar localmente");
        netGame = new JButton("Jugar en red");
        mainTitle = new Label("Chess Game");
        mainTitle.setPreferredSize(new java.awt.Dimension(400, 50));
        mainTitle.setFont(new java.awt.Font("Times", 0, 24));
        this.frame = frame;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        localGame.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                localGameMouseClicked(evt);
            }
        });
        this.add(mainTitle);
        this.add(localGame);
        this.add(netGame);
    }

    private void localGameMouseClicked(MouseEvent evt) {
        //this.mainTitle.setText(evt.toString());
        frame.getContentPane().removeAll();
        frame.invalidate();
        frame.setContentPane(new PlayerForm() );
        frame.validate();
    }
}
