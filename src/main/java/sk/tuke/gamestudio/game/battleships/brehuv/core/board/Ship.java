package sk.tuke.gamestudio.game.battleships.brehuv.core.board;


import sk.tuke.gamestudio.game.battleships.brehuv.core.util.Util;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Ship {

    private int shipSize;
    private int[][] shipPositions;

    /**
     * Basic constructor which create ship.
     *
     * @param size of ship.
     */
    public Ship(int size) {
        shipSize = size;
    }

    /**
     * Place ship to playBoard.
     *
     * @param board       play-board where ship will be placed.
     * @param row         coordinate of start ship on board.
     * @param col         coordinate of start ship on board.
     * @param orientation (V/H) vertical or horizontal orientation of ship on play-board.
     * @return is ship placed successfully.
     */
    //TODO: fix corners of ship and random endless loop
    public boolean placeShip(Tile[][] board, int row, int col, char orientation) {
        boolean canPlace = true;
        shipPositions = new int[shipSize][2];

        //check if ship can be placed
        if ((row + shipSize) > board.length && orientation == 'V') {
            canPlace = false;
        }
        if ((col + shipSize) > board[0].length && orientation == 'H') {
            canPlace = false;
        }

        //TODO: Rewrite this bullshit code.
        //check if ship will be placed over other ship
        if (canPlace) {
            for (int i = 0; i < shipSize; i++) {
                if (orientation == 'H') {
                    try {
                        if (board[row][col + i].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row + 1][col + i].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row - 1][col + i].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row][col + i - 1].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row][col + i + 1].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }

                } else if (orientation == 'V') {
                    try {
                        if (board[row + i][col].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row + i][col + 1].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row + i][col - 1].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row + i - 1][col].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                    try {
                        if (board[row + i + 1][col].getTileState() == TileState.SHIP) canPlace = false;
                    } catch (Exception e) {
                    }
                }
            }
        }

        //place ship to play-board or return false
        if (canPlace) {
            for (int i = 0; i < shipSize; i++) {
                if (orientation == 'H') {
                    Util.writeToBoard(board, new Tile(TileState.SHIP), row, col + i);
                    shipPositions[i][0] = row;
                    shipPositions[i][1] = col + i;
                } else if (orientation == 'V') {
                    Util.writeToBoard(board, new Tile(TileState.SHIP), row + i, col);
                    shipPositions[i][0] = row + i;
                    shipPositions[i][1] = col;
                }
            }
            return true;
        } else {
            return false;
        }
    }



    /**
     * Set ship size.
     *
     * @param shipSize value which overwrite shipSize.
     */
    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    /**
     * Get ship size.
     *
     * @return integer ship size.
     */
    public int getShipSize() {
        return shipSize;
    }

    /**
     * Get 2D array of ship positions.
     *
     * @return int[][] array of ship positions.
     */
    public int[][] getShipPositions() {
        return shipPositions;
    }

    /**
     * Check if ship is touched.
     *
     * @param board where ship is placed.
     */
    public boolean isShipTouched(Tile[][] board) {
        int touches = 0;

        for (int[] shipPosition : shipPositions) {
            if (board[shipPosition[0]][shipPosition[1]].getTileState() == TileState.HIT) {
                touches++;
            }
        }
        return touches > 0;
    }

    /**
     * Check if ship is sunk.
     *
     * @param board where ship is placed.
     */
    public boolean isShipSunk(Tile[][] board) {
        int touches = 0;

        for (int[] shipPosition : shipPositions) {
            if (board[shipPosition[0]][shipPosition[1]].getTileState() == TileState.HIT) {
                touches++;
            }
        }
        return touches == shipSize;
    }

}
