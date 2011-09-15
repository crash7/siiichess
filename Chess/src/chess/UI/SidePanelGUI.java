package chess.UI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

class SidePanelGUI extends JScrollPane {
    private int pieceCant;
    private JPanel panelinterno;
    
    public SidePanelGUI() {
        setLayout(new ScrollPaneLayout());
        panelinterno = new JPanel();
        panelinterno.setPreferredSize(new Dimension(80, 600));
        panelinterno.setLayout(new BoxLayout(panelinterno, BoxLayout.PAGE_AXIS));
        setViewportView(panelinterno);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    public void updatePieces(String[] pieces) {
        if (pieces.length != pieceCant) {
            panelinterno.removeAll();
        	JLabel temp;
            pieceCant=0;
            for(int i = 0; i < pieces.length; i++) {
            	temp = new JLabel(PieceRepositoryGUI.get().getPiece(pieces[i]).getImageIcon());
                panelinterno.add(temp);
                pieceCant++;
                
            }
            panelinterno.revalidate();
            
        }
        
    }
    
}