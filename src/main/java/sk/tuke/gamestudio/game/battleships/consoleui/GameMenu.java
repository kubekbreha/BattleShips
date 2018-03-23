package sk.tuke.gamestudio.game.battleships.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.game.battleships.core.util.DatabaseUtil;
import sk.tuke.gamestudio.game.battleships.core.util.Util;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class GameMenu {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;

    public GameMenu(ScoreService scoreService, CommentService commentService, RatingService ratingService) {
        this.scoreService = scoreService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    /**
     * Game menu, first interaction with user.
     */
    public void showMenu(){
        System.out.println("Welcome to BATTLESHIPS game.");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");
        System.out.println("3. Show best scores");
        System.out.println("4. Show comments");
        System.out.println("5. Comment this game");
        System.out.println("6. Show rating");
        System.out.println("7. Rate this game");

        Scanner reader = new Scanner(System.in);
        int pick = reader.nextInt();
        switch (pick) {
            case 1:
                GameMode gameMode = new PlayerVsComputer();
                break;

            case 2:
                gameMode = new PlayerVsPlayer();
                break;

            case 3:
                new DatabaseUtil(this.scoreService).printScore();
                showMenu();
                break;

            case 4:
                new DatabaseUtil(this.commentService).printComments();
                showMenu();
                break;


            case 5:
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String comment = Util.readLine(bufferedReader);
                new DatabaseUtil(this.commentService).addComment(comment);
                showMenu();
                break;

            case 6:
                new DatabaseUtil(this.ratingService).printRating();
                showMenu();
                break;

            case 7:
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                int rating = Util.readLine(bufferedReader).charAt(0) - '0';
                new DatabaseUtil(this.ratingService).addRating(rating);
                showMenu();
                break;
        }
    }
}
