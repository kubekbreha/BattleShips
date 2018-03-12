package com.gamestudio.entity;

import java.util.Date;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class Score {
    private String game;
    private String player;
    private int points;
    private Date playedOn;

    public Score(String game, String player, int points, Date playedOn) {
        this.game = game;
        this.player = player;
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
        final StringBuilder sb = new StringBuilder("Score{");
        sb.append("game='").append(game).append('\'');
        sb.append(", player='").append(player).append('\'');
        sb.append(", points=").append(points);
        sb.append(", playedon=").append(playedOn);
        sb.append('}');
        return sb.toString();
    }
}
