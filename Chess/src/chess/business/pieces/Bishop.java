package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.pieces.rules.PieceRule;
import java.util.List;

public class Bishop extends Piece {

    private PieceRule pieceRule = new BishopRule();

    public Bishop(char color) {
        super(color, 'B');
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return this.pieceRule.makeMove(move, board, king, oppiece);
    }

    public class BishopRule extends PieceRule {

        public boolean makeMove(Move move, Board board, King king, List oppiece) {
            if (isValidMove(move) && pathIsClear(move, board)) {
                board.move(new PieceMove(this.getPiece(), board.getPieceAt(move.getDestination()), move));
                if (endsInCheck(board, king, oppiece)) {
                    board.undoLastMove();
                    return false;
                } else {
                    return true;
                }

            } else {
                return false;
            }
        }

        private boolean pathIsClear(Move move, Board board) {
            int x = move.getDestination().getX() - move.getSource().getX();
            int y = move.getDestination().getY() - move.getSource().getY();
            x = x / Math.abs(x);
            y = y / Math.abs(y);
            Position posiciontemporal = new Position(move.getSource().getX(), move.getSource().getY());
            Piece piezatemporal;
            while (!posiciontemporal.equals(move.getDestination())) {
                posiciontemporal.setX(posiciontemporal.getX() + x);
                posiciontemporal.setY(posiciontemporal.getY() + y);
                piezatemporal = board.getPieceAt(posiciontemporal);
                if (piezatemporal != null) {
                    if (!this.getPiece().sameColour(piezatemporal)) {
                        if (posiciontemporal.equals(move.getDestination())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                } else {
                    if (posiciontemporal.equals(move.getDestination())) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }
            return false;
        }

        private boolean isValidMove(Move move) {
            int x = move.getDestination().getX() - move.getSource().getX();
            int y = move.getDestination().getY() - move.getSource().getY();
            if (x == y) {
                return true;
            } else {
                return false;
            }

        }
    }
}
