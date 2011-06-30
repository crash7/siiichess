package chess.business.util;

import chess.business.pieces.King;
import chess.business.pieces.Piece;
import java.util.List;

public class Player {
	private char color;
	private String name;
	private List pieces;
	private King king;

	public Player(String name, char color) {
		this.color = color;
		this.name = name;
		
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public String getName() {
		return this.name;
		
	}

	public void setColor(char color) {
		this.color = color;
		
	}

	public char getColor() {
		return this.color;
		
	}

	public King getKing() {
		return this.king;
		
	}

	public void addPiece(Piece piece) {
		this.pieces.add(piece);
		
	}

	public List getPieces() {
		return this.pieces;
		
	}
	
}
