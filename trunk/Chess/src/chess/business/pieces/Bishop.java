package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static BishopRule pieceRule = new BishopRule();

    public Bishop(char color) {
        super(color, 'B');
        
    }
    
    protected PieceRule getRules() {
		return Bishop.pieceRule;
		
	}

    static class BishopRule extends PieceRule {
    	
		public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
			int x = end.getX() - context.getPosition().getX();
	        int y = end.getY() - context.getPosition().getY();
	        
	        if(context.getPosition().isEqual(end)) {
	        	return false;
	        	
	        } else {
	        	if(Math.abs(x) == Math.abs(y)) {
	        		// movimiento valido
	        		x = x / Math.abs(x);
		            y = y / Math.abs(y);
		            Position temp = new Position(context.getPosition().getX(), context.getPosition().getY());
		            Piece thing;
	        		while(!temp.isEqual(end)) {
	        			temp.setX(temp.getX()+x);
	        			temp.setY(temp.getY()+y);
	        			thing = board.getPieceAt(temp);
	        			if(thing != null) {
	        				if(temp.isEqual(end)) {
	        					if(thing.sameColour(context)) {
	        						return false;
	        						
	        					}
	        					
	        				} else {
	        					return false;
	        					
	        				}
	        				
	        			}
	        			
	        		}
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
	            
		}

		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			List positions;
			int x = end.getX() - context.getPosition().getX();
	        int y = end.getY() - context.getPosition().getY();
	        if(Math.abs(x) == Math.abs(y)) {
	        	positions = new ArrayList();
	        	x = x / Math.abs(x);
	            y = y / Math.abs(y);
	            Position temp = new Position(context.getPosition().getX()+x, context.getPosition().getY()+y);
        		while(!temp.isEqual(end)) {
        			temp.setX(temp.getX()+x);
        			temp.setY(temp.getY()+y);
        			if(board.getPieceAt(temp) == null) {
        				positions.add(new Position(temp.getX(), temp.getY()));
        				
        			}
        			
        		}
        		if(positions.size() > 0) {
        			return positions;
        			
        		}
        		
	        }
			return null;
			
		}
		
    }
	
}
