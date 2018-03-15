package com.gamestudio.game.battleships.core.player;

import com.gamestudio.game.battleships.core.board.Tile;
import com.gamestudio.game.battleships.core.board.TileState;
import com.gamestudio.game.battleships.core.util.Util;

import java.util.Random;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class ComputerMedium implements AILevel {

    private Random rand;

    private static int savedRow;
    private static int savedCol;

    private boolean makeCross;
    private int orientation;

    public ComputerMedium(){
        rand = new Random();
    }

    /**
     * AI which create cross around hit field in board.
     *
     * @param board where AI shoots.
     */
    @Override
    public void play(Tile[][] board) {
        int col = rand.nextInt(board[0].length);
        int row = rand.nextInt(board.length);

        if (makeCross) {
            try {
                switch (orientation) {
                    case 0:
                        if (board[savedRow - 1][savedCol].getTileState() == TileState.HIT ||
                                board[savedRow - 1][savedCol].getTileState() == TileState.MISSED) {
                            orientation = 90;
                            play(board);
                        }
                        Util.shootToBoard(board, savedRow - 1, savedCol);
                        orientation = 90;
                        break;

                    case 90:
                        if (board[savedRow][savedCol + 1].getTileState() == TileState.HIT ||
                                board[savedRow][savedCol + 1].getTileState() == TileState.MISSED) {
                            orientation = 180;
                            play(board);
                        }
                        Util.shootToBoard(board,  savedRow, savedCol + 1);
                        orientation = 180;
                        break;

                    case 180:
                        if (board[savedRow + 1][savedCol].getTileState() == TileState.HIT ||
                                board[savedRow + 1][savedCol].getTileState() == TileState.MISSED) {
                            orientation = 270;
                            play(board);
                        }
                        Util.shootToBoard(board, savedRow + 1, savedCol);
                        orientation = 270;
                        break;

                    case 270:
                        if (board[savedRow][savedCol - 1].getTileState() == TileState.HIT ||
                                board[savedRow][savedCol - 1].getTileState() == TileState.MISSED) {
                            orientation = 0;
                            makeCross = false;
                            play(board);
                        }
                        Util.shootToBoard(board, savedRow, savedCol - 1);
                        orientation = 0;
                        makeCross = false;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (orientation == 270) {
                    makeCross = false;
                    orientation = 0;
                } else {
                    orientation += 90;
                }
                play(board);
            }
        } else {
            basicRandomShoot(board, row, col);
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
\     */
    @Override
    public void setHistory(int[][] history) {

    }


    /**
     * Basic shoot to field. If position is 1 make cross set to true.
     *
     * @param board      where to shoot.
     * @param row        where to shoot.
     * @param col        where to shoot.
     */
    private void basicRandomShoot(Tile[][] board, int row, int col) {
        if (board[row][col].getTileState() != TileState.HIT || board[row][col].getTileState() != TileState.MISSED){
            if (board[row][col].getTileState() == TileState.SHIP) {
                makeCross = true;
                savedCol = col;
                savedRow = row;
            }
            Util.shootToBoard(board, row, col);
        } else {
            play(board);
        }
    }
}
