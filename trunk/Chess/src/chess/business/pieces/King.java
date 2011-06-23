package chess.business.pieces;

public class King extends Piece {

    public King(char color) {
        super(color, 'K');
    }

    public PieceRule getMoveRules() {
        return new PieceRule();
    }
}
