package com.battleships.consoleui;

import com.battleships.core.board.Board;
import com.battleships.core.board.Hint;
import com.battleships.core.game.GameController;
import com.battleships.core.game.GameState;
import com.battleships.core.history.AIExpertHistory;
import com.battleships.core.history.SinglePlayerHistory;
import com.battleships.core.player.*;

import java.util.Scanner;

public class PlayerVsPlayer {

    private Board player1Board;
    private Board player2Board;
    private GameController player1Controler;
    private GameController player2Controler;
    private ConsoleUI consoleUI;
    private Player player1;
    private Player player2;


    public PlayerVsPlayer() {
        consoleUI = new ConsoleUI();

        player1Board = new Board(10, 10);
        player2Board = new Board(10, 10);

        player1Controler = new GameController();
        player2Controler = new GameController();

        player1Board.setUpBoardRandom(player1Controler);
        player2Board.setUpBoardRandom(player2Controler);

        player1 = new Human();
        player2 = new Human();

        if (player1Controler.getGameState() == GameState.SETTEDUP && player2Controler.getGameState() == GameState.SETTEDUP) {
            startGame();
        } else {
            System.out.println("Error in setting up map.");
        }
    }


    /**
     * Start playing a game.
     */
    private void startGame() {
        Scanner reader = new Scanner(System.in);

        int shots = 0;
        while (player1Controler.getGameState() != GameState.WON || player2Controler.getGameState() != GameState.WON) {
            int row;
            int col;
            System.out.println("-------ROUND " + shots + "-------");
            System.out.println("-------PLAYER 1-------");
            consoleUI.printPlayBoard(player1Board);

            System.out.println("Enter row number: ");
            row = reader.nextInt();
            System.out.println("Enter col number: ");
            col = reader.nextInt();
            player1.shoot(player1Board.getPlayBoard(), row, col);


            System.out.println("-------PLAYER 2-------");
            consoleUI.printPlayBoard(player2Board);

            System.out.println("Enter row number: ");
            row = reader.nextInt();
            System.out.println("Enter col number: ");
            col = reader.nextInt();
            player2.shoot(player2Board.getPlayBoard(), row, col);

            shots++;

            player1Controler.isGameVon(player1Board.getShips(), player1Board);
            player2Controler.isGameVon(player2Board.getShips(), player2Board);
        }
        reader.close();
        if (player1Controler.getGameState() == GameState.WON) {
            System.out.println("Player 1 won in " + shots + " shoots.");
        } else if (player2Controler.getGameState() == GameState.WON) {
            System.out.println("Player 2 won in " + shots + " shoots.");
        }
    }
}
