package sk.tuke.gamestudio.game.battleships.brehuv.core.player;


import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public abstract class Player {

    /**
     * Perform a shoot.
     *
     * @param board where shoot will go.
     * @param row   coordinate on board.
     * @param col   coordinate on board.
     */
    public void shoot(Tile[][] board, int row, int col) {
    }

    /**
     * Perform a random shoot.
     *
     * @param board where shoot will go.
     */
    public void shootAI(Tile[][] board) {
    }
}
