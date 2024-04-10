package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import client.ServerFacade;
import model.GameData;
import webSocketMessages.serverMessages.Error;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.MakeMove;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.System.out;
import static ui.EscapeSequences.*;

public class GameplayREPL {

    ServerFacade server;
    public static BoardPrinter boardPrinter;
    ChessGame game;
    int gameID;
    public static ChessGame.TeamColor color;

    boolean notificationPending = false;

    public GameplayREPL(ServerFacade server, GameData gameData, ChessGame.TeamColor color) {
        this.server = server;
        this.game = gameData.game();
        this.gameID = gameData.gameID();
        this.color = color;

        this.boardPrinter = new BoardPrinter(game);
    }

    public void run() {
        boolean inGame = true;
        out.print(RESET_TEXT_COLOR + RESET_BG_COLOR);
        boardPrinter.printBoard(color, null);
        while (inGame) {
            String[] input = getUserInput();
            switch (input[0]) {
                case "help":
                    printHelpMenu();
                    break;
                case "redraw":
                    redraw();
                    break;
                case "leave":
                    inGame = false;
                    server.leave(gameID);
                    break;
                case "move":
                    if (input.length == 3 && input[1].matches("[a-h][1-8]") && input[2].matches("[a-h][1-8]")) {
                        ChessPosition from = new ChessPosition(input[1].charAt(1) - '0', input[1].charAt(0) - ('a'-1));
                        ChessPosition to = new ChessPosition(input[2].charAt(1) - '0',input[2].charAt(0) - ('a'-1));
                        server.makeMove(gameID, new ChessMove(from, to, null));
                    }
                    else {
                        out.println("Please provide a to and from coordinate (ex: 'c3 d5')");
                        printMakeMove();
                    }
                    break;
                case "resign":
                    out.println("Are you sure you want to forfeit? (yes/no)");
                    String[] confirmation = getUserInput();
                    if (confirmation.length == 1 && confirmation[0].equalsIgnoreCase("yes")) {
                        server.resign(gameID);
                    }
                    else {
                        out.println("Resignation cancelled");
                    }
                    break;
                case "highlight":
                    if (input.length == 2 && input[1].matches("[a-h][1-8]")) {
                        ChessPosition position = new ChessPosition(input[1].charAt(1) - '0', input[1].charAt(0) - ('a'-1));
                        boardPrinter.printBoard(color, position);
                    }
                    else {
                        out.println("Please provide a coordinate (ex: 'c3')");
                        printHighlight();
                    }
                    break;
                default:
                    out.println("Command not recognized, please try again");
                    printHelpMenu();
                    break;
            }
        }

        PostloginREPL postloginREPL = new PostloginREPL(server);
        postloginREPL.run();
    }

    private String[] getUserInput() {
        String prompt = "IN-GAME";//color == null ? "OBSERVING" : color == ChessGame.TeamColor.WHITE ? "WHITE" : "BLACK";
        out.printf("\n[%s] >>> ", prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(" ");
    }

    private void printHelpMenu() {
        out.println("redraw - redraw the game board");
        out.println("leave - leave the current game");
        printMakeMove();
        out.println("resign - forfeit this game");
        printHighlight();
        out.println("help - show this menu");
    }

    private void printMakeMove() {
        out.println("move <from> <to> - make a move");
    }

    private void printHighlight() {
        out.println("highlight <coordinate> - highlight all legal moves for the given piece");
    }

    private void redraw() {
        boardPrinter.printBoard(color, null);
    }

    private void makeMove(ChessPosition from, ChessPosition to) {

    }

    private void confirmResign() {

    }


}
