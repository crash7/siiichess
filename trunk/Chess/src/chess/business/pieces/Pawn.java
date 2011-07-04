package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import java.util.List;

public class Pawn extends Piece {
    private static PawnRule pieceRule = new PawnRule();

    public Pawn(char color) {
        super(color, 'P');

    }

    public boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
        return Pawn.pieceRule.makeMove(move, board, king, oppiece, safely);
        
    }

    static class PawnRule {
		private boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely) {
			Piece piezaorigen = board.getPieceAt(move.getSource());
			
			if (this.isValidMove(board, move) && this.pathIsClear(move, board)) {
				board.move(new PieceMove(piezaorigen, board.getPieceAt(move.getDestination()), move));
				
				if(safely && king.isChecked(board, oppiece)) {
					board.undoLastMove();
					return false;
					
				} else {
					piezaorigen.incMoves();
					return true;
					
				}

			} else {
				return false;
				
			}
			
		}

		private boolean isValidMove(Board board, Move move) {
			int x = move.getDestination().getX() - move.getSource().getX();
			int y = move.getDestination().getY() - move.getSource().getY();
			y = Math.abs(y);
			
			Piece piezaorigen = board.getPieceAt(move.getSource());
			
			if (Math.abs(x) == 1) {
				
				
				if(piezaorigen.isWhite()) {
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
				
			} else {
				if (Math.abs(x) == 2) {
					if (piezaorigen.getMovesCount() == 0) {
						if(piezaorigen.isWhite()) {
							if (x == -2) {
								return true;
							} else {
								return false;
							}
							
						} else {
							if (x == 2){
								return true;
								
							} else {
								return false;
							}
						}
						
					} else {
						return false;
						
					}
					
				} else {
					return false;
					
				}
			}

		}

		private boolean pathIsClear(Move move, Board board) {
			Piece piezatemporal = null;
			Piece piezaorigen = null;
			if (move.getSource().getY() != move.getDestination().getY()) {
				piezatemporal = board.getPieceAt(move.getDestination());
				piezaorigen = board.getPieceAt(move.getSource());
				if (piezatemporal != null) {
					return !piezatemporal.sameColour(piezaorigen);
					
				} else {
					piezatemporal = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY()));
					PieceMove lastmove = board.getLastMove();
					PieceMove fakemove;
					
					if(piezatemporal != null && piezatemporal == lastmove.getPiece() && piezatemporal instanceof Pawn && piezatemporal.getMovesCount() == 1) {
						// dirty thingy starts
						fakemove = new PieceMove();
						fakemove.setPiece(piezaorigen);
						fakemove.setCaptured(piezatemporal);
						fakemove.setMove(new Move(piezaorigen.getPosition(), piezatemporal.getPosition()));
						
						board.setPieceAt(piezatemporal.getPosition(), null); // pawn pawnd
						board.setPieceAt(piezaorigen.getPosition(), null); // pwan win
						board.setPieceAt(move.getDestination(), piezaorigen); // pawn win
						piezaorigen.incMoves();
						piezatemporal.setActive(false);
						
						return true;

					} else {
						return false; // en passant
					
					}
					
				}

			} else {
				int x = move.getDestination().getX() - move.getSource().getX();
				if (Math.abs(x) == 2) {
					x = x / Math.abs(x);
					piezatemporal = board.getPieceAt(new Position(move
							.getSource().getX() + x, move.getSource().getY()));
					if (piezatemporal == null) {
						piezatemporal = board.getPieceAt(move.getDestination());
						if (piezatemporal == null)
							return true;
						else
							return false;
					} else
						return false;
				} else {
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
