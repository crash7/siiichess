package chess.business.pieces;

import chess.business.rules.BishopRule;
import chess.business.rules.PieceRule;

public class Bishop extends Piece {

    public Bishop(char color) {
        super(color, 'B');
    }

    public PieceRule getMoveRules() {
        return new BishopRule(this);
    }
}
