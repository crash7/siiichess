package chess.business;

import chess.business.pieces.Piece;
import chess.business.rules.PieceRule;
import chess.business.util.Player;
import chess.business.util.Position;
import chess.business.util.Move;
import chess.business.board.Board;


public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private Position positionToPromote;
    private Board board;
    private int status;
    public static int PLAYING = 0;
    public static int WHITECHECK = 1;
    public static int BLACKCHECK = 2;
    public static int WHITECHECKMATE = 3;
    public static int BLACKCHECKMATE = 4;
    
    public int getStatus() {
    	return this.status;
    	
    }
    
    public Board getBoard() {
    	return this.board;
    	
    }
    
    public void newGame(Player w, Player b){
    	this.board = new Board();
    	this.currentPlayer = w;
    	this.opponentPlayer = b;
    	this.status = Game.PLAYING;
    	this.positionToPromote = null;

    }
    
    public boolean move(Player p, Move m){
    	Piece piece;
    	PieceRule rule;
    	if(this.board.validatePosition(m.getSource()) && this.board.validatePosition(m.getDestination())) {
    		piece = this.board.getPieceAt(m.getSource());
    		if(piece != null) {
    			rule = piece.getMoveRule();
    			return rule.makeMove(m, this.board, this.currentPlayer.getKing(), this.opponentPlayer.getPieces());
    			
    		}
    		
    		// Solo si el resultado del movimiento es valido!
        	Player temp = this.currentPlayer;
        	this.currentPlayer = this.opponentPlayer;
        	this.opponentPlayer = temp;
    		
    	}

    	return false;
        
    }
    
    public boolean promotePawnTo(Player player, Piece piece){
    	if(currentPlayer.equals(player)) {
    		board.setPieceAt(this.positionToPromote, piece);
    		
    		return true;
    	}
    	
        return false;
        
    }
    
    public boolean undoLastMove(Player whoAsk) {
    	if(this.currentPlayer.equals(whoAsk)) {
    		this.board.undoLastMove();
    		
    		Player temp = this.currentPlayer;
    		this.currentPlayer = this.opponentPlayer;
    		this.opponentPlayer = temp;
    		
    		return true;
    		    		    		
    	}
    	
    	return false;
    	
    }

}
