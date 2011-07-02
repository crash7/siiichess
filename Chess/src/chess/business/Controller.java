package chess.business;

import chess.dtos.PlayerDTO;
import chess.dtos.PlayerMoveDTO;
import chess.business.board.Board;

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
        Move move = new Move();
        Player businessPlayer = new Player(player.getName(), player.getColor());
        move.setDestination(new Position(playerMove.getDestinationX(), playerMove.getDestinationY()));
        move.setSource(new Position(playerMove.getSourceX(), playerMove.getSourceY()));        
        return currentGame.move(businessPlayer, move);
        
    }
    
    public boolean promoteTo(PlayerDTO player, PlayerDTO piece){
        return true;
        
    }
    
    public String[][] getBoard(){
    	Board board = this.currentGame.getBoard();
        String[][] boardDT = new String[8][8];
        String keyPlayer;
    	for (int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++) {
                Position pos = new Position(i,j);
                keyPlayer = board.getPieceAt(pos).getKeyname() + "," +board.getPieceAt(pos).getColor(); 
                boardDT[i][j] = keyPlayer;
            }
        }            	    	
        return boardDT;
    }
    
    public boolean undoLastMove(PlayerDTO player) {
    	if(player.getId() < 3 && player.getId() > 0) {
    		return this.currentGame.undoLastMove(this.registeredPlayers[player.getId()-1]);
    		
    	}
    	
    	return false;
    	
    }


}
