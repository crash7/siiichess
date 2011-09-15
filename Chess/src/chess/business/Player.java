package chess.business;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import chess.business.pieces.King;
import chess.business.pieces.Piece;

public class Player {
	private int uniqueId;
	private char color;
	private String name;
	private List pieces;
	private King king;

	public Player(String name, char color, int unique) {
		this.color = color;
		this.name = name;
		uniqueId = unique;
		this.pieces = new ArrayList();

	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return name;

	}

	public void setColor(char color) {
		this.color = color;

	}

	public char getColor() {
		return color;

	}
	
	public void setUniqueId(int unique) {
		uniqueId = unique;
		
	}
	
	public int getUniqueId() {
		return uniqueId;
		
	}

	public King getKing() {
		return king;

	}

	public void addPiece(Piece piece) {
		pieces.add(piece);
		if(piece instanceof King) {
			king = (King) piece;
			
		}

	}

	public List getPieces() {
		return pieces;

	}
        
	public List getInactivePieces() {
        List inactive = new ArrayList();
        Iterator iterator = pieces.iterator();
        while(iterator.hasNext()) {
            Piece current = (Piece) iterator.next();
            if(!current.isActive()) {
            	inactive.add(current);
            	
            }
            
        }
        return inactive;

	}

	public boolean equals(Object o) {
		return this.color == ((Player) o).getColor();

	}

	public boolean isWhite() {
		return this.color == 'W' || this.color == 'w';

	}

	public boolean isBlack() {
		return this.color == 'B' || this.color == 'b';

	}

}
