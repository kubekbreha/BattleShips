package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.server.service.*;
import sk.tuke.gamestudio.server.service.JPA.CommentServiceJPA;
import sk.tuke.gamestudio.server.service.JPA.RatingServiceJPA;
import sk.tuke.gamestudio.server.service.JPA.ScoreServiceJPA;

@Configuration
@SpringBootApplication
@EntityScan({"sk.tuke.gamestudio.server.entity"})
public class GameStudioServer {
	public static void main(String[] args) {
		SpringApplication.run(GameStudioServer.class, args);
	}

	@Bean
	public ScoreService scoreService() {return new ScoreServiceJPA();}

	@Bean
	public RatingService ratingService() {return new RatingServiceJPA();}

	@Bean
	public CommentService commentService() {return new CommentServiceJPA();}
}
