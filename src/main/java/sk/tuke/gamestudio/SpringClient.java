package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sk.tuke.gamestudio.game.battleships.consoleui.GameMenu;
import sk.tuke.gamestudio.service.*;

/**
 * Created by Kubo Brehuv with <3 (23.3.2018)
 */
@Configuration
@SpringBootApplication
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class, args);
    }


    @Bean
    public CommandLineRunner runner(GameMenu gameMenu) {
        return (args) -> gameMenu.showMenu();
    }

    @Bean
    public GameMenu gameMenu(ScoreService scoreService, CommentService commentService, RatingService ratingService) {
        return new GameMenu(scoreService(), commentService(), ratingService());
    }


    @Bean
    public CommentService commentService() {
        return new CommentServiceFile();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceFile();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceFile();
    }
}
