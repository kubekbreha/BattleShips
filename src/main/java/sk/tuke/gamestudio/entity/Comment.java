package sk.tuke.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
@Entity
public class Comment implements Comparable<Comment>, Serializable {

    @Id
    @GeneratedValue
    private int indent;

    private String game;
    private String player;
    private String comment;
    private Date commentedOn;

    public Comment(){}

    public Comment( String game, String player, String comment, Date commentedOn) {
        this.game = game;
        this.player = player;
        this.comment = comment;
        this.commentedOn = commentedOn;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", commentedOn=").append(commentedOn);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Comment o) {
        if(o == null) return -1;
        return (int)this.getCommentedOn().getTime() - (int)o.getCommentedOn().getTime();
    }
}
