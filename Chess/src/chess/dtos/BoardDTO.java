package chess.dtos;

public class BoardDTO {

	public BoardDTO() {
            this.square = new String[8][8];
	}
        String square[][];

    public String[][] getSquare() {
        return square;
    }

    public void setSquare(String[][] square) {
        this.square = square;
    }
}
