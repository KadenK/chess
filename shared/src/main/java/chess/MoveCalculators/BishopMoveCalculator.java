package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class BishopMoveCalculator implements MoveCalculator {

    public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPosition) {
        HashSet<ChessMove> moves = HashSet.newHashSet(13); //13 is the max number of moves of a Bishop
        int currX = currPosition.getColumn();
        int currY = currPosition.getRow();
        int[][] moveDirections = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

        ChessGame.TeamColor team = board.getTeamOfSquare(currPosition);

        for (int[] direction : moveDirections) {
            boolean obstructed = false;
            int i = 1;
            while (!obstructed) {
                ChessPosition possiblePosition = new ChessPosition(currY + direction[1]*i, currX + direction[0]*i);
                if (!MoveCalculator.isValidSquare(possiblePosition)) {
                    obstructed = true;
                }
                else if (board.getPiece(possiblePosition) == null) {
                    moves.add(new ChessMove(currPosition, possiblePosition, null));
                }
                else if (board.getTeamOfSquare(possiblePosition) != team) {
                    moves.add(new ChessMove(currPosition, possiblePosition, null));
                    obstructed = true;
                }
                else if (board.getTeamOfSquare(possiblePosition) == team) {
                    obstructed = true;
                }
                else {
                    obstructed = true;
                }
                i++;
            }
        }

        return moves;
    }

}
