package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.battleships.brehuv.core.util.DatabaseUtil;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import javax.xml.stream.events.Comment;

//http://localhost:8080/battleships-brehuv-rating
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControlleRating {


    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commmentService;

    @RequestMapping("/battleships-brehuv-rating")
    public String mines(Model model) {
        return "battleships-brehuv-rating";
    }

    @RequestMapping("/submitrating")
    public String submit(sk.tuke.gamestudio.entity.Comment comment, Model model) {
        DatabaseUtil.addComment(comment.getComment(), commmentService);
        return "battleships-brehuv-rating";
    }
}
