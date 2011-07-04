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

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return Rook.pieceRule.makeMove(move, board, king, oppiece);
    }

    static class RookRule {
        private boolean makeMove(Move move, Board board, King king, List oppiece) {
        	Piece piezaorigen = board.getPieceAt(move.getSource());
        	
            if (isValidMove(move) && pathIsClear(move, board)) {
                board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
                if (king.isChecked(board, oppiece)) {
                board.undoLastMove();
                return false;
                
            } else {
            	piezaorigen.incMoves();
                return true;
                
            }
          }else return false;
        }

        private boolean isValidMove(Move move) {
            Position source = move.getSource();
            Position destination = move.getDestination();
            if (((source.getX() == destination.getX()) && (source.getY() != source.getY()))
                 || ((source.getX() != destination.getX()) && (source.getY() == source.getY()))) {
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
                while (position.getX() != move.getDestination().getX()) {
                    position.setX(position.getX()+x);
                    if (board.getPieceAt(position) != null) {
                        return false;
                    }
                }
            }
            else {
                y = y/Math.abs(y);
                while (position.getY() != move.getDestination().getY()) {
                    position.setY(position.getY()+y);
                    if (board.getPieceAt(position) != null) {
                        return false;
                    }
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
