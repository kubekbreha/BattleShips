package sk.tuke.gamestudio.game.battleships.core.util;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import com.gamestudio.service.*;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.List;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public class DatabaseUtil {

    private static ScoreService scoreService = new ScoreServiceJDBC();
    private static CommentService commentService = new CommentServiceJDBC();
    private static RatingService ratingService = new RatingServiceJDBC();

    /**
     * Print score from database.
     */
    public static void printScore() {
        try {
            List<Score> scores = scoreService.getBestScores(GAME_NAME);

            for (Score s : scores) {
                System.out.println(Util.ANSI_CYAN + "Player: " + Util.ANSI_RESET + s.getPlayer() + Util.ANSI_CYAN +
                        " game: " + Util.ANSI_RESET + s.getGame() +
                        Util.ANSI_CYAN + " points: " + Util.ANSI_RESET + s.getPoints() + Util.ANSI_CYAN + " date: "
                        + Util.ANSI_RESET + s.getPlayedOn());
            }
        } catch (ScoreException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Print comments from database.
     */
    public static void printComments() {
        try {
            List<Comment> comments = commentService.getComments(GAME_NAME);
            for (Comment c : comments) {
                System.out.println(Util.ANSI_CYAN + "Player: " + Util.ANSI_RESET + c.getPlayer() + Util.ANSI_CYAN +
                        " game: " + Util.ANSI_RESET + c.getGame() +
                        Util.ANSI_CYAN + " points: " + Util.ANSI_RESET + c.getComment() + Util.ANSI_CYAN + " date: "
                        + Util.ANSI_RESET + c.getCommentedOn());
            }
        } catch (CommentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Print rating of the game.
     */
    public static void printRating() {
        try {
            int ratings = ratingService.getAverageRating(GAME_NAME);
            System.out.println("Rating of this game is : " + ratings);
        } catch (RatingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Add new rating to database.
     */
    public static void addRating(int ratingValue) {
        try {
            ratingService.setRating(new Rating(
                    GAME_NAME,
                    System.getProperty("user.name"),
                    ratingValue,
                    new Date()
            ));
            System.out.println("Your rating was added to database");
        } catch (RatingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add comment to game.
     */
    public static void addComment(String commentText) {
        try {
            commentService.addComment(new Comment(
                    GAME_NAME,
                    System.getProperty("user.name"),
                    commentText,
                    new Date()
            ));
            System.out.println("Your comment was added to database");
        } catch (CommentException e) {
            e.printStackTrace();
        }
    }


    /**
     * Add score to database .
     *
     * @param score what will be added.
     */
    public static void addScore(int score){
        try {
            scoreService.addScore(new Score(
                    GAME_NAME,
                    System.getProperty("user.name"),
                    score,
                    new Date()
            ));
            System.out.println("Your score was added to database");
        } catch (ScoreException e) {
            System.err.println(e.getMessage());
        }
    }
}
