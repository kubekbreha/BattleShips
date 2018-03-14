package com.gamestudio.game.battleships.core.board;

import com.gamestudio.game.battleships.core.util.Util;
import com.gamestudio.game.battleships.core.player.ComputerExpert;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class Hint {

    private int[][] hintBoard;
    private ComputerExpert computerExpert;

    private int hintRow;
    private int hintCol;

    private int boardRow;
    private int boardCol;

    public Hint(Board board){
        computerExpert = new ComputerExpert();
        hintBoard = Util.createProbabilityBoard(10,10);
        boardRow = board.getBoardRows();
        boardCol = board.getBoardCols();
    }

    /**
     * Set hint board.
     *
     * @param hintBoard which will replace old one.
     */
    public void setHintBoard(int[][] hintBoard) {
        this.hintBoard = hintBoard;
    }

    /**
     * Get hint board.
     * @return actual hint board.
     */
    public int[][] getHintBoard() {
        return hintBoard;
    }

    /**
     * Recalculate probability board after move.
     *
     * @param state to decide calculation.
     * @param row of hit tile.
     * @param col of hit tile.
     */
    public void moveExecuted(TileState state, int row, int col){
        if(state == TileState.MISSED){
            computerExpert.shipMissRecalculate(hintBoard, row, col);
        }else if(state == TileState.HITTED){
            computerExpert.shipHitRecalculate(hintBoard, row, col);
        }
    }

    /**
     * Find hint in board.
     * Hint is biggest number.
     */
    public void findHint(){
        int max = -1000;
        for (int row = 0; row < boardRow; row++) {
            for (int col = 0; col < boardCol; col++) {
                if (hintBoard[row][col] > max) {
                    max = hintBoard[row][col];
                    hintRow = row;
                    hintCol = col;
                }
            }
        }
    }

    /**
     * get hint column.
     *
     * @return column where to shoot.
     */
    public int getHintCol() {
        return hintCol;
    }


    /**
     * get hint row.
     *
     * @return row where to shoot.
     */
    public int getHintRow() {
        return hintRow;
    }


    /**
     * Print probability board for testing purposes.
     */
    public void printHintBoard(){
        for (int row = 0; row < boardRow; row++) {
            for (int col = 0; col < boardCol; col++) {
                System.out.print(hintBoard[row][col]);
            }
            System.out.println();
        }
    }
}
