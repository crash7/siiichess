package UI;

import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JPanel;

class BoardGUI extends JPanel {

    public BoardGUI(GamePanelGUI panel) {
        init();
        
    }
    
    private void init() {
    	setLayout(new GridLayout(8, 8));
    	boolean dark = true;
    	for(int i=0; i < 8; i++) {
    		dark = !dark;
    		for(int j=0; j < 8; j++) {
    			add(new CellGUI(i, j, dark));
    			dark = !dark;
    			
    		}
    		
    	}
    	
    }
    
}
