package com.battleships.core.board;

import com.battleships.consoleui.ConsoleUI;
import com.battleships.core.game.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Board {

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
        int counter = 0;
        int oneFieldNumber = 20;

        List<Double> listOfOne = new ArrayList<>();

        List<Double> listBoardValues = new ArrayList<>();
        for (int i = 0; i < getBoardRows(); i++) {
            for (int j = 0; j < getBoardCols(); j++) {
                getNumberFromBoard(i, j, listBoardValues);
            }
        }

        for (int lists = 0; lists < 100; lists++) {
            for (int i = 0; i < getBoardRows(); i++) {
                for (int j = 0; j < getBoardCols(); j++) {
                    getNumberFromBoard(i, j, listBoardValues);
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

    /**
     * Read number from tile in board.
     *
     * @param row of number in board.
     * @param col of number in board.
     * @param listBoardValues where number will be placed.
     */
    private void getNumberFromBoard(int row, int col, List listBoardValues) {
        double value = 0;
        switch (getPlayBoard()[row][col].getTileState()) {
            case SHIP:
                value = 2;
                break;

            case HITTED:
                value = 1;
                break;

            case WATER:
                value = 0;
                break;

            case MISSED:
                value = 1;
                break;
        }
        listBoardValues.add(value);
    }


    /**
     * Put ships into playing board randomly.
     */
    public void setUpBoardRandom(GameController gameController) {
        Random rand = new Random();
        int[] shipSize = {2, 3, 3, 4};
        int shipNumber = 0;
        int shipsCount = shipSize.length;
        char orientation = 'H';

        while (shipsCount != 0) {
            Ship ship = new Ship(shipSize[shipNumber]);

            //try to place ship while true
            while (!ship.placeShip(getPlayBoard(), rand.nextInt(10), rand.nextInt(10),
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
        gameController.isGameSetUp(shipSize,this);
    }


    /**
     * Put ships into playing board manually.
     */
    public void setUpBoard(GameController gameController) {
        Scanner reader = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI();

        int[] shipSize = {2, 3, 3, 4};
        int shipNumber = 0;
        int shipsCount = shipSize.length;

        while (shipsCount != 0) {
            System.out.println("----SHIP " + shipNumber + "-(" + shipSize[shipNumber] + ")---");
            Ship ship = new Ship(shipSize[shipNumber]);
            System.out.println("Enter row number: ");
            int row = reader.nextInt();
            System.out.println("Enter col number: ");
            int col = reader.nextInt();
            ship.placeShip(getPlayBoard(), row, col, getBoardRows(), getBoardCols(), 'V');
            shipsCount--;

            consoleUI.printPlayBoard(this);

            ships.add(ship);
            shipNumber++;
        }
        gameController.isGameSetUp(shipSize,this);
    }
}
