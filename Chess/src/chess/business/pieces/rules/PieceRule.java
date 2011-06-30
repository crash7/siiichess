package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.Move;
import chess.business.pieces.Piece;
import chess.business.pieces.King;
import java.util.List;

public abstract class PieceRule {
    private Piece piece;

    public PieceRule(Piece piece) {
        this.piece = piece;
        
    }
    
    public boolean endsInCheck(Board board, King king, List oppiece) {
        /* Revisar si alguna lista de piesas se puede mover al rey */
    	/*
    	 * foundcheck = false;
    	 * foreach...oppiece
    	 * 	piece get move rule
    	 * 	if move rule. make move (..)
    	 * 		board undoLastMove
    	 * 		foundcheck = true
    	 * 		break
    	 * 
    	 * return foundcheck
    	 *  
    	 */
    	
    	return true;
        
    }
    
    public abstract boolean makeMove(Move move, Board board, King king, List oppiece);

    public void setPiece(Piece piece) {
        this.piece = piece;
        
    }
    
    public Piece getPiece() {
        return piece;
        
    }

}
