package chess.business.pieces;

public abstract class Piece {

    private char color;
    private Position pos;
    private int movesCount;
    private char keyname;
    private bool active;

    public Piece(char color, char kn) {
    }

    public boolean sameType(Piece p) {
        return true;
    }
    public boolean sameColour(Piece p){
        retunr true;
    }
    public PieceRule getMoveRules(){
        return new PieceRule();
    }
    public boolean hasMove(){
        return true;
    }
    public bool getActive() {
        return active;
    }

    public void setActive(bool active) {
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