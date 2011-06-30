package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.Move;
import chess.business.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class KnightRule extends PieceRule {

	public KnightRule(Piece piece) {
		super(piece);
	}

	public boolean makeMove(Move move, Board board, King king, List oppiece) {
		if (isValidMove(move)
				&& (board.getPieceAt(move.getDestination()) == null)
				|| !(board.getPieceAt(move.getDestination()).sameColour(board
						.getPieceAt(move.getSource())))) {
			board.move(new PieceMove(this.getPiece(), board.getPieceAt(move
					.getDestination()), move));
		}
		return king.getMoveRule().endsInCheck(board, king, oppiece);
	}

	private boolean isValidMove(Move move) {
		Position source = move.getSource();
		Position destination = move.getDestination();
		int difY = Math.abs(source.getY() - destination.getY());
		int difX = Math.abs(source.getX() - destination.getX());
		if ((difY == 2 && difX == 1) || (difY == 1 && difX == 2)) {
			return true;
		} else
			return false;
	}
}
