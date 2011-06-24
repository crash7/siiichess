package chess.business;

import chess.business.pieces.Piece;


public class Game {
    Player[] players;
    Player currentPlayer;
    Player oponentPlayer;
    Position positionToPromote;
    Board board;
    public void newGame(Player w, Player b){

    }
    public boolean move(Player p, Move m){
        return true;
    }
    public boolean promotePawnTo(Player player, Piece piece){
        return true;
    }

}
