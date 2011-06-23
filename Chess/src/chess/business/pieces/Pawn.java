package chess.business.pieces;
public class Pawn extends Piece {

    public Pawn(char color) {
        super(color, 'P');
    }

    public PieceRule getMoveRules() {
        return new PieceRule();
    }
}
