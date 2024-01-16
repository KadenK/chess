package chess;

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
            case KING -> getKingMoves(board, currPos);
            case QUEEN -> null;
            case BISHOP -> null;
            case KNIGHT -> null;
            case ROOK -> null;
            case PAWN -> null;
        };
    }

    /**
     * Checks to see if the position given is a valid square on the board
     *
     * @param position the position to check on the board
     * @return boolean stating if the position is valid
     */
    private boolean isValidSquare(ChessPosition position) {
        return (position.getRow() >= 1 && position.getRow() <= 8 &&
                position.getColumn() >= 1 && position.getColumn() <= 8);
    }

    /**
     * Checks to see if the position given is occupied
     *
     * @param board the current game board to check
     * @param position the position to check on the board
     * @return boolean stating if the position is valid
     */
    private boolean isOccupied(ChessBoard board, ChessPosition position) {
        return board.getPiece(position) != null;
    }

    /**
     * Checks to see if the position given is occupied by a team piece
     *
     * @param board the current game board to check
     * @param position the position to check on the board
     * @return boolean stating if the position is valid
     */
    private boolean isOccupiedByTeam(ChessBoard board, ChessPosition position) {
        ChessPiece pieceAtLocation = board.getPiece(position);
        return pieceAtLocation != null && pieceAtLocation.getTeamColor() == teamColor;
    }

    /**
     * Checks to see if the position given is occupied by a team piece
     *
     * @param board the current game board to check
     * @param position the position to check on the board
     * @return boolean stating if the position is valid
     */
    private boolean isOccupiedByEnemy(ChessBoard board, ChessPosition position) {
        ChessPiece pieceAtLocation = board.getPiece(position);
        return pieceAtLocation != null && pieceAtLocation.getTeamColor() != teamColor;
    }

    private HashSet<ChessMove> getKingMoves(ChessBoard board, ChessPosition currPos) {
        HashSet<ChessMove> moves = HashSet.newHashSet(8);
        int currX = currPos.getColumn();
        int currY = currPos.getRow();
        Integer[][] relativeMoves = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        for (Integer[] relativeMove : relativeMoves) {
            ChessPosition possiblePosition = new ChessPosition(currY + relativeMove[1], currX + relativeMove[0]);
            if (isValidSquare(possiblePosition) && !isOccupiedByTeam(board, possiblePosition)){
                moves.add(new ChessMove(currPos, possiblePosition, null));
            }
        }
        return moves;
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
