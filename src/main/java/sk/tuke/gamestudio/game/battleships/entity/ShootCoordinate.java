package sk.tuke.gamestudio.game.battleships.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShootCoordinate {
    @Id
    @GeneratedValue
    private int ident;

    private int row;

    private int col;

    public ShootCoordinate() {
    }

    public ShootCoordinate(int row, int column) {
        this.row = row;
        this.col = column;
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
}
