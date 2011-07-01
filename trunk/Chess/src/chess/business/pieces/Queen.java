package chess.business.pieces;

import chess.business.pieces.rules.QueenRule;
import chess.business.pieces.rules.PieceRule;

public class Queen extends Piece {
    private PieceRule pieceRule = new QueenRule();
    public Queen(char color) {
        super(color, 'Q');

    }
}
