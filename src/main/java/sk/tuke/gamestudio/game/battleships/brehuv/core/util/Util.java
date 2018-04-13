package sk.tuke.gamestudio.game.battleships.brehuv.core.util;


import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.TileState;

import java.io.BufferedReader;
import java.io.IOException;

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
     * Change tile state.
     *
     * @param board where to write.
     * @param value to write.
     * @param col   coordinate on the board.
     */
    public static void writeToBoard(Tile[][] board, Tile value, int row, int col) {
        board[row][col] = value;
    }


    /**
     * Checking tile state and overwriting in due to conditions.
     *
     * @param board of Tiles.
     * @param row   coordinate on the board.
     * @param col   coordinate on the board.
     */
    public static void shootToBoard(Tile[][] board, int row, int col) {
        Tile tile = board[row][col];
        switch (tile.getTileState()) {
            case SHIP:
                Util.writeToBoard(board, new Tile(TileState.HIT), row, col);
                break;

            case WATER:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case MISSED:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case HIT:
                Util.writeToBoard(board, new Tile(TileState.HIT), row, col);
                break;
        }
    }

    /**
     * Create probability board for hint and expertAI.
     * Only for even number.
     *
     * @return
     */
    public static int[][] createProbabilityBoard(int rows, int cols) {
        int[][] probabilityBoard = new int[rows][cols];

        int base = 8;
        int biggestJ = 0;
        int biggestI = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (i < cols / 2) {
                    if (j < rows / 2) {
                        probabilityBoard[i][j] = i + base + j;
                        biggestJ = j;
                    } else {
                        probabilityBoard[i][j] = i + base + biggestJ;
                        biggestJ--;
                    }
                    biggestI = i + 1;
                } else {
                    if (j < rows / 2) {
                        probabilityBoard[i][j] = biggestI + base + j;
                        biggestJ = j;
                    } else {
                        probabilityBoard[i][j] = biggestI + base + biggestJ;
                        biggestJ--;
                    }
                }
            }
            biggestI--;
        }
        return probabilityBoard;
    }


    /**
     * Read line from user input.
     *
     * @return readed string.
     */
    public static String readLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Nepodarilo sa nacitat vstup, skus znova");
            return "";
        }
    }


}
