package chess;

import java.util.Arrays;

import static chess.ChessGame.TeamColor;
import static chess.ChessPiece.PieceType;


/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] boardLayout;

    public ChessBoard() {
        boardLayout = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        //Subtract one to account for rows/columns starting at 1, rather than 0 like arrays.
        boardLayout[position.getColumn()-1][position.getRow()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        //Subtract one to account for rows/columns starting at 1, rather than 0 like arrays.
        return boardLayout[position.getColumn()-1][position.getRow()-1];
    }

    /**
     * Checks to see if the position given is occupied
     *
     * @param position the position to check on the board
     * @return boolean stating if the position is valid
     */
    public boolean isOccupied(ChessPosition position) {

        return getPiece(position) != null;
    }

    /**
     * Returns the Team Color of the piece at the given square, if no piece present, returns null
     *
     * @param position the position to check on the board
     * @return ChessGame.TeamColor the color of the piece on the square
     */
    public ChessGame.TeamColor getTeamOfSquare(ChessPosition position) {
        ChessPiece pieceAtLocation = getPiece(position);
        return pieceAtLocation != null ? pieceAtLocation.getTeamColor() : null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // Make a new, empty board
        boardLayout = new ChessPiece[8][8];

        //Start creating the white pieces
        addPiece(new ChessPosition(1,1), new ChessPiece(TeamColor.WHITE, PieceType.ROOK));
        addPiece(new ChessPosition(1,2), new ChessPiece(TeamColor.WHITE, PieceType.KNIGHT));
        addPiece(new ChessPosition(1,3), new ChessPiece(TeamColor.WHITE, PieceType.BISHOP));
        addPiece(new ChessPosition(1,4), new ChessPiece(TeamColor.WHITE, PieceType.QUEEN));
        addPiece(new ChessPosition(1,5), new ChessPiece(TeamColor.WHITE, PieceType.KING));
        addPiece(new ChessPosition(1,6), new ChessPiece(TeamColor.WHITE, PieceType.BISHOP));
        addPiece(new ChessPosition(1,7), new ChessPiece(TeamColor.WHITE, PieceType.KNIGHT));
        addPiece(new ChessPosition(1,8), new ChessPiece(TeamColor.WHITE, PieceType.ROOK));
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(2, i), new ChessPiece(TeamColor.WHITE, PieceType.PAWN));
        }

        //Start creating the black pieces
        addPiece(new ChessPosition(8,1), new ChessPiece(TeamColor.BLACK, PieceType.ROOK));
        addPiece(new ChessPosition(8,2), new ChessPiece(TeamColor.BLACK, PieceType.KNIGHT));
        addPiece(new ChessPosition(8,3), new ChessPiece(TeamColor.BLACK, PieceType.BISHOP));
        addPiece(new ChessPosition(8,4), new ChessPiece(TeamColor.BLACK, PieceType.QUEEN));
        addPiece(new ChessPosition(8,5), new ChessPiece(TeamColor.BLACK, PieceType.KING));
        addPiece(new ChessPosition(8,6), new ChessPiece(TeamColor.BLACK, PieceType.BISHOP));
        addPiece(new ChessPosition(8,7), new ChessPiece(TeamColor.BLACK, PieceType.KNIGHT));
        addPiece(new ChessPosition(8,8), new ChessPiece(TeamColor.BLACK, PieceType.ROOK));
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(7,i), new ChessPiece(TeamColor.BLACK, PieceType.PAWN));
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Arrays.deepEquals(boardLayout, that.boardLayout);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardLayout);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x <= 7; x++) {
                output.append("|").append(boardLayout[x][y] != null ? boardLayout[x][y].toString() : " ");
            }
            output.append("|\n");
        }
        return output.toString();
    }
}
