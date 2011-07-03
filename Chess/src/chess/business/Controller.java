package chess.business;

import chess.dtos.PlayerDTO;
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
        registeredPlayers[0] = new Player(white.getName(), white.getColor());
        registeredPlayers[1] = new Player(black.getName(), black.getColor());  
        white.setId(0);
        black.setId(1);
        currentGame.newGame(registeredPlayers[0], registeredPlayers[1]);    	
    }

    public void restartGame() {  
        currentGame.newGame(registeredPlayers[0], registeredPlayers[1]);    	
    }
    
    public int move(PlayerDTO player, int xs, int ys, int xd, int yd){
        Move move = new Move();
        Player businessPlayer = new Player(player.getName(), player.getColor());
        move.setDestination(new Position(xs, ys));
        move.setSource(new Position(xd, yd));        
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
    	if(this.currentGame != null) {
    		Board board = this.currentGame.getBoard();
	        String[][] boardDT = new String[Board.DIMENSION][Board.DIMENSION];
	    	for (int i=0;i< Board.DIMENSION; i++) {
	            for(int j=0;j< Board.DIMENSION; j++) {
	                Position pos = new Position(i,j);
	                Piece piece = board.getPieceAt(pos);
	                if(piece != null) {
	                	boardDT[i][j] = board.getPieceAt(pos).getKeyname() + "," + board.getPieceAt(pos).getColor();
	                }
	                
	            }
	            
	        }
	    	return boardDT;
	    	
        }
    	
        return null;
        
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
    
    public boolean isActive() {
    	return this.currentGame.isActive();
    	
    }


}
