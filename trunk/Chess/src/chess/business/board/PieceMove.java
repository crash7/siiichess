/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.business.board;

import chess.business.Move;


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
