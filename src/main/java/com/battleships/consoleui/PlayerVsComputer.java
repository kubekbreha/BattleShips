package com.battleships.consoleui;

import com.battleships.core.board.Board;
import com.battleships.core.game.GameController;
import com.battleships.core.game.GameState;
import com.battleships.core.history.AIExpertHistory;
import com.battleships.core.history.SinglePlayerHistory;
import com.battleships.core.player.*;

import java.util.Scanner;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */

public class PlayerVsComputer {

    private Board playerBoard;
    private Board computerBoard;
    private SinglePlayerHistory playerHistory;
    private AIExpertHistory computerHistory;
    private GameController playerControler;
    private GameController computerControler;
    private ConsoleUI consoleUI;
    private Player player;
    private Player computer;
    private int computerLevel;


    public PlayerVsComputer() {
        consoleUI = new ConsoleUI();

        playerBoard = new Board(10, 10);
        computerBoard = new Board(10, 10);

        playerControler = new GameController();
        computerControler = new GameController();

        playerBoard.setUpBoardRandom(playerControler);
        computerBoard.setUpBoardRandom(computerControler);

        playerHistory = new SinglePlayerHistory();
        computerHistory = new AIExpertHistory();


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
                break;

            case 2:
                ((Computer) computer).setAiState(new ComputerMedium());
                break;

            case 3:
                ((Computer) computer).setAiState(new ComputerHard());
                break;

            case 4:
                ((Computer) computer).setAiState(new ComputerExpert());
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


    //TODO: fix computer history.

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

            if (playerHistory.getHistorySize() != 0 && shots != 0 && (computerLevel == 4 || computerLevel == 1)) {
                while (askForUndo()) {
                    shots--;
                    if(shots==0) break;
                }
            }


            playerHistory.addToHistory(playerBoard.getPlayBoard());
            computerHistory.addToHistory(computerBoard.getPlayBoard(), ((Computer) computer).getNotTileHistory());


            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();


            player.shoot(playerBoard.getPlayBoard(), row, col);
            computer.shootAI(computerBoard.getPlayBoard());

            shots++;

            playerControler.isGameVon(playerBoard.getShips(), playerBoard);
            computerControler.isGameVon(playerBoard.getShips(), playerBoard);
        }
        reader.close();
        if (computerControler.getGameState() == GameState.WON) {
            System.out.println("Try next time.");
        } else if (playerControler.getGameState() == GameState.WON) {
            System.out.println("Congratulations you won with only " + shots + " shots");
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

            playerBoard.setPlayBoard(playerHistory.getLast());
            playerHistory.removeLast();

            computerBoard.setPlayBoard(computerHistory.getLastTile());
            ((Computer) computer).setNotTileHistory(computerHistory.getLastProbability());
            computerHistory.removeLast();

            System.out.println("Player history size : " + playerHistory.getHistorySize());
            System.out.println("Computer history size : " + computerHistory.getHistorySize());

            consoleUI.printPlayBoard(playerBoard);
            consoleUI.printPlayBoard(computerBoard);

            return true;
        }
        return false;
    }


}
