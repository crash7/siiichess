
package chess.dtos;

public class MoveDTO {
    private int originX;
    private int originY;
    private int previousX;
    private int previousY;

    public MoveDTO() {
    }

    public MoveDTO(int originX, int originY, int previousX, int previousY) {
        this.originX = originX;
        this.originY = originY;
        this.previousX = previousX;
        this.previousY = previousY;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getPreviousX() {
        return previousX;
    }

    public void setPreviousX(int previousX) {
        this.previousX = previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public void setPreviousY(int previousY) {
        this.previousY = previousY;
    }

    @Override
    public String toString() {
        return "Origen: ("+originX+","+originY+") Destino: ("+previousX+","+previousY+")";
    }

}
