package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class KingMoveCalculator implements MoveCalculator {

    public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPos) {
        HashSet<ChessMove> moves = HashSet.newHashSet(8);
        int currX = currPos.getColumn();
        int currY = currPos.getRow();
        Integer[][] relativeMoves = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        for (Integer[] relativeMove : relativeMoves) {
            ChessPosition possiblePosition = new ChessPosition(currY + relativeMove[1], currX + relativeMove[0]);
            if (MoveCalculator.isValidSquare(possiblePosition) && board.getTeamOfSquare(possiblePosition) != board.getTeamOfSquare(currPos)) {
                moves.add(new ChessMove(currPos, possiblePosition, null));
            }
        }
        return moves;
    }

}
