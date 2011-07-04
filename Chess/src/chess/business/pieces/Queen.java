package chess.business.pieces;

import chess.business.Move;
import chess.business.board.Board;
import java.util.List;

public class Queen extends Piece {
    private static QueenRule pieceRule = new QueenRule();
    public Queen(char color) {
        super(color, 'Q');

    }

    public boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
    	return Queen.pieceRule.makeMove(move, board, king, oppiece, safely);
    	
    }
    
    static class QueenRule {
    	private boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
    		Piece piezaorigen = board.getPieceAt(move.getSource());
    		boolean moved = false;
    		Piece piece = new Bishop(piezaorigen.getColor());
            if(piece.makeMove(move, board, king, oppiece, safely)) {
            	moved = true;
            	
            } else {
            	piece = new Rook(piezaorigen.getColor());
            	moved = piece.makeMove(move, board, king, oppiece, safely);
            	
            }
            
            if(moved) {
            	piezaorigen.incMoves();
            	
            }
            
            return moved;
    		
    	}
    	
    }
    
}
