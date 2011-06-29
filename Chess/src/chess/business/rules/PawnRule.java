
package chess.business.rules;

import chess.business.Board;
import chess.business.Move;
import chess.business.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class PawnRule extends PieceRule {

    public PawnRule(Piece piece) {
        super(piece);
    }

    
    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        int x=0;
        int y=0;
        x=move.getDestination().getX()-move.getSource().getX();
        y=move.getDestination().getX()-move.getSource().getY();
        
        Piece piezatemporal;
        if (this.getPiece().getColor()=='w')
        {
            if (x==-2)
            {
                if (y==0 && this.getPiece().getMovesCount()==0)
                {
                    piezatemporal=board.getPieceAt(new Position(move.getSource().getX()-1,move.getSource().getY()));
                    if (piezatemporal==null)
                      {
                        piezatemporal=board.getPieceAt(move.getDestination());
                        if (piezatemporal==null)return true;
                      }
                }
                
            }
            else
            {
                if (x==-1 && y>=-1 && y<=1)
                {
                    if (y==0)
                    {
                        piezatemporal=board.getPieceAt(move.getDestination());
                        if (piezatemporal==null)return true;   
                    }   
                    else
                    {
                        piezatemporal=board.getPieceAt(move.getDestination());
                        if (piezatemporal==null)
                        {
                            piezatemporal=board.getPieceAt(new Position (move.getSource().getX(),move.getDestination().getY()));
                            if (piezatemporal!=null)
                            {
                                if (!piezatemporal.sameColour(this.getPiece()) && piezatemporal.sameType(this.getPiece()))return true;
                                /*Falta verificar que:
                                * a) El movimiento anterior es el que dejo a esa pieza ahi.
                                * b) Fue un doble avance del peon.
                                */
                            }
                         }
                            else
                            {
                                if (!piezatemporal.sameColour(this.getPiece()))return true;
                                
                            }
                        
                    }
                }
            }
        }
        
        return false;
    }
    
}
