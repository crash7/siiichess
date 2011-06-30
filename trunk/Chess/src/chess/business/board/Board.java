package chess.business.board;

import chess.business.util.Position;
import chess.business.pieces.Piece;
import java.util.List;
import java.util.ArrayList;

public class Board {
	private static final int DIMENSION = 8;
    private Square[][] squares;
    private List moves;

    public Board() {
    	this.squares = new Square[Board.DIMENSION][Board.DIMENSION];
			
		for(int i = 0; i < Board.DIMENSION; i++) {
			for(int j = 0; j < Board.DIMENSION; j++) {
				this.squares[i][j] = new Square();
				
			}
			
		}
		
		this.moves = new ArrayList();

    }
	
    public boolean validatePosition(Position p) {
    	return (p.getX() >= 0 && p.getX() < Board.DIMENSION && p.getY() >= 0 && p.getY() < Board.DIMENSION);
    	
    }
    
    public boolean setPieceAt(Position position, Piece piece) {
    	if(this.validatePosition(position)) {
    		this.squares[position.getX()][position.getY()].setPiece(piece);
    		return true;
    		
    	}
    	
    	return false;
    }
    
    public Piece getPieceAt(Position position) {
    	return this.squares[position.getX()][position.getY()].getPiece();
    	
    }
    
    public boolean move(PieceMove move) {
    	/* Locked by Christian!
    	
    	
    	PieceMove pm;
    	Piece temp;
    	if(this.validatePosition(move.getSource()) && this.validatePosition(move.getDestination())) {
    		temp = this.squares[move.getSource().getX()][move.getSource().getY()].getPiece();
    		if(temp != null) {
    			pm = new PieceMove(temp, move);
    			temp = this.squares[move.getDestination().getX()][move.getDestination().getY()].getPiece();
    			if(temp != null) {
    				
    			}
    		}
    		
    	}
    	
    	return false;
    	
    	pm = new PieceMove();
    	
    	pm.setPiece();
    	if(pm.getPiece() != null) {
    		pm.setMove(move);
    		pm.setCaptured
    		
    	}
    	
    }
    	*/
    	return true;
    }
		
    public boolean undoLastMove() {
    	/* Locked by Christian */
    	
    	return true;
			
    }
		
		
}


