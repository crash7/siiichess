package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;
import java.util.List;

public class Queen extends Piece {
    private static QueenRule pieceRule = new QueenRule();
    
    public Queen(char color) {
        super(color, 'Q');

    }
    
    protected PieceRule getRules() {
		return Queen.pieceRule;
		
	}
    
    static class QueenRule extends PieceRule {
    	private Piece rook;
    	private Piece bishop;
    	
		public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
			rook = new Rook(context.getColor());
			rook.setPosition(new Position(context.getPosition().getX(), context.getPosition().getY()));
			if(rook.makeMove(board, end, oppiece)) {
				board.undoLastMove();
				
			} else {
				bishop = new Bishop(context.getColor());
				bishop.setPosition(new Position(context.getPosition().getX(), context.getPosition().getY()));
				if(bishop.makeMove(board, end, oppiece)) {
					board.undoLastMove();
					
				} else {
					return false;
					
				}
    			
    		}
			Piece captured = board.getPieceAt(end);
			if(captured != null) {
				captured.setActive(false);
				
			}
			board.logMove(context, context.getPosition(), captured, end);
			board.setPieceAt(null, context.getPosition());
			board.setPieceAt(context, end);
			return true;
			
		}
    	
		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			List positions;
			rook.setColor(context.getColor());
			rook.setPosition(context.getPosition());
			positions = rook.getThreatenedPositionsTo(end, board);
			if(positions == null) {
				bishop.setColor(context.getColor());
				bishop.setPosition(context.getPosition());
				positions = bishop.getThreatenedPositionsTo(end, board);
				
			}
			return positions;
			
		}
    	
    }
    
}
