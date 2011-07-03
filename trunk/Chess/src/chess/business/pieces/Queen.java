package chess.business.pieces;

import chess.business.Move;
import chess.business.board.Board;
import chess.business.pieces.rules.QueenRule;
import chess.business.pieces.rules.PieceRule;
import java.util.List;

public class Queen extends Piece {
    private PieceRule pieceRule = new QueenRule();
    public Queen(char color) {
        super(color, 'Q');

    }

    public boolean makeMove(Move move, Board board, King king, List oppiece) {
        
        Piece piece = new Bishop(this.getColor());
        boolean flag = false;
        flag= piece.makeMove(move, board, king, oppiece);
        if (!flag)
        {
            piece= new Rook(this.getColor());
            return piece.makeMove(move, board, king, oppiece);
        }
        return flag;
    }
}
