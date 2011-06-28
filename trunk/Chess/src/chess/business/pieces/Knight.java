package chess.business.pieces;

import chess.business.rules.KnightRule;
import chess.business.rules.PieceRule;

public class Knight extends Piece {

    public Knight(char color) {
        super(color, 'N');
    }

    public PieceRule getMoveRules() {
        return new KnightRule(this);
    }
}
