package sk.tuke.gamestudio.game.battleships.core.player;

import sk.tuke.gamestudio.game.battleships.core.board.Tile;
import sk.tuke.gamestudio.game.battleships.core.board.TileState;
import sk.tuke.gamestudio.game.battleships.core.util.Util;

import java.util.Random;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class ComputerBegginer implements AILevel {

    private Random random;

    public ComputerBegginer(){
        random = new Random();
    }


    /**
     * Primitive AI which shoots only randomly to play-board.
     *
     * @param board where AI shoots.
     */
    @Override
    public void play(Tile[][] board ) {
        int row = random.nextInt(board.length);
        int col = random.nextInt(board[0].length);

        if (board[row][col].getTileState() != TileState.HIT) {
            Util.shootToBoard(board, row, col);
        } else {
            play(board);
        }
    }

    /**
     * Not needed.
     *
     * @return empty 2D int board.
     */
    @Override
    public int[][] getHistory() {
        return new int[0][];
    }

    /**
     * Not needed.
     *
     * @return empty 2D int board.
     */
    @Override
    public void setHistory(int[][] history) {

    }
}
