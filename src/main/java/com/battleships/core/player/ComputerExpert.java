package com.battleships.core.player;

import com.battleships.core.board.Tile;
import com.battleships.core.board.TileState;
import com.battleships.core.board.Util;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class ComputerExpert implements AILevel {

    private int rowToShoot;
    private int colToShoot;

    private int[][] probabilityBoard;

    public ComputerExpert() {
        this.probabilityBoard = new int[][]{
                {8, 9, 10, 11, 12, 12, 11, 10, 9, 8},
                {9, 10, 11, 12, 13, 13, 12, 11, 10, 9},
                {10, 11, 12, 13, 14, 14, 13, 12, 11, 10},
                {11, 12, 13, 14, 15, 15, 14, 13, 12, 11},
                {12, 13, 14, 15, 16, 16, 15, 14, 13, 12},
                {12, 13, 14, 15, 16, 16, 15, 14, 13, 12},
                {11, 12, 13, 14, 15, 15, 14, 13, 12, 11},
                {10, 11, 12, 13, 14, 14, 13, 12, 11, 10},
                {9, 10, 11, 12, 13, 13, 12, 11, 10, 9},
                {8, 9, 10, 11, 12, 12, 11, 10, 9, 8},
        };
    }


    @Override
    public void play(Tile[][] board) {
        findBiggest(probabilityBoard);
        Util.shootToBoard(board, rowToShoot, colToShoot);
        if (board[rowToShoot][colToShoot].getTileState() == TileState.MISSED) {
            shipMissRecalculate(probabilityBoard, rowToShoot, colToShoot);
        } else if (board[rowToShoot][colToShoot].getTileState() == TileState.HITTED) {
            shipHitRecalculate(probabilityBoard, rowToShoot, colToShoot);
        }
        //just test if calculations are correct
        //printProbabilityTable();
    }


    /**
     * Find biggest number in probability table and set its row and col.
     *
     * @param probabilityBoard where max number is searched.
     */
    private void findBiggest(int[][] probabilityBoard) {
        int max = -1000;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (probabilityBoard[row][col] > max) {
                    max = probabilityBoard[row][col];
                    rowToShoot = row;
                    colToShoot = col;
                }
            }
        }
    }


    /**
     * Recalculate probability around missed Tile.
     *
     * @param board of probability.
     * @param row where shoot take place.
     * @param col  where shoot take place.
     */
    private void shipMissRecalculate(int[][] board, int row, int col) {
        board[row][col] = 0;

        //horizontal
        if (col + 1 < 10) board[row][col + 1] -= 3;
        if (col + 2 < 10) board[row][col + 2] -= 2;
        if (col + 3 < 10) board[row][col + 3] -= 1;
        if (col - 1 > 0) board[row][col - 1] -= 3;
        if (col - 2 > 0) board[row][col - 2] -= 2;
        if (col - 3 > 0) board[row][col - 3] -= 1;

        //vertical
        if (row + 1 < 10) board[row + 1][col] -= 3;
        if (row + 2 < 10) board[row + 2][col] -= 2;
        if (row + 3 < 10) board[row + 3][col] -= 1;
        if (row - 1 > 0) board[row - 1][col] -= 3;
        if (row - 2 > 0) board[row - 2][col] -= 2;
        if (row - 3 > 0) board[row - 3][col] -= 1;
    }


    /**
     * Recalculate probability around hitted Tile.
     *
     * @param board of probability.
     * @param row where shoot take place.
     * @param col  where shoot take place.
     */
    private void shipHitRecalculate(int[][] board, int row, int col) {
        board[row][col] = 0;

        //horizontal
        if (col + 1 < 10) board[row][col + 1] += 5;
        if (col + 2 < 10) board[row][col + 2] += 4;
        if (col + 3 < 10) board[row][col + 3] += 3;
        if (col - 1 > 0) board[row][col - 1] += 5;
        if (col - 2 > 0) board[row][col - 2] += 4;
        if (col - 3 > 0) board[row][col - 3] += 3;

        //vertical
        if (row + 1 < 10) board[row + 1][col] += 5;
        if (row + 2 < 10) board[row + 2][col] += 4;
        if (row + 3 < 10) board[row + 3][col] += 3;
        if (row - 1 > 0) board[row - 1][col] += 5;
        if (row - 2 > 0) board[row - 2][col] += 4;
        if (row - 3 > 0) board[row - 3][col] += 3;
    }

    /**
     * Print probability table. For checking if recalculation was correct.
     */
    public void printProbabilityTable() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (int i = 0; i < probabilityBoard.length; i++) {
            for (int j = 0; j < probabilityBoard.length; j++) {
                System.out.print(probabilityBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}