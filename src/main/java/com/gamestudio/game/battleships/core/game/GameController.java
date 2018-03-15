package com.gamestudio.game.battleships.core.game;

import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Ship;
import com.gamestudio.game.battleships.core.board.TileState;

import java.util.ArrayList;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */
public class GameController {

    private GameState gameState;
    private Board board;

    public GameController(Board board){
        this.board = board;
    }

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
    public boolean isGameWon(ArrayList<Ship> ships) {
        int sunkedShips = 0;
        for (Ship ship : ships) {
            if (ship.isShipSunk(board.getPlayBoard())) {
                sunkedShips++;
            }
        }
        if (sunkedShips == ships.size()) {
            setGameState(GameState.WON);
            return true;
        } else {
            setGameState(GameState.PLAYING);
            return false;
        }
    }


    /**
     * Check if game is set up.
     *
     * @return true if game is won otherwise false.
     */
    public boolean isGameSetUp(int[] shipsSizes) {
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
            return true;
        } else {
            gameState = GameState.NOTSETTEDUP;
            return false;
        }
    }
}
