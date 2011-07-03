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

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return Knight.pieceRule.makeMove(move, board, king, oppiece);
        
    }

    static class KnightRule {
        private boolean makeMove(Move move, Board board, King king, List oppiece) {
        	Piece piezaorigen = board.getPieceAt(move.getSource());
        
        	if (isValidMove(move) && (board.getPieceAt(move.getDestination()) == null)
                    || !(board.getPieceAt(move.getDestination()).sameColour(piezaorigen))) {
                board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
                
            }
            
            if(king.isChecked(board, oppiece)) {
                board.undoLastMove();
                return false;
            
            } else {
            	piezaorigen.incMoves();
                return true;
            
            }
        
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