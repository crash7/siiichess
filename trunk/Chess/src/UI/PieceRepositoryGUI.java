package UI;

import java.util.HashMap;

public class PieceRepositoryGUI {
	private HashMap repository;
	private static PieceRepositoryGUI instance = new PieceRepositoryGUI();
	
	public static PieceRepositoryGUI get() {
		return instance;
		
	}
	
	private PieceRepositoryGUI() {
		buildRepository();
		
	}
	
	private void buildRepository() {
		repository = new HashMap();
		// white
		repository.put(new String("kw"), new PieceGUI("white_king.png", 'k', 'b')); // king
		repository.put(new String("qw"), new PieceGUI("white_queen.png", 'k', 'b')); // queen
		repository.put(new String("bw"), new PieceGUI("white_bishop.png", 'k', 'b')); // bishop
		repository.put(new String("nw"), new PieceGUI("white_knight.png", 'k', 'b')); // Knight
		repository.put(new String("rw"), new PieceGUI("white_rook.png", 'k', 'b')); // Rook
		repository.put(new String("pw"), new PieceGUI("white_pawn.png", 'k', 'b')); // Pawn
		
		// black
		repository.put(new String("kb"), new PieceGUI("black_king.png", 'k', 'b')); // king
		repository.put(new String("qb"), new PieceGUI("black_queen.png", 'k', 'b')); // queen
		repository.put(new String("bb"), new PieceGUI("black_bishop.png", 'k', 'b')); // bishop
		repository.put(new String("nb"), new PieceGUI("black_knight.png", 'k', 'b')); // Knight
		repository.put(new String("rb"), new PieceGUI("black_rook.png", 'k', 'b')); // Rook
		repository.put(new String("pb"), new PieceGUI("black_pawn.png", 'k', 'b')); // Pawn
		
		// big images
		repository.put(new String("kwb"), new PieceGUI("white_king_big.png", 'k', 'b')); // white king big
		repository.put(new String("kbb"), new PieceGUI("black_king_big.png", 'k', 'b')); // black king big
				
	}
	
	public PieceGUI getPiece(String codename) {
		return (PieceGUI)repository.get(codename); 
		
	}
	
	public PieceGUI getPiece(char name, char color) {
		return getPiece(" " + name + color);
		
	}

}
