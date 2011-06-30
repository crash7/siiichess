package chess.business;

import chess.business.pieces.Piece;


public class PieceMove {
    private Piece piece;
		private Piece captured;
    private Move move;
    
		public PieceMove(Piece p, Move m){
        this.piece=p;
        this.move=m;
    }
		
		public PieceMove(Piece p, Move m, Piece c){
        this(p, m);
				this.captured = c;
    }

    public Move getMove() {
        return move;
    }

    public Piece getPiece() {
        return piece;
    }
    
}
