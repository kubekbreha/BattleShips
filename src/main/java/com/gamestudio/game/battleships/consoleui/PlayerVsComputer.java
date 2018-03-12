package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.entity.Score;
import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Hint;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.history.AIExpertHistory;
import com.gamestudio.game.battleships.core.history.SinglePlayerHistory;
import com.gamestudio.game.battleships.core.player.*;
import com.gamestudio.service.ScoreException;
import com.gamestudio.service.ScoreService;
import com.gamestudio.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.Scanner;

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


    /**
     * Player vs Computer play mode.
     */
    public PlayerVsComputer() {
        consoleUI = new PrintBoard();

        playerControler = new GameController();
        computerControler = new GameController();

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

    //TODO: print playing user board ships.
    private void setupBoard(GameController playerC, GameController computerC) {

        System.out.println("Setup map.");
        System.out.println("1. Manually");
        System.out.println("2. Randomly");

        Scanner reader = new Scanner(System.in);
        int setupMode = reader.nextInt();
        switch (setupMode) {
            case 1:
                playerBoard = new Board(10, 10);
                playerBoard.setUpBoard(playerC);
                break;

            case 2:
                playerBoard = new Board(10, 10);
                playerBoard.setUpBoardRandom(playerC);
                break;
        }
        computerBoard = new Board(10, 10);
        computerBoard.setUpBoardRandom(computerC);

        hint = new Hint(playerBoard);
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
            consoleUI.printPlayBoard(playerBoard);
            System.out.println("-------COMPUTER------");
            consoleUI.printPlayBoard(computerBoard);

            hint.findHint();

            if (playerHistory.getHistorySize() != 0 && shots != 0 && (computerLevel == 4 || computerLevel == 1)) {
                while (askForUndo()) {
                    shots--;
                    if (shots == 0) break;
                }
            }


            playerHistory.addToHistory(playerBoard.getPlayBoard());
            if (computerLevel == 4) {
                computerHistoryHard.addToHistory(computerBoard.getPlayBoard(), ((Computer) computer).getNotTileHistory());
            } else if (computerLevel == 1) {
                computerHistoryEasy.addToHistory(playerBoard.getPlayBoard());
            }

            askForHint();

            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();


            player.shoot(playerBoard.getPlayBoard(), row, col);
            hint.moveExecuted(playerBoard.getPlayBoard()[row][col].getTileState(), row, col);
            computer.shootAI(computerBoard.getPlayBoard());

            shots++;

            if(playerControler.isGameWon(playerBoard.getShips(), playerBoard)){
                break;
            }
            if(computerControler.isGameWon(computerBoard.getShips(), computerBoard)){
                break;
            }
        }
        reader.close();

        if (computerControler.getGameState() == GameState.WON) {
            System.out.println("Try next time.");
            consoleUI.printPlayBoard(playerBoard);
            System.out.println();
            consoleUI.printPlayBoard(computerBoard);
        } else if (playerControler.getGameState() == GameState.WON) {
            System.out.println("Congratulations you won with only " + shots + " shots");
            try {
                scoreService.addScore(new Score(
                        GAME_NAME,
                        System.getProperty("user.name"),
                        shots,
                        new Date()
                ));
                System.out.println("Your score was added to database");
            } catch (ScoreException e) {
                System.err.println(e.getMessage());
            }

        }
    }


    /**
     * Ask user if he want to step back.
     *
     * @return true if he wants.
     */
    private boolean askForUndo() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Step back ? (Y/N)");
        if (reader.next().charAt(0) == 'Y') {
            if (computerLevel == 4) {
                playerBoard.setPlayBoard(playerHistory.getLast());
                playerHistory.removeLast();
                //TODO: fix history hint

                computerBoard.setPlayBoard(computerHistoryHard.getLastTile());
                ((Computer) computer).setNotTileHistory(computerHistoryHard.getLastProbability());
                computerHistoryHard.removeLast();

                System.out.println("Player history size : " + playerHistory.getHistorySize());
                System.out.println("Computer history size : " + computerHistoryHard.getHistorySize());

                consoleUI.printPlayBoard(playerBoard);
                consoleUI.printPlayBoard(computerBoard);

                return true;
            } else if (computerLevel == 1) {
                playerBoard.setPlayBoard(playerHistory.getLast());
                playerHistory.removeLast();

                computerBoard.setPlayBoard(computerHistoryEasy.getLast());
                computerHistoryEasy.removeLast();

                System.out.println("Player history size : " + playerHistory.getHistorySize());
                System.out.println("Computer history size : " + computerHistoryEasy.getHistorySize());

                consoleUI.printPlayBoard(playerBoard);
                consoleUI.printPlayBoard(computerBoard);

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
            System.out.println("Row: " + hint.getHintRow() + "  Col: " + hint.getHintCol());
        }
    }

}
