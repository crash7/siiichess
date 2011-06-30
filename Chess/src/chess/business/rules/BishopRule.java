
package chess.business.rules;

import chess.business.board.Board;
import chess.business.util.Move;
import chess.business.util.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class BishopRule extends PieceRule {

    public BishopRule(Piece piece) {
        super(piece);
    }

  
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        if (isValidMove(move) && pathIsClear(move, board))
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
    
    private boolean pathIsClear(Move move, Board board) {
        int x=move.getDestination().getX()-move.getSource().getX();
        int y=move.getDestination().getY()-move.getSource().getY();
        x=x/Math.abs(x);
        y=y/Math.abs(y);
        Position posiciontemporal = new Position(move.getSource().getX(),move.getSource().getY());
        Piece piezatemporal;
        while (!posiciontemporal.equals(move.getDestination()))
        {
            posiciontemporal.setX(posiciontemporal.getX()+x);
            posiciontemporal.setY(posiciontemporal.getY()+y);
            piezatemporal=board.getPieceAt(posiciontemporal);
            if (piezatemporal!=null)
            {
                if (!this.getPiece().sameColour(piezatemporal))
                {
                    if (posiciontemporal.equals(move.getDestination()))return true;
                    else return false;
                }
                else return false;
                
            }
            else
            {
                if (posiciontemporal.equals(move.getDestination()))return true;
                    else return false;
            }
            
        }
       return false;
    }
    
    private boolean isValidMove(Move move) {
        int x=move.getDestination().getX()-move.getSource().getX();
        int y=move.getDestination().getY()-move.getSource().getY();
        if (x==y)return true;
        else return false;
        
    }
    
}
