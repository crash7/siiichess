package UI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SidePanelGUI extends JPanel {
    private int pieceCant;

    public SidePanelGUI() {
    	super();
        setLayout(new GridLayout(1, 8));
        
    }
    
    public void updatePieces(String[] pieces) {
        if (pieces.length != this.pieceCant) {
        	JLabel temp;
            pieceCant=0;
            removeAll();
            for(int i = 0; i < pieces.length; i++) {
            	temp = new JLabel(PieceRepositoryGUI.get().getPiece(pieces[i]).getImage());
                add(temp);
                pieceCant++;
            }
            revalidate();
            
        }
        
    }
    
}
