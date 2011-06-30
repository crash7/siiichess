package chess.business.pieces;

import chess.business.pieces.rules.KnightRule;
import chess.business.pieces.rules.PieceRule;

public class Knight extends Piece {

	public Knight(char color) {
		super(color, 'N');

	}

	public PieceRule getMoveRule() {
		return new KnightRule(this);

	}

}
