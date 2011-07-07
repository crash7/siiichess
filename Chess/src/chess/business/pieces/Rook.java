package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import java.util.List;

public class Rook extends Piece {
    private static RookRule pieceRule = new RookRule();

    public Rook(char color) {
        super(color, 'R');
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
        return Rook.pieceRule.makeMove(move, board, king, oppiece, safely);
    }

    static class RookRule {
        private boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
        	Piece piezaorigen = board.getPieceAt(move.getSource());
        	
            if (isValidMove(move) && pathIsClear(move, board)) {
            	if(safely) { // el movimiento tiene que ser seguro, veamos el isCheck..
            		if(board.getPieceAt(move.getDestination())!=null) {
            			board.getPieceAt(move.getDestination()).setActive(false);
            		}
            		board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
	                if (king.isChecked(board, oppiece)) {
		                board.undoLastMove();
		                return false;
	                
		            } else {
		            	piezaorigen.incMoves();
		                return true;
		                
		            }
	                
                } else { // el movimiento es valido, con eso me alcanza
                	return true;
                	
                }
            }else return false;
        }

        private boolean isValidMove(Move move) {
            Position source = move.getSource();
            Position destination = move.getDestination();
            if (((source.getX() == destination.getX()) && (source.getY() != destination.getY()))
                 || ((source.getX() != destination.getX()) && (source.getY() == destination.getY()))) {
                return true;
            } else {
                return false;
            }
        }

        private boolean pathIsClear(Move move, Board board) {
            int x = move.getDestination().getX() - move.getSource().getX();
            int y = move.getDestination().getY() -  move.getSource().getY();
            Position position = new Position (move.getSource().getX(),move.getSource().getY());
            if (Math.abs(x) >= 1) {
                x = x/Math.abs(x);
                position.setX(position.getX()+x);
                while (position.getX() != move.getDestination().getX()) {
                    if (board.getPieceAt(position) != null) {
                        return false;
                    }
                    position.setX(position.getX()+x);
                }
            }
            else {
                y = y/Math.abs(y);
                position.setY(position.getY()+y);
                while (position.getY() != move.getDestination().getY()) {
                   if (board.getPieceAt(position) != null) {
                        return false;
                    }
                   position.setY(position.getY()+y);
                }
                
                
            }
            Piece piece = null;
            piece= board.getPieceAt(position);
            if (piece != null)
            {   
                if (board.getPieceAt(move.getSource()).sameColour(piece))return false;
                return true;
            }
            else return true;
            
            
        }
    }
}
