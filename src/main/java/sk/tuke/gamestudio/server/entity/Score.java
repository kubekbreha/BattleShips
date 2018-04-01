package sk.tuke.gamestudio.server.entity;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Score implements Serializable, Comparable<Score> {

    @Id
    @GeneratedValue
    int ident;

    private String game;
    private String username;
    private int points;
    private Date playedOn;

    public Score() {
    }

    public Score(String game, String username, int points, Date playedOn) {
        this.game = game;
        this.username = username;
        this.points = points;
        this.playedOn = playedOn;
    }

    public int getIdent() {
        return ident;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", username='" + username + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    @Override
    public int compareTo(Score s) {
        return s.getPoints() - this.points;
    }
}
