package UI;

import java.awt.GridLayout;
import javax.swing.JPanel;

class BoardGUI extends JPanel {

    public BoardGUI() {
        setLayout(new GridLayout(8, 8));
        paintBoard(new String[8][8]);

    }

    public void paintBoard(String[][] board) {
        CellGUI tempCell;
        boolean dark = true;
        removeAll();
        for(int i = 0; i < board.length; i++) {
            dark = !dark;
            for(int j = 0; j < board[i].length; j++) {
                tempCell = new CellGUI(i, j, dark);
                if(board[i][j] != null) {
                	tempCell.setPiece(PieceRepositoryGUI.get().getPiece(board[i][j]));

                }
                add(tempCell);
                dark = !dark;

            }

        }
        this.revalidate();
    }
    
}