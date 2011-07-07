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

	public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
		return King.pieceRule.makeMove(move, board, threatened, oppiece, safely);

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
	static class KingRule extends PieceRule {
		public boolean makeMove(Move move, Board board, Piece threatened, List oppiece, boolean safely) {
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
						if(safely) { // el movimiento tiene que ser seguro, veamos el isCheck..
							board.move(new PieceMove(piezaorigen, piezatemporal, move));
							piezatemporal.setActive(false); // captura
							if(threatened.isChecked(board, oppiece)) {
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
					
				} else {
					if(safely) { // el movimiento tiene que ser seguro, veamos el isCheck..
						board.move(new PieceMove(piezaorigen, piezatemporal, move));
						if (threatened.isChecked(board, oppiece)) {
							board.undoLastMove();
							return false;
							
						} else {
							piezaorigen.incMoves();
							return true;
							
						}
						
					} else { // el movimiento es valido, con eso me alcanza
						return true;
						
					}
					
				}

			} else { // Castling?
				/*
				 * If this don't work for some reason, just comment it and return false.
				 * 
				 */
				
				if(safely && !threatened.isChecked(board, oppiece) && !piezaorigen.hasMoved() && y == 2) {
					int yA, yB;
					
					if(move.getDestination().getY() - move.getSource().getY() > 0) { // Torre tablero derecha
						piezatemporal = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY() + 1));
						yA = move.getSource().getY() + 1;
						yB = yA + 1;
						
						
					} else { // Torre tablero izquierda
						piezatemporal = board.getPieceAt(new Position(move.getSource().getX(), move.getDestination().getY() - 2));
						yA = move.getSource().getY() - 1;
						yB = yA - 1;
						
					}
					if(piezatemporal != null) {
						if(!piezatemporal.hasMoved()) {
							if(board.getPieceAt(new Position(move.getSource().getX(), yA)) == null 
									&& board.getPieceAt(new Position(move.getSource().getX(), yB)) == null) {
								
								threatened.setPosition(new Position(move.getSource().getX(), yB));
								if(threatened.isChecked(board, oppiece) == false) {
									threatened.setPosition(new Position(move.getSource().getX(), yB));
									if(safely && !threatened.isChecked(board, oppiece)) {
										
										// dirty move
										// VERY dirty
										// my apologies
										move.setDestination(piezatemporal.getPosition()); // fake move para el rollback
										board.addMove(new PieceMove(piezaorigen, piezatemporal, move)); // meto un move a la fuerza
										board.setPieceAt(move.getSource(), null); // rey
										board.setPieceAt(piezatemporal.getPosition(), null); // torre
										board.setPieceAt(new Position(move.getSource().getX(), yB), piezaorigen); // rey
										board.setPieceAt(new Position(move.getSource().getX(), yA), piezatemporal); // torre
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

		private boolean isCheckMated(Board board, King king, List oppiece, List cppieces) {
			boolean mated = true;
			King temp = new King(king.getColor());
			List positions = this.getPosiblePositions(king, board);
                        board.setPieceAt(king.getPosition(), null);
                        List temppieces = new ArrayList();
                        Piece thepiece=null;
                        List positionslistcheck = new ArrayList();
			if (positions.isEmpty()){
                            temp.setPosition(king.getPosition());
                            if (temp.isChecked(board, oppiece) == false) {
					mated = false;
					

				}
                            else{
                                positionslistcheck.add(king.getPosition());
                                
                            }
                                
                          
                               
                        }
                        else{
                              Iterator i = positions.iterator();
			Piece piece = null;
			while (i.hasNext()) {
				temp.setPosition((Position) i.next());
                                piece=board.getPieceAt(temp.getPosition());
                                if (piece!=null)piece.setActive(false);
                                if (temp.isChecked(board, oppiece) == false) {
					mated = false;
					break;

				}
                                else{
                                    positionslistcheck.add(temp.getPosition());
                                }
                                if (piece!=null)piece.setActive(true);

			} 
                            
                        }
                        board.setPieceAt(king.getPosition(), king);
                        if (!positionslistcheck.isEmpty()){
                            Iterator j = positionslistcheck.iterator();
                            while (j.hasNext()){
                                Position temposition = (Position)j.next();
                                Iterator pieceiterator = oppiece.iterator();
                                while (pieceiterator.hasNext()){
                                    thepiece =(Piece) pieceiterator.next();
                                    if (thepiece.isActive())
                                    {
                                        if (thepiece.makeMove(new Move(thepiece.getPosition(),temp.getPosition()), board, king, oppiece,false))
                                        {
                                            temppieces.add(thepiece);
                                        }
                                    
                                    }
                            }
                                pieceiterator = temppieces.iterator();
                                while (pieceiterator.hasNext()){
                                    thepiece =(Piece) pieceiterator.next();
                                    if (thepiece.makeMove(null, board, thepiece, oppiece, mated))
                                }
                            }
                            
                        }
                            
			return mated;
		}

		private List getPosiblePositions(King king, Board board) {
			List positions = new ArrayList();
			int x = king.getPosition().getX();
			int y = king.getPosition().getY();

			for (int i = x - 1; i <= x+1; i++) {
				for (int j = y - 1; j <= y+1; j++) {
                                    if (board.validatePosition(new Position (i,j))){
                                        if (board.getPieceAt(new Position (i,j))!=null){
                                            if (!board.getPieceAt(new Position (i,j)).sameColour(king)){
                                             positions.add(new Position(i, j));
                                            }
                                             
                                        }
                                        else positions.add(new Position(i, j));
                                               
                                    }

				}

			}

			return positions;

		}

	}

}
