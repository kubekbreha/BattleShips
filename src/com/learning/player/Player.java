package com.learning.player;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public abstract class Player {

    /**
     * Stack for in-game history
     */
    Deque<int[][]> historyStack = new ArrayDeque<>();

    /**
     * Perform a shoot.
     * @param board where shoot will go.
     * @param row coordinate on board.
     * @param col coordinate on board.
     */
    public void shoot(int[][] board, int row, int col) {

    }

    /**
     * One step back.
     * @param board board where step-back take place.
     */
    public void undo(int[][] board){

    }

    /**
     * Get history size.
     * @return int of history size.
     */
    public int getHistorySize(){
        return historyStack.size();
    }

}
