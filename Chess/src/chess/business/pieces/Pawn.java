package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.pieces.rules.PieceRule;
import java.util.List;

public class Pawn extends Piece {

    private PieceRule pieceRule = new PawnRule();

    public Pawn(char color) {
        super(color, 'P');

    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        return this.pieceRule.makeMove(move, board, king, oppiece);
    }

    public class PawnRule extends PieceRule {

    public PawnRule() {
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        if (isValidMove(move) && pathIsClear(move, board)) {
            board.move(new PieceMove(this.getPiece(), board.getPieceAt(move.getDestination()), move));
            if (endsInCheck(board, king, oppiece)) {
                board.undoLastMove();
                return false;
            } else {
                return true;
            }


        } else {
            return false;
        }
    }

    private boolean isValidMove(Move move) {
        int x = move.getDestination().getX() - move.getSource().getX();
        int y = move.getDestination().getY() - move.getSource().getY();
        y = Math.abs(y);
        if (Math.abs(x)==1)
        {
           if (this.getPiece().isWhite()) {
            if (x == -1 && (y == 1 || y == 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (x == 1 && (y == 1 || y == 0)) {
                return true;
            } else {
                return false;
            }
        } 
        }
        else
        {
            if (Math.abs(x)==2)
            {
               if (this.getPiece().getMovesCount()==0)
               {
                   if (this.getPiece().isWhite())
                   {
                       if (x==-2)return true;
                       else return false;
                   }
                   else
                   {
                       if (x==2)return true;
                       else return false;
                   }
               }else return false;
            }else return false;
        }
        
        
    }

    private boolean pathIsClear(Move move, Board board) {
        Piece piezatemporal = null;
        if (move.getSource().getY() != move.getDestination().getY()) {
            piezatemporal = board.getPieceAt(move.getDestination());
            if (piezatemporal != null) {
                if (piezatemporal.sameColour(this.getPiece())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;//comer pasando.
            }

        } else {
            int x=move.getDestination().getX()-move.getSource().getX();
            if (Math.abs(x)==2){
                x=x/Math.abs(x);
                piezatemporal = board.getPieceAt(new Position(move.getSource().getX()+x, move.getSource().getY()));
                if (piezatemporal==null)
                {
                    piezatemporal=board.getPieceAt(move.getDestination());
                    if (piezatemporal==null)return true;
                    else return false;
                }
                else return false;
            }
            else{
                piezatemporal = board.getPieceAt(move.getDestination());
            if (piezatemporal != null) {
                return false;
            } else {
                return true;
            }
            }
            

        }
    }
}
}
