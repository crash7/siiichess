package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;
import java.util.List;

public class Pawn extends Piece {
    private static PawnRule pieceRule = new PawnRule();

    public Pawn(char color) {
        super(color, 'P');

    }
    
    protected PieceRule getRules() {
		return Pawn.pieceRule;
		
	}

    static class PawnRule extends PieceRule {
    	
		public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
			int x = end.getX() - context.getPosition().getX();
			int y = end.getY() - context.getPosition().getY();
			
    		if(Math.abs(x) == 1) {
    			if(context.isWhite()) {
    				if(x != -1 && (y != 0 || Math.abs(y) != 1)) {
    					return false;
    					
    				}
    				
    			} else {
    				if(x != 1 && (y != 0 || Math.abs(y) != 1)) {
    					return false;
    					
    				}
    				
    			}
				if(y == 0) {
					if(board.getPieceAt(end) == null) {
						// alter table
						board.logMove(context, context.getPosition(), null, end);
						board.setPieceAt(null, context.getPosition());
						board.setPieceAt(context, end);
						return true;
						
					} else {
						return false;
						
					}
					
				} else if(Math.abs(y) == 1) {
					Piece captured = board.getPieceAt(end);
					if(captured == null) { // comer al paso
						/*captured = board.getPieceAt(new Position(context.getPosition().getX(), end.getY()));
						Piece lastmovepiece = board.getLastMovePiece();
						if(lastmovepiece != null) {
							if(captured == lastmovepiece && lastmovepiece instanceof Pawn && lastmovepiece.getMovesCount() == 1) {
								// tenemos un comer al paso original
								// set table
								board.logMove(context, context.getPosition(), captured, captured.getPosition());
								board.setPieceAt(null, context.getPosition());
								board.setPieceAt(context, end);
								board.setPieceAt(null, captured.getPosition());
								captured.setActive(false);
								return true;
								
							} else {
								return false;
								
							}
							
						} else {
							return false;
							
						}*/
						return false;
						
					} else {
						if(context.sameColour(captured)) {
							return false;
						
						} else {
							board.logMove(context, context.getPosition(), captured, end);
							board.setPieceAt(null, context.getPosition());
							board.setPieceAt(context, end);
							captured.setActive(false);
							return true;
							
						}
						
					}
					
					
				} else {
					return false;
					
				}
    			
    		} else if(Math.abs(x) == 2 && y == 0 && !context.hasMoved()) {
    			if(context.isWhite()) {
    				if(x == -2) {
    					if((board.getPieceAt(end) == null) && (board.getPieceAt(new Position(end.getX()+1, y)) == null)) {
    						board.logMove(context, context.getPosition(), null, end);
    						board.setPieceAt(null, context.getPosition());
    						board.setPieceAt(context, end);
    						return true;
    						
    					} else {
    						return false;
    						
    					}
    					
    				} else {
    					return false;
    					
    				}
    				
    			} else {
    				if(x == 2) {
    					if((board.getPieceAt(end) == null) && (board.getPieceAt(new Position(end.getX()-1, y)) == null)) {
    						board.logMove(context, context.getPosition(), null, end);
    						board.setPieceAt(null, context.getPosition());
    						board.setPieceAt(context, end);
    						return true;
    						
    					} else {
    						return false;
    						
    					}
    					
    				} else {
    					return false;
    					
    				}
    				
    			}
    			
    		} else {
    			return false;
    			
    		}
    	}

		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			return null;		
			
		}
    	
   }
    
}
