package chess.business.pieces;
public class Rook extends Piece {
    public Rook(char color){
        super(color, 'R');
    }
    public PieceRule  getMoveRules(){
        return new PieceRule();
    }
}
