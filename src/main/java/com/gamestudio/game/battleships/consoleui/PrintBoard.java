package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.TileState;
import com.gamestudio.game.battleships.core.util.Util;

/**
 * Created by Kubo Brehuv with <3 (19.2.2018)
 */
public class PrintBoard {

    /**
     * Print playBoard to console.
     */
    public void printPlayBoard(Board board) {
        System.out.print(Util.ANSI_CYAN + "* " + Util.ANSI_RESET);
        for (int i = 0; i < 10; i++) {
            System.out.print(Util.ANSI_CYAN + i + " " + Util.ANSI_RESET);
        }
        System.out.println();

        for (int i = 0; i < board.getBoardCols(); i++) {
            System.out.print(Util.ANSI_CYAN + (char) (i + 'A') + " " + Util.ANSI_RESET);
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getPlayBoard()[i][j].getTileState() == TileState.SHIP) {
                    System.out.print(Util.ANSI_RESET + "*" + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.HITTED) {
                    System.out.print(Util.ANSI_RED + "O" + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.MISSED) {
                    System.out.print(Util.ANSI_BLACK + "O" + " " + Util.ANSI_RESET);
                } else {
                    System.out.print("*" + " ");
                }
            }
            System.out.println();
        }
    }


    /**
     * Print playBoard to console.
     */
    public void printPlayBoardTesting(Board board) {
        System.out.print(Util.ANSI_CYAN + "* " + Util.ANSI_RESET);
        for (int i = 0; i < 10; i++) {
            System.out.print(Util.ANSI_CYAN + i + " " + Util.ANSI_RESET);
        }
        System.out.println();

        for (int i = 0; i < board.getBoardCols(); i++) {
            System.out.print(Util.ANSI_CYAN + (char) (i + 65) + " " + Util.ANSI_RESET);
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getPlayBoard()[i][j].getTileState() == TileState.SHIP) {
                    System.out.print(Util.ANSI_CYAN + 5 + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.HITTED) {
                    System.out.print(Util.ANSI_RED + 2 + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.MISSED) {
                    System.out.print(Util.ANSI_BLACK + 2 + " " + Util.ANSI_RESET);
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}