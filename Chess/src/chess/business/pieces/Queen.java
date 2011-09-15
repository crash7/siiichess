package chess.business.pieces;

import chess.business.Board;
import chess.business.Move;
import java.util.List;

public class Queen extends Piece {
    private static QueenRule pieceRule = new QueenRule();
    public Queen(char color) {
        super(color, 'Q');

    }

    public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
    	return Queen.pieceRule.makeMove(move, board, threatened, oppiece, safely);
    	
    }
    
    public boolean isChecked(Board board, List oppiece) {
    	return Queen.pieceRule.isChecked(board, this, oppiece);
    	
    }
    
    static class QueenRule extends PieceRule {
    	public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
    		Piece piezaorigen = board.getPieceAt(move.getSource());
    		boolean moved = false;
    		Piece piece = new Bishop(piezaorigen.getColor());
            if(piece.makeMove(move, board, threatened, oppiece, safely)) {
            	moved = true;
               
            	
            } else {
            	piece = new Rook(piezaorigen.getColor());
            	moved = piece.makeMove(move, board, threatened, oppiece, safely);
                
            	
            }
            return moved;
    		
    	}
    	
    }
    
}
