package chess.business;

public class Controller {

    Game currentGame;
    Player[] registeredPlayers;

    public void newGame(PlayerDto playerA, PlayerDto playerB) {
    }

    public void restartGame() {
    }
    public int move(PlayerDto player, PlayerMoveDto playerMove){
        return 0;
    }
    public boolean promoteTo(PlayerDto player, PieceDto piece){
        return true;
    }
    public BoardDto getBoard(){
        return new BoardDto();
    }
    public void undoLastMove(){
        
    }
}
