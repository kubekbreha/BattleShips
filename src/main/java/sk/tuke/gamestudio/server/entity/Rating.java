package sk.tuke.gamestudio.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
@Entity
public class Rating implements Comparable<Rating>, Serializable {

    @Id
    @GeneratedValue
    private int indent;

    private String game;
    private String player;
    private int rating;
    private Date ratedon;

    public Rating(){}

    public Rating( String game, String player, int rating, Date ratedon) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedon = ratedon;
    }

    public int getIndent() {
        return indent;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRatedon() {
        return ratedon;
    }

    public void setRatedon(Date ratedon) {
        this.ratedon = ratedon;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", ratedon=").append(ratedon);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Rating o) {
        if(o == null) return -1;
        return this.getRating() - o.getRating();
    }
}