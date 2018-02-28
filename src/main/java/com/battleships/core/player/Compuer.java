package com.battleships.core.player;

import com.battleships.core.board.Tile;
import com.battleships.core.player.Player;

public class Compuer extends Player {

    private AIState aiState;

    public void setAiState(AIState aiState) {
        this.aiState = aiState;
    }

    public AIState getAiState() {
        return aiState;
    }

    @Override
    public void shootAI(Tile[][] board) {
        aiState.play(board);
    }

}
