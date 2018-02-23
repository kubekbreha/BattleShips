package com.battleships.core.player;

import com.battleships.core.board.Util;

import java.util.Random;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Computer extends Player {

    private Random rand;

    private static int savedRow;
    private static int savedCol;

    private boolean makeCross;
    private int orientation;

    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean newLoop = false;

    public Computer() {
        rand = new Random();
    }


    @Override
    public void shootRand(int[][] board, char level) {
        switch (level) {
            case 'B':
                randomBegginer(board);
                break;

            case 'M':
                randomMedium(board);
                break;

            case 'H':
                randomHard(board);
                break;
        }
    }

    /**
     * Primitive AI which shoots only randomly to play-board.
     *
     * @param board where AI shoots.
     */
    private void randomBegginer(int[][] board) {
        int row = rand.nextInt(10);
        int col = rand.nextInt(10);

        if (board[row][col] != 1) {
            Util.writeToBoard(board, 1, row, col);
        } else {
            randomBegginer(board);
        }
    }


    /**
     * AI which create cross around hit field in board.
     *
     * @param board where AI shoots.
     */
    private void randomMedium(int[][] board) {
        int col = rand.nextInt(10);
        int row = rand.nextInt(10);

        if (makeCross) {
            try {
                switch (orientation) {
                    case 0:
                        if (board[savedRow - 1][savedCol] == 1) {
                            orientation = 90;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow - 1, savedCol);
                        orientation = 90;
                        break;

                    case 90:
                        if (board[savedRow][savedCol + 1] == 1) {
                            orientation = 180;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol + 1);
                        orientation = 180;
                        break;

                    case 180:
                        if (board[savedRow + 1][savedCol] == 1) {
                            orientation = 270;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow + 1, savedCol);
                        orientation = 270;
                        break;

                    case 270:
                        if (board[savedRow][savedCol - 1] == 1) {
                            orientation = 0;
                            makeCross = false;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol - 1);
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
                randomMedium(board);
            }
        } else {
            basicRandomShoot(board, row, col, 'M');
        }
    }


    /**
     * AI which create cross around hit field in board and destroy whole ship.
     *
     * @param board where AI shoots.
     */
    private void randomHard(int[][] board) {
        int col = rand.nextInt(10);
        int row = rand.nextInt(10);

        if(moveUp && newLoop){
            if(savedRow - 2 > 0 && board[savedRow - 2][savedCol] != 1) {
                Util.writeToBoard(board, 1, savedRow - 2, savedCol);
                newLoop = false;
            }else if(savedRow - 3 > 0 && board[savedRow - 3][savedCol] != 1) {
                Util.writeToBoard(board, 1, savedRow - 3, savedCol);
                newLoop = false;
                moveUp = false;
                moveDown = true;
            }else{
                moveUp = false;
                moveDown = true;
            }
        }


        if(moveDown && newLoop){
            if(savedRow + 2 < 10 && board[savedRow + 2][savedCol] != 1) {
                Util.writeToBoard(board, 1, savedRow + 2, savedCol);
                newLoop = false;
            }else if(savedRow + 3 < 10 && board[savedRow + 3][savedCol] != 1) {
                Util.writeToBoard(board, 1, savedRow + 3, savedCol);
                newLoop = false;
                moveDown = false;
            }else{
                moveDown = false;
            }
        }


        if(moveLeft && newLoop){
            if(savedCol - 2 > 0 && board[savedRow][savedCol-2] != 1) {
                Util.writeToBoard(board, 1, savedRow, savedCol-2);
                newLoop = false;
            }else if(savedCol - 3 > 0 && board[savedRow][savedCol-3] != 1) {
                Util.writeToBoard(board, 1, savedRow, savedCol-3);
                newLoop = false;
                moveLeft = false;
                moveRight = true;
            }else{
                moveLeft = false;
                moveRight = true;
            }
        }


        if(moveRight && newLoop){
            if(savedCol + 2 < 0 && board[savedRow][savedCol+2] != 1) {
                Util.writeToBoard(board, 1, savedRow, savedCol+2);
                newLoop = false;
            }else if(savedCol + 3 < 0 && board[savedRow][savedCol+3] != 1) {
                Util.writeToBoard(board, 1, savedRow, savedCol+3);
                newLoop = false;
                moveRight = false;
            }else{
                moveRight = false;
            }
        }




        if (makeCross && newLoop) {
            switch (orientation) {
                case 0:
                    if (savedRow - 1 > 0) {
                        if (board[savedRow - 1][savedCol] == 1) {
                            orientation = 90;
                            randomHard(board);
                        }
                        if (board[savedRow - 1][savedCol] == 3) {
                            moveUp = true;
                        }
                        Util.writeToBoard(board, 1, savedRow - 1, savedCol);
                        orientation = 90;
                    } else {
                        orientation += 90;
                        randomHard(board);
                    }
                    break;

                case 90:
                    if (savedCol + 1 < 10) {
                        if (board[savedRow][savedCol + 1] == 1) {
                            orientation = 180;
                            randomHard(board);
                        }
                        if (board[savedRow][savedCol + 1] == 3) {
                            moveRight = true;
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol + 1);
                        orientation = 180;
                    } else {
                        orientation += 90;
                        randomHard(board);
                    }
                    break;

                case 180:
                    if (savedRow + 1 < 10) {
                        if (board[savedRow + 1][savedCol] == 1) {
                            orientation = 270;
                            randomHard(board);
                        }
                        if (board[savedRow + 1][savedCol] == 3) {
                            moveDown = true;
                        }
                        Util.writeToBoard(board, 1, savedRow + 1, savedCol);
                        orientation = 270;
                    } else {
                        orientation += 90;
                        randomHard(board);
                    }
                    break;

                case 270:
                    if (savedCol - 1 > 0) {
                        if (board[savedRow][savedCol - 1] == 1) {
                            orientation = 0;
                            makeCross = false;
                            randomHard(board);
                        }
                        if (board[savedRow][savedCol - 1] == 3) {
                            moveLeft = true;
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol - 1);
                        orientation = 0;
                        makeCross = false;
                    } else {
                        makeCross = false;
                        orientation = 0;
                        randomHard(board);
                    }
                    break;
            }
        } else {
            basicRandomShoot(board, row, col, 'H');
        }
        newLoop = true;

    }


    /**
     * Basic shoot to field. If position is 1 make cross set to true.
     *
     * @param board      where to shoot.
     * @param row        where to shoot.
     * @param col        where to shoot.
     * @param difficulty level of difficulty.
     */
    private void basicRandomShoot(int[][] board, int row, int col, char difficulty) {
        if (board[row][col] != 1) {
            if (board[row][col] == 3) {
                makeCross = true;
                savedCol = col;
                savedRow = row;
            }
            Util.writeToBoard(board, 1, row, col);
        } else {
            if (difficulty == 'H') {
                randomHard(board);
            } else if (difficulty == 'M') {
                randomMedium(board);
            }
        }
    }
}