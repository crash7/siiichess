package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LocalFormGUI extends JPanel {
	
	public LocalFormGUI() {
		super();
		init();
		
	}
	
	private void init() {
		JLabel title = new JLabel("rose");
		JLabel whiteimage = new JLabel(PieceRepositoryGUI.get().getPiece("kwb").getImage());
		JLabel blackimage = new JLabel(PieceRepositoryGUI.get().getPiece("kbb").getImage());;
		JTextField whitename = new JTextField("Blancas", 20);
		JTextField blackname = new JTextField("Negras", 20);
	    JButton start = new JButton("Comenzar");
	    GridBagConstraints gbcontraints;
	    
	    setLayout(new GridBagLayout());
	    
	    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    title.setFont(new Font("Trebuchet MS", Font.BOLD, 64));
	    gbcontraints = new GridBagConstraints();
	    gbcontraints.gridx = 0;
	    gbcontraints.gridy = 0;
	    //gbcontraints.weightx = 2.0;
	    add(title, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 1;
		add(whiteimage, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 1;
	    add(whitename, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 0;
		gbcontraints.gridy = 2;
	    add(blackimage, gbcontraints);
	    
	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 1;
		gbcontraints.gridy = 2;
	    add(blackname, gbcontraints);

	    gbcontraints = new GridBagConstraints();
		gbcontraints.gridx = 2;
		gbcontraints.gridy = 4;
		gbcontraints.anchor = GridBagConstraints.CENTER;
		gbcontraints.weightx = 2.0;
		gbcontraints.fill = GridBagConstraints.HORIZONTAL;
	    add(start, gbcontraints);
	    
	    
	    
		
	}

}
