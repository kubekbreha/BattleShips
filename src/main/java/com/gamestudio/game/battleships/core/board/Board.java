package com.gamestudio.game.battleships.core.board;

import com.gamestudio.game.battleships.consoleui.ConsoleUI;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Board {

    public static final String GAME_NAME = "battleships-brehuv";

    private Tile[][] playBoard;
    private int boardRows;
    private int boardCols;
    private ArrayList<Ship> ships;

    /**
     * Basic constructor where you set size of playTable.
     * Fill board with water tiles.
     *
     * @param cols
     * @param rows
     */
    public Board(int cols, int rows) {
        ships = new ArrayList<>();
        this.boardCols = cols;
        this.boardRows = rows;

        playBoard = new Tile[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                playBoard[i][j] = new Tile(TileState.WATER);
            }
        }
    }

    /**
     * Get list of ships.
     *
     * @return list of ships.
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Get playBoard.
     *
     * @return playBoard 2D array.
     */
    public Tile[][] getPlayBoard() {
        return playBoard;
    }

    /**
     * Set play board.
     *
     * @param board which will be set.
     */
    public void setPlayBoard(Tile[][] board) {
        this.playBoard = board;
    }

    /**
     * @return size of board rows.
     */
    public int getBoardRows() {
        return boardRows;
    }

    /**
     * @return size of board cols.
     */
    public int getBoardCols() {
        return boardCols;
    }

    /**
     * Set size of board rows.
     *
     * @param boardRows
     */
    public void setBoardRows(int boardRows) {
        this.boardRows = boardRows;
    }

    /**
     * Set size of board cols.
     */
    public void setBoardCols(int boardCols) {
        this.boardCols = boardCols;
    }


    /**
     * Create double array from 2D board.
     *
     * @return double[]
     */
    public List getDaoubleArrayOfBoardWithShips() {
        double value = 0;
        boolean shipPresent = false;

        List<Double> valuesList = new ArrayList<>();

        for (int i = 0; i < getBoardRows(); i++) {
            for (int j = 0; j < getBoardCols(); j++) {
                switch (playBoard[i][j].getTileState()) {
                    case SHIP:
                        shipPresent = true;
                        value = 2.0;
                        break;

                    case HITTED:
                        value = 1.0;
                        break;

                    case WATER:
                        value = 0.0;
                        break;

                    case MISSED:
                        value = 1.0;
                        break;

                    default:
                        value = 0.0;
                        break;
                }
                valuesList.add(value);
            }
            if (shipPresent) {
                valuesList.add(1.0);
            } else {
                valuesList.add(0.0);
            }
            shipPresent = false;
            for (int x = 0; x < valuesList.size(); x++) {
                System.out.print(valuesList.get(x) + " ");
            }
            System.out.print(valuesList.size());
            System.out.println();
        }
        return valuesList;
    }


    /**
     * Put ships into playing board randomly.
     */
    public void setUpBoardRandom(GameController gameController) {
        Random rand = new Random();
        final int[] shipSize = {2, 2, 3, 4};

        int shipNumber = 0;
        int shipsCount = shipSize.length;
        char orientation = 'H';

        while (shipsCount != 0) {
            Ship ship = new Ship(shipSize[shipNumber]);

            //try to place ship while true
            while (!ship.placeShip(getPlayBoard(), rand.nextInt(boardRows), rand.nextInt(boardCols),
                    getBoardRows(), getBoardCols(), orientation)) {
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
        gameController.isGameSetUp(shipSize, this);
    }


    /**
     * Put ships into playing board manually.
     */
    public void setUpBoard(GameController gameController) {
        Scanner reader = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI();
        final int[] shipSize = {2, 2, 3, 4};


        int shipNumber = 0;
        int shipsCount = shipSize.length;

        while (shipsCount != 0) {
            System.out.println("----SHIP " + shipNumber + "-(" + shipSize[shipNumber] + ")---");
            Ship ship = new Ship(shipSize[shipNumber]);
            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();
            System.out.println("Orientation (V/H): ");
            char orientation = reader.next().charAt(0);

            if (orientation == 'V') {
                ship.placeShip(getPlayBoard(), row, col, getBoardRows(), getBoardCols(), 'V');
            } else if (orientation == 'H') {
                ship.placeShip(getPlayBoard(), row, col, getBoardRows(), getBoardCols(), 'H');
            }

            shipsCount--;

            consoleUI.printPlayBoard(this);

            ships.add(ship);
            shipNumber++;
        }
        gameController.isGameSetUp(shipSize, this);
        if (gameController.getGameState() == GameState.NOTSETTEDUP) {
            setUpBoard(gameController);
        }
    }
}
