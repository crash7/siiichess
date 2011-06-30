
package chess.business.rules;

import chess.business.board.Board;
import chess.business.util.Move;
import chess.business.util.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class KingRule extends PieceRule {

    public KingRule(Piece piece) {
        super(piece);
    }

    
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        int x=move.getDestination().getX()-move.getSource().getX();
        int y=move.getDestination().getY()-move.getSource().getY();
        x=Math.abs(x);
        y=Math.abs(y);
        if (x<=1 && y<=1) //No es un enroque
        {
            Piece piezatemporal = board.getPieceAt(move.getDestination());
            if (piezatemporal!=null)
            {
                if (!piezatemporal.sameColour(this.getPiece()))
                {
                    board.move(move);
                    if (endsInCheck(board, king, oppiece))
                    {
                        board.undoLastMove();
                        return false;
                    }
                    else return true;
                }
                else return false;
            }
            else
            {
                 board.move(move);
                    if (endsInCheck(board, king, oppiece))
                    {
                        board.undoLastMove();
                        return false;
                    }
                    else return true;
            }
        }
        else return false; //enroque
    }
    public boolean isChecked(Board board, List oppiece) {
        return true;
    }    
}
