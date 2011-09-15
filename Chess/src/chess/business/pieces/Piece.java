package chess.business.pieces;

import chess.business.Board;
import chess.business.Position;
import chess.business.pieces.Piece;

import java.util.Iterator;
import java.util.List;

public abstract class Piece {
	private char keyName;
    private char color;
    private int movesCount;
    private boolean active;
    private Position temp;
    
    public Piece(char color, char kn) {
        this.color = color;
        keyName = kn;
        active = true;
    }

    public boolean sameType(Piece p) {
        return this.keyName == p.getKeyName();

    }

    public boolean sameColour(Piece p) {
        return color == p.getColor();

    }

    public void setActive(boolean active) {
        this.active = active;

    }

    public boolean isActive() {
        return this.active;

    }

    public void setColor(char color) {
        this.color = color;

    }

    public char getColor() {
        return this.color;

    }

    public void setPosition(Position pos) {
        temp = pos;

    }

    public Position getPosition() {
        return temp;

    }

    public void setKeyName(char keyname) {
        this.keyName = keyname;

    }

    public char getKeyName() {
        return this.keyName;

    }

    public int getMovesCount() {
        return this.movesCount;

    }

    public boolean hasMoved() {
        return this.movesCount > 0;

    }
    
    public void incMoves() {
    	movesCount++;
    	
    }

    public void decMoves() {
    	movesCount--;
    	
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;

    }

    public boolean isWhite() {
        return this.color == 'w' || this.color == 'W';

    }

    public boolean isBlack() {
        return this.color == 'b' || this.color == 'B';

    }
    
    public boolean makeMove(Board board, Position end, List oppiece) {
    	return getRules().makeMove(this, board, end, oppiece);
    	
    }
    
    public boolean isThreatened(Board board, List oppiece) {
    	return getRules().isThreatened(this, board, oppiece);
    	
    }
    
    public List getThreatenedPositionsTo(Position end, Board board) {
    	return getRules().getThreatenedPositionsTo(this, end, board);
    	
    }
    
    protected abstract PieceRule getRules();
    
    // Clase interna
    static abstract class PieceRule {
    	
    	// This piece, with this board, to this position, from this enemys
    	public abstract boolean makeMove(Piece context, Board board, Position end, List oppiece);
    	
    	// This piece, to this place
    	public abstract List getThreatenedPositionsTo(Piece context, Position end, Board board);
    	
    	// This piece, in this board, from this enemys
    	public boolean isThreatened(Piece context, Board board, List oppiece) {
			Piece current;
			Iterator iterator = oppiece.iterator();
			while(iterator.hasNext()) {
				current = (Piece) iterator.next();
				if(current.isActive()) {
					if(current.makeMove(board, context.getPosition(), null)) {
						board.undoLastMove();
						System.out.println("[WARNING] " + context.getKeyName() + "" + context.getColor() + " esta amenazado por " + current.keyName + "" + current.getColor());
						return true;
						
					}
					
				}
				
			}
			return false;
			
    	}
    	
    }
    
}
