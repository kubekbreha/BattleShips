package com.learning.board;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Ship {

    private int shipSize;

    /**
     * Basic constructor which create ship.
     * @param size of ship.
     */
    public Ship(int size){
        shipSize = size;
    }

    /**
     * Place ship to playBoard.
     * @param board play-board where ship will be placed.
     * @param row coordinate of start ship on board.
     * @param col coordinate of start ship on board.
     * @param orientation (V/H) vertical or horizontal orientation of ship on play-board.
     */
    public void placeShip(int[][] board ,int row, int col, char orientation){
        for(int i = 0; i<shipSize; i++){
            if(orientation == 'H'){
                Util.writeToBoard(board, 3, row, col+i);
            }else if(orientation == 'V'){
                Util.writeToBoard(board, 3, row+i, col);
            }
        }
    }
}
