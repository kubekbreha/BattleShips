package com.gamestudio.game.battleships.core.board;

import com.gamestudio.game.battleships.consoleui.PrintBoard;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Board {

    public static final String GAME_NAME = "battleships-brehuv";
    private BufferedReader bufferedReader;
    final Pattern ROWCOLPATTERN = Pattern.compile("[0-9]");
    final Pattern ORIENTATIONPATTERN = Pattern.compile("[V|H]");


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
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
        PrintBoard consoleUI = new PrintBoard();
        final int[] shipSize = {2, 2, 3, 4};


        int shipNumber = 0;
        int shipsCount = shipSize.length;

        while (shipsCount != 0) {
            System.out.println("----SHIP " + shipNumber + "-(" + shipSize[shipNumber] + ")---");
            Ship ship = new Ship(shipSize[shipNumber]);

            placeShip(ship);

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

    /**
     * Place ship to board based on user input.
     *
     * @param ship which will be placed.
     */
    private void placeShip(Ship ship) {
        int shipTileCountBefore = getShipTilesCount();

        int row = getRowFromInput();
        int col = getColFromInput();
        char orientation = getShipPlacementOrientation();

        if (orientation == 'V') {
            ship.placeShip(getPlayBoard(), row, col, getBoardRows(), getBoardCols(), 'V');
            System.out.println("placed");
        } else if (orientation == 'H') {
            ship.placeShip(getPlayBoard(), row, col, getBoardRows(), getBoardCols(), 'H');
            System.out.println("placed");
        }
        int shipTileCountAfter = getShipTilesCount();

        if(shipTileCountBefore == shipTileCountAfter){
            System.out.println("You can not place ship like that. Try again!");
            placeShip(ship);
        }
    }


    /**
     * Get input from user for board row.
     *
     * @return integer value onecpyher.
     */
    private int getRowFromInput() {
        int row = 0;
        System.out.println("Pick row:");
        String line = Util.readLine(bufferedReader);
        Matcher m = ROWCOLPATTERN.matcher(line);
        if (m.matches()) {
            row = m.group(0).charAt(0) - '0';
        } else {
            System.out.println("Wrong input. Please try again.");
            getRowFromInput();
        }
        return row;
    }


    /**
     * Get input from user for board col.
     *
     * @return integer value onecpyher.
     */
    private int getColFromInput() {
        int col = 0;
        System.out.println("Pick col:");
        String line = Util.readLine(bufferedReader);
        Matcher m = ROWCOLPATTERN.matcher(line);
        if (m.matches()) {
            col = m.group(0).charAt(0) - '0';
        } else {
            System.out.println("Wrong input. Please try again.");
            getColFromInput();
        }
        return col;
    }


    /**
     * Get input from user for orientation of ship.
     *
     * @return char value.
     */
    private char getShipPlacementOrientation() {
        char orientation = ' ';
        System.out.println("Pick orientation (V/H):");
        String line = Util.readLine(bufferedReader);
        Matcher m = ORIENTATIONPATTERN.matcher(line);
        if (m.matches()) {
            orientation = m.group(0).charAt(0);
        } else {
            System.out.println("Wrong input. Please try again.");
            getShipPlacementOrientation();
        }
        return orientation;
    }

    /**
     * Count ship tiles in board.
     *
     * @return integer count of ship tiles.
     */
    private int getShipTilesCount() {
        int shipTileCount = 0;
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardRows; j++) {
                if (playBoard[i][j].getTileState() == TileState.SHIP) {
                    shipTileCount++;
                }
            }
        }
        return shipTileCount;
    }
}
