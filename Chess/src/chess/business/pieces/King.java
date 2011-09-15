package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class King extends Piece {
	private static KingRule pieceRule = new KingRule();
        
	public King(char color) {
		super(color, 'K');

	}
	
	protected PieceRule getRules() {
		return King.pieceRule;
		
	}
	
	// this board, to this enemys, with my fellows
	public boolean isCheckMated(Board board, List oppiece, List cppiece) {
		return King.pieceRule.isCheckMated(this, board, oppiece, cppiece);

	}

	static class KingRule extends PieceRule {
		
		public boolean makeMove(Piece context, Board board, Position end, List oppiece) {
			int x = end.getX() - context.getPosition().getX();
			int y = end.getY() - context.getPosition().getY();
			
			Piece captured;
			if(Math.abs(x) <= 1 && Math.abs(y) <= 1) { // No es un enroque
				captured = board.getPieceAt(end);
				if(captured != null) {
					if(captured.sameColour(context)) {
						return false;
						
					} else {
						captured.setActive(false);
						
					}
					
				}
				board.logMove(context, context.getPosition(), captured, end);
				board.setPieceAt(null, context.getPosition());
				board.setPieceAt(context, end);
				return true;

			} else { // Castling?
				/*if(!context.hasMoved() && y == 2 && !context.isThreatened(board, oppiece)) {
					int yA, yB;
					
					if(y > 0) { // Torre tablero derecha
						captured = board.getPieceAt(new Position(context.getPosition().getX(), end.getY() + 1));
						yA = context.getPosition().getY() + 1;
						yB = yA + 1;
						
					} else { // Torre tablero izquierda
						captured = board.getPieceAt(new Position(context.getPosition().getX(), end.getY() - 2));
						yA = context.getPosition().getY() - 1;
						yB = yA - 1;
						
					}
					if(captured != null) {
						if(!captured.hasMoved()) {
							if(board.getPieceAt(new Position(context.getPosition().getX(), yA)) == null
									&& board.getPieceAt(new Position(context.getPosition().getX(), yB)) == null) {
								
								context.setPosition(new Position(context.getPosition().getX(), yB));
								if(context.isThreatened(board, oppiece) == false) {
									context.setPosition(new Position(context.getPosition().getX(), yB));
									if(!context.isThreatened(board, oppiece)) {
										// tenemos enroque
										board.logMove(context, context.getPosition(), captured, captured.getPosition());
										board.setPieceAt(null, context.getPosition());
										board.setPieceAt(null, captured.getPosition());
										board.setPieceAt(context, new Position(context.getPosition().getX(), yB));
										board.setPieceAt(captured, new Position(context.getPosition().getX(), yA));
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
				*/
				return false;
			}
			
		}
		
		public List getThreatenedPositionsTo(Piece context, Position end, Board board) {
			return null;
			
		}
		
		// me, in this lawn, to this enemies, with my fellows
		private boolean isCheckMated(King context, Board board, List oppiece, List cppieces) {
			
			if(context.isThreatened(board, oppiece)) {
				// recolectar guias de escape
				Position trypos;
	            int x = context.getPosition().getX();
	            int y = context.getPosition().getY();
	            for(int i = x-1; i <= x+1; i++) {
	                for(int j = y-1; j <= y+1; j++) {
	                	trypos = new Position(i, j);
	                	if(!trypos.isEqual(context.getPosition())) {
		                    if(board.checkPosition(trypos)) {
		                        if((board.getPieceAt(trypos) == null) || (board.getPieceAt(trypos) != null && !board.getPieceAt(trypos).sameColour(context))) {
		                        	if(context.makeMove(board, trypos, oppiece)) {
		                        		if(!context.isThreatened(board, oppiece)) {
		                        			board.undoLastMove();
		                        			return false;
		                        			
		                        		} else {
		                        			board.undoLastMove();
		                        			
		                        		}
		                        		
		                        	}
	
		                        }
	
		                    }
		                    
	                	}

	                }

	            }
				// buscar quien me amenaza
	            Iterator iterator = oppiece.iterator();
				Piece threater = null;
				Piece enemy = null;
				while(iterator.hasNext()) {
					enemy = (Piece) iterator.next();
					if(enemy.makeMove(board, context.getPosition(), cppieces)) {
						board.undoLastMove();
						if(threater == null) {
							threater = enemy;
							
						} else { 
							return true; // tengo dos mirandome, ya fue
							
						}

					}
					
				}
				if(threater == null) {
					System.out.println("Are you kidding me?");
					return true;
					
				} else {
					List positionsbetweenus = threater.getThreatenedPositionsTo(context.getPosition(), board);
					Iterator positerator;
					Position currentpos;
					Piece currentpiece;
					if(positionsbetweenus != null) {
						positerator = positionsbetweenus.iterator();
						while(positerator.hasNext()) {
							currentpos = (Position) positerator.next();
							iterator = cppieces.iterator();
							while(iterator.hasNext()) {
								currentpiece = (Piece) iterator.next();
								if(currentpiece.makeMove(board, currentpos, oppiece)) {
									board.undoLastMove();
									return false;
									
								}
								
							}
							
						}
						return true; // nadie me puede tapar
						
					} else {
						// no hay forma de tapar! =/
						return true;
						
					}
					
				}
				
			} else { // no esta ni en jaque!
				return false;

			}
			
		}
		
	}

}
