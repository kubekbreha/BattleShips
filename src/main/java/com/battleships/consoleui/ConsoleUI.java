package com.battleships.consoleui;

import com.battleships.core.history.SinglePlayerHistory;
import com.battleships.core.board.Board;
import com.battleships.core.board.Ship;
import com.battleships.core.board.TileState;
import com.battleships.core.board.Util;
import com.battleships.core.player.*;

import java.util.*;

/**
 * Created by Kubo Brehuv with <3 (19.2.2018)
 */
public class ConsoleUI {

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
            System.out.print(Util.ANSI_CYAN + (char) (i + 65) + " " + Util.ANSI_RESET);
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getPlayBoard()[i][j].getTileState() == TileState.SHIP) {
                    System.out.print(Util.ANSI_RESET + 0 + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.HITTED) {
                    System.out.print(Util.ANSI_RED + 1 + " " + Util.ANSI_RESET);
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.MISSED) {
                    System.out.print(Util.ANSI_BLACK + 1 + " " + Util.ANSI_RESET);
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}