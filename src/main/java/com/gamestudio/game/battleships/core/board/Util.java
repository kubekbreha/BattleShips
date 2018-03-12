package com.gamestudio.game.battleships.core.board;

import com.gamestudio.entity.Comment;
import com.gamestudio.entity.Rating;
import com.gamestudio.entity.Score;
import com.gamestudio.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.gamestudio.game.battleships.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Util {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static ScoreService scoreService = new ScoreServiceJDBC();
    private static CommentService commentService = new CommentServiceJDBC();
    private static RatingService ratingService = new RatingServiceJDBC();

    /**
     * Change tile state.
     *
     * @param board where to write.
     * @param value to write.
     * @param col   coordinate on the board.
     */
    public static void writeToBoard(Tile[][] board, Tile value, int row, int col) {
        board[row][col] = value;
    }


    /**
     * Checking tile state and overwriting in due to conditions.
     *
     * @param board of Tiles.
     * @param row   coordinate on the board.
     * @param col   coordinate on the board.
     */
    public static void shootToBoard(Tile[][] board, int row, int col){
        Tile tile = board[row][col];
        switch (tile.getTileState()){
            case SHIP:
                Util.writeToBoard(board, new Tile(TileState.HITTED), row, col);
                break;

            case WATER:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case MISSED:
                Util.writeToBoard(board, new Tile(TileState.MISSED), row, col);
                break;

            case HITTED:
                Util.writeToBoard(board, new Tile(TileState.HITTED), row, col);
                break;
        }
    }

    /**
     * Create probability board for hint and expertAI.
     *
     * @return
     */
    //TODO: create dynamic probability board
    public static int[][] createProbabilityBoard(){
        return new int[][]{
                {8, 9, 10, 11, 12, 12, 11, 10, 9, 8},
                {9, 10, 11, 12, 13, 13, 12, 11, 10, 9},
                {10, 11, 12, 13, 14, 14, 13, 12, 11, 10},
                {11, 12, 13, 14, 15, 15, 14, 13, 12, 11},
                {12, 13, 14, 15, 16, 16, 15, 14, 13, 12},
                {12, 13, 14, 15, 16, 16, 15, 14, 13, 12},
                {11, 12, 13, 14, 15, 15, 14, 13, 12, 11},
                {10, 11, 12, 13, 14, 14, 13, 12, 11, 10},
                {9, 10, 11, 12, 13, 13, 12, 11, 10, 9},
                {8, 9, 10, 11, 12, 12, 11, 10, 9, 8},
        };
    }

    /**
     * Print score from database.
     */
    public static void printScore() {
        try {
            List<Score> scores = scoreService.getBestScores(GAME_NAME);

            for (Score s : scores) {
                System.out.println(s);
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
                System.out.println(c);
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
     * Read line from user input.
     *
     * @return readed string.
     */
    public static String readLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Nepodarilo sa nacitat vstup, skus znova");
            return "";
        }
    }

    /**
     * Add new rating to database.
     */
    public static void addRating(int ratingValue){
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

}
