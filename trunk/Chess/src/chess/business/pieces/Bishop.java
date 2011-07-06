package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import java.util.List;

public class Bishop extends Piece {
    private static BishopRule pieceRule = new BishopRule();

    public Bishop(char color) {
        super(color, 'B');
        
    }

    public boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
        return Bishop.pieceRule.makeMove(move, board, king, oppiece, safely);
        
    }

    static class BishopRule {
        private boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
        	Piece piezaorigen = board.getPieceAt(move.getSource());
            if (isValidMove(move) && pathIsClear(move, board)) {
            	if(safely) { // el movimiento tiene que ser seguro, veamos el isCheck..
            		board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
            		if (board.getPieceAt(move.getDestination())!=null)board.getPieceAt(move.getDestination()).setActive(false);
            		if(king.isChecked(board, oppiece)) {
            			board.undoLastMove();
            			return false;
            			
            		} else {
            			piezaorigen.incMoves();
            			return true;
            			
            		}
            		
            	} else { // el movimiento es valido, con eso me alcanza
					return true;
					
				}

            } else {
                return false;
                
            }
            
        }

        private boolean pathIsClear(Move move, Board board) {
            int x = move.getDestination().getX() - move.getSource().getX();
            int y = move.getDestination().getY() - move.getSource().getY();
            x = x / Math.abs(x);
            y = y / Math.abs(y);
            Position posiciontemporal = new Position(move.getSource().getX(), move.getSource().getY());
            Piece piezatemporal=null;
            Piece piezaorigen = board.getPieceAt(move.getSource());
            while (!posiciontemporal.equals(move.getDestination())) {
                posiciontemporal.setX(posiciontemporal.getX() + x);
                posiciontemporal.setY(posiciontemporal.getY() + y);
                piezatemporal = board.getPieceAt(posiciontemporal);
                if (piezatemporal != null) {
                    if (!piezaorigen.sameColour(piezatemporal)) {
                        if (posiciontemporal.equals(move.getDestination())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                } else {
                    if (posiciontemporal.equals(move.getDestination())) {
                        return true;
                    }
                }

            }
            return false;
        }

        private boolean isValidMove(Move move) {
            int x = move.getDestination().getX() - move.getSource().getX();
            int y = move.getDestination().getY() - move.getSource().getY();
            if (Math.abs(x) == Math.abs(y)){
                return true;
            } else {
                return false;
            }

        }
    }
}
