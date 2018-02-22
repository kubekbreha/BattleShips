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
                        if(board[savedRow-1][savedCol] == 1){
                            orientation = 90;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow - 1, savedCol);
                        orientation = 90;
                        break;

                    case 90:
                        if(board[savedRow][savedCol+1] == 1){
                            orientation = 180;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol + 1);
                        orientation = 180;
                        break;

                    case 180:
                        if(board[savedRow+1][savedCol] == 1){
                            orientation = 270;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow + 1, savedCol);
                        orientation = 270;
                        break;

                    case 270:
                        if(board[savedRow][savedCol-1] == 1){
                            orientation = 0;
                            makeCross = false;
                            randomMedium(board);
                        }
                        Util.writeToBoard(board, 1, savedRow, savedCol - 1);
                        orientation = 0;
                        makeCross = false;
                        break;
                }
            }catch (ArrayIndexOutOfBoundsException e ){
                if(orientation == 270){
                    makeCross = false;
                    orientation = 0;
                }else{
                    orientation += 90;
                }
                randomMedium(board);
            }
        } else {
            if (board[row][col] != 1) {
                if (board[row][col] == 3) {
                    makeCross = true;
                    savedCol = col;
                    savedRow = row;
                }
                Util.writeToBoard(board, 1, row, col);
            } else {
                randomMedium(board);
            }
        }
    }

    

}