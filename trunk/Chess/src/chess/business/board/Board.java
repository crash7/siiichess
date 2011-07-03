package chess.business.board;

import chess.business.Position;
import chess.business.pieces.Piece;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
	
	
	// porque el board tiene que llenar el tablero? porque tiene saber como van las cosas?
	// y si cambiamos la modalidad a juego de reinas?
	// como hacemos que simplemente heredando Game y sobreescribiendo los metodos juguemos solo con reinas?
	// y que las reinas vayan en lugares especificos? 
    public void llenarTablero(List Pieces1, List Pieces2) {
        Iterator iterator1 = Pieces1.iterator();
        Iterator iterator2 = Pieces2.iterator();
        int i=0;

        while(iterator1.hasNext() && iterator2.hasNext()){
            this.setPieceAt(new Position(Board.DIMENSION-1,i), (Piece)iterator1.next());
            this.setPieceAt(new Position(Board.DIMENSION-2,i), (Piece)iterator1.next());
            this.setPieceAt(new Position(0,i), (Piece)iterator2.next());
            this.setPieceAt(new Position(1,i), (Piece)iterator2.next());
            i++;
        }
    }

	public boolean validatePosition(Position p) {
		return (p.getX() >= 0 && p.getX() < Board.DIMENSION && p.getY() >= 0 && p
				.getY() < Board.DIMENSION);

	}

	public boolean setPieceAt(Position position, Piece piece) {
		if (this.validatePosition(position)) {
			piece.setPosition(position);
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
		PieceMove lastmove = (PieceMove) this.moves
				.remove(this.moves.size() - 1);

		if (lastmove != null) {
			this.squares[lastmove.getSource().getX()][lastmove.getSource()
					.getY()].setPiece(lastmove.getPiece());
			this.squares[lastmove.getDestination().getX()][lastmove
					.getDestination().getY()].setPiece(lastmove.getCaptured());
			lastmove.getCaptured().setActive(true);

			return true;
		}

		return false;

	}

}
