package com.learning;

import com.learning.board.Board;
import com.learning.board.Ship;
import com.learning.player.Human;
import com.learning.player.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kubo Brehuv with <3 (19.2.2018)
 */
public class ConsoleGUI {

    private Random rand;
    private Board board;
    private Scanner reader;
    private Player player;
    private ArrayList<Ship> ships;


    public ConsoleGUI() {
        this.board = new Board(10, 10);
        this.rand = new Random();
        this.reader = new Scanner(System.in);
        this.player = new Human();
        this.ships = new ArrayList<>();
    }


    /**
     * Put ships into playing board manually.
     */
    public void setUpBoard() {
        int[] shipSize = {2, 1};
        int shipNumber = 0;
        int shipsCount = shipSize.length;

        while (shipsCount != 0) {
            System.out.println("----SHIP " + shipNumber + "-(" + shipSize[shipNumber] + ")---");
            Ship ship = new Ship(shipSize[shipNumber]);
            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();
            ship.placeShip(board.getPlayBoard(), row, col, board.getBoardRows(), board.getBoardCols(), 'V');
            shipsCount--;
            board.printPlayBoard();
            ships.add(ship);
            shipNumber++;
        }
    }


    /**
     * Put ships into playing board randomly.
     */
    public void setUpBoardRandom() {
        int[] shipSize = {2, 2, 2, 2, 2};
        int shipNumber = 0;
        int shipsCount = shipSize.length;

        while (shipsCount != 0) {
            Ship ship = new Ship(shipSize[shipNumber]);
            while (!ship.placeShip(board.getPlayBoard(), rand.nextInt(10), rand.nextInt(10),
                    board.getBoardRows(), board.getBoardCols(), 'V')) {

            }

            shipsCount--;
            ships.add(ship);
            shipNumber++;
        }
        board.printPlayBoard();
    }


    /**
     * Start playing a game.
     */
    public void startGame() {
        int shots = 0;
        while (!isGameVon()) {
            System.out.println("-------ROUND " + shots + "-------");
            board.printPlayBoard();
            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();
            player.shoot(board.getPlayBoard(), row, col);
            shots++;
        }
        reader.close();
        System.out.println("Congratulations you won with only " + shots + " shots");
    }

    /**
     * Write to console positions where ship is placed.
     *
     * @param ship of which position will be written out.
     */
    private void writeOutShipPositions(Ship ship) {
        int[][] pos = ship.getShipPositions();
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(pos[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * Check if player won the game.
     *
     * @return true if game is won otherwise false.
     */
    private boolean isGameVon() {
        int sunkedShips = 0;
        for (Ship ship : ships) {
            if (ship.isShipSunk(board.getPlayBoard())) {
                sunkedShips++;
            }
        }
        return sunkedShips == ships.size();
    }
}
