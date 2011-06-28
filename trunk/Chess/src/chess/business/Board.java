package chess.business;

import chess.business.pieces.Piece;
import java.util.ArrayList;

public class Board {
    private ArrayList squares;
    private ArrayList moves;
    private int dimensions;
    public Board(){

    }
    public boolean move(Move move){
        return true;
    }
    public boolean undoLastMove(){
    return true;
    }
    public void changePiece(Position position, Piece piece){
    }
    public Piece getPieceAt(Position position){
        return null;
    }
}


