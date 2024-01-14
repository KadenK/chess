package chess;

import static chess.ChessGame.TeamColor;
import static chess.ChessPiece.PieceType;


/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private final int boardRowCount = 8;
    private final int boardColumnCount = 8;
    private ChessPiece[][] boardLayout;

    public ChessBoard() {
        boardLayout = new ChessPiece[boardColumnCount][boardRowCount];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        boardLayout[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        if (boardLayout[position.getRow()][position.getColumn()] != null) {
            return boardLayout[position.getRow()][position.getColumn()];
        }
        else {
            return null;
        }
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // Make a new, empty board
        boardLayout = new ChessPiece[boardColumnCount][boardRowCount];

        //Start creating the white pieces
        boardLayout[0][0] = new ChessPiece(TeamColor.WHITE, PieceType.ROOK);
        boardLayout[1][0] = new ChessPiece(TeamColor.WHITE, PieceType.KNIGHT);
        boardLayout[2][0] = new ChessPiece(TeamColor.WHITE, PieceType.BISHOP);
        boardLayout[3][0] = new ChessPiece(TeamColor.WHITE, PieceType.QUEEN);
        boardLayout[4][0] = new ChessPiece(TeamColor.WHITE, PieceType.KING);
        boardLayout[5][0] = new ChessPiece(TeamColor.WHITE, PieceType.BISHOP);
        boardLayout[6][0] = new ChessPiece(TeamColor.WHITE, PieceType.KNIGHT);
        boardLayout[7][0] = new ChessPiece(TeamColor.WHITE, PieceType.ROOK);
        for (int i = 0; i < boardColumnCount; i++) {
            boardLayout[i][1] = new ChessPiece(TeamColor.WHITE, PieceType.PAWN);
        }

        //Start creating the black pieces
        boardLayout[0][7] = new ChessPiece(TeamColor.BLACK, PieceType.ROOK);
        boardLayout[1][7] = new ChessPiece(TeamColor.BLACK, PieceType.KNIGHT);
        boardLayout[2][7] = new ChessPiece(TeamColor.BLACK, PieceType.BISHOP);
        boardLayout[3][7] = new ChessPiece(TeamColor.BLACK, PieceType.QUEEN);
        boardLayout[4][7] = new ChessPiece(TeamColor.BLACK, PieceType.KING);
        boardLayout[5][7] = new ChessPiece(TeamColor.BLACK, PieceType.BISHOP);
        boardLayout[6][7] = new ChessPiece(TeamColor.BLACK, PieceType.KNIGHT);
        boardLayout[7][7] = new ChessPiece(TeamColor.BLACK, PieceType.ROOK);
        for (int i = 0; i < boardColumnCount; i++) {
            boardLayout[i][6] = new ChessPiece(TeamColor.BLACK, PieceType.PAWN);
        }

    }
}
