package chess.business;

import chess.business.pieces.*;
import chess.business.board.Board;
import java.util.List;
import java.util.Iterator;

public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private Position positionToPromote;
    private Board board;
    private int status;

    public int getStatus() {
        return status;
    }


    private static int PLAYING = 0;
    private static int WHITECHECK = 1;
    private static int BLACKCHECK = 2;
    private static int WHITECHECKMATE = 3;
    private static int BLACKCHECKMATE = 4;
    private static int CHECKMATE = 5;
    private static int ILEGALMOVE = 6;
    
    public Board getBoard() {
        return this.board;

    }
    
    public void newGame(Player w, Player b){    	
    	this.currentPlayer = w;
        this.createPieceList(this.currentPlayer);
    	this.opponentPlayer = b;
        this.createPieceList(this.opponentPlayer);
    	this.board = new Board();
        this.fillBoard(this.currentPlayer.getPieces(),this.opponentPlayer.getPieces());
        this.status = Game.PLAYING;
    	this.positionToPromote = null;
    	
    	// test tip    	
    	// this.board.setPieceAt(new Position(6, 1), null);
    	
    }

    public void move(Player p, Move m) {
        int moveResult = Game.ILEGALMOVE;
        if (this.status == Game.PLAYING && currentPlayer.equals(p)) {
            Piece piece;
            if (this.board.validatePosition(m.getSource()) && this.board.validatePosition(m.getDestination())) {
                piece = this.board.getPieceAt(m.getSource());
                if (piece != null) {
                  if (piece.getColor() == p.getColor()) {
                    if (piece.makeMove(m, this.board, this.currentPlayer.getKing(), this.opponentPlayer.getPieces(), true)) {
                        // Solo si el resultado del movimiento es valido!
                        Player temp = this.currentPlayer;
                        this.currentPlayer = this.opponentPlayer;
                        this.opponentPlayer = temp;
                        
                        King cpking = this.currentPlayer.getKing();

                        if (cpking.isCheckMated(this.board, this.opponentPlayer.getPieces(), this.currentPlayer.getPieces())) {
                            if (this.currentPlayer.isWhite()) {
                                moveResult = Game.WHITECHECKMATE;

                            } else {
                                moveResult = Game.BLACKCHECKMATE;

                            }

                            this.status = Game.CHECKMATE;

                        } else if (cpking.isChecked(this.board, this.opponentPlayer.getPieces())) { 
                            if (this.currentPlayer.isWhite()) {
                                moveResult = Game.WHITECHECK;

                            } else {
                                moveResult = Game.BLACKCHECK;

                            }

                        } else {
                            moveResult = Game.PLAYING;
                        }

                    }
                  }
                }

            }
        }
        
            status=moveResult;
            
        
        
    }

    public boolean promotePawnTo(Player player, Piece piece) {
        if (currentPlayer.equals(player)) {
        	piece.setPosition(this.positionToPromote);
            board.setPieceAt(this.positionToPromote, piece);
            currentPlayer.addPiece(piece);
            return true;
            
        }

        return false;

    }

    public boolean undoLastMove(Player whoAsk) {
        if (this.currentPlayer.equals(whoAsk)) {
            this.board.undoLastMove();

            Player temp = this.currentPlayer;
            this.currentPlayer = this.opponentPlayer;
            this.opponentPlayer = temp;

            return true;

        }

        return false;

    }
    private void createPieceList(Player player){
            player.addPiece(new Rook(player.getColor()));
            player.addPiece(new Knight(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new Bishop(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new Queen(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new King(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new Bishop(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new Knight(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            player.addPiece(new Rook(player.getColor()));
            player.addPiece(new Pawn(player.getColor()));
            
    }

	public boolean isActive() {
		return this.status == Game.PLAYING;
		
	}

    public void fillBoard(List Pieces1, List Pieces2) {
        Iterator iterator1 = Pieces1.iterator();
        Iterator iterator2 = Pieces2.iterator();
        int i=0;

        while(iterator1.hasNext() && iterator2.hasNext()){
        	this.board.setPieceAt(new Position(Board.DIMENSION-1,i), (Piece)iterator1.next());
            //this.board.setPieceAt(new Position(Board.DIMENSION-2,i), (Piece)iterator1.next());
            this.board.setPieceAt(new Position(0,i), (Piece)iterator2.next());
            //this.board.setPieceAt(new Position(1,i), (Piece)iterator2.next());
            i++;
        }
        
    }

}
