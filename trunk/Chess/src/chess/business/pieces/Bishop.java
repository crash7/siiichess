package chess.business.pieces;

import chess.business.pieces.rules.BishopRule;
import chess.business.pieces.rules.PieceRule;

public class Bishop extends Piece {

	public Bishop(char color) {
		super(color, 'B');
	}

	public PieceRule getMoveRule() {
		return new BishopRule(this);
	}

}
