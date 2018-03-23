package sk.tuke.gamestudio.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class Score implements Comparable<Score>, Serializable {

    private String player;
    private String game;
    private int points;
    private Date playedOn;

    public Score( String player, String game, int points, Date playedOn) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    @Override
    public int compareTo(Score o) {
        if(o == null) return -1;
        return this.getPoints() - o.getPoints();
    }
}