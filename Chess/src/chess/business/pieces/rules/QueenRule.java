package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import chess.business.Move;
import java.util.List;

public class QueenRule extends PieceRule {

    public QueenRule() {
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        PieceRule rule = new BishopRule();
        boolean flag = rule.makeMove(move, board, king, oppiece);
        if (!flag) {
            rule = new RookRule();
            flag = rule.makeMove(move, board, king, oppiece);
        }
        return flag;
    }
}
