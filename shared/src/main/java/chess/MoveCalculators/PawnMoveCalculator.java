package chess.MoveCalculators;

import chess.*;
import chess.ChessPiece.PieceType;

import java.util.HashSet;

public class PawnMoveCalculator implements MoveCalculator {

    public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPos) {
        HashSet<ChessMove> moves = HashSet.newHashSet(12); //12 is the max possible number of moves for a pawn, counting promotions
        int currX = currPos.getColumn();
        int currY = currPos.getRow();
        ChessGame.TeamColor team = board.getTeamOfSquare(currPos);

        //Determine promotion facts
        boolean promotion = (team == ChessGame.TeamColor.WHITE && currY == 7) | (team == ChessGame.TeamColor.BLACK && currY == 2);
        PieceType[] promotionPieces = promotion ? new PieceType[]{PieceType.BISHOP, PieceType.KNIGHT, PieceType.QUEEN, PieceType.ROOK} : new PieceType[]{null};

        // Generate all three potential positions
        int yIncrement = team == ChessGame.TeamColor.WHITE ? 1 : -1;
        ChessPosition forward = new ChessPosition(currY + yIncrement, currX);
        ChessPosition leftAttack = new ChessPosition(currY + yIncrement, currX - 1);
        ChessPosition rightAttack = new ChessPosition(currY + yIncrement, currX + 1);

        //Generate all potential moves
        for (PieceType promotionPiece : promotionPieces) {
            if (MoveCalculator.isValidSquare(forward) && !board.isOccupied(forward)) {
                moves.add(new ChessMove(currPos, forward, promotionPiece));
            }
            if (MoveCalculator.isValidSquare(leftAttack) && (board.getTeamOfSquare(leftAttack) != team && board.getTeamOfSquare(leftAttack) != null)) {
                moves.add(new ChessMove(currPos, leftAttack, promotionPiece));
            }
            if (MoveCalculator.isValidSquare(rightAttack) && (board.getTeamOfSquare(rightAttack) != team && board.getTeamOfSquare(rightAttack) != null)) {
                moves.add(new ChessMove(currPos, rightAttack, promotionPiece));
            }
        }

        //If it is the first move of the pawn
        if ((currY == 2 && team == ChessGame.TeamColor.WHITE) | (currY == 7 && team == ChessGame.TeamColor.BLACK)) {
            ChessPosition doubleForward = new ChessPosition(currY + yIncrement * 2, currX);
            if (MoveCalculator.isValidSquare(doubleForward) && !board.isOccupied(doubleForward) && !board.isOccupied(forward)) {
                moves.add(new ChessMove(currPos, doubleForward, null));
            }
        }

        return moves;
    }

}
