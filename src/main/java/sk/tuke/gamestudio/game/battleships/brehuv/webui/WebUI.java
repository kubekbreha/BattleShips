package sk.tuke.gamestudio.game.battleships.brehuv.webui;

public class WebUI {
//    private Field field;

    public void processCommand( String rowString, String columnString) {
			//TODO: implement what should happen when a command comes (open, mark, newgame, ...)
    }

    public String renderAsHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");

        //TODO: append body of the table according to the field content

        sb.append("</table>");

        return sb.toString();
    }

    private void createField() {
//        field = new Field(9, 9, 10);
    }
}
