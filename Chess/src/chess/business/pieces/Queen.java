package chess.business.pieces;
import chess.business.rules.*;

public class Queen extends Piece {

    public Queen(char color) {
        super(color, 'Q');
    }

    @Override
    public PieceRule getMoveRules() {
        return new QueenRule(this);
    }
}
