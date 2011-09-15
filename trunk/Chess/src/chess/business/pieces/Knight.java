package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;
import java.util.List;

public class Knight extends Piece {
    private static KnightRule pieceRule = new KnightRule();

    public Knight(char color) {
        super(color, 'N');

    }
    
	protected PieceRule getRules() {
		return Knight.pieceRule;
		
	}

    static class KnightRule extends PieceRule {

    	public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
            int x = Math.abs(context.getPosition().getX() - end.getX());
            int y = Math.abs(context.getPosition().getY() - end.getY());
            if((y == 2 && x == 1) || (y == 1 && x == 2)) {
            	// movimiento valido
            	Piece captured = board.getPieceAt(end);
            	if(captured != null) {
            		if(captured.sameColour(context)) {
            			return false;
            			
            		} else {
            			captured.setActive(false);
            			captured.incMoves();
            		}
            		
            	}
            	board.logMove(context, context.getPosition(), captured, end);
				board.setPieceAt(null, context.getPosition());
				board.setPieceAt(context, end);
				context.incMoves();
				return true;
            	 
            } else {
            	return false;
            	
            }
            
		}

		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			return null;
			
		}
        
    }
    
}