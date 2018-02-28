package com.battleships.core.player;

import com.battleships.core.board.Tile;

interface AIState {
    void play(Tile[][] board);
}
