/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chess.business;

import chess.business.Move;


public class PieceMove {
    private Piece piece;
    private Move move;
    public PieceMove(Piece p, Move m){
        this.piece=p;
        this.move=m;
    }

    public Move getMove() {
        return move;
    }

    public Piece getPiece() {
        return piece;
    }
    
}