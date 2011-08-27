/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.FlowLayout;
import javax.swing.JTextField;

/**
 *
 * @author mori
 */
public class PlayerForm extends javax.swing.JPanel {
    private JTextField name;
    public PlayerForm () {
        name = new JTextField(5);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        this.add(name);
        
    }
    
}
