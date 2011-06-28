
package chess.business.rules;

import chess.business.Board;
import chess.business.Move;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class KingRule extends PieceRule {

    public KingRule(Piece piece) {
        super(piece);
    }

    @Override
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return true;
    }
    public boolean isChecked(Board board, List oppiece) {
        return true;
    }    
}
