package com.battleships.core.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Board {

    private Tile[][] playBoard;
    private int boardRows;
    private int boardCols;


    /**
     * Basic constructor where you set size of playTable.
     * Fill board with water tiles.
     *
     * @param cols
     * @param rows
     */
    public Board(int cols, int rows) {
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

    private void getNumberFromBoard(int i, int j, List listBoardValues) {
        double value = 0;
        switch (getPlayBoard()[i][j].getTileState()) {
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


}
