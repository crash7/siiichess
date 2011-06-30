
package chess.business.rules;

import chess.business.Board;
import chess.business.Move;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class QueenRule extends PieceRule {

    public QueenRule(Piece piece) {
        super(piece);
    }

    
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        PieceRule rule = new BishopRule(this.getPiece());
        boolean flag =rule.makeMove(move, board, king, oppiece);
        if (!flag)
        {
            rule = new RookRule(this.getPiece());
            flag = rule.makeMove(move, board, king, oppiece);
        }
        return flag;
    }
    
}
