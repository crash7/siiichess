package chess.business;

import chess.business.pieces.Bishop;
import chess.business.pieces.King;
import chess.business.pieces.Knight;
import chess.business.pieces.Pawn;
import chess.business.pieces.Piece;
import chess.business.pieces.Queen;
import chess.business.pieces.Rook;
import chess.dtos.MoveDTO;
import chess.dtos.PlayerDTO;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class BusinessController extends Observable {
	public static final int LEGALMOVE = 9;
	public static final int ILEGALMOVE = 10;
	public static final int WHITECHECK = 2;
	public static final int BLACKCHECK = 3;
	public static final int WHITECHECKMATE = 4;
	public static final int BLACKCHECKMATE = 5;
	private Player currentPlayer;
	private Player opponentPlayer;
	private int status;
	private boolean running;
	private Board board;
	private Position promotedPosition;
    
    public BusinessController() {
    	running = false;
    	
    }
    
    public void newGame(PlayerDTO white, PlayerDTO black) {
    	currentPlayer = new Player(white.getName(), white.getColor(), white.hashCode());
    	opponentPlayer = new Player(black.getName(), black.getColor(), black.hashCode());
    	initPlayer(currentPlayer);
    	initPlayer(opponentPlayer);
    	board = new Board(currentPlayer.getPieces(), opponentPlayer.getPieces());
    	running = true;
    	setChanged();
    	notifyObservers(new Integer(BusinessController.LEGALMOVE));
    	
    }
    
    public void playerMove(PlayerDTO player, int xs, int ys, int xd, int yd) {
    	if(running) {
	    	if(player.hashCode() == currentPlayer.getUniqueId()) {
	    		Piece selected = board.getPieceAt(new Position(xs, ys));
	    		if(selected != null) {
	    			if(selected.getColor() == currentPlayer.getColor()) {
	    				if(selected.makeMove(board, new Position(xd, yd), opponentPlayer.getPieces())) {
	    					if(currentPlayer.getKing().isThreatened(board, opponentPlayer.getPieces())) {
	    						board.undoLastMove();
	    						status = BusinessController.ILEGALMOVE;
	    						System.out.println("[WARNING] Rey " + currentPlayer.getColor() + " esta en jaque");
	    						
	    					} else {
	    						status = BusinessController.LEGALMOVE;
	    						swapPlayers();
	    						if(currentPlayer.getKing().isThreatened(board, opponentPlayer.getPieces())) {
	    							if(currentPlayer.getKing().isCheckMated(board, opponentPlayer.getPieces(), currentPlayer.getPieces())) {
	    								if(currentPlayer.isWhite()) {
	    									status = BusinessController.WHITECHECKMATE;
	    									running = false;
	    									//System.out.println("[FATAL] Rey blanco quedo en jaque mate");
	    									
	    								} else {
	    									status = BusinessController.BLACKCHECKMATE;
	    									running = false;
	    									//System.out.println("[FATAL] Rey negro quedo en jaque mate");
	    									
	    								}
	    								
	    							} else {
	    								if(currentPlayer.isWhite()) {
	    									status = BusinessController.WHITECHECK;
	    									//System.out.println("[WARNING] Rey negro quedo en jaque");
	    									
	    								} else {
	    									status = BusinessController.BLACKCHECK;
	    									//System.out.println("[WARNING] Rey negro quedo en jaque");
	    									
	    								}
	    								
	    							}
	    							
	    						}
	    						
	    					}
	    					
	    				} else {
	    					status = BusinessController.ILEGALMOVE;
	    					
	    				}
	    				
	    			} else {
	    				status = BusinessController.ILEGALMOVE;
	    				
	    			}
	    			
	    		} else {
	    			status = BusinessController.ILEGALMOVE;
	    			
	    		}
	    		
	    	} else {
	    		status = BusinessController.ILEGALMOVE;
	    		System.out.println("[WARNING] Jugador incorrecto");
	    		System.out.println("chash: " + currentPlayer.getUniqueId() + " " + currentPlayer.getColor());
	    		System.out.println("ohash: " + opponentPlayer.getUniqueId() + " " + opponentPlayer.getColor());
	    		System.out.println("phash: " + player.hashCode() + " " + player.getColor());

	    	}
	    	
    	} else {
    		status = BusinessController.ILEGALMOVE;
    		
    	}
    	setChanged();
    	notifyObservers(new Integer(status));

    }
    
    public String[][] getBoard() {
    	Piece piece;
    	String[][] output = new String[Board.DIMENSION][Board.DIMENSION];
    	if(board != null) {
	    	for(int i = 0; i < Board.DIMENSION; i++) {
	            for(int j = 0; j < Board.DIMENSION; j++) {
	                piece = board.getPieceAt(new Position(i,j));
	                if(piece != null) {
	                	output[i][j] = piece.getKeyName() + "" + piece.getColor();
	                	
	                }
	                
	            }
	            
	        }
	    	
        }
    	return output;
    	
    }
    
    public String[] getInactivePiecesOf(PlayerDTO player) {
    	List inactivepieces = null;
    	String[] result = null;
    	if(player.hashCode() == currentPlayer.getUniqueId()) {
    		inactivepieces = currentPlayer.getInactivePieces();
    		
    	} else if(player.hashCode() == opponentPlayer.getUniqueId()) {
    		inactivepieces = opponentPlayer.getInactivePieces();
    		
    	}
    	if(inactivepieces != null) {
	    	result = new String[inactivepieces.size()];
	    	Iterator iterator = inactivepieces.iterator();
	    	int index = 0;
	        Piece temp;
	        while(iterator.hasNext()) {
	        	temp = (Piece) iterator.next();
	        	result[index] = temp.getKeyName() + "" + temp.getColor();
	        	index++;
	        	
	        }
	        
    	}
    	return result;
    	    	
    }
    
    public boolean usePromotion(PlayerDTO player, char piecename) {
    	if(promotedPosition != null) {
    		promotedPosition = new Position();
    		
    	}
    	return false;
    	/*
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
    	
    	en game
    	
    	if (currentPlayer.equals(player)) {
        	piece.setPosition(this.positionToPromote);
            board.setPieceAt(this.positionToPromote, piece);
            currentPlayer.addPiece(piece);
            return true;
            
        }
    	 */
    	
    }
    
    public int getStatus() {
    	return status;
    	
    }
    
    public boolean isRunning() {
    	return running;
    	
    }
    
    public void clientMoveError() {
    	System.out.println("Error al mover la pieza en el cliente");
    	setChanged();
    	notifyObservers(new Integer(BusinessController.ILEGALMOVE));
    	
    }
    
    public MoveDTO getLastMove() {
        Position[] p = board.getLastMovePositions();
        if(p != null) {
            return new MoveDTO(p[0].getX(),p[0].getY(),p[1].getX(),p[1].getY());
        }
        return null;
    }
        
    private void swapPlayers() {
    	Player temp = currentPlayer;
    	currentPlayer = opponentPlayer;
    	opponentPlayer = temp;
    	
    }
    
    private void initPlayer(Player player) {
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