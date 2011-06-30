package chess.business.pieces;

import chess.business.pieces.rules.PieceRule;
import chess.business.pieces.rules.KingRule;

public class King extends Piece {

	public King(char color) {
		super(color, 'K');
	}

	public PieceRule getMoveRule() {
		return new KingRule(this);

	}

}
