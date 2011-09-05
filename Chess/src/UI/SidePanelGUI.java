package UI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SidePanelGUI extends JPanel {
    //private final PieceGUI piece;

    private int pieceCant;

    public SidePanelGUI() {
        //this.add(new JLabel("side Panel"));
        setLayout(new GridLayout(1, 8));
    }
    public void updatePieces(String[] pieces) {
        JLabel temp = null;
        if (pieces.length != this.pieceCant) {
            for (int i = 0; i < pieces.length; i++) {
                temp.setIcon(PieceRepositoryGUI.get().getPiece(pieces[i]).getImage());
                this.add(temp);
                pieceCant++;
            }
        }
    }
}
