package chess.business.pieces;

import chess.business.Position;
import chess.business.rules.PieceRule;

public abstract class Piece {

    private char color;
    private Position pos;
    private int movesCount;
    private char keyname;
    private boolean active;

    public Piece(char color, char kn) {
    }

    public boolean sameType(Piece p) {
        return true;
    }
    public boolean sameColour(Piece p){
        return true;
    }
    public abstract PieceRule getMoveRules();
    public boolean hasMove(){
        return true;
    }
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public char getKeyname() {
        return keyname;
    }

    public void setKeyname(char keyname) {
        this.keyname = keyname;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}