package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import java.util.List;

public class Knight extends Piece {
    private static KnightRule pieceRule = new KnightRule();

    public Knight(char color) {
        super(color, 'N');

    }

    public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
        return Knight.pieceRule.makeMove(move, board, threatened, oppiece, safely);
        
    }
    
    public boolean isChecked(Board board, List oppiece) {
    	return Knight.pieceRule.isChecked(board, this, oppiece);
    	
    }

    static class KnightRule extends PieceRule {
        public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
        	Piece piezaorigen = board.getPieceAt(move.getSource());
        
        	if(isValidMove(move) && ((board.getPieceAt(move.getDestination()) == null)
        			|| !(board.getPieceAt(move.getDestination()).sameColour(piezaorigen)))) {
        		if(safely) { // el movimiento tiene que ser seguro, veamos el isCheck..
        			if (board.getPieceAt(move.getDestination())!=null) {
        				board.getPieceAt(move.getDestination()).setActive(false);
        			}
        			board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
        			
        			if(threatened.isChecked(board, oppiece)) {
        				board.undoLastMove();
        				return false;
        				
        			} else {
        				piezaorigen.incMoves();
        				return true;
        				
        			}
        			
	    		} else { // el movimiento es valido, con eso me alcanza
	    			return true;
	             	
	            }
                
            }
                else return false;
            
        
        }

        private boolean isValidMove(Move move) {
            Position source = move.getSource();
            Position destination = move.getDestination();
            int difY = Math.abs(source.getY() - destination.getY());
            int difX = Math.abs(source.getX() - destination.getX());

            return ((difY == 2 && difX == 1) || (difY == 1 && difX == 2));
            
        }
        
    }
    
}