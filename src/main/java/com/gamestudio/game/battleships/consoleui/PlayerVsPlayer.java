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
    private Scanner reader;

    /**
     * Player vs Player game mode.
     */
    public PlayerVsPlayer() {
        reader = new Scanner(System.in);
        consoleUI = new PrintBoard();

        player1Board = new Board(10, 10);
        player2Board = new Board(10, 10);


        player1Board.setUpBoardRandom();
        player2Board.setUpBoardRandom();

        player1Controler = new GameController(player1Board);
        player2Controler = new GameController(player2Board);

        player1Controler.isGameSetUp(player1Board.getShipSizes());
        player2Controler.isGameSetUp(player2Board.getShipSizes());


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
        int coors[];

        int shots = 0;
        while (player1Controler.getGameState() != GameState.WON || player2Controler.getGameState() != GameState.WON) {
            System.out.println("-------ROUND " + shots + "-------");

            System.out.println("-------PLAYER 1-------");
            consoleUI.printPlayBoard(player1Board, false);
            coors = getCoordinationFromUser();
            player1.shoot(player1Board.getPlayBoard(), coors[0], coors[1]);


            System.out.println("-------PLAYER 2-------");
            consoleUI.printPlayBoard(player2Board, false);
            coors = getCoordinationFromUser();
            player2.shoot(player2Board.getPlayBoard(), coors[0], coors[1]);

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

    /**
     *  Get coordination input from user.
     *
     * @return
     */
    private int[] getCoordinationFromUser() {
        System.out.println("Enter row character: ");
        int row = reader.next().charAt(0) - 'A';
        System.out.println("Enter col number: ");
        int col = reader.nextInt();
        return new int[]{row, col};
    }
}
