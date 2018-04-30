package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;

public class WebRenderUtil {


    static void showPlayTable(StringBuilder sb, Board board, boolean clickable, boolean debug) {
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
                        if(debug) {
                            image = "ship";
                        }else{
                            image = "water";
                        }
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

}
