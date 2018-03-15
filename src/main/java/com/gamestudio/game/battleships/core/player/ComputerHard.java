package com.gamestudio.game.battleships.core.player;

import com.gamestudio.game.battleships.core.board.Tile;
import com.gamestudio.game.battleships.core.board.TileState;
import com.gamestudio.game.battleships.core.util.Util;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class ComputerHard implements AILevel {

    private int rowToShoot;
    private int colToShoot;

    private int[][] probabilityBoard;


    public ComputerHard(int rows, int cols){
        this.probabilityBoard = Util.createProbabilityBoard(rows, cols);
    }

    /**
     * AI which create cross around hit field in board and destroy whole ship.
     *
     * @param board where AI shoots.
     */
    @Override
    public void play(Tile[][] board) {
        findBiggest(probabilityBoard);
        Util.shootToBoard(board, rowToShoot, colToShoot);
        if (board[rowToShoot][colToShoot].getTileState() == TileState.MISSED) {
            shipMissRecalculate(probabilityBoard, rowToShoot, colToShoot);
        } else if (board[rowToShoot][colToShoot].getTileState() == TileState.HIT) {
            shipHitRecalculate(probabilityBoard, rowToShoot, colToShoot);
        }
    }


    /**
     * Not needed.
     *
     * @return empty 2D int board.
     */
    @Override
    public int[][] getHistory() {
        return new int[0][];
    }


    /**
     * Not needed.
     *
     * @return empty 2D int board.
     */
    @Override
    public void setHistory(int[][] history) {

    }

    /**
     * Find biggest number in probability table and set its row and col.
     *
     * @param probabilityBoard where max number is searched.
     */
    private void findBiggest(int[][] probabilityBoard) {
        int max = -1000;
        for (int row = 0; row < probabilityBoard.length; row++) {
            for (int col = 0; col < probabilityBoard[0].length; col++) {
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
    public void shipMissRecalculate(int[][] board, int row, int col) {
        board[row][col] = 0;

        //horizontal
        if (col + 1 < board[0].length) board[row][col + 1] -= 1;
        if (col - 1 > 0) board[row][col - 1] -= 1;

        //vertical
        if (row + 1 < board.length) board[row + 1][col] -= 1;
        if (row - 1 > 0) board[row - 1][col] -= 1;
    }


    /**
     * Recalculate probability around hitted Tile.
     *
     * @param board of probability.
     * @param row where shoot take place.
     * @param col  where shoot take place.
     */
    public void shipHitRecalculate(int[][] board, int row, int col) {
        board[row][col] = 0;

        //horizontal
        if (col + 1 < board[0].length) board[row][col + 1] += 1;
        if (col - 1 > 0) board[row][col - 1] += 2;

        //vertical
        if (row + 1 < board.length) board[row + 1][col] += 1;
        if (row - 1 > 0) board[row - 1][col] += 2;
    }
}
