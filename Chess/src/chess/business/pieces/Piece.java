package chess.business.pieces;

import chess.business.Move;
import chess.business.Position;
import chess.business.board.Board;
import chess.business.pieces.Piece;
import java.util.List;

public abstract class Piece {

    private char color;
    private int movesCount;
    private char keyname;
    private boolean active;
    private Position position;

    public Piece(char color, char kn) {
        this.color = color;
        this.keyname = kn;
        this.active = true;
    }

    public boolean sameType(Piece p) {
        return this.keyname == p.getKeyname();

    }

    public boolean sameColour(Piece p) {
        return this.color == p.getColor();

    }

    public void setActive(boolean active) {
        this.active = active;
        if(!active) {
        	System.out.println(this);
        	
        }

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
        this.position = pos;

    }

    public Position getPosition() {
        return this.position;

    }

    public void setKeyname(char keyname) {
        this.keyname = keyname;

    }

    public char getKeyname() {
        return this.keyname;

    }

    public int getMovesCount() {
        return this.movesCount;

    }

    public boolean hasMoved() {
        return this.movesCount > 0;

    }
    
    public void incMoves() {
    	this.movesCount++;
    	
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
    
    public abstract boolean makeMove(Move move, Board board, King king, List oppiece, boolean safely);
}
