package chess.business.board;

import chess.business.pieces.Piece;

public class Square {
	private Piece piece;
	
    public Square() {
    	this.piece = null;

    }
    
    public Piece getPiece() {
    	return this.piece;
    	
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        
    }
    
    public boolean isEmpty(){
    	return this.piece == null;

    }

}
