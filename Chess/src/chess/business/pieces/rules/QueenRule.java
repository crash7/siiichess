package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import chess.business.Move;
import java.util.List;

public class QueenRule extends PieceRule {

	public QueenRule(Piece piece) {
		super(piece);
	}

	public boolean makeMove(Move move, Board board, King king, List oppiece) {
		PieceRule rule = new BishopRule(this.getPiece());
		boolean flag = rule.makeMove(move, board, king, oppiece);
		if (!flag) {
			rule = new RookRule(this.getPiece());
			flag = rule.makeMove(move, board, king, oppiece);
		}
		return flag;
	}

}
