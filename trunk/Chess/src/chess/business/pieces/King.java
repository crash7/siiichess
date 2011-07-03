package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.board.PieceMove;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class King extends Piece {
	private static KingRule pieceRule = new KingRule();

	public King(char color) {
		super(color, 'K');

	}

	public boolean makeMove(Move move, Board board, King king, List oppiece) {
		return King.pieceRule.makeMove(move, board, king, oppiece);

	}

	public boolean isChecked(Board board, List oppiece) {
		return King.pieceRule.isChecked(board, this, oppiece);

	}

	public boolean isCheckMated(Board board, List oppiece) {
		return King.pieceRule.isCheckMated(board, this, oppiece);

	}

	/*
	 * Porque una static inner class? Porque no necesitamos la referencia a la
	 * clase que la contiene y porque...tenes que leer el capitulo 8 del TIJ
	 */
	static class KingRule {
		private boolean makeMove(Move move, Board board, King king, List oppiece) {
			int x = move.getDestination().getX() - move.getSource().getX();
			int y = move.getDestination().getY() - move.getSource().getY();

			x = Math.abs(x);
			y = Math.abs(y);
			
			Piece piezaorigen = board.getPieceAt(move.getSource());
			Piece piezatemporal;

			if (x <= 1 && y <= 1) { // No es un enroque
				
				piezatemporal = board.getPieceAt(move.getDestination());
								
				if (piezatemporal != null) {
					if (!piezatemporal.sameColour(piezaorigen)) {
						board.move(new PieceMove(piezaorigen, piezatemporal, move));
						if(king.isChecked(board, oppiece)) {
							board.undoLastMove();
							return false;
							
						} else {
							piezaorigen.incMoves();
							return true;
							
						}
						
					} else {
						return false;
						
					}
					
				} else {
					board.move(new PieceMove(piezaorigen, piezatemporal, move));
					if (king.isChecked(board, oppiece)) {
						board.undoLastMove();
						return false;
						
					} else {
						piezaorigen.incMoves();
						return true;
						
					}
					
				}

			} else { // Castling?
				/*
				 * If this don't work for some reason, just comment it and return false.
				 * 
				 */
				
				if(king.isChecked(board, oppiece) && !piezaorigen.hasMoved() && x == 2) {
					int xA, xB;
					
					if(move.getDestination().getX() - move.getSource().getX() > 0) { // Torre tablero derecha
						piezatemporal = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY() + 1));
						xA = move.getSource().getX() + 1;
						xB = xA + 1;
						
						
					} else { // Torre tablero izquierda
						piezatemporal = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY() - 2));
						xA = move.getSource().getX() - 1;
						xB = xA - 1;
						
					}
					if(piezatemporal != null) {
						if(piezatemporal.hasMoved()) {
							if(board.getPieceAt(new Position(xA, move.getSource().getY())) == null 
									&& board.getPieceAt(new Position(xB, move.getSource().getY())) == null) {
								
								king.setPosition(new Position(xA, move.getSource().getY()));
								if(king.isChecked(board, oppiece) == false) {
									king.setPosition(new Position(xB, move.getSource().getY()));
									if(king.isChecked(board, oppiece) == false) {
										
										// dirty move
										// VERY dirty
										// my apologies
										move.setDestination(piezatemporal.getPosition()); // fake move para el rollback
										board.addMove(new PieceMove(piezaorigen, piezatemporal, move)); // meto un move a la fuerza
										board.setPieceAt(new Position(xA, move.getSource().getY()), piezaorigen); // rey
										board.setPieceAt(new Position(xB, move.getSource().getY()), piezatemporal); // torre
										piezaorigen.incMoves();
										piezatemporal.incMoves();
										
										return true;
										
									} else {
										return false;
										
									}
									
								} else {
									return false;
									
								}
									
							} else {
								return false;
								
							}
							
						} else {
							return false;
							
						}
						
					} else {
						return false;
						
					}
						
				} else {
					return false;
					
				}
				
			}
			
		}

		private boolean isChecked(Board board, King king, List oppiece) {
			Piece current;
			Move move = new Move();
			boolean checked = false;

			Iterator i = oppiece.iterator();

			move.setDestination(king.getPosition());
			while (i.hasNext()) {
				current = (Piece) i.next();
				if(current.isActive()) {
					move.setSource(current.getPosition());
					if (current.makeMove(move, board, king, oppiece)) {
						board.undoLastMove();
						checked = true;
						break;
		
					}
					
				}

			}

			return checked;

		}

		private boolean isCheckMated(Board board, King king, List oppiece) {
			boolean mated = true;
			King temp = new King(king.getColor());
			List positions = this.getPosiblePositions(king);
			Iterator i = positions.iterator();
			while (i.hasNext()) {
				temp.setPosition((Position) i.next());
				if (temp.isChecked(board, oppiece) == false) {
					mated = false;
					break;

				}

			}

			return mated;
		}

		private List getPosiblePositions(King king) {
			List positions = new ArrayList();
			int x = king.getPosition().getX();
			int y = king.getPosition().getY();

			for (int i = x - 1; i <= x; i++) {
				for (int j = y - 1; j <= y; j++) {
					positions.add(new Position(i, j));

				}

			}

			return positions;

		}

	}

}
