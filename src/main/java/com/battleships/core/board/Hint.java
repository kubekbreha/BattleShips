package com.battleships.core.board;

import com.battleships.core.player.ComputerExpert;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class Hint {

    private int[][] hintBoard;
    private ComputerExpert computerExpert;

    private int hintRow;
    private int hintCol;

    public Hint(){
        computerExpert = new ComputerExpert();
        hintBoard = Util.createProbabilityBoard();
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
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
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
}
