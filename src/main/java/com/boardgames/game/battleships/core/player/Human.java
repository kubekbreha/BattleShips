package com.boardgames.game.battleships.core.player;

import com.boardgames.game.battleships.core.board.Tile;
import com.boardgames.game.battleships.core.board.Util;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Human extends Player {

    @Override
    public void shoot(Tile[][] board, int row, int col) {
        Util.shootToBoard(board, row, col);
    }
}
