package chess.business.pieces;
import chess.business.rules.QueenRule;
import chess.business.rules.PieceRule;

public class Queen extends Piece {

    public Queen(char color) {
        super(color, 'Q');
        
    }
   
    public PieceRule getMoveRule() {
        return new QueenRule(this);
        
    }
    
}
