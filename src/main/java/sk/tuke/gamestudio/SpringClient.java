package sk.tuke.gamestudio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.consoleui.GameMenu;
import sk.tuke.gamestudio.server.service.*;

@Configuration
@SpringBootApplication
public class SpringClient {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringClient.class).web(false).run(args);
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
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceRestClient();
    }
}