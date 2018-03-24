package sk.tuke.gamestudio.game.battleships.entity;


import sk.tuke.gamestudio.game.battleships.core.board.Board;
import sk.tuke.gamestudio.game.battleships.core.player.Player;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Command {
    @Id
    @GeneratedValue
    private int ident;

    private int row;

    private int col;

    @Enumerated
    private CommandType commandType;

    public Command() {
    }

    public Command(int row, int col, CommandType commandType) {
        this.row = row;
        this.col = col;
        this.commandType = commandType;
    }

    public int getIdent() {
        return ident;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void execute(Player player, Board board) {
        player.shoot(board.getPlayBoard(), row, col);
    }
}
