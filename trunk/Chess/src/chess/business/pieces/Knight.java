package chess.business.pieces;

public class Knight extends Piece {

    public Knight(char color) {
        super(color, 'N');
    }

    public PieceRule getMoveRules() {
        return new PieceRule();
    }
}
