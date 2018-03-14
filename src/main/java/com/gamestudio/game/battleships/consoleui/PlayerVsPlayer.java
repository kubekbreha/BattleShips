package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.player.Human;
import com.gamestudio.game.battleships.core.player.Player;
import com.gamestudio.service.ScoreService;
import com.gamestudio.service.ScoreServiceJDBC;

import java.util.Scanner;


/**
 * Created by Kubo Brehuv with <3 (5.3.2018)
 */
public class PlayerVsPlayer implements GameMode{

    private Board player1Board;
    private Board player2Board;
    private GameController player1Controler;
    private GameController player2Controler;
    private PrintBoard consoleUI;
    private Player player1;
    private Player player2;
    private ScoreService scoreService = new ScoreServiceJDBC();

    /**
     * Player vs Player game mode.
     */
    public PlayerVsPlayer() {
        consoleUI = new PrintBoard();

        player1Board = new Board(10, 10);
        player2Board = new Board(10, 10);


        player1Board.setUpBoardRandom();
        player2Board.setUpBoardRandom();

        player1Controler = new GameController(player1Board);
        player2Controler = new GameController(player2Board);

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

            player1Controler.isGameWon(player1Board.getShips());
            player2Controler.isGameWon(player2Board.getShips());
        }
        reader.close();
        if (player1Controler.getGameState() == GameState.WON) {
            System.out.println("Player 1 won in " + shots + " shoots.");
        } else if (player2Controler.getGameState() == GameState.WON) {
            System.out.println("Player 2 won in " + shots + " shoots.");
        }
    }
}
