package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public interface MoveCalculator {

    static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPosition) {
        return null;
    }

    static boolean isValidSquare(ChessPosition position) {
        return (position.getRow() >= 1 && position.getRow() <= 8) &&
                (position.getColumn() >= 1 && position.getColumn() <= 8);
    }

}
