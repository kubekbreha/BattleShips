package com.battleships.core.game;

import com.battleships.core.board.Board;
import com.battleships.core.board.Ship;
import com.battleships.core.board.TileState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class GameController {

    private GameState gameState;

    /**
     * Get tile state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Set tile state.
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Check if player won the game and set state.
     *
     * @return true if game is won otherwise false.
     */
    public void isGameVon(ArrayList<Ship> ships, Board board) {
        int sunkedShips = 0;
        for (Ship ship : ships) {
            if (ship.isShipSunk(board.getPlayBoard())) {
                sunkedShips++;
            }
        }
        if (sunkedShips == ships.size()) {
            gameState = GameState.WON;
        } else {
            gameState = GameState.PLAYING;
        }
    }


    /**
     * Check if game is set up.
     *
     * @return true if game is won otherwise false.
     */
    public void isGameSetUp(int[] shipsSizes, Board board) {
        int shipsListCount = 0;
        for (int shipSize : shipsSizes) {
            shipsListCount += shipSize;
        }

        int shipsTileCount = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if(board.getPlayBoard()[row][col].getTileState() == TileState.SHIP){
                    shipsTileCount++;
                }
            }
        }

        if (shipsTileCount == shipsListCount) {
            gameState = GameState.SETTEDUP;
        } else {
            gameState = GameState.NOTSETTEDUP;
        }
    }
}
