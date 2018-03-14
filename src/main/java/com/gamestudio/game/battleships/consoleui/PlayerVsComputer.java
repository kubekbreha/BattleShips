package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.entity.Comment;
import com.gamestudio.entity.Score;
import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Hint;
import com.gamestudio.game.battleships.core.board.TileState;
import com.gamestudio.game.battleships.core.util.DatabaseUtil;
import com.gamestudio.game.battleships.core.util.Util;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.history.AIExpertHistory;
import com.gamestudio.game.battleships.core.history.SinglePlayerHistory;
import com.gamestudio.game.battleships.core.player.*;
import com.gamestudio.service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gamestudio.game.battleships.core.board.Board.GAME_NAME;


/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class PlayerVsComputer implements GameMode {

    private Board playerBoard;
    private Board computerBoard;

    private SinglePlayerHistory playerHistory;
    private AIExpertHistory computerHistoryHard;
    private SinglePlayerHistory computerHistoryEasy;

    private GameController playerControler;
    private GameController computerControler;
    private PrintBoard consoleUI;
    private Player player;
    private Player computer;
    private Hint hint;
    private int computerLevel;

    private ScoreService scoreService = new ScoreServiceJDBC();
    private CommentService commentService = new CommentServiceJDBC();
    private BufferedReader bufferedReader;

    final Pattern YESNOPATTERN = Pattern.compile("[Y|N]");

    /**
     * Player vs Computer play mode.
     */
    public PlayerVsComputer() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        consoleUI = new PrintBoard();


        setupBoard(playerControler, computerControler);

        playerHistory = new SinglePlayerHistory();

        player = new Human();
        computer = new Computer();

        System.out.println("Please choose difficulty of computer:");
        System.out.println("1. Begginer (undo)");
        System.out.println("2. Medium (no undo)");
        System.out.println("3. Hard (no undo)");
        System.out.println("4. Expert (undo)");
        Scanner reader = new Scanner(System.in);
        computerLevel = reader.nextInt();
        switch (computerLevel) {
            case 1:
                ((Computer) computer).setAiState(new ComputerBegginer());
                computerHistoryEasy = new SinglePlayerHistory();
                break;

            case 2:
                ((Computer) computer).setAiState(new ComputerMedium());
                break;

            case 3:
                ((Computer) computer).setAiState(new ComputerHard());
                break;

            case 4:
                ((Computer) computer).setAiState(new ComputerExpert());
                computerHistoryHard = new AIExpertHistory();
                break;

            default:
                ((Computer) computer).setAiState(new ComputerExpert());
                break;
        }

        if (computerControler.getGameState() == GameState.SETTEDUP && playerControler.getGameState() == GameState.SETTEDUP) {
            startGame();
        } else {
            System.out.println("Error in setting up map.");
        }
    }

    private void setupBoard(GameController playerC, GameController computerC) {

        System.out.println("Setup map.");
        System.out.println("1. Manually");
        System.out.println("2. Randomly");

        Scanner reader = new Scanner(System.in);
        int setupMode = reader.nextInt();
        switch (setupMode) {
            case 1:
                playerBoard = new Board(10, 10);
                playerBoard.setUpBoard();
                break;

            case 2:
                playerBoard = new Board(10, 10);
                playerBoard.setUpBoardRandom();
                break;
        }
        computerBoard = new Board(10, 10);
        computerBoard.setUpBoardRandom();

        playerControler = new GameController(playerBoard);
        computerControler = new GameController(computerBoard);

        playerControler.isGameSetUp(playerBoard.getShipSize());
        computerControler.isGameSetUp(computerBoard.getShipSize());

        hint = new Hint(computerBoard);
    }


    /**
     * Start playing a game.
     */
    private void startGame() {
        Scanner reader = new Scanner(System.in);

        int shots = 0;
        while (computerControler.getGameState() != GameState.WON || playerControler.getGameState() != GameState.WON) {
            System.out.println("-------ROUND " + shots + "-------");
            System.out.println("-------PLAYER--------");
            consoleUI.printPlayBoard(playerBoard, true);
            System.out.println("-------COMPUTER------");
            consoleUI.printPlayBoard(computerBoard, false);

            hint.findHint();

            if (playerHistory.getHistorySize() != 0 && shots != 0 && (computerLevel == 4 || computerLevel == 1)) {
                while (askForUndo()) {
                    shots--;
                    if (shots == 0) break;
                }
            }


            playerHistory.addToHistory(computerBoard.getPlayBoard());
            playerHistory.addToProbabilityHistory(hint.getHintBoard());

            if (computerLevel == 4) {
                computerHistoryHard.addToHistory(playerBoard.getPlayBoard(), ((Computer) computer).getNotTileHistory());
            } else if (computerLevel == 1) {
                computerHistoryEasy.addToHistory(playerBoard.getPlayBoard());
            }

            askForHint();

            int coor[] = askShootCoordinations(reader, computerBoard);

            player.shoot(computerBoard.getPlayBoard(), coor[0], coor[1]);
            hint.moveExecuted(computerBoard.getPlayBoard()[coor[0]][coor[1]].getTileState(), coor[0], coor[1]);
            computer.shootAI(playerBoard.getPlayBoard());

            shots++;

            if (playerControler.isGameWon(computerBoard.getShips())) {
                break;
            }
            if (computerControler.isGameWon(playerBoard.getShips())) {
                break;
            }
        }
        reader.close();

        if (computerControler.getGameState() == GameState.WON) {
            System.out.println("Try next time.");
            consoleUI.printPlayBoard(playerBoard, true);
            System.out.println();
            consoleUI.printPlayBoard(computerBoard, false);
        } else if (playerControler.getGameState() == GameState.WON) {
            System.out.println("Congratulations you won with only " + shots + " shots");

            DatabaseUtil.addScore(shots);

            System.out.println("Do you wan to add comment ? (Y/N)");
            String line = Util.readLine(bufferedReader);
            Matcher m = YESNOPATTERN.matcher(line);
            if (m.matches()) {
                String comment = Util.readLine(bufferedReader);
                DatabaseUtil.addComment(comment);
            } else {
                System.out.println("Here are comments.");
                DatabaseUtil.printComments();
            }


        }
    }

    /**
     * Ask coordinations where to shoot.
     *
     * @param reader
     * @param board
     * @return int array consist of row and col.
     */
    private int[] askShootCoordinations(Scanner reader, Board board) {
        System.out.println("Enter row char: ");
        int row = reader.next().charAt(0) - 'A';
        System.out.println("Enter col number : ");
        int col = reader.nextInt();
        ;

        if (board.getPlayBoard()[row][col].getTileState() == TileState.HITTED) {
            askShootCoordinations(reader, board);
        }

        int ret[] = {row, col};
        return ret;
    }


    /**
     * Ask user if he want to step back.
     *
     * @return true if he wants.
     */
    private boolean askForUndo() {
        System.out.println("Step back ? (Y/N)");

        String line = Util.readLine(bufferedReader);
        Matcher m = YESNOPATTERN.matcher(line);
        if (m.matches() && line.charAt(0) == 'Y') {
            if (computerLevel == 4) {
                playerBoard.setPlayBoard(playerHistory.getLast());
                playerHistory.removeLast();

                hint.setHintBoard(playerHistory.getLastProbability());
                playerHistory.removeLastProbability();

                computerBoard.setPlayBoard(computerHistoryHard.getLastTile());
                ((Computer) computer).setNotTileHistory(computerHistoryHard.getLastProbability());
                computerHistoryHard.removeLast();

                System.out.println("Player history size : " + playerHistory.getHistorySize());
                System.out.println("Computer history size : " + computerHistoryHard.getHistorySize());

                System.out.println("-------PLAYER--------");
                consoleUI.printPlayBoard(playerBoard, true);
                System.out.println("-------COMPUTER------");
                consoleUI.printPlayBoard(computerBoard, false);

                return true;
            } else if (computerLevel == 1) {
                playerBoard.setPlayBoard(playerHistory.getLast());
                playerHistory.removeLast();

                computerBoard.setPlayBoard(computerHistoryEasy.getLast());
                computerHistoryEasy.removeLast();

                System.out.println("Player history size : " + playerHistory.getHistorySize());
                System.out.println("Computer history size : " + computerHistoryEasy.getHistorySize());

                System.out.println("-------PLAYER--------");
                consoleUI.printPlayBoard(playerBoard, true);
                System.out.println("-------COMPUTER------");
                consoleUI.printPlayBoard(computerBoard, false);

                return true;
            }
        }
        return false;
    }

    /**
     * Ask user if he want to step back.
     *
     * @return true if he wants.
     */
    private void askForHint() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Want hint ? (Y/N)");
        if (reader.next().charAt(0) == 'Y') {
            //hint.printHintBoard();
            System.out.println("Row: " + (char) (hint.getHintRow() + 'A') + "  Col: " + hint.getHintCol());
        }
    }
}
