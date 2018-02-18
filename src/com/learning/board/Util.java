package com.learning.board;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Util {

    /**
     * Write int to board.
     * @param board where to write.
     * @param value to write.
     * @param row coordinate on the board.
     * @param col coordinate on the board.
     */
    public static void writeToBoard(int[][] board, int value, int row, int col){
        board[row][col] = value;
    }

}
