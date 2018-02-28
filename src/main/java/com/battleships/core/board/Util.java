package com.battleships.core.board;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Util {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Put tile to board.
     *
     * @param board where to write.
     * @param value to write.
     * @param row   coordinate on the board.
     * @param col   coordinate on the board.
     */
    public static void writeToBoard(Tile[][] board, Tile value, int row, int col) {
        board[row][col] = value;
    }

    public static void shootToBoard(Tile[][] board, int row, int col){
        Tile tile = board[row][col];
        switch (tile.getTileState()){
            case SHIP:
                Util.writeToBoard(board, new Tile(TileState.HITTED), row, col);
                break;

            case WATER:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case MISSED:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case HITTED:
                Util.writeToBoard(board, new Tile(TileState.HITTED), row, col);
                break;
        }
    }

}
