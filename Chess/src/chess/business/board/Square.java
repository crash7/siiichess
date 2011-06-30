package chess.business.board;

import chess.business.pieces.Piece;

public class Square {
	private Piece piece;

	public Square(Piece p) {
		this.piece = p;

	}

	public Square() {
		this(null);

	}

	public void setPiece(Piece piece) {
		this.piece = piece;

	}

	public Piece getPiece() {
		return this.piece;

	}

	public boolean isEmpty() {
		return this.piece == null;

	}

}
