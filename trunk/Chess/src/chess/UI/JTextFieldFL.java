/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UI;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Izo
 */
public class JTextFieldFL extends JTextField {
    private String texto;
    
    public JTextFieldFL(String s){
        super(s);
        texto=s;
        addFocusListener(new FocusListener() {

             public void focusGained(FocusEvent e) {
        setText("");
    }

    public void focusLost(FocusEvent e) {
        if (getText().equals("")) setText(texto);
        else texto=getText();
    }
        });
    }

 
    
}
