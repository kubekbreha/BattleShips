package sk.tuke.gamestudio.game.battleships.consoleui;

import sk.tuke.gamestudio.game.battleships.core.board.Board;
import sk.tuke.gamestudio.game.battleships.core.board.TileState;
import sk.tuke.gamestudio.game.battleships.core.util.Util;

/**
 * Created by Kubo Brehuv with <3 (19.2.2018)
 */
public class ConsoleBoard {

    /**
     * Print playBoard to console.
     */
    public void printPlayBoard(Board board, boolean visibleShips) {
        System.out.print(Util.ANSI_CYAN + "* " + Util.ANSI_RESET);
        for (int i = 0; i < 10; i++) {
            System.out.print(Util.ANSI_CYAN + i + " " + Util.ANSI_RESET);
        }
        System.out.println();

        for (int i = 0; i < board.getBoardCols(); i++) {
            System.out.print(Util.ANSI_CYAN + (char) (i + 'A') + " " + Util.ANSI_RESET);
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getPlayBoard()[i][j].getTileState() == TileState.SHIP) {
                    if(!visibleShips) {
                        System.out.print(Util.ANSI_RESET + "*" + " " + Util.ANSI_RESET);
                    }else {
                        System.out.print(Util.ANSI_YELLOW + "*" + " " + Util.ANSI_RESET);
                    }
                } else if (board.getPlayBoard()[i][j].getTileState() == TileState.HIT) {
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
}