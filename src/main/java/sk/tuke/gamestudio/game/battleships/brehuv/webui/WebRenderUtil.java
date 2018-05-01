package sk.tuke.gamestudio.game.battleships.brehuv.webui;

import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Tile;

public class WebRenderUtil {


    static void showPlayTable(StringBuilder sb, Board board, boolean clickable, boolean debug, int hintRow, int hintCol) {
        sb.append("<table cellspacing=\"0\">");
        sb.append("<thead>\n");
        sb.append("<tr>\n");
        sb.append("<th>"+"</th>");

        for (int i = 1 ; i<11 ; i++){
                sb.append("<th>"+i+"</th>");
        }
        sb.append("</tr>\n");
        sb.append("</thead>\n");
        sb.append("<tbody>\n");
        for (int row = 0; row < board.getBoardRows(); row++) {

            sb.append("<tr>\n");
            sb.append("<td>"+(char)('A'+row)+"</td>\n");

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
                if(hintCol==col && hintRow==row ){
                    sb.append("<img class='" + "mines-tile" + "' src='" + String.format("/images/battleships/brehuv/%s.png", "hint") + "'>\n");
                }else{
                    sb.append("<img class='" + "mines-tile" + "' src='" + String.format("/images/battleships/brehuv/%s.png", image) + "'>\n");
                }
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</tbody>\n");
        sb.append("</table>");
    }

}
