package chess.business;

import chess.business.pieces.Piece;

public class Square {
    private Piece piece;
    public Square(){

    }
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean isEmpty(){
        if (this.piece == null) {
            return true;
        }
        else {
            return false;
        }

    }

}
