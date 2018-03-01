package com.battleships.core.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class AIExpertHistory {
    /**
     * HashMap for in-game history
     */
    private List<int[][]> historyList;


    public AIExpertHistory(){
        this.historyList = new ArrayList<>();
    }


    /**
     * Get last item in array.
     */
    public int[][] getLast(){
        return historyList.get(historyList.size()-1);
    }


    /**
     * Remove last item in array.
     */
    public void removeLast(){
        historyList.remove(historyList.size()-1);
    }


    /**
     * Add copy of 2D array to history.
     *
     * @param board which will be added.
     */
    public void addToHistory(int[][] board){
        int[][] retBoard = new int[board.length][];
        for(int i = 0; i < board.length; i++)
            retBoard[i] = board[i].clone();
        historyList.add(retBoard);
    }

    /**
     * Get history size.
     *
     * @return int of history size.
     */
    public int getHistorySize() {
        return historyList.size();
    }

}
