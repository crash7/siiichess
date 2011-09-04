package chess.dtos;

public class InactivePieceDTO {

    public InactivePieceDTO() {
    }
    private char color;
    private char keyname;

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
}