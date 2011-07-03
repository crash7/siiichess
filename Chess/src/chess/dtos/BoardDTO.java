package chess.dtos;

public class BoardDTO {
    PieceDTO[][] board;

    public void setPieceDTOAt(int x, int y, PieceDTO p) {
        this.board[x][y] = p;
    }

    public PieceDTO getPieceDTOAt(int x, int y) {
        return this.board[x][y];
    }
}
