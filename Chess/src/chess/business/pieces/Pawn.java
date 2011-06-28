package chess.business.pieces;

import chess.business.rules.PawnRule;
import chess.business.rules.PieceRule;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color, 'P');
    }

    public PieceRule getMoveRules() {
        return new PawnRule(this);
    }
}
