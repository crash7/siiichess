package chess.business;

import chess.business.pieces.Piece;
import java.util.List;

public class Board {
		private static final int DIMENSION = 8;
    private Square[][] squares;
    private List moves;

    public Board() {
			this.squares = new Square[][];
			
			for(int i = 0; i < Board.DIMENSION; i++) {
				for(int j = 0; j < Board.DIMENSION; j++) {
					this.squares[i][j] = new Square();
					
				}
				
			}

    }
		
    public boolean move(Move move) {
        
				
				
				return true;
				
    }
		
    public boolean undoLastMove() {
			return true;
			
    }
		
   public bool setPieceAt(Position position, Piece piece) {
			if(this.validatePosition(position)) {
				this.[position.getX()][position.getY()].setPiece(piece);
				return true;
				
			}
			
			return false;
		}
		
    public Piece getPieceAt(Position position) {
        return null;
				
    }
		
		public bool validatePosition(Position position) {
			if() {
			
			}
		}
}

