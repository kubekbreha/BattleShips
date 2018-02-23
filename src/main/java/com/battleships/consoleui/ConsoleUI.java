package com.battleships.consoleui;

import com.battleships.core.History;
import com.battleships.core.board.Board;
import com.battleships.core.board.Ship;
import com.battleships.core.board.Util;
import com.battleships.core.player.Computer;
import com.battleships.core.player.Human;
import com.battleships.core.player.Player;

import java.util.*;

/**
 * Created by Kubo Brehuv with <3 (19.2.2018)
 */
public class ConsoleUI {

    private Random rand;
    private Board board;
    private Scanner reader;
    private Player player;
    private ArrayList<Ship> ships;
    private History history;


    public ConsoleUI() {
        this.board = new Board(10, 10);
        this.rand = new Random();
        this.reader = new Scanner(System.in);
        this.player = new Computer();
        this.ships = new ArrayList<>();
        this.history = new History();
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
            printPlayBoard();
            ships.add(ship);
            shipNumber++;
        }
    }


    /**
     * Put ships into playing board randomly.
     */
    public void setUpBoardRandom() {
        int[] shipSize = {2, 4, 2, 3, 1, 3, 2, 4};
        int shipNumber = 0;
        int shipsCount = shipSize.length;
        char orientation = 'H';

        while (shipsCount != 0) {
            Ship ship = new Ship(shipSize[shipNumber]);

            //try to place ship while true
            while (!ship.placeShip(board.getPlayBoard(), rand.nextInt(10), rand.nextInt(10),
                    board.getBoardRows(), board.getBoardCols(), orientation)) {
            }
            if (shipsCount % 2 == 0) {
                orientation = 'V';
            } else {
                orientation = 'H';
            }

            shipsCount--;
            ships.add(ship);
            shipNumber++;
        }
        printPlayBoard();
    }


    /**
     * Ask user if he want to step back.
     *
     * @return true if he wants.
     */
    private boolean askForUndo() {
        System.out.println("Step back ? (Y/N)");
        if (reader.next().charAt(0) == 'Y') {

            board.setPlayBoard(history.getLast());
            history.removeLast();

            System.out.println("Deleting history size: " + history.getHistorySize());
            return true;
        }
        return false;
    }


    /**
     * Start playing a game.
     */
    public void startGame() throws InterruptedException {
        int shots = 0;
        while (!isGameVon()) {
            this.history.addToHistory(board.getPlayBoard());

            System.out.println("-------ROUND " + shots + "-------");
            printPlayBoard();

            if (history.getHistorySize() != 0 && shots != 0) {
                while (askForUndo()) {
                    printPlayBoard();
                }
            }

            /*System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();
            player.shoot(board.getPlayBoard(), row, col);*/
            player.shootRand(board.getPlayBoard(), 'H');
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

    /**
     * Print playBoard to console.
     */
    public void printPlayBoard() {
        System.out.print(Util.ANSI_CYAN + "* " + Util.ANSI_RESET);
        for (int i = 0; i < 10; i++) {
            System.out.print(Util.ANSI_CYAN + i + " " + Util.ANSI_RESET);
        }
        System.out.println();

        for (int i = 0; i < board.getBoardCols(); i++) {
            System.out.print(Util.ANSI_CYAN + (char) (i + 65) + " " + Util.ANSI_RESET);
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getPlayBoard()[i][j] == 3) {
                    System.out.print(Util.ANSI_RED + board.getPlayBoard()[i][j] + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j] == 1) {
                    System.out.print(Util.ANSI_YELLOW + board.getPlayBoard()[i][j] + " " + Util.ANSI_RESET);
                } else {
                    System.out.print(board.getPlayBoard()[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    /**
     * Create double array from 2D board.
     *
     * @return daouble[]
     */
    public List getDaoubleArrayOfBoardWithShips() {
        int counter = 0;
        int oneFieldNumber = 20;

        List<Double> listOfOne = new ArrayList<>();

        List<Double> listBoardValues = new ArrayList<>();
        for (int i = 0; i < board.getBoardRows(); i++) {
            for (int j = 0; j < board.getBoardCols(); j++) {
                double value = board.getPlayBoard()[i][j];
                listBoardValues.add(value);
            }
        }


        for (int lists = 0; lists < 100; lists++) {
            for (int i = 0; i < board.getBoardRows(); i++) {
                for (int j = 0; j < board.getBoardCols(); j++) {
                    double value = board.getPlayBoard()[i][j];
                    listOfOne.add(value);
                }
            }
            oneFieldNumber++;
            listOfOne.add((double) oneFieldNumber);


            if (listBoardValues.get(counter) == 0) {
                //6 means ship is not on field
                listOfOne.add(6.0);
            } else if (listBoardValues.get(counter) == 3) {
                //8 ship is on field
                listOfOne.add(8.0);
            }
            counter++;
        }

        return listOfOne;
    }
}