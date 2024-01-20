package chess;

import chess.MoveCalculators.*;

import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor teamColor;
    private final PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
        teamColor = pieceColor;
        pieceType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return teamColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @param board The current board of the game
     * @param currPos The chess piece's current position
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition currPos) {
        return switch (pieceType) {
            case KING -> KingMoveCalculator.getMoves(board, currPos);
            case QUEEN -> QueenMoveCalculator.getMoves(board, currPos);
            case BISHOP -> BishopMoveCalculator.getMoves(board, currPos);
            case KNIGHT -> KnightMoveCalculator.getMoves(board, currPos);
            case ROOK -> null;
            case PAWN -> PawnMoveCalculator.getMoves(board, currPos);
        };
    }

    @Override
    public String toString() {
        return switch (pieceType) {
            case KING -> teamColor == ChessGame.TeamColor.WHITE ? "K" : "k";
            case QUEEN -> teamColor == ChessGame.TeamColor.WHITE ? "Q" : "q";
            case BISHOP -> teamColor == ChessGame.TeamColor.WHITE ? "B" : "b";
            case KNIGHT -> teamColor == ChessGame.TeamColor.WHITE ? "N" : "n";
            case ROOK -> teamColor == ChessGame.TeamColor.WHITE ? "R" : "r";
            case PAWN -> teamColor == ChessGame.TeamColor.WHITE ? "P" : "p";
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return teamColor == that.teamColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, pieceType);
    }
}
