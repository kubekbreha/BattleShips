package com.battleships.core.player;

import com.battleships.core.board.Tile;

interface AILevel {
    void play(Tile[][] board);
}
