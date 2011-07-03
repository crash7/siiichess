package chess.business;

import chess.business.pieces.*;
import chess.business.pieces.rules.KingRule;
import chess.business.pieces.rules.PieceRule;
import chess.business.board.Board;

public class Game {

    private Player currentPlayer;
    private Player opponentPlayer;
    private Position positionToPromote;
    private Board board;
    private int status;
    private static int PLAYING = 0;
    private static int WHITECHECK = 1;
    private static int BLACKCHECK = 2;
    private static int WHITECHECKMATE = 3;
    private static int BLACKCHECKMATE = 4;
    private static int CHECKMATE = 5;
    private static int ILEGALMOVE = 6;
    

    public int getStatus() {
        return this.status;

    }

    public Board getBoard() {
        return this.board;

    }
    
    public void newGame(Player w, Player b){    	
    	this.currentPlayer = w;
        this.createPieceList(this.currentPlayer);
    	this.opponentPlayer = b;
        this.createPieceList(this.opponentPlayer);
    	this.board = new Board(currentPlayer.getPieces(),opponentPlayer.getPieces());
        this.status = Game.PLAYING;
    	this.positionToPromote = null;
    }

    public int move(Player p, Move m) {
        int moveResult = Game.ILEGALMOVE;
        if (this.status == Game.PLAYING) {
            Piece piece;
            PieceRule rule;
            if (this.board.validatePosition(m.getSource()) && this.board.validatePosition(m.getDestination())) {
                piece = this.board.getPieceAt(m.getSource());
                if (piece != null) {
                    rule = piece.getMoveRule();
                    if (rule.makeMove(m, this.board, this.currentPlayer.getKing(), this.opponentPlayer.getPieces())) {
                        // Solo si el resultado del movimiento es valido!
                        Player temp = this.currentPlayer;
                        this.currentPlayer = this.opponentPlayer;
                        this.opponentPlayer = temp;

                        KingRule cpking = (KingRule) this.currentPlayer.getKing().getMoveRule();

                        if (cpking.isCheckMated(this.board, this.opponentPlayer.getPieces())) {
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
        return moveResult;
    }

    public boolean promotePawnTo(Player player, Piece piece) {
        if (currentPlayer.equals(player)) {
            board.setPieceAt(this.positionToPromote, piece);

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
            player.addPiece(new Pawn(player.getColor()));
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

}