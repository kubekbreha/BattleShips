package com.gamestudio.game.battleships.core.history;

import com.gamestudio.game.battleships.core.board.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class SinglePlayerHistory{

    /**
     * HashMap for in-game history
     */
    private List<Tile[][]> historyList;
    private List<int[][]> historyProbabilityList;

    public SinglePlayerHistory(){
        this.historyList = new ArrayList<>();
        this.historyProbabilityList = new ArrayList<>();
    }



    /**
     * Get last item in array.
     */
    public Tile[][] getLast(){
        return historyList.get(historyList.size()-1);
    }


    /**
     * Remove last item in array.
     */
    public void removeLast(){
        historyList.remove(historyList.size()-1);
    }

    /**
     * Get last item in probability array.
     */
    public int[][] getLastProbability(){
        return historyProbabilityList.get(historyProbabilityList.size()-1);
    }


    /**
     * Remove last item in probability array.
     */
    public void removeLastProbability(){
        historyProbabilityList.remove(historyProbabilityList.size()-1);
    }



    /**
     * Add copy of 2D array to history.
     *
     * @param board which will be added.
     */
    public void addToHistory(Tile[][] board){
        Tile[][] retBoard = new Tile[board.length][];
        for(int i = 0; i < board.length; i++)
            retBoard[i] = board[i].clone();
        historyList.add(retBoard);
    }

    /**
     * Add copy of 2D array to probability history.
     *
     * @param board which will be added.
     */
    public void addToProbabilityHistory(int[][] board){
        int[][] retBoard = new int[board.length][];
        for(int i = 0; i < board.length; i++)
            retBoard[i] = board[i].clone();
        historyProbabilityList.add(retBoard);
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
