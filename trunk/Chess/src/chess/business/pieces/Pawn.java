package chess.business.pieces;

import chess.business.pieces.rules.PawnRule;
import chess.business.pieces.rules.PieceRule;

public class Pawn extends Piece {

    public Pawn(char color) {
        super(color, 'P');
        
    }

    public PieceRule getMoveRule() {
        return new PawnRule(this);
        
    }
    
}
