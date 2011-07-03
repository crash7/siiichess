package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.Move;
import chess.business.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class RookRule extends PieceRule {

    public RookRule() {
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        if (isValidMove(move) && pathIsClear(move, board)) {
            board.move(new PieceMove(this.getPiece(), board.getPieceAt(move.getDestination()), move));
        }
        if (endsInCheck(board, king, oppiece)) {
            board.undoLastMove();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidMove(Move move) {
        Position source = move.getSource();
        Position destination = move.getDestination();
        if ((source.getX() == destination.getX())
                && (source.getY() != source.getY())
                || (source.getX() != destination.getX())
                && (source.getY() == source.getY())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean pathIsClear(Move move, Board board) {
        Position source = move.getSource();
        Position destination = move.getDestination();
        boolean valid = true;
        if (source.getX() != destination.getX()) {

            int i = source.getX();
            while (i < destination.getX() && valid) {
                i++;
                if (board.getPieceAt(new Position(i, source.getY())) != null) {
                    valid = false;
                }
            }
        } else if (source.getY() != destination.getY()) {

            int i = source.getY();
            while (i < destination.getY() && valid) {
                i++;
                if (board.getPieceAt(new Position(i, source.getX())) != null) {
                    valid = false;
                }
            }
        }
        return valid;
    }
}