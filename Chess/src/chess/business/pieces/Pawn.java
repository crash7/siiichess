package chess.business.pieces;

import chess.business.rules.PawnRule;
import chess.business.rules.PieceRule;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color, 'P');
        
    }

    public PieceRule getMoveRule() {
        return new PawnRule(this);
        
    }
    
}
