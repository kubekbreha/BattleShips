package sk.tuke.gamestudio.game.battleships.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class GamePlay {

    @Id
    @GeneratedValue
    private int ident;

    private int rowCount;

    private int columnCount;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "gameplay_ident")
    private Set<ShootCoordinate> shootCoordinates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    @JoinColumn(name = "gameplay_ident")
    private List<Command> commands;

    public GamePlay() {
    }

    public GamePlay(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public void setShootCoordinates(Set<ShootCoordinate> shootCoordinates) {
        this.shootCoordinates = shootCoordinates;
    }

    public int getIdent() {
        return ident;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public String getUsername() {
        return username;
    }

    public Set<ShootCoordinate> getShootCoordinates() {
        return shootCoordinates;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(Command command) {
        if (commands == null) {
            commands = new ArrayList<>();
        }
        commands.add(command);
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

