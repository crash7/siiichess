package chess.business;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import chess.business.pieces.Piece;

public class Board {
	public static final int DIMENSION = 8;
	private Piece[][] pieces;
	private List loggedMoves;

	public Board(List whitePieces, List blackPieces) {
		pieces = new Piece[Board.DIMENSION][Board.DIMENSION];
		loggedMoves = new ArrayList();
		createBoard(whitePieces, blackPieces);
		
	}
	
	public void setPieceAt(Piece who, Position where) {
		if(checkPosition(where)) {
			if(who != null) {
				who.setPosition(where);
				
			}
			pieces[where.getX()][where.getY()] = who;

		}
		
	}

	public Piece getPieceAt(Position where) {
		if(checkPosition(where)) {
			return pieces[where.getX()][where.getY()];
			
		}
		return null;

	}

	public void logMove(Piece origin, Position originpos, Piece previous, Position previouspos) {
		loggedMoves.add(new LoggedMove(origin, originpos, previous, previouspos));
		
	}
	
	public boolean undoLastMove() {
		LoggedMove lastmove = (LoggedMove) loggedMoves.remove(loggedMoves.size() - 1);
		if(lastmove != null) {
			lastmove.origin.decMoves();
			setPieceAt(lastmove.origin, lastmove.originPos);
			setPieceAt(lastmove.previous, lastmove.previousPos);
			if(lastmove.previous != null) {
				lastmove.previous.setActive(true);
				lastmove.previous.decMoves();
				
			}
			return true;
			
		}
		return false;

	}
	
	public Piece getLastMovePiece() {
		if(loggedMoves.size() > 0) {
			return ((LoggedMove) loggedMoves.get(loggedMoves.size() - 1)).origin;
			
		}
		return null;
	    
	}

    public Position[] getLastMovePositions(){
        if(loggedMoves.size() > 0) {
            Position[] positions = new Position[2];
            LoggedMove move = (LoggedMove) loggedMoves.get(loggedMoves.size() - 1);
            positions[0] = move.originPos;
            positions[1] = move.previousPos;
            return positions;
            
        }
        return null;
        
    }

	public boolean checkPosition(Position p) {
		return (p.getX() >= 0 && p.getX() < Board.DIMENSION && p.getY() >= 0 && p.getY() < Board.DIMENSION);

	}
	
	private void createBoard(List whitePieces, List blackPieces) {
		Iterator whiteiterator = whitePieces.iterator();
        Iterator blackiterator = blackPieces.iterator();
        int i=0;

        while(whiteiterator.hasNext() && blackiterator.hasNext()) {
        	setPieceAt((Piece) whiteiterator.next(), new Position(Board.DIMENSION-1, i));
        	setPieceAt((Piece) whiteiterator.next(), new Position(Board.DIMENSION-2, i));
        	setPieceAt((Piece) blackiterator.next(), new Position(0, i));
        	setPieceAt((Piece) blackiterator.next(), new Position(1, i));
            i++;
            
        }
	
	}
	
	private static class LoggedMove {
		Piece origin;
		Position originPos;
		Piece previous;
		Position previousPos;
		
		LoggedMove(Piece origin, Position originpos, Piece previous, Position previouspos) {
			this.origin = origin;
			this.originPos = originpos;
			this.previous = previous;
			this.previousPos = previouspos;
			
		}
		
	}
	
}
