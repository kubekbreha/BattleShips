package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.*;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.history.BoardsHistory;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.*;

import java.util.ArrayList;
import java.util.List;


public class WebUIMultiPlayer {

    private Board boardRestartBU;
    private Board boardRestartOponentBU;

    private Board boardSetup;
    private Player player;
    private GameController gameController;
    private BoardsHistory playerHistory;
    private Hint hint;

    private Board boardOponent;
    private Player playerOponent;
    private GameController gameControllerOponent;
    private BoardsHistory playerHistoryOponent;

    private int hintCount;
    private int undoCount;
    private boolean settingUp;
    private List<Integer> shipSizes;
    private List<Ship> ships;
    private List<Integer> shipSizesBU;
    private List<Ship> shipsBU;
    private BoardsHistory setupHistory;

    private boolean begginerAI = false;
    private boolean mediumAI = false;
    private boolean hardAI = false;
    private boolean expertAI = false;

    private char orientation = 'V';

    private boolean showHint;


    public void processCommand(String command, String rowString, String columnString) {
        int row = 0, col = 0;
        if (rowString != null) {
            row = Integer.parseInt(rowString);
        }

        if (columnString != null) {
            col = Integer.parseInt(columnString);
        }


        if (boardSetup == null) {
            boardSetup = new Board(10,10);
        } else if (command != null) {
            switch (command) {

            }
        }
    }



    public String renderPlayer1Board() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //first board
        //WebRenderUtil.showPlayTable(sb, boardSetup, false);
        sb.append("</div></div>");

        return sb.toString();
    }

    public String renderPlayer2Board() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //second board
        //WebRenderUtil.showPlayTable(sb, boardOponent, true);
        sb.append("</div></div>");

        return sb.toString();
    }



}
