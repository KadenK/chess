package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.HashSet;

public class RookMoveCalculator implements MoveCalculator {

    public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPos) {
        HashSet<ChessMove> moves = HashSet.newHashSet(14); //14 is the max possible number of moves for a Rook
        int currX = currPos.getColumn();
        int currY = currPos.getRow();
        Integer[][] relativeMoves = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        for (Integer[] relativeMove : relativeMoves) { //Iterate over each direction
            boolean obstructed = false;
            int i = 1;
            while (!obstructed) { //Iterate in that direction until obstructed
                ChessPosition possiblePosition = new ChessPosition(currY + (relativeMove[1] * i), currX + (relativeMove[0] * i));
                //If it is a valid square and open
                if (MoveCalculator.isValidSquare(possiblePosition) && !board.isOccupied(possiblePosition)) {
                    moves.add(new ChessMove(currPos, possiblePosition, null));
                    i++;
                }
                //If it is a valid square and an enemy
                else if (MoveCalculator.isValidSquare(possiblePosition) && board.getTeamOfSquare(possiblePosition) != board.getTeamOfSquare(currPos)) {
                    moves.add(new ChessMove(currPos, possiblePosition, null));
                    obstructed = true;
                }
                //If it is a valid square and a teammate
                else if (MoveCalculator.isValidSquare(possiblePosition) && board.getTeamOfSquare(possiblePosition) != board.getTeamOfSquare(currPos)) {
                    obstructed = true;
                }
                else {
                    obstructed = true;
                }
            }
        }
        return moves;
    }

}
