package com.battleships.core.game;

import com.battleships.consoleui.ConsoleUI;
import com.battleships.core.board.Board;
import com.battleships.core.history.AIExpertHistory;
import com.battleships.core.history.SinglePlayerHistory;
import com.battleships.core.player.Computer;
import com.battleships.core.player.ComputerExpert;
import com.battleships.core.player.Human;
import com.battleships.core.player.Player;

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


    public PlayerVsComputer() throws InterruptedException {
        consoleUI = new ConsoleUI();

        playerBoard = new Board(10, 10);
        computerBoard = new Board(10, 10);

        playerControler = new GameController();
        computerControler = new GameController();

        playerBoard.setUpBoardRandom(playerControler);
        computerBoard.setUpBoardRandom(computerControler);

        playerHistory = new SinglePlayerHistory();

        //TODO: Choose AIlevel.

        computerHistory = new AIExpertHistory();

        player = new Human();
        computer = new Computer();
        ((Computer) computer).setAiState(new ComputerExpert());



        if (computerControler.getGameState() == GameState.SETTEDUP && playerControler.getGameState() == GameState.SETTEDUP) {
            startGame();
        } else {
            System.out.println("Error in setting up map.");
        }
    }


    /**
     * Start playing a game.
     */
    private void startGame() throws InterruptedException {
        Scanner reader = new Scanner(System.in);

        int shots = 0;
        while (computerControler.getGameState() != GameState.WON || playerControler.getGameState() != GameState.WON) {
            playerHistory.addToHistory(playerBoard.getPlayBoard());
            //computerHistory.addToHistory(computerBoard.getPlayBoard());

            System.out.println("-------ROUND " + shots + "-------");
            System.out.println("-------PLAYER--------");
            consoleUI.printPlayBoard(playerBoard);
            System.out.println("-------COMPUTER------");
            consoleUI.printPlayBoard(computerBoard);

            if (playerHistory.getHistorySize() != 0 && shots != 0) {
                while (askForUndo()) {
                    consoleUI.printPlayBoard(playerBoard);
                    consoleUI.printPlayBoard(computerBoard);
                }
            }

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

            computerBoard.setPlayBoard(playerHistory.getLast());
            playerHistory.removeLast();

            System.out.println("Player history size : " + playerHistory.getHistorySize());
            System.out.println("Computer history size : " + computerHistory.getHistorySize());
            return true;
        }
        return false;
    }


}
