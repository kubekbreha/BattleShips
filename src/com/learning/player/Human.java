package com.learning.player;

import com.learning.board.Util;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Human extends Player {

    @Override
    public void shoot(int[][] board, int row, int col) {
        Util.writeToBoard(board, 1, row, col);
    }
}
