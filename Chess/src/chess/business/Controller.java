package chess.business;

import chess.dtos.PlayerDTO;
import chess.dtos.PlayerMoveDTO;
import chess.dtos.PieceDTO;
import chess.business.board.Board;
import chess.business.pieces.Bishop;
import chess.business.pieces.Knight;
import chess.business.pieces.Piece;
import chess.business.pieces.Queen;
import chess.business.pieces.Rook;

public class Controller {
    private Game currentGame;
    private Player[] registeredPlayers;    
    public Controller() {
    	this.registeredPlayers = new Player[2];
    	
    }

    public Player[] getRegisteredPlayers() {
        return registeredPlayers;
    }

    public void setRegisteredPlayers(Player[] registeredPlayers) {
        this.registeredPlayers = registeredPlayers;
    }
    
    public void newGame(PlayerDTO white, PlayerDTO black) {
    	currentGame = new Game();
        Player playerWhite = new Player(white.getName(), white.getColor());
        Player playerBlack = new Player(black.getName(), black.getColor());        
        currentGame.newGame(playerWhite, playerBlack);    	
    }

    public void restartGame() {        
    }
    
    public int move(PlayerDTO player, PlayerMoveDTO playerMove){
        Move move = new Move();
        Player businessPlayer = new Player(player.getName(), player.getColor());
        move.setDestination(new Position(playerMove.getDestinationX(), playerMove.getDestinationY()));
        move.setSource(new Position(playerMove.getSourceX(), playerMove.getSourceY()));        
        return currentGame.move(businessPlayer, move);
        
    }
    
    public boolean promoteTo(PlayerDTO player, PieceDTO piece){
    	Piece promotep;
    	if(this.validPlayer(player)) {
	    	switch(piece.getKeyname()) {
	    		case 'B':
	    		case 'b':
	    			promotep = new Bishop(piece.getColor());
	    			break;
	    			
	    		case 'N':
	    		case 'n':
	    			promotep = new Knight(piece.getColor());
	    			break;
	    			
	    		case 'R':
	    		case 'r':
	    			promotep = new Rook(piece.getColor());
	    			break;
	    			
	    		case 'Q':
	    		case 'q':
	    			promotep = new Queen(piece.getColor());
	    			break;
	    			
	    		default:
	    			promotep = null;
	    		
	    	
	    	}
	    	
	    	if(promotep != null) {
	    		this.currentGame.promotePawnTo(this.registeredPlayers[player.getId()-1], promotep);
	    		return true;
	    		
	    	}
    	}
    	
        return false;
        
    }
    
    public String[][] getBoard(){
    	Board board = this.currentGame.getBoard();
        String[][] boardDT = new String[Board.DIMENSION][Board.DIMENSION];
    	for (int i=0;i< Board.DIMENSION; i++) {
            for(int j=0;j< Board.DIMENSION; j++) {
                Position pos = new Position(i,j);
                boardDT[i][j] = board.getPieceAt(pos).getKeyname() + "," + board.getPieceAt(pos).getColor(); 
            }
            
        }   
    	
        return boardDT;
    }
    
    public boolean undoLastMove(PlayerDTO player) {
    	if(this.validPlayer(player)) {
    		return this.currentGame.undoLastMove(this.registeredPlayers[player.getId()-1]);
    		
    	}
    	
    	return false;
    	
    }
    
    private boolean validPlayer(PlayerDTO p) {
    	return (p.getId() < 3 && p.getId() > 0);
    	
    }


}