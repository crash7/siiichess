package chess.business.board;

import chess.business.util.Move;
import chess.business.pieces.Piece;

public class PieceMove {
    private Piece piece;
    private Piece captured;
    private Move move;
    
    public PieceMove(Piece p, Piece c, Move m) {
    	this.piece = p;
    	this.captured = c;
    	this.move = m;
    	
    }
    
    public PieceMove() {
    	this(null, null, null);
    	
    }
    
    public void setMove(Move m) {
    	this.move = m;
    	
    }

    public Move getMove() {
        return move;
        
    }

    public void setPiece(Piece p) {
    	this.piece = p;
    	
    }
    
    public Piece getPiece() {
        return piece;
        
    }
    
    public void setCaptured(Piece c) {
    	this.captured = c;
    	
    }
    
    public Piece getCaptured() {
    	return this.captured;
    	
    }
    
}
