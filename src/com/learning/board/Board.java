package com.learning.board;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Board {

    private int[][] playBoard;
    private int boardRows;
    private int boardCols;

    /**
     * Basic constructor where you set size of playTable.
     * Fill board with zeros.
     * @param cols
     * @param rows
     */
    public Board(int cols, int rows) {
        this.boardCols = cols;
        this.boardRows = rows;

        playBoard = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                playBoard[i][j] = 0;
            }
        }
    }


    /**
     * Print playBoard to console.
     */
    public void printPlayBoard() {
        for (int i = 0; i < boardCols; i++) {
            for (int j = 0; j < boardRows; j++) {
                System.out.print(playBoard[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * Get specific coordinate from board.
     *
     * @param col
     * @param row
     * @return value on specific coordinate.
     */
    public int getBoardCoordinate(int col, int row) {
        return playBoard[col][row];
    }

    /**
     * Get playBoard.
     * @return playBoard 2D array.
     */
    public int[][] getPlayBoard(){
        return playBoard;
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
}
