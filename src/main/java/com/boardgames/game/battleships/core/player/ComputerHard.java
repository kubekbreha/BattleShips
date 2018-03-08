package com.boardgames.game.battleships.core.player;

import com.boardgames.game.battleships.core.board.Tile;
import com.boardgames.game.battleships.core.board.TileState;
import com.boardgames.game.battleships.core.board.Util;

import java.util.Random;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class ComputerHard implements AILevel {

    private Random rand;

    private static int savedRow;
    private static int savedCol;

    private boolean makeCross;
    private int orientation;

    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean newLoop = true;


    public ComputerHard(){
        rand = new Random();
    }

    /**
     * AI which create cross around hit field in board and destroy whole ship.
     *
     * @param board where AI shoots.
     */
    @Override
    public void play(Tile[][] board) {
            int col = rand.nextInt(10);
            int row = rand.nextInt(10);

            if (moveUp && newLoop) {
                if (savedRow - 2 > 0 && (board[savedRow - 2][savedCol].getTileState() != TileState.MISSED ||
                         board[savedRow - 2][savedCol].getTileState() != TileState.HITTED )) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow - 2, savedCol);
                } else if (savedRow - 3 > 0 && (board[savedRow - 3][savedCol].getTileState() != TileState.MISSED ||
                         board[savedRow - 3][savedCol].getTileState() != TileState.HITTED )) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow - 3, savedCol);
                    moveUp = false;
                    moveDown = true;
                } else {
                    moveUp = false;
                    moveDown = true;
                }
                makeCross = false;
            }


            if (moveDown && newLoop) {
                if (savedRow + 2 < 10 && (board[savedRow + 2][savedCol].getTileState() != TileState.MISSED ||
                         board[savedRow + 2][savedCol].getTileState() != TileState.HITTED)) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow + 2, savedCol);
                } else if (savedRow + 3 < 10 && (board[savedRow + 3][savedCol].getTileState() != TileState.MISSED ||
                         board[savedRow + 3][savedCol].getTileState() != TileState.HITTED )) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow + 3, savedCol);
                    moveDown = false;
                } else {
                    moveDown = false;
                }
                makeCross = false;
            }


            if (moveLeft && newLoop) {
                if (savedCol - 2 > 0 && (board[savedRow][savedCol - 2].getTileState() != TileState.HITTED  ||
                        board[savedRow][savedCol - 2].getTileState() != TileState.MISSED)) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow, savedCol - 2);
                } else if (savedCol - 3 > 0 && (board[savedRow][savedCol - 3].getTileState() != TileState.HITTED ||
                        board[savedRow][savedCol - 3].getTileState() != TileState.MISSED)) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow, savedCol - 3);
                    moveLeft = false;
                    moveRight = true;
                } else {
                    moveLeft = false;
                    moveRight = true;
                    makeCross = false;
                }
            }


            if (moveRight && newLoop) {
                if (savedCol + 2 < 0 && (board[savedRow][savedCol + 2].getTileState() != TileState.HITTED ||
                        board[savedRow][savedCol + 2].getTileState() != TileState.MISSED)) {
                    newLoop = false;
                    Util.shootToBoard(board, savedRow, savedCol + 2);
                } else if (savedCol + 3 < 0 && (board[savedRow][savedCol + 3].getTileState() != TileState.HITTED ||
                        board[savedRow][savedCol + 3].getTileState() != TileState.MISSED )) {
                    newLoop = false;
                    Util.shootToBoard(board,  savedRow, savedCol + 3);
                    moveRight = false;
                } else {
                    moveRight = false;
                    makeCross = false;
                }
            }

            if (newLoop) {
                if (makeCross) {
                    switch (orientation) {
                        case 0:
                            if (savedRow - 1 > 0) {
                                if (board[savedRow - 1][savedCol].getTileState() == TileState.HITTED
                                        || board[savedRow - 1][savedCol].getTileState() == TileState.MISSED) {
                                    orientation = 90;
                                    play(board);
                                }
                                if (board[savedRow - 1][savedCol].getTileState() == TileState.SHIP) {
                                    moveUp = true;
                                }
                                Util.shootToBoard(board, savedRow - 1, savedCol);
                                orientation = 90;
                            } else {
                                orientation += 90;
                                newLoop = true;
                                play(board);
                            }
                            break;

                        case 90:
                            if (savedCol + 1 < 10) {
                                if (board[savedRow ][savedCol+1].getTileState() == TileState.HITTED
                                        || board[savedRow][savedCol+1].getTileState() == TileState.MISSED) {
                                    orientation = 180;
                                    play(board);
                                }
                                if (board[savedRow][savedCol + 1].getTileState() == TileState.SHIP) {
                                    moveRight = true;
                                }
                                Util.shootToBoard(board, savedRow, savedCol + 1);
                                orientation = 180;
                            } else {
                                orientation += 90;
                                newLoop = true;
                                play(board);
                            }
                            break;

                        case 180:
                            if (savedRow + 1 < 10) {
                                if (board[savedRow + 1][savedCol].getTileState() == TileState.HITTED
                                        || board[savedRow + 1][savedCol].getTileState() == TileState.MISSED) {
                                    orientation = 270;
                                    play(board);
                                }
                                if (board[savedRow + 1][savedCol].getTileState() == TileState.SHIP) {
                                    moveDown = true;
                                }
                                Util.shootToBoard(board,  savedRow + 1, savedCol);
                                orientation = 270;
                            } else {
                                orientation += 90;
                                newLoop = true;
                                play(board);
                            }
                            break;

                        case 270:
                            if (savedCol - 1 > 0) {
                                if (board[savedRow ][savedCol-1].getTileState() == TileState.HITTED
                                        || board[savedRow ][savedCol-1].getTileState() == TileState.MISSED) {
                                    orientation = 0;
                                    makeCross = false;
                                    play(board);
                                }
                                if (board[savedRow][savedCol - 1].getTileState() == TileState.SHIP) {
                                    moveLeft = true;
                                }
                                Util.shootToBoard(board, savedRow, savedCol - 1);
                                orientation = 0;
                                makeCross = false;
                            } else {
                                makeCross = false;
                                orientation = 0;
                                newLoop = true;
                                play(board);
                            }
                            break;
                    }
                } else {
                    basicRandomShoot(board, row, col, 'H');
                }
            }

            newLoop = true;

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

    /**
     * Basic shoot to field. If position is 1 make cross set to true.
     *
     * @param board      where to shoot.
     * @param row        where to shoot.
     * @param col        where to shoot.
     * @param difficulty level of difficulty.
     */
    private void basicRandomShoot(Tile[][] board, int row, int col, char difficulty) {
        if (board[row][col].getTileState() != TileState.HITTED) {
            if (board[row][col].getTileState() == TileState.SHIP) {
                makeCross = true;
                savedRow = row;
                savedCol = col;
            }
            Util.shootToBoard(board, row, col);
        } else {
            play(board);
        }
    }
}
