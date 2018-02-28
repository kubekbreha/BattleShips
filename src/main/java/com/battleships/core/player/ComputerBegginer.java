package com.battleships.core.player;

import com.battleships.core.board.Tile;
import com.battleships.core.board.TileState;
import com.battleships.core.board.Util;

import java.util.Random;

public class ComputerBegginer implements AIState {

    private Random rand;

    public ComputerBegginer(){
        rand = new Random();
    }


    /**
     * Primitive AI which shoots only randomly to play-board.
     *
     * @param board where AI shoots.
     */
    @Override
    public void play(Tile[][] board) {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);

        if (board[row][col].getTileState() != TileState.HITTED) {
            Util.shootToBoard(board, row, col);
        } else {
            play(board);
        }
    }
}
