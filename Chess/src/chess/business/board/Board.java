package chess.business.board;

import chess.business.Position;
import chess.business.pieces.Piece;
import java.util.List;
import java.util.ArrayList;

public class Board {
	public static final int DIMENSION = 8;
	private Square[][] squares;
	private List moves;

	public Board() {
		this.squares = new Square[Board.DIMENSION][Board.DIMENSION];

		for (int i = 0; i < Board.DIMENSION; i++) {
			for (int j = 0; j < Board.DIMENSION; j++) {
				this.squares[i][j] = new Square();

			}

		}                
                
		this.moves = new ArrayList();

	}
	
	public boolean validatePosition(Position p) {
		return (p.getX() >= 0 && p.getX() < Board.DIMENSION && p.getY() >= 0 && p
				.getY() < Board.DIMENSION);

	}

	public boolean setPieceAt(Position position, Piece piece) {
		if (this.validatePosition(position)) {
			if(piece != null) {
				piece.setPosition(position);
			}
			this.squares[position.getX()][position.getY()].setPiece(piece);
			return true;

		}

		return false;
	}

	public Piece getPieceAt(Position position) {
		if (this.validatePosition(position)) {
			return this.squares[position.getX()][position.getY()].getPiece();
		}

		return null;

	}

	public boolean move(PieceMove move) {
		if (this.validatePosition(move.getSource())
				&& this.validatePosition(move.getDestination())
				&& !move.getSource().equals(move.getDestination())
				&& move.getPiece() != null) {
			this.squares[move.getSource().getX()][move.getSource().getY()]
					.setPiece(null);
			
			move.getPiece().setPosition(move.getDestination());
			this.squares[move.getDestination().getX()][move.getDestination()
					.getY()].setPiece(move.getPiece());
			
			if (move.getCaptured() != null) {
				move.getCaptured().setActive(false);

			}

			this.moves.add(move);

			return true;

		}

		return false;

	}
	
	public void addMove(PieceMove m) {
		this.moves.add(m);
		
	}

	public boolean undoLastMove() {
		PieceMove lastmove = (PieceMove) this.moves.remove(this.moves.size() - 1);

		if (lastmove != null) {
			this.squares[lastmove.getSource().getX()][lastmove.getSource()
					.getY()].setPiece(lastmove.getPiece());
			this.squares[lastmove.getDestination().getX()][lastmove
					.getDestination().getY()].setPiece(lastmove.getCaptured());
			if(lastmove.getCaptured() != null) {
				lastmove.getCaptured().setActive(true);
			}

			return true;
		}

		return false;

	}

	public PieceMove getLastMove() {
	    return (PieceMove)this.moves.get(this.moves.size() - 1);
	    
	}
	
}
