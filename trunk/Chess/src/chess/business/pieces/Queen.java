package chess.business.pieces;

public class Queen extends Piece {

    public Queen(char color) {
        super(color, 'Q');
    }

    public PieceRule getMoveRules() {
        return new PieceRule();
    }
}
