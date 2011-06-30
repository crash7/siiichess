package chess.business.rules;

import chess.business.board.Board;
import chess.business.util.Move;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public abstract class PieceRule {
    private Piece piece;

    public PieceRule(Piece piece) {
        this.piece = piece;
    }
    public boolean endsInCheck(Board board, King king, List oppiece) {
        return true;
    }
    public abstract boolean makeMove(Move move, Board board, King king, List oppiece);

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }


}
