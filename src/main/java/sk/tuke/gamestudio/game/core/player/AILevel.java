package sk.tuke.gamestudio.game.core.player;


import sk.tuke.gamestudio.game.core.board.Tile;

/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
interface AILevel {

    void play(Tile[][] board);

    int[][] getHistory();

    void setHistory(int[][] history);

}