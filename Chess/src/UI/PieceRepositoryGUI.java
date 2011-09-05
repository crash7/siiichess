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
		repository.put(new String("Kw"), new PieceGUI("white_king.png", 'K', 'w')); // king
		repository.put(new String("Qw"), new PieceGUI("white_queen.png", 'Q', 'w')); // queen
		repository.put(new String("Bw"), new PieceGUI("white_bishop.png", 'B', 'w')); // bishop
		repository.put(new String("Nw"), new PieceGUI("white_knight.png", 'N', 'w')); // Knight
		repository.put(new String("Rw"), new PieceGUI("white_rook.png", 'R', 'w')); // Rook
		repository.put(new String("Pw"), new PieceGUI("white_pawn.png", 'P', 'w')); // Pawn
		
		// black
		repository.put(new String("Kb"), new PieceGUI("black_king.png", 'K', 'b')); // king
		repository.put(new String("Qb"), new PieceGUI("black_queen.png", 'Q', 'b')); // queen
		repository.put(new String("Bb"), new PieceGUI("black_bishop.png", 'B', 'b')); // bishop
		repository.put(new String("Nb"), new PieceGUI("black_knight.png", 'N', 'b')); // Knight
		repository.put(new String("Rb"), new PieceGUI("black_rook.png", 'R', 'b')); // Rook
		repository.put(new String("Pb"), new PieceGUI("black_pawn.png", 'P', 'b')); // Pawn
		
		// big images
		repository.put(new String("Kwb"), new PieceGUI("white_king_big.png", 'K', 'b')); // white king big
		repository.put(new String("Kbb"), new PieceGUI("black_king_big.png", 'K', 'b')); // black king big
				
	}
	
	public PieceGUI getPiece(String codename) {
		return (PieceGUI)repository.get(codename);
		
	}
	
}
