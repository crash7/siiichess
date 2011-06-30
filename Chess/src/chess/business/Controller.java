package chess.business;

import chess.business.board.Board;
import chess.business.dtos.*;

public class Controller {
    private Game currentGame;
    private Player[] registeredPlayers;

    public Controller() {
    	this.registeredPlayers = new Player[2];
    	
    }
    
    public void newGame(PlayerDTO white, PlayerDTO black) {
    	
    	
    }

    public void restartGame() {
    	
    	
    }
    
    public int move(PlayerDTO player, PlayerMoveDTO playerMove){
        return 0;
        
    }
    
    public boolean promoteTo(PlayerDTO player, PlayerDTO piece){
        return true;
        
    }
    
    public BoardDTO getBoard(){
    	Board board = this.currentGame.getBoard();
    	
    	// conversion...
    	
        return new BoardDTO();
    }
    
    public boolean undoLastMove(PlayerDTO player) {
    	if(player.getId() < 3 && player.getId() > 0) {
    		return this.currentGame.undoLastMove(this.registeredPlayers[player.getId()-1]);
    		
    	}
    	
    	return false;
    	
    }


}
