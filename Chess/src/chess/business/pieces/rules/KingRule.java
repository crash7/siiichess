package chess.business.pieces.rules;

import chess.business.board.Board;
import chess.business.board.PieceMove;
import chess.business.Move;
import chess.business.Position;
import chess.business.pieces.King;
import chess.business.pieces.Piece;
import chess.business.pieces.Rook;

import java.util.List;

public class KingRule extends PieceRule {

	public KingRule(Piece piece) {
		super(piece);

	}

	public boolean makeMove(Move move, Board board, King king, List oppiece) {
        int x=move.getDestination().getX() - move.getSource().getX();
        int y=move.getDestination().getY() - move.getSource().getY();
        
        x = Math.abs(x);
        y = Math.abs(y);
        
        if (x<=1 && y<=1) { //No es un enroque
        	Piece piezatemporal = board.getPieceAt(move.getDestination());
            if (piezatemporal!=null)
            {
                if (!piezatemporal.sameColour(this.getPiece()))
                {
                    board.move(new PieceMove(this.getPiece(),board.getPieceAt(move.getDestination()), move));
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
                 board.move(new PieceMove(this.getPiece(),board.getPieceAt(move.getDestination()), move));
                    if (endsInCheck(board, king, oppiece))
                    {
                        board.undoLastMove();
                        return false;
                    }
                    else return true;
            }
        
        } else { // Castling?
        	// Castling anulado!
        	/*
        	if(!this.isChecked(board, oppiece) && !this.getPiece().hasMove() && x == 2) {
        		Piece temp;
        		if(move.getDestination().getX() - move.getSource().getX() > 0) {
        			temp = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY() + 1));
        			
        		} else {
        			temp = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getX() - 2));
        			
        		}
        		if(temp != null && temp instanceof Rook && !temp.hasMove()) {
        			x = move.getSource().getX();
        			y = move.getSource().getY();
        			int dy = move.getDestination().getY();
        			
        			for(int i; i < ) {
        				
        			}
        			
        			return true;
        			
        		}
        		
        	}        	
        	*/
        }
        
        return false;
        
    }

	public boolean isChecked(Board board, List oppiece) {
		return true;

	}

	public boolean isCheckMated(Board board, List oppiece) {
		return true;

	}

}
