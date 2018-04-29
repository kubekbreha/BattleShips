package sk.tuke.gamestudio.game.battleships.brehuv.core.util;


import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.RatingException;
import sk.tuke.gamestudio.service.ScoreException;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.List;


/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */

public class DatabaseUtil {

    /**
     * Print score from database.
     */
    public static void printScore(ScoreService scoreService) {
        try {
            List<Score> scores = scoreService.getBestScores(Board.GAME_NAME);

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
    public static void printComments(CommentService commentService) {
        try {
            List<Comment> comments = commentService.getComments(Board.GAME_NAME);
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
    public static void printAverageRating(RatingService ratingService) {
        try {
            System.out.println(""+ ratingService.getAverageRating(Board.GAME_NAME));
        } catch (RatingException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Add new rating to database.
     */
    public static void setRating(int ratingValue, RatingService ratingService) {
        try {
            ratingService.setRating(new Rating(
                    Board.GAME_NAME,
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
    public static void addComment(String commentText, CommentService commentService) {
        try {
            commentService.addComment(new Comment(
                    Board.GAME_NAME,
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
    public static void addScore(int score, ScoreService scoreService, String userName) {
        try {
            scoreService.addScore(new Score(
                    Board.GAME_NAME,
                    userName,
                    score,
                    new Date()
            ));
            System.out.println("Your score was added to database");
        } catch (ScoreException e) {
            System.err.println(e.getMessage());
        }
    }
}
