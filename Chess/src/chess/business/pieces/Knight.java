package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.pieces.rules.PieceRule;
import java.util.List;

public class Knight extends Piece {

    private PieceRule pieceRule = new KnightRule();

    public Knight(char color) {
        super(color, 'N');

    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return this.pieceRule.makeMove(move, board, king, oppiece);
    }

    public class KnightRule extends PieceRule {

        public boolean makeMove(Move move, Board board, King king, List oppiece) {
            if (isValidMove(move)
                    && (board.getPieceAt(move.getDestination()) == null)
                    || !(board.getPieceAt(move.getDestination()).sameColour(board.getPieceAt(move.getSource())))) {
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
            int difY = Math.abs(source.getY() - destination.getY());
            int difX = Math.abs(source.getX() - destination.getX());
            if ((difY == 2 && difX == 1) || (difY == 1 && difX == 2)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
