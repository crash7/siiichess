package chess.business.pieces;

import chess.business.rules.PieceRule;
import chess.business.rules.KingRule;

public class King extends Piece {

	public King(char color) {
		super(color, 'K');
	}

	public PieceRule getMoveRules() {
		return new KingRule(this);

	}

}
