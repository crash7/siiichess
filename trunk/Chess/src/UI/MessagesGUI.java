/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.FlowLayout;
import java.util.concurrent.FutureTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Izo
 */
public class MessagesGUI extends JPanel {
    private JLabel message;
    public MessagesGUI(){
        super();
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        message = new JLabel("Bienvenid@ a el juego");
        add(message);
    }
    public void Update(String s){
        message.setText(s);
    }
            
}
