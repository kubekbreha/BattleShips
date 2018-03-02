package com.battleships.core.player;

import com.battleships.core.board.Tile;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class Computer extends Player {

    private AILevel aiState;

    public void setAiState(AILevel aiState) {
        this.aiState = aiState;
    }

    public AILevel getAiState() {
        return aiState;
    }

    @Override
    public void shootAI(Tile[][] board) {
        aiState.play(board);
    }

    public int[][] getNotTileHistory(){
        return aiState.getHistory();
    }

    public void setNotTileHistory(int[][] history){
        aiState.setHistory(history);
    }
}
