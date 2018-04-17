package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.*;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.history.BoardsHistory;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Computer;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.ComputerExpert;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;


public class WebUISinglePlayer {

    private Board board;
    private Player player;
    private GameController gameController;
    private BoardsHistory playerHistory;
    private Hint hint;

    private Board boardOponent;
    private Player playerOponent;
    private GameController gameControllerOponent;
    private BoardsHistory playerHistoryOponent;

    private boolean showHint;


    public void processCommand(String command, String rowString, String columnString) {
        int row = 0, col = 0;
        if (rowString != null) {
            row = Integer.parseInt(rowString);
        }

        if (columnString != null) {
            col = Integer.parseInt(columnString);
        }


        if (board == null) {
            setUpGame();
        } else if (command != null) {
            hint.findHint();
            switch (command) {
                case "undo":
                    if (playerHistory.getHistorySize() != 0) {
                        boardOponent.setPlayBoard(playerHistory.getLast());
                        playerHistory.removeLast();
                        hint.setHintBoard(playerHistory.getLastProbability());
                        playerHistory.removeLastProbability();

                        board.setPlayBoard(playerHistoryOponent.getLast());
                        playerHistoryOponent.removeLast();
                        ((Computer) playerOponent).setNotTileHistory(playerHistoryOponent.getLastProbability());
                        playerHistoryOponent.removeLastProbability();
                    }
                    break;

                case "hint":
                    showHint = true;
                    break;
            }
        } else if (rowString != null || columnString != null) {

            playerHistory.addToHistory(boardOponent.getPlayBoard());
            playerHistory.addToProbabilityHistory(hint.getHintBoard());
            playerHistoryOponent.addToHistory(board.getPlayBoard());
            playerHistoryOponent.addToProbabilityHistory(((Computer) playerOponent).getNotTileHistory());


            //play while game not won
            //cant shoot to same place
            if (!gameControllerOponent.isGameWon(boardOponent.getShips())
                    && !gameController.isGameWon(board.getShips()) &&
                    boardOponent.getPlayBoard()[row][col].getTileState() != TileState.HIT &&
                    boardOponent.getPlayBoard()[row][col].getTileState() != TileState.MISSED) {

                player.shoot(boardOponent.getPlayBoard(), Integer.parseInt(rowString), Integer.parseInt(columnString));
                hint.moveExecuted(boardOponent.getPlayBoard()[row][col].getTileState(), row, col);

                playerOponent.shootAI(board.getPlayBoard());
            }
        }
    }


    public String renderAsHtml1() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //first board
        showPlayTable(sb, board, false);
        sb.append("</div></div>");

        return sb.toString();
    }

    public String renderAsHtml2() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //second board
        showPlayTable(sb, boardOponent, true);
        sb.append("</div></div>");

        return sb.toString();
    }


    private void showPlayTable(StringBuilder sb, Board board, boolean clickable) {
        sb.append("<table cellspacing=\"0\">");
        for (int row = 0; row < board.getBoardRows(); row++) {
            sb.append("<tr>\n");
            for (int col = 0; col < board.getBoardCols(); col++) {
                Tile tile = board.getBoardTile(row, col);
                sb.append("<td>\n");

                if (clickable) {
                    sb.append("<a  href='" + String.format("?row=%d&column=%d", row, col) + "'>\n");
                }

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
                        image = "hitted";
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected tile state " + tile.getTileState());
                }
                sb.append("<img class='" + "mines-tile" + "' src='" + String.format("/images/battleships/brehuv/%s.png", image) + "'>\n");
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");

    }

    public String renderHint() {
        StringBuilder sb = new StringBuilder();
        if (showHint) {
            sb.append("<p>");
            sb.append(String.format("Row: <span class=\"badge secondary\">" + (char) (hint.getHintRow() + '1') + "</span> " +
                    " Col: <span class=\"badge secondary\">" + (hint.getHintCol()+1) + "</span>" ));
            sb.append("</p>\n");
            showHint = false;
        }
        return sb.toString();
    }


    private void setUpGame() {
        board = new Board(10, 10);
        boardOponent = new Board(10, 10);
        board.setUpBoardRandom();
        boardOponent.setUpBoardRandom();
        hint = new Hint(boardOponent);
        player = new Human();
        playerOponent = new Computer();
        ((Computer) playerOponent).setAiState(new ComputerExpert(10, 10));
        gameController = new GameController(boardOponent);
        gameControllerOponent = new GameController(board);
        playerHistory = new BoardsHistory();
        playerHistoryOponent = new BoardsHistory();
    }


}
