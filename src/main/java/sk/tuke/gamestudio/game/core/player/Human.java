package sk.tuke.gamestudio.game.core.player;


import sk.tuke.gamestudio.game.core.board.Tile;
import sk.tuke.gamestudio.game.core.util.Util;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Human extends Player {

    @Override
    public void shoot(Tile[][] board, int row, int col) {
        Util.shootToBoard(board, row, col);
    }
}
