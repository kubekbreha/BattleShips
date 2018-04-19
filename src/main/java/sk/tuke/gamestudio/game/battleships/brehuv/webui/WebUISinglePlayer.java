package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.*;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.history.BoardsHistory;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.*;

import java.util.ArrayList;
import java.util.List;


public class WebUISinglePlayer {

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
            initShips();
            boardSetup = new Board(10, 10);
            boardOponent = new Board(10, 10);
            boardOponent.setUpBoardRandom();
            gameController = new GameController(boardOponent);
            gameControllerOponent = new GameController(boardSetup);
        } else if (command != null) {
            if (hint != null) {
                hint.findHint();
            }
            switch (command) {
                case "undo":
                    if (playerHistory.getHistorySize() != 0 && undoCount != 0) {
                        boardOponent.setPlayBoard(playerHistory.getLast());
                        playerHistory.removeLast();
                        hint.setHintBoard(playerHistory.getLastProbability());
                        playerHistory.removeLastProbability();

                        boardSetup.setPlayBoard(playerHistoryOponent.getLast());
                        playerHistoryOponent.removeLast();
                        ((Computer) playerOponent).setNotTileHistory(playerHistoryOponent.getLastProbability());
                        playerHistoryOponent.removeLastProbability();
                        undoCount--;

                    }
                    break;

                case "hint":
                    showHint = true;
                    break;

                case "back":
                    System.out.println("back");
                    if (shipsBU.size() != 0) {
                        boardSetup.setPlayBoard(setupHistory.getLast());
                        setupHistory.removeLast();

                        ships.add(0, shipsBU.get(0));
                        shipSizes.add(0, shipSizesBU.get(0));
                        shipsBU.remove(0);
                        shipSizesBU.remove(0);
                    }
                    break;

                case "horizontal":
                    orientation = 'H';
                    break;

                case "vertical":
                    orientation = 'V';
                    break;

                case "restart":
                    //setUpGame();
                    break;

                case "random":
                    //setUpGame();
                    boardSetup.setUpBoardRandom();
                    int limit = shipSizes.size();
                    for (int i = 0; i < limit; i++) {
                        shipSizes.remove(0);
                    }
                    break;

                case "begginer":
                    player = new Human();
                    playerOponent = new Computer();
                    ((Computer) playerOponent).setAiState(new ComputerBegginer());
                    break;

                case "medium":
                    player = new Human();
                    playerOponent = new Computer();
                    ((Computer) playerOponent).setAiState(new ComputerMedium());
                    break;

                case "hard":
                    hint = new Hint(boardOponent);
                    player = new Human();
                    playerOponent = new Computer();
                    playerHistory = new BoardsHistory();
                    playerHistoryOponent = new BoardsHistory();
                    hintCount = 3;
                    undoCount = 3;
                    ((Computer) playerOponent).setAiState(new ComputerHard(10, 10));
                    break;

                case "expert":
                    hint = new Hint(boardOponent);
                    player = new Human();
                    playerOponent = new Computer();
                    playerHistory = new BoardsHistory();
                    playerHistoryOponent = new BoardsHistory();
                    hintCount = 3;
                    undoCount = 3;
                    ((Computer) playerOponent).setAiState(new ComputerExpert(10, 10));
                    break;
            }

        } else if (rowString != null || columnString != null) {

            if (!settingUp) {
                if (playerHistory != null) {
                    playerHistory.addToHistory(boardOponent.getPlayBoard());
                    playerHistory.addToProbabilityHistory(hint.getHintBoard());
                    playerHistoryOponent.addToHistory(boardSetup.getPlayBoard());
                    playerHistoryOponent.addToProbabilityHistory(((Computer) playerOponent).getNotTileHistory());
                }


                if (!gameControllerOponent.isGameWon(boardOponent.getShips())
                        && !gameController.isGameWon(boardSetup.getShips()) &&
                        boardOponent.getPlayBoard()[row][col].getTileState() != TileState.HIT &&
                        boardOponent.getPlayBoard()[row][col].getTileState() != TileState.MISSED) {

                    player.shoot(boardOponent.getPlayBoard(), Integer.parseInt(rowString), Integer.parseInt(columnString));

                    if (hint != null) {
                        hint.moveExecuted(boardOponent.getPlayBoard()[row][col].getTileState(), row, col);
                    }

                    playerOponent.shootAI(boardSetup.getPlayBoard());
                }
            } else {
                if (ships.size() != 0) {
                    setupHistory.addToHistory(boardSetup.getPlayBoard());
                    ships.get(0).placeShip(boardSetup.getPlayBoard(), row, col, orientation);
                    boardSetup.addShipToShips(ships.get(0));
                    shipsBU.add(0, ships.get(0));
                    shipSizesBU.add(0, shipSizes.get(0));
                    ships.remove(0);
                    shipSizes.remove(0);
                }

            }

        }
        if(ships.size() == 0){
            settingUp = false;
        }
    }


    private void initShips() {
        ships = new ArrayList<>();
        setupHistory = new BoardsHistory();

        shipSizes = new ArrayList<>();
        shipSizes.add(2);
        shipSizes.add(2);
        shipSizes.add(3);
        shipSizes.add(3);
        shipSizes.add(4);
        for (int i = 0; i < shipSizes.size(); i++) {
            ships.add(new Ship(shipSizes.get(i)));
        }
        shipSizesBU = new ArrayList<>();
        shipsBU = new ArrayList<>();
    }


    public String renderPlayer1Board() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"row\"><div class=\"col-xs-6\">");
        //first board
        showPlayTable(sb, boardSetup, false);
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

            for (int col = 0; col < 10; col++) {
                sb.append("<td>\n");
                if (col < shipSizes.get(row)) {
                    sb.append("<img class='" + "mines-tile" + "' src='" + String.format("/images/battleships/brehuv/ship.png") + "'>\n");
                }
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");

        return sb.toString();
    }

    private int findBiggestInList(List<Integer> list) {
        int max = 0;

        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        System.out.println("biggest: " + max);

        return max;
    }

    public String orientationButtons() {

        StringBuilder sb = new StringBuilder();
        if (orientation == 'H') {
            sb.append("<button id=\"horiButton\" class=\"buttonMargin btn-secondary\" onclick=\"location.href='?command=horizontal'\">" +
                    "Horizontal" + "</button> <button id=\"vertButton\" class=\"buttonMargin\" onclick=\"location.href='?command=vertical'\"> " +
                    "Vertical" + "</button>");
        } else {
            sb.append("<button id=\"horiButton\" class=\"buttonMargin\" onclick=\"location.href='?command=horizontal'\">" +
                    "Horizontal" + "</button> <button id=\"vertButton\" class=\"buttonMargin btn-secondary\" onclick=\"location.href='?command=vertical'\"> " +
                    "Vertical" + "</button>");
        }

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
}
