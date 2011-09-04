package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

class BoardGUI extends JPanel {

    public BoardGUI(GamePanelGUI panel) {
        init();
        
    }
    
    private void init() {
    	CellGUI temp;
    	setLayout(new GridLayout(8, 8));
    	boolean dark = true;
    	for(int i=0; i < 8; i++) {
    		dark = !dark;
    		for(int j=0; j < 8; j++) {
    			
    			temp = new CellGUI(i, j, dark);
    			add(temp);
    			dark = !dark;
    			if(i == 0 || i == 7) {
    				temp.setPiece(PieceRepositoryGUI.get().getPiece("kb"));
    				
    			}
    		}
    		
    	}
    	
    }
    
}
