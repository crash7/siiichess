package chess.UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

class SidePanelGUI extends JPanel {
    private int pieceCant;

    public SidePanelGUI() {
    	super();
        setLayout(new GridLayout(8, 1));
        JLabel header = new JLabel("PIEZAS COMIDAS");
        header.setBorder(new EmptyBorder(0, 4, 0, 4));
        add(header);
    }
    
    public void updatePieces(String[] pieces) {
        if (pieces.length != this.pieceCant) {
            JLabel temp;
            pieceCant=0;
            removeAll();
            JLabel header = new JLabel("PIEZAS COMIDAS");
            header.setBorder(new EmptyBorder(0, 4, 0, 4));
            add(header);
            for(int i = 0; i < pieces.length; i++) {
            	temp = new JLabel(PieceRepositoryGUI.get().getPiece(pieces[i]).getImage());
                add(temp);
                pieceCant++;
            }
            revalidate();
            
        }
        
    }
    
}
