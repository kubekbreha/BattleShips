package com.battleships.core.player;

import com.battleships.core.board.Tile;
import com.battleships.core.board.Util;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Human extends Player {

    @Override
    public void shoot(Tile[][] board, int row, int col) {
        Util.shootToBoard(board, row, col);
    }
}
