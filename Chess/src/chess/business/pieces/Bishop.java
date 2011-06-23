package chess.business.pieces;

public class Bishop extends Piece {

    public Bishop(char color) {
        super(color, 'B');
    }

    public PieceRule getMoveRules() {
        return new PieceRule();
    }
}
