package chess.business.pieces;

import chess.business.rules.PieceRule;
import chess.business.rules.RookRule;

public class Rook extends Piece {
    
	public Rook(char color){
        super(color, 'R');
    }
        
    public PieceRule  getMoveRules(){
        return new RookRule(this);
        
    }
    
}
