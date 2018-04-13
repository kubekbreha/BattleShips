package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;

public class WebUI {

    private Board board;

    public void processCommand( String rowString, String columnString) {
        if (board == null) {
            createBoard();
        }

    }

    public String renderAsHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");

        for (int row = 0; row < board.getBoardRows(); row++) {
            sb.append("<tr>\n");
            for (int col = 0; col < board.getBoardCols(); col++) {
                Tile tile = board.getBoardTile(row, col);
                sb.append("<td>\n");

                sb.append("<a  href='" + String.format("?row=%d&column=%d", row, col) + "'>\n");

                String image = "";
                switch (tile.getTileState()) {
                    case WATER:
                        image = "closed";
                        break;
                    case SHIP:
                        image = "mine";
                        break;
                    case MISSED:
                        image = "closed";
                        break;
                    case HIT:
                        image = "closed";
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected tile state " + tile.getTileState());
                }

                sb.append("<img src='" + String.format("/images/battleships/brehuv/%s.png", image) + "'>\n");
                sb.append("</a>\n");

                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>");

        return sb.toString();
    }

    private void createBoard() {
        board = new Board(10, 10);
        board.setUpBoardRandom();
    }
}
