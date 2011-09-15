package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static RookRule pieceRule = new RookRule();

    public Rook(char color) {
        super(color, 'R');
        
    }
    
    protected PieceRule getRules() {
		return Rook.pieceRule;
		
	}

    static class RookRule extends PieceRule {
    	
		public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
			if(((context.getPosition().getX() == end.getX()) && (context.getPosition().getY() != end.getY()))
	                 || ((context.getPosition().getX() != end.getX()) && (context.getPosition().getY() == end.getY()))) {
				// movimiento valido
				int x = end.getX() - context.getPosition().getX();
	            int y = end.getY() - context.getPosition().getY(); 
	            Position temp = new Position(context.getPosition().getX(), context.getPosition().getY());
				if(Math.abs(x) >= 1) { // vertical
					x = x/Math.abs(x);
					temp.setX(temp.getX()+x);
	                while (temp.getX() != end.getX()) {
	                    if (board.getPieceAt(temp) != null) {
	                        return false;
	                        
	                    }
	                    temp.setX(temp.getX()+x);
	                    
	                }
					
					
				} else { // horizontal
					y = y/Math.abs(y);
					temp.setY(temp.getY()+y);
	                while (temp.getY() != end.getY()) {
	                    if (board.getPieceAt(temp) != null) {
	                        return false;
	                        
	                    }
	                    temp.setY(temp.getY()+y);
	                    
	                }
					
				}
				// parece que tenemos el camino libre de bichos
				Piece captured = board.getPieceAt(end);
				if(captured != null) {
					if(captured.sameColour(context)) {
						return false;
						
					} else {
						captured.setActive(false);
						
					}
					
				}
				board.logMove(context, context.getPosition(), captured, end);
				board.setPieceAt(null, context.getPosition());
				board.setPieceAt(context, end);
				return true;
				
			} else {
				return false; // movimiento no valido
				
			}
			
		}

		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			// TODO Auto-generated method stub
			List positions = new ArrayList();
            int x = end.getX() - context.getPosition().getX();
            int y = end.getY() - context.getPosition().getY();
            Position temp = new Position(context.getPosition().getX(), context.getPosition().getY());
            if (Math.abs(x) >= 1) { // vertical
                x = x / Math.abs(x);
                temp.setX(temp.getX() + x);
                while (temp.getX() != end.getX()) {
                    if (board.getPieceAt(temp) != null) {
                        return null;
 
                    } else {
                    	positions.add(new Position(temp.getX(), temp.getY()));
                    	
                    }
                    temp.setX(temp.getX() + x);
 
                }
 
 
            } else { // horizontal
                y = y / Math.abs(y);
                temp.setY(temp.getY() + y);
                while (temp.getY() != end.getY()) {
                    if (board.getPieceAt(temp) != null) {
                        return null;
 
                    }else {
                    	positions.add(new Position(temp.getX(), temp.getY()));
                    	
                    }
                    temp.setY(temp.getY() + y);
 
                }
 
            }
            return positions;
			
		}
		
    }

}
