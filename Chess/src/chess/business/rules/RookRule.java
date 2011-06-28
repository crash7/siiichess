
package chess.business.rules;

import chess.business.Board;
import chess.business.Move;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class RookRule extends PieceRule {

    public RookRule(Piece piece) {
        super(piece);
    }

    @Override
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return true;
    }
    
}
