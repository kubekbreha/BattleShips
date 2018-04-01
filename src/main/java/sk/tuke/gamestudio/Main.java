package sk.tuke.gamestudio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.consoleui.GameMenu;
import sk.tuke.gamestudio.game.core.util.DatabaseUtil;
import sk.tuke.gamestudio.server.service.*;

@Configuration
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner(GameMenu gameMenu) {
        return (args) -> gameMenu.showMenu();
    }

    @Bean
    public GameMenu gameMenu() {
        return new GameMenu();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }
}