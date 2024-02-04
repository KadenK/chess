package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class KnightMoveCalculator implements MoveCalculator {

    public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPosition) {
        HashSet<ChessMove> moves = HashSet.newHashSet(8); //8 is the max number of moves of a Knight
        int currX = currPosition.getColumn();
        int currY = currPosition.getRow();
        int[][] relativeMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

        ChessGame.TeamColor team = board.getTeamOfSquare(currPosition);

        for (int[] relativeMove : relativeMoves) {
            ChessPosition possiblePosition = new ChessPosition(currY + relativeMove[1], currX + relativeMove[0]);
            if (MoveCalculator.isValidSquare(possiblePosition) && board.getTeamOfSquare(possiblePosition) != team) {
                moves.add(new ChessMove(currPosition, possiblePosition, null));
            }
        }

        return moves;
    }

}
