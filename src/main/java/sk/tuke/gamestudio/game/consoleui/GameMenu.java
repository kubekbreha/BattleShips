package sk.tuke.gamestudio.game.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.game.core.util.DatabaseUtil;
import sk.tuke.gamestudio.game.core.util.Util;
import sk.tuke.gamestudio.server.service.*;

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
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    public GameMenu() {
    }

    /**
     * Game menu, first interaction with user.
     */
    public void showMenu() {
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
                new PlayerVsComputer(scoreService);
                break;

            case 2:
                new PlayerVsPlayer();
                break;

            case 3:
                DatabaseUtil.printScore(scoreService);
                showMenu();
                break;

            case 4:
                DatabaseUtil.printComments(commentService);
                showMenu();
                break;


            case 5:
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String comment = Util.readLine(bufferedReader);
                DatabaseUtil.addComment(comment, commentService);
                showMenu();
                break;

            case 6:
                DatabaseUtil.printAverageRating(ratingService);
                showMenu();
                break;

            case 7:
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                int rating = Util.readLine(bufferedReader).charAt(0) - '0';
                DatabaseUtil.addRating(rating, ratingService);
                showMenu();
                break;

            case 8:
                DatabaseUtil.addScore(55 , scoreService);
        }
    }
}
