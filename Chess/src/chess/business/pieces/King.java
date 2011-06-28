package chess.business.pieces;

import chess.business.rules.KingRule;
import chess.business.rules.PieceRule;

public class King extends Piece {

    public King(char color) {
        super(color, 'K');
    }

    public PieceRule getMoveRules() {
        return new KingRule(this);
    }
}
