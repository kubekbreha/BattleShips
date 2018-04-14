package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Hint;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.history.BoardsHistory;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;

import java.util.Scanner;

public class WebUI {

    private Board board;
    private Player player;
    private GameController gameController;
    private BoardsHistory playerHistory;
    private Hint hint;

    private boolean showHint;


    public void processCommand(String command, String rowString, String columnString) {

        int row = 0, col = 0;
        if(rowString != null) {
            row = Integer.parseInt(rowString);
        }

        if(columnString != null) {
            col = Integer.parseInt(columnString);
        }


        if (board == null) {
            setUpGame();
        }else if(command != null){
            hint.findHint();
            switch (command){
                case "undo":
                    if (playerHistory.getHistorySize() != 0) {
                        board.setPlayBoard(playerHistory.getLast());
                        playerHistory.removeLast();
                        hint.setHintBoard(playerHistory.getLastProbability());
                        playerHistory.removeLastProbability();
                    }
                    break;

                case "hint":
                    showHint = true;
                    break;
            }
        }else if(rowString != null || columnString != null){
            playerHistory.addToHistory(board.getPlayBoard());
            playerHistory.addToProbabilityHistory(hint.getHintBoard());
            player.shoot(board.getPlayBoard(), Integer.parseInt(rowString), Integer.parseInt(columnString));
            hint.moveExecuted(board.getPlayBoard()[row][col].getTileState(), row, col);
        }
    }

    public String renderAsHtml() {
        StringBuilder sb = new StringBuilder();


        //first board
        sb.append("<div class=\"row\">" +
                  "<div class=\"col-xs-6\">");

        sb.append("<table cellspacing=\"0\">");
        for (int row = 0; row < board.getBoardRows(); row++) {
            sb.append("<tr>\n");
            for (int col = 0; col < board.getBoardCols(); col++) {
                Tile tile = board.getBoardTile(row, col);
                sb.append("<td>\n");
                sb.append("<a  href='" + String.format("?row=%d&column=%d", row, col) + "'>\n");
                String image = "";
                switch (tile.getTileState()) {
                    case WATER:
                        image = "water";
                        break;
                    case SHIP:
                        image = "ship";
                        break;
                    case MISSED:
                        image = "missed";
                        break;
                    case HIT:
                        image = "destroyed";
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected tile state " + tile.getTileState());
                }
                sb.append("<img class='" + "mines-tile"  + "' src='" + String.format("/images/battleships/brehuv/%s.png", image) + "'>\n");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");

        sb.append("</div>" +
                "<div class=\"col-xs-6\">");

        //second playboard
        sb.append("<table cellspacing=\"0\">");
        for (int row = 0; row < board.getBoardRows(); row++) {
            sb.append("<tr>\n");
            for (int col = 0; col < board.getBoardCols(); col++) {
                Tile tile = board.getBoardTile(row, col);
                sb.append("<td>\n");
                sb.append("<a  href='" + String.format("?row=%d&column=%d", row, col) + "'>\n");
                String image = "";
                switch (tile.getTileState()) {
                    case WATER:
                        image = "water";
                        break;
                    case SHIP:
                        image = "ship";
                        break;
                    case MISSED:
                        image = "missed";
                        break;
                    case HIT:
                        image = "destroyed";
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected tile state " + tile.getTileState());
                }
                sb.append("<img class='" + "mines-tile"  + "' src='" + String.format("/images/battleships/brehuv/%s.png", image) + "'>\n");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");

        sb.append("</div>" +
                "</div>");

        if(showHint){
            sb.append("<p>");
            sb.append(String.format("Row: " + (char) (hint.getHintRow() + 'A') + "  Col: " + hint.getHintCol()));
            sb.append("</p>\n");
            showHint = false;
        }

        return sb.toString();
    }








    private void setUpGame() {
        board = new Board(10, 10);
        board.setUpBoardRandom();
        hint = new Hint(board);
        player = new Human();
        gameController = new GameController(board);
        playerHistory = new BoardsHistory();
    }


}
