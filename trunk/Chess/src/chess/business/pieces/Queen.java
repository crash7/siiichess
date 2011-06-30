package chess.business.pieces;
import chess.business.pieces.rules.QueenRule;
import chess.business.pieces.rules.PieceRule;

public class Queen extends Piece {

    public Queen(char color) {
        super(color, 'Q');
        
    }
   
    public PieceRule getMoveRule() {
        return new QueenRule(this);
        
    }
    
}
