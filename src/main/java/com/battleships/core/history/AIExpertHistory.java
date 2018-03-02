package com.battleships.core.history;

import com.battleships.core.board.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class AIExpertHistory {
    /**
     * HashMap for in-game history
     */
    private List<Tile[][]> historyListTile;
    private List<int[][]> historyListProbability;


    public AIExpertHistory() {
        this.historyListTile = new ArrayList<>();
        this.historyListProbability = new ArrayList<>();
    }


    /**
     * Get last item in array.
     */
    public Tile[][] getLastTile(){
        return historyListTile.get(historyListTile.size()-1);
    }

    /**
     * Get last item in array.
     */
    public int[][] getLastProbability(){
        return historyListProbability.get(historyListProbability.size()-1);
    }


    /**
     * Remove last item in array.
     */
    public void removeLast(){
        historyListProbability.remove(historyListProbability.size()-1);
        historyListTile.remove(historyListTile.size()-1);
    }


    /**
     * Add copy of 2D array to history.
     *
     * @param boardTile which will be added.
     */
    public void addToHistory(Tile[][] boardTile, int[][] boardProbability){
        Tile[][] retBoardTile = new Tile[boardTile.length][];
        for(int i = 0; i < boardTile.length; i++) {
            retBoardTile[i] = boardTile[i].clone();
        }

        int[][] retBoardProbability = new int[boardProbability.length][];
        for(int i = 0; i < boardTile.length; i++) {
            retBoardProbability[i] = boardProbability[i].clone();
        }
        historyListTile.add(retBoardTile);
        historyListProbability.add(retBoardProbability);
    }

    /**
     * Get history size.
     *
     * @return int of history size.
     */
    public int getHistorySize() {
        return historyListTile.size();
    }

}
