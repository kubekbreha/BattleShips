package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.*;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.history.BoardsHistory;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Computer;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.ComputerExpert;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;

import java.util.ArrayList;
import java.util.List;


public class WebUISinglePlayer {

    private Board board;
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
                    if (playerHistory.getHistorySize() != 0 && undoCount != 0) {
                        boardOponent.setPlayBoard(playerHistory.getLast());
                        playerHistory.removeLast();
                        hint.setHintBoard(playerHistory.getLastProbability());
                        playerHistory.removeLastProbability();

                        board.setPlayBoard(playerHistoryOponent.getLast());
                        playerHistoryOponent.removeLast();
                        ((Computer) playerOponent).setNotTileHistory(playerHistoryOponent.getLastProbability());
                        playerHistoryOponent.removeLastProbability();
                        undoCount--;

                    }
                    break;

                case "hint":
                    showHint = true;
                    break;

                case "restart":
                    setUpGame();
                    break;
            }
        } else if (rowString != null || columnString != null) {

            if (!settingUp) {
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
            } else {
                if (ships.size() != 0) {
                    ships.get(0).placeShip(boardSetup.getPlayBoard(), row, col, 'H');
                    ships.remove(0);
                    shipSizes.remove(0);
                }
            }
        }
    }


    public String renderPlayer1Board() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //first board
        showPlayTable(sb, board, false);
        sb.append("</div></div>");

        return sb.toString();
    }

    public String renderPlayer2Board() {
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
        System.out.println(showHint);
        if (showHint && hintCount != 0) {
            sb.append("<p>");
            sb.append(String.format("Row: <span class=\"badge secondary\">" + (char) (hint.getHintRow() + '1') + "</span> " +
                    " Col: <span class=\"badge secondary\">" + (hint.getHintCol() + 1) + "</span>"));
            sb.append("</p>\n");
            showHint = false;
            hintCount--;
        }
        return sb.toString();
    }

    public String renderHintCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append(String.format("You have <span class=\"badge danger\">" + hintCount + "</span> hint left."));
        sb.append("</p>\n");
        return sb.toString();
    }

    public String renderUndoCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append(String.format("You have <span class=\"badge danger\">" + undoCount + "</span> undo left."));
        sb.append("</p>\n");
        return sb.toString();
    }


    public String renderShipsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table cellspacing=\"0\">");
        for (int row = 0; row < shipSizes.size(); row++) {
            sb.append("<tr>\n");

            for (int col = 0; col < 10 ; col++) {
                sb.append("<td>\n");
                if(col<shipSizes.get(row)) {
                    sb.append("<img class='" + "mines-tile" + "' src='" + String.format("/images/battleships/brehuv/ship.png") + "'>\n");
                }
                sb.append("</td>\n");
            }

            sb.append("</tr>\n");
        }
        sb.append("</table>");

        return sb.toString();
    }

    public String renderSetupBoard() {
        settingUp = true;
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //second board
        showPlayTable(sb, boardSetup, true);
        sb.append("</div></div>");
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
        hintCount = 3;
        undoCount = 3;
        //setup board setup
        boardSetup = new Board(10, 10);
        ships = new ArrayList<>();


        shipSizes = new ArrayList<>();
        shipSizes.add(2);
        shipSizes.add(2);
        shipSizes.add(3);
        shipSizes.add(3);
        shipSizes.add(4);
        for (int i = 0; i < shipSizes.size(); i++) {
            ships.add(new Ship(shipSizes.get(i)));
        }
    }


}
