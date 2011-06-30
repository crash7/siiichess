package chess.business.pieces;

import chess.business.pieces.rules.PieceRule;
import chess.business.pieces.rules.RookRule;

public class Rook extends Piece {
    
	public Rook(char color){
        super(color, 'R');
    }
        
    public PieceRule  getMoveRule(){
        return new RookRule(this);
        
    }
    
}
