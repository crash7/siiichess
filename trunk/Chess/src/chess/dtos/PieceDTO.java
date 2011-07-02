package chess.dtos;

public class PieceDTO {

    public PieceDTO() {
    }
    private char color;
    private char keyname;
    private int posX;
    private int posY;

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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
